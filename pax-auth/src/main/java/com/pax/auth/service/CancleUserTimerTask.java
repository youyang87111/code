package com.pax.auth.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pax.auth.dao.UserDao;
import com.pax.auth.entity.User;
import com.pax.common.util.ConstantUtil;
import com.pax.common.util.DateUtils;

/**
 * 定时任务(注销用户冻结时间超过30天的用户)
 * 
 *
 */

@Service
public class CancleUserTimerTask {
protected final Logger	logger		= LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserDao userDao;
	
	@Transactional
	public void execute() {
		
		try {
			logger.info("开始定时注销用户冻结时间超过30天的用户...");
			List<User> list = userDao.listAll();
			for (User user : list) {
				String currentTime = DateUtils.stringDateFormat(DateUtils.getCurrentDateString(), "yyyy-MM-dd HH:mm:ss");
				if(StringUtils.isBlank(user.getFrozentime())) continue;
				String frozenTime = DateUtils.stringDateFormat(user.getFrozentime(), "yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				try {
					Date currentDate = sdf.parse(currentTime);
					Date frozenDate = sdf.parse(frozenTime);
					int count = DateUtils.daysBetween(frozenDate, currentDate);
					if(count>ConstantUtil.CANCLE_USER_TIME){
						userDao.cancel(String.valueOf(user.getId()));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			logger.error("删除用户冻结时间超过30天的用户的定时任务出错", e);
		} catch (Throwable e) {
			logger.error("删除用户冻结时间超过30天的用户的定时任务出错", e);
		}
	}
}
