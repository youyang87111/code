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
import com.pax.busi.resourcemgr.dao.CposMerDao;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.busi.resourcemgr.input.CposMerAddInput;
import com.pax.busi.resourcemgr.input.CposMerUpdateInput;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
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
public class CposMerServiceImpl extends BaseService implements CposMerService{
	
	@Resource
	private CposMerDao cposMerDao;
	
	@Resource
	private CommonDao commonDao;
	
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(CposMerAddInput input) {
		CposMer cposMer = new CposMer();
		addConvert(input, cposMer);
		
		cposMerDao.save(cposMer);
	}

	private void addConvert(CposMerAddInput addInput, CposMer cposMer) {
		String mcr = addInput.getMcr();
		Mcr m = commonDao.getMcr(mcr);
		if(m == null){
			throw new BusinessException("商户来源不存在");
		}
		String mid = addInput.getMid();
		CposMer tmp = cposMerDao.get(mcr, mid);
		if (tmp != null) {
			throw new BusinessException("商户号重复");
		} else {
			
			cposMer.setMcr(mcr);
			cposMer.setMid(mid);
			cposMer.setName(addInput.getName());
			cposMer.setDepid(String.valueOf(WebUtils.getUser().getOrg().getId()));
			cposMer.setBuildoper(WebUtils.getUser().getLoginname());
			cposMer.setBuilddatetime_short(DateUtils.getCurrentDateString());
			cposMer.setStatus(BaseEntity.STATUS_0);
			cposMer.setAuditstatus(BaseEntity.STATUS_0);
		}
	}

	@Override
	public PageInfo<CposMer> list(PageQueryParam pageQueryParam) {
		initializeFiltering(pageQueryParam);
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<CposMer> cposMers = cposMerDao.list(pageQueryParam);
		
		PageInfo<CposMer> pageInfo = new PageInfo<CposMer>(cposMers);
		
		return pageInfo;
	}


	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(CposMerUpdateInput updateInput) {
		ValidatorUtils.validateWithException(updateInput);
		CposMer cposMer = getCposMer(updateInput.getId());
		if (BaseEntity.STATUS_2.equals(cposMer.getStatus())) {
			throw new BusinessException("该记录处于启用状态，不能被修改");
		}
		cposMer.setName(updateInput.getName());
		cposMer.setModifyoper(WebUtils.getUser().getLoginname());
		cposMer.setModifydatetime_short(DateUtils.getCurrentDateString());
		cposMerDao.update(cposMer);
	}


	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void audit(String id, String audit) {
			CposMer cposMer = getCposMer(id);
			if (BaseEntity.STATUS_1.equals(audit)) {
				if (BaseEntity.STATUS_2.equals(cposMer.getAuditstatus())) {
					throw new BusinessException("该记录已经是审核通过状态");
				}
				cposMer.setPassStatus();
			} else {
				if (BaseEntity.STATUS_1.equals(cposMer.getAuditstatus())) {
					throw new BusinessException("该记录已经是审核不通过状态");
				}
				if (BaseEntity.STATUS_2.equals(cposMer.getAuditstatus())) {
					throw new BusinessException("该记录已经是审核通过状态");
				}
				cposMer.setAuditstatus(BaseEntity.STATUS_1);
			}

			cposMer.setAuditoper(WebUtils.getUser().getLoginname());
			cposMer.setAuditdatetime_short(DateUtils.getCurrentDateString());
			cposMerDao.audit(cposMer);

		
	}


	

	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
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
						throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户名称格式不正确"));
					}
				}
				
				CposMer tmp = cposMerDao.get(mcr,mid);
				if (tmp != null) {
					throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户号已经存在"));
				} else {
					CposMer cposMer = new CposMer();
					cposMer.setMcr(mcr);
					cposMer.setMid(mid);
					cposMer.setName(name);
					cposMer.setDepid(String.valueOf(WebUtils.getUser().getOrg().getId()));
					cposMer.setBuildoper(importInput.getBuildoper());
					cposMer.setBuilddatetime_short(importInput.getBuilddatetime());
					cposMer.setStatus(BaseEntity.STATUS_2);
					cposMer.setAuditstatus(BaseEntity.STATUS_2);
					cposMerDao.save(cposMer);
				}
			}
		}catch (BusinessException e) {
				throw e;
		} catch (InvalidOperationException e) {
			throw new BusinessException("请上传excel表格文件");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("系统异常");
		}
	}


	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void frozen(String[] ids) {
		for (String id : ids) {

			CposMer cposMer = getCposMer(id);
			if (!BaseEntity.STATUS_2.equals(cposMer.getStatus())) {
				throw new BusinessException("该记录不能冻结");
			}
			cposMer.setStatus(BaseEntity.STATUS_1);
			cposMerDao.frozen(cposMer);
		}
	}
	

	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void unfrozen(String[] ids) {
		for (String id : ids) {
			CposMer cposMer = getCposMer(id);
			
			if (!BaseEntity.STATUS_1.equals(cposMer.getStatus())) {
				throw new BusinessException("该记录不能解冻");
			}
			
			cposMer.setStatus(BaseEntity.STATUS_2);
			
			cposMerDao.unfrozen(cposMer);
		}
				
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(String[] ids) {

		for (String id : ids) {
			
			CposMer cposMer = getCposMer(id);
			if(!BaseEntity.STATUS_0.equals(cposMer.getStatus())){
				throw new BusinessException("只能删除未启用的记录");
			}
			
			cposMer.setStatus(BaseEntity.STATUS_3);
			
			cposMerDao.delete(cposMer);
		}
	}

	@Override
	@Transactional
	public CposMer detail(String id) {
		CposMer cposMer = getCposMer(id);
		if(cposMer == null){
			throw new BusinessException("接入商户不存在");
		}
		return cposMer;
	}

	@Override
	public List<CposMer> listAll() {
		
		PageQueryParam pageQueryParam = new PageQueryParam();
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		
		pageQueryParam.setFilterMap(filterMap);
		
		initializeFiltering(pageQueryParam);
		
		List<CposMer> list = cposMerDao.list(pageQueryParam);
		
		return list;
	}
	
	@Transactional
	public CposMer getCposMer(String id){
		
		String[] ids = id.split("-");
		String mcr = ids[0];
		String mid = ids[1];
		
		List<CposMer> list = listAll();
		
		CposMer cposMer = cposMerDao.get(mcr,mid);
		
		if (cposMer == null) {
			throw new BusinessException("商户号不存在");
		}
		
		if (list.contains(cposMer)) {
			return cposMer;
		} else {
			throw new BusinessException("你没有权限操作该记录");
		}
		
	}
	
	

}
