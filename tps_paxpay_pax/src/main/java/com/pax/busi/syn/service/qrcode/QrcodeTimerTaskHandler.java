package com.pax.busi.syn.service.qrcode;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.pax.busi.syn.dao.SynDao;
import com.pax.busi.syn.entity.Qrcode;
import com.pax.core.exception.BusinessException;
import com.pax.core.util.DateUtils;
import com.pax.core.util.SftpUtils;

@Service
public class QrcodeTimerTaskHandler {
	
	@Resource
	private SynDao			synDao;
	
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
				
				if (lsEntry.getFilename().contains("qrcode")) {
					// 处理终端文件
					logger.info("============处理二维码文件：" + lsEntry.getFilename());
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
							
							String qrtoken = ss[1];
							String mid = ss[2];
							String tid = ss[3];
							String random = ss[4];
							String storename = ss[5];
							String createtime = ss[6];
							String createuser = ss[7];
							String expire = ss[8];
							String repay = ss[9];
							String paycheck = ss[10];
							String rbid = ss[11];
							
							Qrcode qrcode = synDao.getQrcode(qrtoken);
							
							if (qrcode == null) {
								qrcode = new Qrcode();
								qrcode.setQrtoken(qrtoken);
								qrcode.setMid(mid);
								qrcode.setTid(tid);
								qrcode.setRandom(random);
								qrcode.setStorename(storename);
								qrcode.setCreatetime(createtime);
								qrcode.setCreateuser(createuser);
								qrcode.setExpire(expire);
								qrcode.setRepay(repay);
								qrcode.setPaycheck(paycheck);
								qrcode.setRbid(rbid);
								
								synDao.saveQrcode(qrcode);
							}
							
						} catch (Exception e) {
							logger.error("处理记录：" + lineTxt + "出错", e);
							entrys.add(lineTxt);
						}
						
					}
					
					read.close();
					
					// 将处理失败的记录，保存到错误文件中
					if (CollectionUtils.isNotEmpty(entrys)) {
						
						String fileName = "error_qrcode_"	+ DateUtils.getCurrentDateString2()
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