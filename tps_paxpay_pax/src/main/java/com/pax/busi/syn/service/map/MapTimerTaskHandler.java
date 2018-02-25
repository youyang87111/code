package com.pax.busi.syn.service.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.pax.auth.dao.OrganizationDao;
import com.pax.auth.entity.Organization;
import com.pax.busi.common.dao.CommonDao;
import com.pax.busi.common.entity.AppClass;
import com.pax.busi.common.entity.Branch;
import com.pax.busi.mapmgr.dao.BranchMapDao;
import com.pax.busi.mapmgr.dao.MerMapDao;
import com.pax.busi.mapmgr.dao.TermMapDao;
import com.pax.busi.mapmgr.entity.BranchMapView;
import com.pax.busi.mapmgr.entity.MerMap;
import com.pax.busi.mapmgr.entity.TermMapView;
import com.pax.busi.resourcemgr.dao.CposMerDao;
import com.pax.busi.resourcemgr.dao.CposTermDao;
import com.pax.busi.resourcemgr.dao.RMerDao;
import com.pax.busi.resourcemgr.dao.RTermDao;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.busi.resourcemgr.entity.CposTerm;
import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.busi.resourcemgr.entity.RTerm;
import com.pax.busi.syn.dao.SynDao;
import com.pax.busi.syn.entity.Amcamsg;
import com.pax.busi.syn.entity.Amcrmsg;
import com.pax.busi.syn.entity.MidToAppid;
import com.pax.busi.syn.entity.MidToSubmid;
import com.pax.core.entity.BaseEntity;
import com.pax.core.exception.BusinessException;
import com.pax.core.util.DateUtils;
import com.pax.core.util.SftpUtils;

@Service
public class MapTimerTaskHandler {
	
	@Resource
	private CposMerDao		cposMerDao;
	@Resource
	private CposTermDao		cposTermDao;
	
	@Resource
	private RMerDao			rMerDao;
	@Resource
	private RTermDao		rTermDao;
	
	@Resource
	private OrganizationDao	organizationDao;
	
	@Resource
	private BranchMapDao	branchMapDao;
	
	@Resource
	private MerMapDao		merMapDao;
	
	@Resource
	private TermMapDao		termMapDao;
	
	@Resource
	private SynDao			synDao;
	
	@Resource
	private CommonDao		commonDao;
	
	protected final Logger	logger	= LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("all")
	@Transactional
	public void processBankFile(List<String> fileNames, String ftp_directory,
								Vector<LsEntry> vector) {
		
		try {
			
			int count = 0;
			
			String builddate = DateUtils.getCurrentDateString();
			String iddate = DateUtils.date2string(new Date(), DateUtils.LONG_FORMAT_YY);
			
			String branchMapModeId = branchMapDao.getMaxRidFromMode(builddate.substring(2, 8));
			if (StringUtils.isBlank(branchMapModeId)) {
				branchMapModeId = "000001";
			}
			String branchMapId = branchMapDao.getMaxRid("01", builddate.substring(2, 8));
			if (StringUtils.isBlank(branchMapId)) {
				branchMapId = "000001";
			}
			
			Map<String, String> idMap = new HashMap<String, String>();
			idMap.put("branchMapModeId", branchMapModeId);
			idMap.put("branchMapId", branchMapId);
			
			Collections.sort(vector, new Comparator() {
				@Override
				public int compare(Object o1, Object o2) {
					
					LsEntry e1 = (LsEntry) o1;
					LsEntry e2 = (LsEntry) o2;
					
					return e1.getFilename().compareTo(e2.getFilename());
					
				}
			});
			
			for (LsEntry lsEntry : vector) {
				
				if (lsEntry.getFilename().contains("bankBindShip")) {
					count = count + 1;
					if (count == 10) {
						break;
					}
					// 处理银行映射文件
					logger.info("============处理银行映射文件：" + lsEntry.getFilename());
					InputStream ins = SftpUtils.download(ftp_directory, lsEntry.getFilename(),
						SftpUtils.tps_sftp);
					InputStreamReader read = new InputStreamReader(ins, "UTF-8");// 考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read);
					String lineTxt = null;
					
					List<String> entrys = new ArrayList<String>();
					
					while ((lineTxt = bufferedReader.readLine()) != null) {
						
						try {
							
							logger.info("处理记录：" + lineTxt);
							String[] ss = lineTxt.split("\\|");
							String operType = ss[0];
							String lbid = ss[1];
							String mid = ss[2];
							String name = ss[3].replaceAll(" ", "");
							String depId = "1";
							//String depId = ss[4];
							String appcls = ss[5];
							String rbid = ss[6];
							
							// 支付宝appid
							String alipay_appid = ss[7];
							// 支付宝门店id
							String alipay_storeid = ss[8];
							// 微信子商户号
							String wechat_sub_merid = ss[9];
							// 威富通分配的商户号,微众分配的商户号，也共用这个字段
							String wft_merchantid = ss[10];
							// 密钥
							String wft_merkey = ss[11];
							// 进件标识
							String wft_intype = ss[12];
							// 渠道号或者大商户号
							String wft_bid = ss[13];
							// 微赢家商户号
							String wyj_submid = ss[14];
							// 签名密钥
							String wyj_key = ss[15];
							// 值为 "01"：为普通商户（没有门店的商户）， 值为"03"：大商户（有门店的商户）；
							String wyj_mertype = ss[16];
							// 当wyj_mertype为03时为门店号
							String wyj_storeid = ss[17];
							
							String amca_appid = ss[18];
							String amca_storeid = ss[19];
							String amca_pubkey = ss[20];
							String amca_prikey = ss[21];
							String amcr_appid = ss[22];
							String amcr_mid = ss[23];
							String amcr_key = ss[24];
							String amcr_submid = ss[25];
							String amcr_cert1 = ss[26];
							String amcr_cert2 = ss[27];
							String amcr_cert3 = ss[28];
							amcr_cert1 = amcr_cert1.replaceAll("\\*", "\n");
							amcr_cert2 = amcr_cert2.replaceAll("\\*", "\n");
							amcr_cert3 = amcr_cert3.replaceAll("\\*", "\n");
							
							String rmid = "";
							//paxus中新增转出商户号，为了兼容以前的老系统，需要判断ss中的字段长度
							if (ss.length >= 31) {
								rmid = ss[29];
							} else {
								//以前老版本的话，转入和接入都是相同的
								rmid = mid;
							}
							
							Branch lbranch = commonDao.getBranch(lbid);
							
							if (lbranch == null) {
								throw new BusinessException("接入渠道不存在");
							}
							
							CposMer cposMer = cposMerDao.get(lbranch.getMcr(), mid);
							if (cposMer == null) {
								throw new BusinessException("接入商户不存在");
							}
							
							Branch rbranch = commonDao.getBranch(rbid);
							if (rbranch == null) {
								throw new BusinessException("转出通道：" + rbid + "不存在");
							}
							
							AppClass appClass = commonDao.getAppClass(appcls);
							if (appClass == null) {
								throw new BusinessException("交易类别：" + appcls + "不存在");
							}
							
							if ("1".equals(operType)	|| "2".equals(operType)
								|| "5".equals(operType)) {
								
								// 插入或者更新转出商户， paxus来了，就改成rmid了
								insertOrUpdateRmer(builddate, rbranch.getMcr(), mid,rmid, name, depId);
								
								// 插入渠道映射mode，mid不等于空，表示要做商户个性化
								insertBranchMapMode(idMap, lbid, lbranch.getMcr(), mid, appcls,
									iddate, builddate);
								
								// 插入渠道映射,mid不等于空，表示要做商户个性化
								insertBranchMap(idMap, lbid, lbranch.getMcr(), mid, rbid, appcls,
									iddate, builddate);
								
								// 插入商户映射mode
								insertMerMapMode(lbid, lbranch.getMcr(), mid, rbid, appcls,
									builddate);
								
								// 插入商户映射，paxus来了，需要转出商户了，不能从接入商户复制了
								insertMerMap(lbid, lbranch.getMcr(), mid, rbid, rbranch.getMcr(),
									rmid, appcls, builddate);
								
								// 插入转出终端和终端映射,paxus来了，需要转出商户了，不能从接入商户复制了
								insertRterm(lbid, lbranch.getMcr(), mid, rbid, rbranch.getMcr(),
									rmid, builddate);
								
								if ("ALP2".equals(rbranch.getId())) {
									// 插入支付宝映射表
									insertOrUpdateMidToAppid(builddate, rbid, rmid, alipay_appid,
										alipay_storeid, "", "", "");
								}else if ("AMCA".equals(rbranch.getId())) {
									// 插入支付宝自主收银映射表
									insertOrUpdateAmcamsg(rbid, rmid, amca_appid, amca_pubkey,
										amca_prikey, amca_storeid, "", "", "", "");
								}else if ("UNIC".equals(rbranch.getId())) {//沃支付
									// 插入支付宝自主收银映射表
									//t_b_amcamsg.bid: UNIC， t_b_amcamsg.mid:平台商户号， t_b_amcamsg.appid:渠道商户号，  t_b_amcamsg.alp_pubkey 渠道公钥，t_b_amcamsg.amca_prikey渠道私钥
									insertOrUpdateAmcamsg(rbid, rmid, amcr_submid, amcr_cert1,
											amcr_cert2, amca_storeid, "", "", "", "");
								}else if ("AMCR".equals(rbranch.getId())) {
									// 插入微信自主收银映射表
									insertOrUpdateAmcrmsg(rbid, rmid, amcr_appid, amcr_mid,
										amcr_key, amcr_submid, amcr_cert1, amcr_cert2, amcr_cert3,
										"", "");
								}else if ("WXPP".equals(rbranch.getId())) {
									// 插入微信映射表
									insertOrUpdateMidToSubmid(builddate, rbid, rmid, appcls,
										wechat_sub_merid, "", "", "");
								}else if ("CDHR".equals(rbranch.getId())) {
									// 插入微赢家映射表
									insertOrUpdateMidToSubmid(builddate, rbid, rmid, appcls,
										wyj_submid, wyj_key, wyj_mertype, wyj_storeid);
								}else if ( "SWIF".equals(rbranch.getId())|| 
										   "CNCB".equals(rbranch.getId())|| 
										   "MSMP".equals(rbranch.getId())|| 
										   "BWIF".equals(rbranch.getId())||
										   "ZCFX".equals(rbranch.getId())) {
									// 插入威富通映射表，中信银行存的一样,民生银行存的一样
									insertOrUpdateMidToSubmid(builddate, rbid, rmid, "00",
											wft_merchantid, wft_merkey, wft_intype, wft_bid);
								}else if ("BRCB".equals(rbranch.getId())||"WZPP".equals(rbranch.getId())||"SPDB".equals(rbranch.getId())) {
									insertOrUpdateMidToSubmid(builddate, rbid, rmid, appcls,
											wft_merchantid, wft_merkey, wft_intype, wft_bid);
								}else{
									insertOrUpdateMidToSubmid(builddate, rbid, rmid, "00",
											wft_merchantid, wft_merkey, wft_intype, wft_bid);
								}
								
							} else if ("3".equals(operType)) {
								deleteMidToSubmid(rbid, rmid, appcls);
							} else if ("4".equals(operType)) {
								// ==============冻结操作==============
								// 删除终端映射
								// deleteTermMap(cposMer, lbranch, rbranch);
								// 删除终端映射mode
								// deleteTermMapMode(cposMer, lbranch, rbranch);
								// 删除商户映射
								deleteMerMap(lbid, mid, rbid, appcls);
								// 删除商户映射mode
								deleteMerMapMode(lbid, mid, rbid, appcls);
								// 删除渠道映射
								deleteBranchMap(lbid, mid, appcls);
								// 删除渠道映射mode
								deleteBranchMapMode(lbid, mid, appcls);
							}
							
						} catch (Exception e) {
							logger.error("处理记录：" + lineTxt + "出错", e);
							entrys.add(lineTxt);
						}
						
					}
					
					read.close();
					
					// 将处理失败的记录，保存到错误文件中
					if (CollectionUtils.isNotEmpty(entrys)) {
						
						String fileName = "error_bankBindShip_"	+ DateUtils.getCurrentDateString2()
											+ ".txt";
						
						logger.info("将处理出错的记录保存到" + fileName + "文件中");
						
						PrintWriter pw = new PrintWriter(new OutputStreamWriter(
							new FileOutputStream(new File(fileName)), "UTF-8"));
						
						for (String string : entrys) {
							logger.info("写入错误记录：" + string);
							pw.println(string);
						}
						
						pw.flush();
						pw.close();
						
						SftpUtils.upload(ftp_directory, fileName, SftpUtils.tps_sftp);
						
						logger.info("文件" + fileName + "上传完成");
					}
					
					fileNames.add(lsEntry.getFilename());
					
				}
				
			}
		} catch (
		
		BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("处理失败", e);
			throw new BusinessException("系统异常");
		}
		
	}
	
	private void deleteBranchMapMode(String lbid, String lmid, String appClass) {
		
		logger.info("删除一条渠道映射_交易类别_" + appClass + "的mode记录");
		
		branchMapDao.deleteBranchMapMode(lbid, lmid, appClass);
		
		logger.info("删除成功");
		
	}
	
	private void deleteBranchMap(String lbid, String lmid, String appClass) {
		
		logger.info("删除一条渠道一一映射_" + appClass);
		
		branchMapDao.deleteO2O(lbid, lmid, appClass);
		
		logger.info("删除成功");
		
	}
	
	private void deleteMerMapMode(String lbid, String lmid, String rbid, String cls) {
		
		logger.info("删除一条商户一一" + cls + "映射mode");
		
		merMapDao.deleteMerMapMode(lbid, lmid, cls, rbid);
		
		logger.info("删除成功");
		
	}
	
	private void deleteMerMap(String lbid, String lmid, String rbid, String appClass) {
		
		logger.info("删除一条商户一一映射_" + appClass);
		
		merMapDao.delete(lbid, lmid, appClass, rbid, "01");
		
		logger.info("删除成功");
		
	}
	
	private void insertMerMap(	String lbid, String lmcr, String lmid, String rbid, String rmcr,
								String rmid, String appClass, String builddate) {
		
		MerMap merMapO2O = merMapDao.get(lbid, lmid, appClass, rbid, "01");
		
		if (merMapO2O == null) {
			
			logger.info("新增一条商户一一映射_" + appClass);
			
			merMapO2O = new MerMap();
			
			merMapO2O.setLbid(lbid);
			
			merMapO2O.setLmcr(lmcr);
			
			merMapO2O.setLmid(lmid);
			
			merMapO2O.setCls(appClass);
			
			merMapO2O.setMmf("01");
			
			merMapO2O.setRbid(rbid);
			
			merMapO2O.setRmcr(rmcr);
			
			// rmid和cposMer的mid是一样的,paxus来了，就不一样了
			merMapO2O.setRmid(rmid);
			
			merMapO2O.setBuildoper("admin");
			merMapO2O.setBuilddatetime_short(builddate);
			merMapO2O.setStatus(BaseEntity.STATUS_2);
			merMapO2O.setAuditstatus(BaseEntity.STATUS_2);
			
			merMapDao.save(merMapO2O);
			
			logger.info("新增成功");
		} else {
			logger.info("商户一一映射已经存在");
		}
	}
	
	private void insertRterm(	String lbid, String lmcr, String lmid, String rbid, String rmcr,
								String rmid, String builddate) {
		
		RMer rMer = rMerDao.get(rmcr, rmid);
		
		List<CposTerm> list = cposTermDao.listBySyn(lmcr, lmid, BaseEntity.STATUS_3);
		
		logger.info("接入终端有" + list.size() + "个");
		
		for (CposTerm cposTerm : list) {
			// 查看对应的转出终端是否存在，没有才插入
			RTerm tempRTerm = rTermDao.get(rmcr, rmid, cposTerm.getTid());
			if (tempRTerm == null) {
				
				logger.info("新增一条转出终端");
				
				RTerm rTerm = new RTerm();
				rTerm.setMer(rMer);
				rTerm.setTid(cposTerm.getTid());
				rTerm.setDepid(cposTerm.getDepid());
				rTerm.setBuildoper("管理员");
				rTerm.setBuilddatetime_short(builddate);
				rTerm.setStatus(BaseEntity.STATUS_2);
				rTerm.setAuditstatus(BaseEntity.STATUS_2);
				
				rTermDao.saveBySyn(rTerm);
				
				logger.info("插入成功");
				
			} else {
				logger.info("转出终端已经存在，不做任何处理");
			}
			
			insertTermMap(lbid, lmcr, lmid, rbid, rmcr, rmid, cposTerm.getTid(), builddate);
			
		}
	}
	
	private void insertTermMap(	String lbid, String lmcr, String lmid, String rbid, String rmcr,
								String rmid, String tid, String builddate) {
		
		TermMapView tempTermMapMode = termMapDao.getTermMapMode(lbid, lmid, rbid, rmid);
		
		if (tempTermMapMode == null) {
			
			logger.info("新增一条终端一一映射mode");
			
			TermMapView termMapMode = new TermMapView();
			termMapMode.setLbid(lbid);
			termMapMode.setLmcr(lmcr);
			termMapMode.setLmid(lmid);
			termMapMode.setRbid(rbid);
			termMapMode.setRmcr(rmcr);
			termMapMode.setRmid(rmid);
			
			termMapMode.setTmf("01");
			
			termMapMode.setBuildoper("admin");
			termMapMode.setBuilddatetime_short(builddate);
			termMapMode.setStatus(BaseEntity.STATUS_2);
			termMapMode.setAuditstatus(BaseEntity.STATUS_2);
			termMapDao.saveTermMapMode(termMapMode);
			
			logger.info("插入成功");
		} else {
			logger.info("终端映射mode已经存在");
		}
		
		TermMapView termMap = termMapDao.getTermMap(lbid, lmcr, lmid, rbid, rmcr, rmid, tid, tid);
		
		if (termMap == null) {
			
			logger.info("新增一条终端一一映射");
			
			termMap = new TermMapView();
			termMap.setLbid(lbid);
			termMap.setLmcr(lmcr);
			termMap.setLmid(lmid);
			termMap.setLtid(tid);
			termMap.setRbid(rbid);
			termMap.setRmcr(rmcr);
			termMap.setRmid(rmid);
			termMap.setRtid(tid);
			termMap.setBuildoper("admin");
			termMap.setBuilddatetime_short(builddate);
			termMap.setStatus(BaseEntity.STATUS_2);
			termMap.setAuditstatus(BaseEntity.STATUS_2);
			
			termMapDao.save(termMap);
			
			logger.info("插入成功");
		} else {
			logger.info("终端映射已经存在");
		}
	}
	
	private void deleteMidToSubmid(String bid, String mid, String classId) {
		
		MidToSubmid tempMidToSubmid = synDao.getMidToSubmid(bid, mid, classId);
		if (tempMidToSubmid != null) {
			
			synDao.deleteMidToSubmid(tempMidToSubmid);
			
			logger.info("该条记录删除成功");
		}
	}
	
	private void insertOrUpdateRmer(String date, String rmcr, String mid, String rmid, String merName,
									String depId) {
		// 先查询数据库有没得转出商户
		RMer temp = rMerDao.get(rmcr, rmid);
		// 没有，才插入
		if (temp == null) {
			
			logger.info("新增一条转出商户");
			
			temp = new RMer();
			
			temp.setMcr(rmcr);
			temp.setMid(rmid);
			
			temp.setName(merName);
			
			Organization org = organizationDao.get(depId);
			
			if (org == null) {
				throw new BusinessException("机构不存在");
			}
			temp.setDepid(depId);
			
			temp.setBuildoper("管理员");
			temp.setBuilddatetime_short(date);
			temp.setStatus(BaseEntity.STATUS_2);
			temp.setAuditstatus(BaseEntity.STATUS_2);
			
			rMerDao.saveBySyn(temp);
			
			logger.info("转出商户插入成功");
			
		} else {
			logger.info("转出商户已经存在，修改转出商户");
			
			if (mid.equals(rmid)) {
				temp.setName(merName);
				Organization org = organizationDao.get(depId);
				
				if (org == null) {
					throw new BusinessException("机构不存在");
				}
				
				temp.setDepid(depId);
				
				temp.setModifydatetime(date);
				temp.setModifyoper("管理员");
				
				rMerDao.updateBySyn(temp);
				
				logger.info("该条记录修改成功");
			}else{
				logger.info("接入商户和转出商户不一样，不做任何修改");
			}
			
			
		}
	}
	
	private BranchMapView createNewBranchMapMode(	Map<String, String> idMap, String lbid,
													String lmcr, String mid, String appClass,
													String bmf, String buildoper, String iddate,
													String builddate) {
		
		BranchMapView branchMapMode = new BranchMapView();
		
		String rid = idMap.get("branchMapModeId");
		rid = StringUtils.leftPad(String.valueOf(Integer.parseInt(rid) + 1), 6, '0');
		
		idMap.put("branchMapModeId", rid);
		
		branchMapMode.setRid(iddate + rid);
		
		branchMapMode.setLbid(lbid);
		branchMapMode.setLmcr(lmcr);
		branchMapMode.setLmid(mid);
		
		branchMapMode.setCls(appClass);
		
		branchMapMode.setBmf(bmf);
		
		branchMapMode.setBuildoper(buildoper);
		branchMapMode.setBuilddatetime_short(builddate);
		// 直接让其变成启用和审核通过状态
		branchMapMode.setStatus(BaseEntity.STATUS_2);
		branchMapMode.setAuditstatus(BaseEntity.STATUS_2);
		
		return branchMapMode;
	}
	
	private MerMap createNewMerMapMode(	String lbid, String lmcr, String lmid, String appClass,
										String rbid, String mmf, String buildoper,
										String builddate) {
		
		MerMap merMapMode = new MerMap();
		
		merMapMode.setLbid(lbid);
		
		merMapMode.setLmcr(lmcr);
		merMapMode.setLmid(lmid);
		
		merMapMode.setCls(appClass);
		
		merMapMode.setMmf(mmf);
		
		merMapMode.setRbid(rbid);
		
		merMapMode.setBuildoper(buildoper);
		merMapMode.setBuilddatetime_short(builddate);
		
		merMapMode.setStatus(BaseEntity.STATUS_2);
		merMapMode.setAuditstatus(BaseEntity.STATUS_2);
		
		return merMapMode;
	}
	
	// mid不等于空，表示要做商户个性化
	private void insertBranchMap(	Map<String, String> idMap, String lbid, String lmcr, String lmid,
									String rbid, String appClass, String iddate, String builddate) {
		// 查看T_B_BRANCHMAP表中是否已经存在映射记录
		BranchMapView branchMapO2O = branchMapDao.getBranchMapView("01", lbid, lmid, appClass, null,
			null, null, null);
		
		if (branchMapO2O == null) {
			
			logger.info("新增一条渠道一一映射_" + appClass);
			
			branchMapO2O = new BranchMapView();
			
			String rid = idMap.get("branchMapId");
			rid = StringUtils.leftPad(String.valueOf(Integer.parseInt(rid) + 1), 6, '0');
			
			idMap.put("branchMapId", rid);
			
			branchMapO2O.setRid(iddate + rid);
			
			branchMapO2O.setLbid(lbid);
			
			if (StringUtils.isNotBlank(lmid)) {
				branchMapO2O.setLmcr(lmcr);
				branchMapO2O.setLmid(lmid);
			}
			
			branchMapO2O.setCls(appClass);
			
			branchMapO2O.setBmf("01");
			
			branchMapO2O.setRbid(rbid);
			
			branchMapO2O.setBuildoper("admin");
			branchMapO2O.setBuilddatetime_short(builddate);
			branchMapO2O.setStatus(BaseEntity.STATUS_2);
			branchMapO2O.setAuditstatus(BaseEntity.STATUS_2);
			branchMapDao.save(branchMapO2O);
			
			logger.info("新增成功");
		} else {
			logger.info("渠道一一映射已经存在");
		}
	}
	
	// mid不等于空，表示要做商户个性化
	private void insertBranchMapMode(	Map<String, String> idMap, String lbid, String lmcr,
										String lmid, String appClass, String iddate,
										String builddate) {
		
		BranchMapView branchMapMode = branchMapDao.getBranchMapMode(lbid, lmid, appClass);
		
		if (branchMapMode == null) {
			
			logger.info("新增一条渠道映射_交易类别_" + appClass + "的mode记录");
			
			branchMapMode = createNewBranchMapMode(idMap, lbid, lmcr, lmid, appClass, "01", "admin",
				iddate, builddate);
			
			branchMapDao.saveBranchMapMode(branchMapMode);
			
			logger.info("新增成功");
			
		} else {
			logger.info("渠道映射mode已经存在");
		}
	}
	
	private void insertMerMapMode(	String lbid, String lmcr, String lmid, String rbid, String cls,
									String builddate) {
		
		// 查看T_B_MERMAP_MODE表中是否已经存在映射记录
		MerMap merMapMode = merMapDao.getMerMapMode(lbid, lmid, cls, rbid);
		if (merMapMode == null) {
			
			logger.info("新增一条商户一一" + cls + "映射mode");
			merMapMode = createNewMerMapMode(lbid, lmcr, lmid, cls, rbid, "01", "admin", builddate);
			merMapDao.saveMerMapMode(merMapMode);
			
			logger.info("新增成功");
		} else {
			logger.info("商户一一映射mode已经存在");
		}
	}
	
	private void insertOrUpdateMidToSubmid(	String date, String bid, String mid, String classId,
											String submid, String notes1, String notes2,
											String notes3) {
		
		MidToSubmid tempMidToSubmid = synDao.getMidToSubmid(bid, mid, classId);
		
		if (tempMidToSubmid == null) {
			logger.info("新增一条MidToSubmid映射关系");
			MidToSubmid midToSubmid = new MidToSubmid();
			midToSubmid.setBid(bid);
			midToSubmid.setMid(mid);
			midToSubmid.setClassId(classId);
			midToSubmid.setSubmid(submid);
			midToSubmid.setNotes1(notes1);
			midToSubmid.setNotes2(notes2);
			midToSubmid.setNotes3(notes3);
			midToSubmid.setBuilddatetime(date);
			synDao.saveMidToSubmid(midToSubmid);
			
			logger.info("插入成功");
		} else {
			logger.info("修改一条MidToSubmid映射关系");
			String orgSubmid = tempMidToSubmid.getSubmid();
			String orgNotes1 = tempMidToSubmid.getNotes1();
			String orgNotes2 = tempMidToSubmid.getNotes2();
			String orgNotes3 = tempMidToSubmid.getNotes3();
			tempMidToSubmid.setSubmid(submid);
			tempMidToSubmid.setNotes1(notes1);
			tempMidToSubmid.setNotes2(notes2);
			tempMidToSubmid.setNotes3(notes3);
			synDao.updateMidToSubmid(tempMidToSubmid);
			
		}
	}
	
	private void insertOrUpdateMidToAppid(	String date, String bid, String mid, String appid,
											String storeid, String notes1, String notes2,
											String notes3) {
		
		MidToAppid tempMidToAppid = synDao.getMidToAppid(mid);
		if (tempMidToAppid == null) {
			logger.info("新增一条支付宝映射关系");
			MidToAppid midToAppid = new MidToAppid();
			midToAppid.setMid(mid);
			midToAppid.setAppid(appid);
			midToAppid.setStorid(storeid);
			midToAppid.setNotes1(notes1);
			midToAppid.setNotes2(notes2);
			midToAppid.setNotes3(notes3);
			midToAppid.setBuilddatetime(date);
			synDao.saveMidToAppid(midToAppid);
			
			logger.info("插入成功");
		} else {
			logger.info("修改一条支付宝映射关系");
			tempMidToAppid.setAppid(appid);
			tempMidToAppid.setStorid(storeid);
			synDao.updateMidToAppid(tempMidToAppid);
			
		}
	}
	
	private void insertOrUpdateAmcamsg(	String bid, String mid, String appid, String alp_pubkey,
										String amca_prikey, String notes1, String notes2,
										String notes3, String notes4, String notes5) {
		
		Amcamsg tempAmcamsg = synDao.getAmcamsg(bid, mid);
		if (tempAmcamsg == null) {
			logger.info("新增一条支付宝自主收银");
			Amcamsg amcamsg = new Amcamsg();
			amcamsg.setBid(bid);
			amcamsg.setMid(mid);
			amcamsg.setAppid(appid);
			amcamsg.setAlp_pubkey(alp_pubkey);
			amcamsg.setAmca_prikey(amca_prikey);
			amcamsg.setNotes1(notes1);
			amcamsg.setNotes2(notes2);
			amcamsg.setNotes3(notes3);
			amcamsg.setNotes4(notes4);
			amcamsg.setNotes5(notes5);
			synDao.saveAmcamsg(amcamsg);
			logger.info("插入成功");
		} else {
			logger.info("修改一条支付宝自主收银");
			tempAmcamsg.setAppid(appid);
			tempAmcamsg.setAlp_pubkey(alp_pubkey);
			tempAmcamsg.setAmca_prikey(amca_prikey);
			tempAmcamsg.setNotes1(notes1);
			tempAmcamsg.setNotes2(notes2);
			tempAmcamsg.setNotes3(notes3);
			tempAmcamsg.setNotes4(notes4);
			tempAmcamsg.setNotes5(notes5);
			synDao.updateAmcamsg(tempAmcamsg);
		}
	}
	
	private void insertOrUpdateAmcrmsg(	String bid, String mid, String appid, String amcr_mid,
										String amcr_key, String amcr_submid, String notes1,
										String notes2, String notes3, String notes4,
										String notes5) {
		
		Amcrmsg tempAmcrmsg = synDao.getAmcrmsg(bid, mid);
		
		if (tempAmcrmsg == null) {
			logger.info("新增一条微信自主收银");
			Amcrmsg amcrmsg = new Amcrmsg();
			amcrmsg.setBid(bid);
			amcrmsg.setMid(mid);
			amcrmsg.setAppid(appid);
			amcrmsg.setAmcr_mid(amcr_mid);
			amcrmsg.setAmcr_key(amcr_key);
			amcrmsg.setAmcr_submid(amcr_submid);
			amcrmsg.setNotes1(notes1);
			amcrmsg.setNotes2(notes2);
			amcrmsg.setNotes3(notes3);
			amcrmsg.setNotes4(notes4);
			amcrmsg.setNotes5(notes5);
			synDao.saveAmcrmsg(amcrmsg);
			logger.info("插入成功");
		} else {
			logger.info("修改一条微信自主收银");
			tempAmcrmsg.setAppid(appid);
			tempAmcrmsg.setAmcr_mid(amcr_mid);
			tempAmcrmsg.setAmcr_key(amcr_key);
			tempAmcrmsg.setAmcr_submid(amcr_submid);
			tempAmcrmsg.setNotes1(notes1);
			tempAmcrmsg.setNotes2(notes2);
			tempAmcrmsg.setNotes3(notes3);
			tempAmcrmsg.setNotes4(notes4);
			tempAmcrmsg.setNotes5(notes5);
			synDao.updateAmcrmsg(tempAmcrmsg);
		}
	}
	
	public static void main(String[] args) {
		
		Vector<String> vector = new Vector<String>();
		
		vector.add("2017");
		vector.add("2015");
		vector.add("2019");
		vector.add("2013");
		
		Collections.sort(vector, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				
				String e1 = (String) o1;
				String e2 = (String) o2;
				
				return e1.compareTo(e2);
				
			}
		});
		
		for (String string : vector) {
			System.out.println(string);
		}
	}
}
