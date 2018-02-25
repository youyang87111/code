package com.pax.busi.resourcemgr.service;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.busi.common.dao.CommonDao;
import com.pax.busi.common.entity.Mcr;
import com.pax.busi.resourcemgr.dao.RMerDao;
import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.busi.resourcemgr.input.RMerAddInput;
import com.pax.busi.resourcemgr.input.RMerUpdateInput;
import com.pax.core.entity.BaseEntity;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.DateUtils;
import com.pax.core.util.ExcelUtils;
import com.pax.core.util.RegexUtils;
import com.pax.core.util.TranslationUtils;
import com.pax.core.util.ValidateUtils;
import com.pax.core.util.ValidatorUtils;
import com.pax.core.util.WebUtils;

@Service
public class RMerServiceImpl extends BaseService implements RMerService{

	@Resource
	private RMerDao rMerDao;
	
	@Resource
	private CommonDao commonDao;
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(RMerAddInput input) {
		RMer rMer = new RMer();
		addConvert(input, rMer);
		rMerDao.save(rMer);
	}

	private void addConvert(RMerAddInput addInput, RMer rMer) {
		String mcr = addInput.getMcr();
		Mcr m = commonDao.getMcr(mcr);
		if(m == null){
			throw new BusinessException("商户来源不存在");
		}
		String mid = addInput.getMid();
		RMer tmp = rMerDao.get(mcr,mid);
		if (tmp != null) {
			throw new BusinessException("商户号重复");
		} else {
			
			rMer.setMcr(mcr);
			rMer.setMid(mid);
			rMer.setName(addInput.getName());
			rMer.setDepid(String.valueOf(WebUtils.getUser().getOrg().getId()));
			rMer.setBuildoper(WebUtils.getUser().getLoginname());
			rMer.setBuilddatetime_short(DateUtils.getCurrentDateString());
			rMer.setStatus(BaseEntity.STATUS_0);
			rMer.setAuditstatus(BaseEntity.STATUS_0);
		}
	}

	@Override
	public PageInfo<RMer> list(PageQueryParam pageQueryParam) {
		
		initializeFiltering(pageQueryParam);
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<RMer> rMers = rMerDao.list(pageQueryParam);
		
		PageInfo<RMer> pageInfo = new PageInfo<RMer>(rMers);
		
		return pageInfo;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(RMerUpdateInput updateInput) {
		ValidatorUtils.validateWithException(updateInput);
		
		RMer rMer = getRMer(updateInput.getId());
		
		if (BaseEntity.STATUS_2.equals(rMer.getStatus())) {
			throw new BusinessException("该记录处于启用状态，不能被修改");
		}
		
		rMer.setName(updateInput.getName());
		rMer.setModifyoper(WebUtils.getUser().getLoginname());
		rMer.setModifydatetime_short(DateUtils.getCurrentDateString());
		
		rMerDao.update(rMer);
	}
	
	public RMer getRMer(String id){
		String[] ids = id.split("-");
		String mcr = ids[0];
		String mid = ids[1];
		
		List<RMer> list = listAll();
		RMer rMer = rMerDao.get(mcr, mid);
		if(rMer == null){
			throw new BusinessException("转出商户不存在");
		}
		if (list.contains(rMer)) {
			return rMer;
		} else {
			throw new BusinessException("你没有权限操作该记录");
		}
	}

	@Override
	public RMer detail(String id) {
		RMer rMer = getRMer(id);
        return rMer;
	}

	@Override
	public List<RMer> listAll() {
		
		PageQueryParam pageQueryParam = new PageQueryParam();
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		
		pageQueryParam.setFilterMap(filterMap);
		
		initializeFiltering(pageQueryParam);
		
		List<RMer> list = rMerDao.list(pageQueryParam);
		
		return list;
	}

	@Override
	@Transactional
	public void audit(String id, String audit) {
		RMer rMer = getRMer(id);
		//审核通过
		if (BaseEntity.STATUS_1.equals(audit)) {
			if (BaseEntity.STATUS_2.equals(rMer.getAuditstatus())) {
				throw new BusinessException("该记录已经是审核通过状态");
			}
			rMer.setPassStatus();
		} else {
			if (BaseEntity.STATUS_1.equals(rMer.getAuditstatus())) {
				throw new BusinessException("该记录已经是审核不通过状态");
			}
			if (BaseEntity.STATUS_2.equals(rMer.getAuditstatus())) {
				throw new BusinessException("该记录已经是审核通过状态");
			}
			rMer.setAuditstatus(BaseEntity.STATUS_1);
		}
		
		rMer.setAuditoper(WebUtils.getUser().getLoginname());
		rMer.setAuditdatetime_short(DateUtils.getCurrentDateString());
		rMerDao.audit(rMer);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void frozen(String[] ids) {
		
		for (String id : ids) {
			RMer rMer = getRMer(id);
			if (!BaseEntity.STATUS_2.equals(rMer.getStatus())) {
				throw new BusinessException("该记录不能冻结");
			}
			rMer.setStatus(BaseEntity.STATUS_1);
			rMerDao.frozen(rMer);
		}
	}
	

	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void unfrozen(String[] ids) {
		for (String id : ids) {
			RMer rMer = getRMer(id);
			
			if (!BaseEntity.STATUS_1.equals(rMer.getStatus())) {
				throw new BusinessException("该记录不能解冻");
			}
			
			rMer.setStatus(BaseEntity.STATUS_2);
			
			rMerDao.unfrozen(rMer);
		}		
	}

	@Override
	public void importInput(MerTermImportInput importInput) {
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		ValidateUtils.validates(importInput);
		try {
			String mcr = importInput.getMcr();
			Mcr m = commonDao.getMcr(mcr);
			if(m == null){
				throw new BusinessException("商户来源不存在");
			}
			
			List<String[]> merList = ExcelUtils.excel2List(importInput.getFile().getInputStream());
			
			int len = merList.size();
			
			if (len == 0) {
				throw new BusinessException("商户记录不能为空");
			}
			
			for (int i = 0; i < len; i++) {
				String[] ss = merList.get(i);
				int sslen = ss.length;
				if (sslen < 2) {
					throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的格式不正确"));
				} else if (sslen >= 2) {
					
					//最大取2个
					sslen = 2;
				}
				String mid = ss[0];
				String name = ss[1];
				
				if (StringUtils.isBlank(mid)) {
					throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户号为空"));
				} else {
					if (!RegexUtils.validate("^[A-Za-z0-9]{15}+$", mid)) {
						throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户号格式不正确"));
					}
				}
				
				if (StringUtils.isBlank(name)) {
					throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户名称为空"));
				} else {
					if (!RegexUtils.validate(
						"^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z0-9\\(\\)（）]{2,40}$", name)) {
						throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户名称式不正确"));
					}
				}
				
				
				RMer tempMer = rMerDao.get(mcr,mid);
				//Mcr mcr = mcrDao.getById(mcr_id);
				if (tempMer != null) {
					throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户号已经存在"));
				} else {
					RMer rMer = new RMer();
					rMer.setMcr(mcr);
					rMer.setMid(mid);
					rMer.setName(name);
					rMer.setDepid(String.valueOf(WebUtils.getUser().getOrg().getId()));
					rMer.setBuildoper(importInput.getBuildoper());
					rMer.setBuilddatetime_short(importInput.getBuilddatetime());
					rMer.setStatus(BaseEntity.STATUS_2);
					rMer.setAuditstatus(BaseEntity.STATUS_2);
					rMerDao.save(rMer);
				}
				
			}
		} catch (BusinessException e) {
			throw e;
		} catch (InvalidOperationException e) {
			throw new BusinessException("请上传excel表格文件");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("系统异常");
		}		
	}

	@Override
	@Transactional
	public void delete(String[] ids) {
		for (String id : ids) {
			
			RMer rMer = getRMer(id);
			
			if(!BaseEntity.STATUS_0.equals(rMer.getStatus())){
				throw new BusinessException("只能删除未启用的记录");
			}
			
			rMer.setStatus(BaseEntity.STATUS_3);
			
			rMerDao.delete(rMer);
		}		
	}


}
