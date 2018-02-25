package com.pax.busi.syn.service.lmer;

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
import com.pax.auth.dao.OrganizationDao;
import com.pax.auth.entity.Organization;
import com.pax.busi.common.dao.CommonDao;
import com.pax.busi.common.entity.Branch;
import com.pax.busi.resourcemgr.dao.CposMerDao;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.core.entity.BaseEntity;
import com.pax.core.exception.BusinessException;
import com.pax.core.util.DateUtils;
import com.pax.core.util.SftpUtils;

@Service
public class LmerTimerTaskHandler {
	
	@Resource
	private CposMerDao		cposMerDao;
	@Resource
	private CommonDao		commonDao;
	@Resource
	private OrganizationDao	organizationDao;
	
	protected final Logger	logger	= LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("all")
	@Transactional
	public void processMerFile(	String date, List<String> fileNames, String ftp_directory,
								
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
				
				if (lsEntry.getFilename().contains("lmer")) {
					// 处理商户文件
					logger.info("============处理商户文件：" + lsEntry.getFilename());
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
							String bid = ss[1];
							String mid = ss[2];
							String name = ss[3];
							//String depId = ss[4];
							String depId = "1";
							String agentId = ss[5];
							// // 结算账户类型，对公账户：01，私人账户：02
							// String settl_acct_type = ss[6];
							// // 结算账户开户名称
							// String settl_acct_name = ss[7];
							// // 结算账户开户银行ID
							// String settl_acct_bank_id = ss[8];
							// // 结算账户开户银行名称
							// String settl_acct_bank_name = ss[9];
							// // 结算账户开户银行城市ID
							// String settl_acct_bankcity_id = ss[10];
							// // 结算账户开户银行城市名称
							// String settl_acct_bankcity_name = ss[11];
							// // 结算账户开户支行ID
							// String settl_acct_subbank_id = ss[12];
							// // 结算账户开户支行名称
							// String settl_acct_subbank_name = ss[13];
							// // 结算账户银行账号
							// String settl_acct_no = ss[14];
							// String commission_combo_id = ss[15];
							String storeid = ss[16];
							String storename = ss[17];
							String provincename = "";
							String cityname = "";
							String districtname = "";
							String address = "";
							String timeZoneNew = "";
							if(ss.length>18){
								provincename = ss[18];
								cityname = ss[19];
								districtname = ss[20];
								address = ss[21];
								if(ss.length>22){
									timeZoneNew = ss[22];
								}
							}
							
							Branch branch = commonDao.getBranch(bid);
							
							if (branch == null) {
								throw new BusinessException("接入渠道不存在");
							}
							
							if ("1".equals(operType) || "2".equals(operType)) {
								
								// 先查询数据库有没得
								CposMer temp = cposMerDao.get(branch.getMcr(), mid);
								// 没有，才插入
								if (temp == null) {
									
									logger.info("该条记的操作为新增");
									
									CposMer cposMer = new CposMer();
									cposMer.setMcr(branch.getMcr());
									cposMer.setMid(mid);
									
									cposMer.setName(name);
									cposMer.setAgent_id(agentId);
									
									cposMer.setStoreid(storeid);
									cposMer.setStorename(storename);
									cposMer.setTimeZoneNew(timeZoneNew);
									cposMer.setProvincename(provincename);
									cposMer.setCityname(cityname);
									cposMer.setDistrictname(districtname);
									cposMer.setAddress(address);
									if (StringUtils.isNotBlank(timeZoneNew)) {
										cposMer.setConv_currency("1");
									}
									
									Organization org = organizationDao.get(depId);
									if (org == null) {
										throw new BusinessException("机构不存在");
									}
									
									cposMer.setDepid(depId);
									
									cposMer.setAgent_id(agentId);
									
									cposMer.setBuildoper("admin");
									cposMer.setBuilddatetime_short(date);
									cposMer.setStatus(BaseEntity.STATUS_2);
									cposMer.setAuditstatus(BaseEntity.STATUS_2);
									
									cposMerDao.saveBySyn(cposMer);
									
								} else {
									logger.info("该条记的操作为修改");
									
									temp.setName(name);
									
									Organization org = organizationDao.get(depId);
									if (org == null) {
										throw new BusinessException("机构不存在");
									}
									
									temp.setDepid(depId);
									
									temp.setModifydatetime_short(date);
									temp.setModifyoper("admin");
									if (StringUtils.isNotBlank(timeZoneNew)) {
										temp.setTimeZoneNew(timeZoneNew);
										temp.setConv_currency("1");
									}
									temp.setStorename(name);
									temp.setProvincename(provincename);
									temp.setCityname(cityname);
									temp.setDistrictname(districtname);
									temp.setAddress(address);
									
									cposMerDao.updateBySyn(temp);
									
									logger.info("该条记录修改成功");
								}
								
							} else if ("3".equals(operType)) {
								logger.info("该条记的操作为删除");
								// 先查询数据库有没得
								CposMer temp = cposMerDao.get(branch.getMcr(), mid);
								// 没有，不做任何处理
								if (temp != null) {
									
									temp.setStatus(BaseEntity.STATUS_3);
									temp.setModifydatetime_short(date);
									temp.setModifyoper("admin");
									
									cposMerDao.updateBySyn(temp);
									
									logger.info("该条记录删除成功");
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
						
						String fileName = "error_lmer_"	+ DateUtils.getCurrentDateString2()
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
	
}
