package com.pax.auth.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pax.auth.dao.UserDao;
import com.pax.auth.entity.User;
import com.pax.core.entity.BaseEntity;
import com.pax.core.util.ConstantUtil;
import com.pax.core.util.DateUtils;
import com.pax.core.util.TranslationUtils;
import com.pax.core.util.WebUtils;

/**
 * 定时任务(冻结超过90天未登录的用户)
 * 
 *
 */
@Service
public class FrozenUserTimerTask {
	protected final Logger	logger		= LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserDao userDao;
	
	@Transactional
	public void execute() {
		
		try {
			logger.info("开始定时冻结用户...");
			List<User> list = userDao.listAll();
			for (User user : list) {
				String currentTime = DateUtils.stringDateFormat(DateUtils.getCurrentDateString(), "yyyy-MM-dd HH:mm:ss");
				if(StringUtils.isBlank(user.getLastlogintime())) continue;
				String lastlogintime = DateUtils.stringDateFormat(user.getLastlogintime(), "yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				try {
					Date currentDate = sdf.parse(currentTime);
					Date lastupdatepwdDate = sdf.parse(lastlogintime);
					int count = DateUtils.daysBetween(lastupdatepwdDate, currentDate);
					if(count>ConstantUtil.FROZEN_USER_TIME){
						user.setStatus(BaseEntity.STATUS_1);
						user.setFrozenreason("用户90天未登录");
						userDao.frozenUser(String.valueOf(user.getId()),user.getFrozenreason(),DateUtils.getCurrentDateString());
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			logger.error("冻结用户的定时任务出错", e);
		} catch (Throwable e) {
			logger.error("冻结用户的定时任务出错", e);
		}
	}
}
