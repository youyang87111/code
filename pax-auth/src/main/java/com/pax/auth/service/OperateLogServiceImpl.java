package com.pax.auth.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.auth.dao.OperateLogDao;
import com.pax.auth.dao.OperateLogParamDao;
import com.pax.auth.entity.OperateLog;
import com.pax.auth.entity.OperateLogParam;
import com.pax.common.exception.BusinessException;
import com.pax.common.model.PageQueryParam;

@Service("operateLogService")
public class OperateLogServiceImpl implements OperateLogService {
	
	@Resource
	private OperateLogDao		operateLogDao;
	
	@Resource
	private OperateLogParamDao	operateLogParamDao;
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(OperateLog log) {
		
		int id = operateLogDao.getNextId();
		log.setId(id);
		operateLogDao.save(log);
		
		List<OperateLogParam> params = log.getParams();
		for (OperateLogParam param : params) {
			param.setOperatelogid(id);
			operateLogParamDao.save(param);
		}
	}
	
	@Override
	public PageInfo<OperateLog> list(PageQueryParam pageQueryParam) {
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<OperateLog> list = operateLogDao.list(pageQueryParam);
		
		PageInfo<OperateLog> pageInfo = new PageInfo<OperateLog>(list);
		
		return pageInfo;
	}
	
	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			OperateLog operateLog = operateLogDao.get(Integer.valueOf(id));
			if (operateLog == null) {
				throw new BusinessException("该日志不存在");
			}
			operateLogDao.delete(Integer.valueOf(id));
			operateLogParamDao.delete(Integer.valueOf(id));
		}
	}
	
	@Override
	public List<OperateLogParam> getParamsById(String id) {
		
		List<OperateLogParam> list = operateLogParamDao.list(id);
		
		return list;
	}
	
}
