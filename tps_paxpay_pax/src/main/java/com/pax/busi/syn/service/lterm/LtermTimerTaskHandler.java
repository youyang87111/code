package com.pax.busi.syn.service.lterm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.pax.busi.common.dao.CommonDao;
import com.pax.busi.common.entity.Branch;
import com.pax.busi.common.entity.Mcr;
import com.pax.busi.mapmgr.dao.TermMapDao;
import com.pax.busi.mapmgr.entity.TermMapView;
import com.pax.busi.resourcemgr.dao.CposMerDao;
import com.pax.busi.resourcemgr.dao.CposTermDao;
import com.pax.busi.resourcemgr.dao.RMerDao;
import com.pax.busi.resourcemgr.dao.RTermDao;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.busi.resourcemgr.entity.CposTerm;
import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.busi.resourcemgr.entity.RTerm;
import com.pax.core.entity.BaseEntity;
import com.pax.core.exception.BusinessException;
import com.pax.core.util.DateUtils;
import com.pax.core.util.SftpUtils;

@Service
public class LtermTimerTaskHandler {
	
	@Resource
	private CposMerDao		cposMerDao;
	@Resource
	private RMerDao			rMerDao;
	@Resource
	private CposTermDao		cposTermDao;
	@Resource
	private RTermDao		rTermDao;
	@Resource
	private TermMapDao		termMapDao;
	@Resource
	private CommonDao		commonDao;
	
	protected final Logger	logger	= LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("all")
	@Transactional
	public void processTermFile(String date, List<String> fileNames, String ftp_directory,
								Vector<LsEntry> vector)	throws Exception,
														UnsupportedEncodingException, IOException {
		
		try {
			Collections.sort(vector, new Comparator() {
				@Override
				public int compare(Object o1, Object o2) {
					
					LsEntry e1 = (LsEntry) o1;
					LsEntry e2 = (LsEntry) o2;
					
					return e1.getFilename().compareTo(e2.getFilename());
					
				}
			});
			for (LsEntry lsEntry : vector) {
				
				if (lsEntry.getFilename().contains("lterm")) {
					// 处理终端文件
					logger.info("============处理终端文件：" + lsEntry.getFilename());
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
							String rbids = ss[2];
							String mid = ss[3];
							String tid = ss[4];
							//String depId = ss[5];
							String depId = "1";
							// 装机位置
							String position = ss[6];
							
							String rmids = "";
							
							//paxus新加了rmids
							if (ss.length >= 8) {
								rmids = ss[7];
							}
							
							Branch lbranch = commonDao.getBranch(lbid);
							
							if (lbranch == null) {
								throw new BusinessException("接入渠道不存在");
							}
							
							CposMer cposMer = cposMerDao.get(lbranch.getMcr(), mid);
							
							if (cposMer == null) {
								throw new BusinessException("接入商户不存在");
							}
							
							if ("1".equals(operType) || "2".equals(operType)) {
								
								// 先查询数据库有没得
								CposTerm temp = cposTermDao.get(lbranch.getMcr(), mid, tid);
								// 没有，才插入
								if (temp == null) {
									
									logger.info("该条记的操作为新增");
									
									CposTerm cposTerm = new CposTerm();
									cposTerm.setMer(cposMer);
									cposTerm.setTid(tid);
									cposTerm.setPosition(position);
									cposTerm.setDepid(depId);
									cposTerm.setBuildoper("admin");
									cposTerm.setBuilddatetime_short(date);
									cposTerm.setStatus(BaseEntity.STATUS_2);
									cposTerm.setAuditstatus(BaseEntity.STATUS_2);
									
									cposTermDao.saveBySyn(cposTerm);
									
									String[] rbidArry = rbids.split("-");
									
									if (StringUtils.isBlank(rmids)) {
										for (String rbid : rbidArry) {
											
											Branch rbranch = commonDao.getBranch(rbid);
											
											if (rbranch == null) {
												throw new BusinessException("转出渠道不存在");
											}
											// 插入转出终端和终端映射
											inertRTerm(lbid, lbranch.getMcr(), mid, rbid,
												rbranch.getMcr(), mid, tid, depId, date);
										}
									} else {
										String[] rmidss = rmids.split("-");
										for (int i = 0; i < rbidArry.length; i++) {
											
											String rbid = rbidArry[i];
											String rmid = rmidss[i];
											
											Branch rbranch = commonDao.getBranch(rbid);
											
											if (rbranch == null) {
												throw new BusinessException("转出渠道不存在");
											}
											
											// 插入转出终端和终端映射
											inertRTerm(lbid, lbranch.getMcr(), mid, rbid,
												rbranch.getMcr(), rmid, tid, depId, date);
										}
									}
									
								} else {
									logger.info("该条记的操作为修改");
									
									temp.setPosition(position);
									temp.setDepid(depId);
									
									temp.setModifydatetime_short(date);
									temp.setModifyoper("admin");
									
									cposTermDao.updateBySyn(temp);
									
									logger.info("该条记录修改成功");
								}
								
							} else if ("3".equals(operType)) {
								logger.info("该条记的操作为删除");
								// 先查询数据库有没得
								CposTerm temp = cposTermDao.get(lbranch.getMcr(), mid, tid);
								// 没有，不做任何处理
								if (temp != null) {
									
									temp.setStatus(BaseEntity.STATUS_3);
									temp.setModifydatetime_short(date);
									temp.setModifyoper("admin");
									
									cposTermDao.updateBySyn(temp);
									
									logger.info("该条记录删除成功");
								} else {
									logger.info("接入终端不存在");
								}
							}
							
						} catch (Exception e) {
							logger.error("处理记录：" + lineTxt + "出错", e);
							entrys.add(lineTxt);
						}
						
					}
					
					read.close();
					
					// 将处理失败的记录，保存到错误文件中
					if (CollectionUtils.isNotEmpty(entrys)) {
						
						String fileName = "error_lterm_"	+ DateUtils.getCurrentDateString2()
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
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			logger.error("处理失败", e);
			throw new BusinessException("系统异常");
		}
	}
	
	private void inertRTerm(String lbid, String lmcr, String lmid, String rbid, String rmcr,
							String rmid, String tid, String depId, String date) {
		
		Mcr rMcrObj = commonDao.getMcr(rmcr);
		
		if (rMcrObj == null) {
			throw new BusinessException("转出商户来源不存在");
		}
		
		RMer rmer = rMerDao.get(rmcr, rmid);
		
		// 转出商户存在
		if (rmer != null) {
			// 继续查看转出商户下的终端是否存在了
			RTerm tempRTerm = rTermDao.get(rmcr, rmid, tid);
			if (tempRTerm == null) {
				logger.info("新增一条转出终端");
				
				RTerm rTerm = new RTerm();
				rTerm.setMer(rmer);
				rTerm.setTid(tid);
				rTerm.setDepid(depId);
				rTerm.setBuildoper("admin");
				rTerm.setBuilddatetime_short(date);
				rTerm.setStatus(BaseEntity.STATUS_2);
				rTerm.setAuditstatus(BaseEntity.STATUS_2);
				
				rTermDao.saveBySyn(rTerm);
				
				insertTermMap(lbid, lmcr, lmid, rbid, rmcr, rmid, tid, date);
				
			} else {
				insertTermMap(lbid, lmcr, lmid, rbid, rmcr, rmid, tid, date);
				logger.info("转出终端已经存在");
			}
		} else {
			logger.info("转出商户不存在，不做任何操作");
		}
	}
	
	private void insertTermMap(	String lbid, String lmcr, String lmid, String rbid, String rmcr,
								String rmid, String tid, String date) {
		
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
			termMapMode.setBuilddatetime_short(date);
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
			termMap.setBuilddatetime_short(date);
			termMap.setStatus(BaseEntity.STATUS_2);
			termMap.setAuditstatus(BaseEntity.STATUS_2);
			
			termMapDao.save(termMap);
			
			logger.info("插入成功");
		} else {
			logger.info("终端映射已经存在");
		}
	}
	
}