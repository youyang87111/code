package com.pax.busi.syn.service.qrcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpException;
import com.pax.core.util.ConstantUtil;
import com.pax.core.util.DateUtils;
import com.pax.core.util.SftpUtils;

@Service
public class QrcodeTimerTask {
	
	@Resource
	private QrcodeTimerTaskHandler	qrcodeTimerTaskHandler;
	
	protected final Logger			logger	= LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	public void execute() {
		
		logger.info("开始执行读取二维码文件的定时任务");
		
		try {
			
			List<String> fileNames = new ArrayList<String>();
			
			String date = DateUtils.getCurrentDateString();
			
			// 将文件从ftp服务器上面读取出来
			
			String ftp_directory = ConstantUtil.TPS_FTP_DIRECTORY;
			
			Vector<LsEntry> vector = SftpUtils.listFiles(ftp_directory, SftpUtils.tps_sftp);
			
			qrcodeTimerTaskHandler.processTermFile(date, fileNames, ftp_directory, vector);
			
			for (LsEntry lsEntry : vector) {
				
				for (String fileName : fileNames) {
					if (lsEntry.getFilename().equals(fileName)) {
						logger.info("删除文件：" + fileName);
						SftpUtils.delete(ftp_directory, fileName, SftpUtils.tps_sftp);
						logger.info("删除文件成功");
					}
				}
				
			}
			
		} catch (SftpException e) {
			logger.error("sftp连接断开", e);
			SftpUtils.reconnect();
		} catch (Exception e) {
			logger.error("定时任务出错", e);
		}
		
	}
	
}