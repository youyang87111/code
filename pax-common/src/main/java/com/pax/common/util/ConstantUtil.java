package com.pax.common.util;

public class ConstantUtil {
	
	/*TPS的sftp配置*/
	public static String	TPS_FTP_IP;
	public static String	TPS_FTP_PORT;
	public static String	TPS_FTP_USERNAME;
	public static String	TPS_FTP_PASSWD;
	public static String	TPS_FTP_DIRECTORY;
	public static int	UPDATE_PASSWORD_TIME;
	public static int	FROZEN_USER_TIME;
	public static int	CANCLE_USER_TIME;
	
	
	public static String getTPS_FTP_IP() {
		return TPS_FTP_IP;
	}
	
	public static void setTPS_FTP_IP(String tPS_FTP_IP) {
		TPS_FTP_IP = tPS_FTP_IP;
	}
	
	public static String getTPS_FTP_PORT() {
		return TPS_FTP_PORT;
	}
	
	public static void setTPS_FTP_PORT(String tPS_FTP_PORT) {
		TPS_FTP_PORT = tPS_FTP_PORT;
	}
	
	public static String getTPS_FTP_USERNAME() {
		return TPS_FTP_USERNAME;
	}
	
	public static void setTPS_FTP_USERNAME(String tPS_FTP_USERNAME) {
		TPS_FTP_USERNAME = tPS_FTP_USERNAME;
	}
	
	public static String getTPS_FTP_PASSWD() {
		return TPS_FTP_PASSWD;
	}
	
	public static void setTPS_FTP_PASSWD(String tPS_FTP_PASSWD) {
		TPS_FTP_PASSWD = tPS_FTP_PASSWD;
	}
	
	public static String getTPS_FTP_DIRECTORY() {
		return TPS_FTP_DIRECTORY;
	}

	public static int getUPDATE_PASSWORD_TIME() {
		return UPDATE_PASSWORD_TIME;
	}

	public static void setUPDATE_PASSWORD_TIME(int PDATE_PASSWORD_TIME) {
		UPDATE_PASSWORD_TIME = PDATE_PASSWORD_TIME;
	}

	public static int getFROZEN_USER_TIME() {
		return FROZEN_USER_TIME;
	}

	public static void setFROZEN_USER_TIME(int fROZEN_USER_TIME) {
		FROZEN_USER_TIME = fROZEN_USER_TIME;
	}

	public static int getCANCLE_USER_TIME() {
		return CANCLE_USER_TIME;
	}

	public static void setCANCLE_USER_TIME(int cANCLE_USER_TIME) {
		CANCLE_USER_TIME = cANCLE_USER_TIME;
	}

	public static void setTPS_FTP_DIRECTORY(String tPS_FTP_DIRECTORY) {
		TPS_FTP_DIRECTORY = tPS_FTP_DIRECTORY;
	}
	
	
}
