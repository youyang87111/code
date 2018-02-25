package com.pax.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtils {
	
	public static ChannelSftp		tps_sftp;
	
	/** 普通日志 */
	protected final static Logger	logger	= LoggerFactory.getLogger(SftpUtils.class);
	
	private SftpUtils() {
		
	}
	
	static {
		
		String tps_ftp_ip = ConstantUtil.TPS_FTP_IP;
		String tps_ftp_port = ConstantUtil.TPS_FTP_PORT;
		String tps_ftp_username = ConstantUtil.TPS_FTP_USERNAME;
		String tps_ftp_passwd = ConstantUtil.TPS_FTP_PASSWD;
		
		try {
			tps_sftp = connect(tps_ftp_ip, Integer.parseInt(tps_ftp_port), tps_ftp_username,
				tps_ftp_passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*** 连接sftp服务器* 
	 *   @param host 主机
	 * * @param port 端口
	 * * @param username 用户名
	 * * @param password 密码
	 * * @return
	 * @throws Exception */
	private static ChannelSftp connect(	String host, int port, String username,
										String password) throws Exception {
		
		JSch jsch = new JSch();
		Session sshSession = null;
		Channel channel = null;
		ChannelSftp sftp = null;
		
		//jsch.setKnownHosts("/" + username + "/.ssh/known_hosts");
		sshSession = jsch.getSession(username, host, port);
		
		sshSession.setPassword(password);
		
		Properties sshConfig = new Properties();
		sshConfig.put("StrictHostKeyChecking", "no");
		sshSession.setConfig(sshConfig);
		
		sshSession.connect();
		
		channel = sshSession.openChannel("sftp");
		//打开连接
		channel.connect();
		sftp = (ChannelSftp) channel;
		logger.info("连接到 " + host);
		
		return sftp;
	}
	
	public static void reconnect() {
		
		String tps_ftp_ip = ConstantUtil.TPS_FTP_IP;
		String tps_ftp_port = ConstantUtil.TPS_FTP_PORT;
		String tps_ftp_username = ConstantUtil.TPS_FTP_USERNAME;
		String tps_ftp_passwd = ConstantUtil.TPS_FTP_PASSWD;
		
		try {
			tps_sftp = connect(tps_ftp_ip, Integer.parseInt(tps_ftp_port), tps_ftp_username,
				tps_ftp_passwd);
		} catch (Exception e) {
			logger.error("重连失败", e);
		}
	}
	
	/*** 上传文件，注意上传后将删除原文件* 
	 * @param directory 上传的目录
	 * * @param uploadFile 要上传的文件
	 * * @param sftp
	 * @throws Exception */
	public static void upload(	String directory, String uploadFile,
								ChannelSftp sftp) throws Exception {
		
		sftp.cd(directory);
		File file = new File(uploadFile);
		FileInputStream fins = new FileInputStream(file);
		
		logger.info("上传目录：" + directory + ",上传文件名：" + file.getName());
		sftp.put(fins, file.getName());
		fins.close();
		file.delete();
		logger.info("上传成功");
		
	}
	
	/**
	 * 
	 * @param directory
	 * @param fileName
	 * @param ins
	 * @param sftp
	 * @throws Exception
	 */
	public static void upload(	String directory, String fileName, InputStream ins,
								ChannelSftp sftp) throws Exception {
		
		sftp.cd(directory);
		
		logger.info("上传目录：" + directory + ",上传文件名：" + fileName);
		sftp.put(ins, fileName);
		logger.info("上传成功");
	}
	
	/*** 下载文件* 
	 * @param directory 下载目录
	 * * @param downloadFile 下载的文件
	 * * @param saveFile 存在本地的路径
	 * * @param sftp
	 * @throws Exception */
	public static void download(String directory, String downloadFile, String saveFile,
								ChannelSftp sftp) throws Exception {
		
		sftp.cd(directory);
		File file = new File(saveFile);
		sftp.get(downloadFile, new FileOutputStream(file));
		logger.info("文件下载成功,下载的目录：" + directory + ",下载的文件名：" + downloadFile + ",下载到本地：" + saveFile);
		
	}
	
	/**
	 * 
	 * @param directory
	 * @param downloadFile
	 * @param sftp
	 * @return
	 * @throws Exception
	 */
	public static InputStream download(	String directory, String downloadFile,
										ChannelSftp sftp) throws Exception {
		
		sftp.cd(directory);
		
		InputStream ins = sftp.get(downloadFile);
		
		logger.info("文件下载成功");
		
		return ins;
		
	}
	
	/*** 删除文件* 
	 * @param directory 要删除文件所在目录
	 * * @param deleteFile 要删除的文件
	 * * @param sftp*/
	public static void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 列出目录下的文件
	 * @param directory 要列出的目录(绝对路径)
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Vector listFiles(String directory, ChannelSftp sftp) throws SftpException {
		return sftp.ls(directory);
	}
	
	/**
	 * 测试
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
	}
}
