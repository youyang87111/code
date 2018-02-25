package com.pax.busi.resourcemgr.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.ws.Holder;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.auth.entity.User;
import com.pax.busi.common.dao.CommonDao;
import com.pax.busi.common.entity.Mcr;
import com.pax.busi.resourcemgr.dao.CposMerDao;
import com.pax.busi.resourcemgr.dao.CposTermDao;
import com.pax.busi.resourcemgr.dao.RMerDao;
import com.pax.busi.resourcemgr.dao.RTermDao;
import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.busi.resourcemgr.entity.RTerm;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.busi.resourcemgr.input.RTermAddInput;
import com.pax.busi.resourcemgr.input.RTermUpdateInput;
import com.pax.busi.resourcemgr.webservice.ServicePortType;
import com.pax.core.entity.BaseEntity;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.DateUtils;
import com.pax.core.util.ExcelUtils;
import com.pax.core.util.RegexUtils;
import com.pax.core.util.TranslationUtils;
import com.pax.core.util.ValidateUtils;
import com.pax.core.util.WebUtils;


@Service
public class RTermServiceImpl extends BaseService implements RTermService{
	
	protected static final Log	logger	= LogFactory.getLog(RTermServiceImpl.class);
	
	@Resource
	private CposMerDao cposMerDao;
	
	@Resource
	private CposTermDao cposTermDao;
	
	@Resource 
	private RMerDao rMerDao;
	
	@Resource
	private RTermDao rTermDao;
	
	@Resource
	private CommonDao commonDao;
	
	@Override
	public PageInfo<RTerm> list(PageQueryParam pageQueryParam) {
		
		initializeFiltering(pageQueryParam);
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<RTerm> rTerms = rTermDao.list(pageQueryParam);
		
		PageInfo<RTerm> pageInfo = new PageInfo<RTerm>(rTerms);
		
		return pageInfo;
	}

	@Override
	public List<RTerm> listAll() {

		PageQueryParam pageQueryParam = new PageQueryParam();
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		
		pageQueryParam.setFilterMap(filterMap);
		
		initializeFiltering(pageQueryParam);
		
		List<RTerm> list = rTermDao.list(pageQueryParam);
		
		return list;
	}

	@Override
	public RTerm detail(String id) {
		RTerm rTerm = getRTerm(id);
		
		return rTerm;
	}

	private RTerm getRTerm(String id) {
		String[] ids = id.split("-");
		String mcr = ids[0];
		String mid = ids[1];
		String tid = ids[2];
		RTerm rTerm = rTermDao.get(mcr,mid,tid);
		List<RTerm> list = listAll();
		if(rTerm == null){
			throw new BusinessException("转出终端不存在");
		}
		if (list.contains(rTerm)) {
			return rTerm;
		} else {
			throw new BusinessException("你没有权限操作该记录");
		}
	}
	
	@Override
	@Transactional
	public void save(RTermAddInput input) {
		RTerm rTerm = new RTerm();
		addConvert(input, rTerm);
		rTermDao.save(rTerm);		
	}

	private void addConvert(RTermAddInput addInput, RTerm rTerm) {
		String mcr = addInput.getMcr();
		Mcr m = commonDao.getMcr(mcr);
		if(m == null){
			throw new BusinessException("商户来源不存在");
		}
		String mid = addInput.getMid();
		RMer rMer = rMerDao.get(mcr, mid);
		if(rMer == null){
			throw new BusinessException("商户号不存在");
		}
		String tid = addInput.getTid();
		
		RTerm tmp = rTermDao.get(mcr, mid, tid);
		if (tmp != null) {
			throw new BusinessException("终端号重复");
		} else {
			
			rTerm.setMer(rMer);
			rTerm.setTid(tid);
			rTerm.setDepid(rMer.getDepid());
			rTerm.setBuildoper(WebUtils.getUser().getLoginname());
			rTerm.setBuilddatetime_short(DateUtils.getCurrentDateString());
			rTerm.setStatus(BaseEntity.STATUS_0);
			rTerm.setAuditstatus(BaseEntity.STATUS_0);
		}
	}

	@Override
	@Transactional
	public void update(RTermUpdateInput updateInput) {
		RTerm rTerm = getRTerm(updateInput.getId());
		if (BaseEntity.STATUS_2.equals(rTerm.getStatus())) {
			throw new BusinessException("该记录处于启用状态，不能被修改");
		}
		rTerm.setModifyoper(updateInput.getModifyoper());
		rTerm.setModifydatetime_short(updateInput.getModifydatetime());
		rTermDao.update(rTerm);
	}

	@Override
	@Transactional
	public void audit(String id, String audit) {
		RTerm rTerm = getRTerm(id);
		
		RMer rMer = rTerm.getMer();
		
		if (!BaseEntity.STATUS_2.equals(rMer.getStatus())) {
			throw new BusinessException("商户未启用，不能进行审核");
		}
		
		//审核通过
		if (BaseEntity.STATUS_1.equals(audit)) {
			if (BaseEntity.STATUS_2.equals(rTerm.getAuditstatus())) {
				throw new BusinessException("该记录已经是审核通过状态");
			}
			rTerm.setPassStatus();
		} else {
			if (BaseEntity.STATUS_1.equals(rTerm.getAuditstatus())) {
				throw new BusinessException("该记录已经是审核不通过状态");
			}
			if (BaseEntity.STATUS_2.equals(rTerm.getAuditstatus())) {
				throw new BusinessException("该记录已经是审核通过状态");
			}
			rTerm.setAuditstatus(BaseEntity.STATUS_1);
		}
		
		rTerm.setAuditoper(WebUtils.getUser().getLoginname());
		rTerm.setAuditdatetime_short(DateUtils.getCurrentDateString());
		rTermDao.audit(rTerm);
	}

	@Override
	@Transactional
	public void frozen(String[] ids) {
		for (String id : ids) {
			RTerm rTerm = getRTerm(id);
			
			if (!BaseEntity.STATUS_2.equals(rTerm.getStatus())) {
				throw new BusinessException("该记录不能冻结");
			}
			
			rTerm.setStatus(BaseEntity.STATUS_1);
			rTermDao.frozen(rTerm);
		}

	}

	@Override
	@Transactional
	public void unfrozen(String[] ids) {
		for (String id : ids) {
			RTerm rTerm = getRTerm(id);
			
			if (!BaseEntity.STATUS_1.equals(rTerm.getStatus())) {
				throw new BusinessException("该记录不能解冻");
			}
			
			rTerm.setStatus(BaseEntity.STATUS_2);
			rTermDao.unfrozen(rTerm);
		}
	}

	@Override
	@Transactional
	public void delete(String[] ids) {
		for (String id : ids) {
			
			RTerm rTerm = getRTerm(id);
			
			if(!BaseEntity.STATUS_0.equals(rTerm.getStatus())){
				throw new BusinessException("只能删除未启用的记录");
			}
			
			rTerm.setStatus(BaseEntity.STATUS_3);
			
			rTermDao.delete(rTerm);
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
				String tid = ss[1];
				
				if (StringUtils.isBlank(mid)) {
					throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户号为空"));
				} else {
					if (!RegexUtils.validate("^[A-Za-z0-9]{15}+$", mid)) {
						throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户号格式不正确"));
					}
				}
				
				if (StringUtils.isBlank(tid)) {
					throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的终端号为空"));
				} else {
					if (!RegexUtils.validate("^[A-Za-z0-9]{8}+$", tid)) {
						throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的终端号格式不正确"));
					}
				}
				RMer tempMer = rMerDao.get(mcr, mid);
				if (tempMer == null) {
					throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户号不存在"));
				} else {
					
					RTerm tempTerm = rTermDao.get(mcr, mid, tid);
					if (tempTerm != null) {
						throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的终端号已经存在"));
					} else {
						RTerm rTerm = new RTerm();
						rTerm.setMer(tempMer);
						rTerm.setTid(tid);
						rTerm.setDepid(tempMer.getDepid());
						rTerm.setBuildoper(importInput.getBuildoper());
						rTerm.setBuilddatetime_short(importInput.getBuilddatetime());
						rTerm.setStatus(BaseEntity.STATUS_2);
						rTerm.setAuditstatus(BaseEntity.STATUS_2);
						rTermDao.save(rTerm);
					}
					
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
	public void exchangeKey() {
		com.pax.busi.resourcemgr.webservice.Service service =
				new com.pax.busi.resourcemgr.webservice.Service();
		ServicePortType portType = service.getService();
		Holder<Integer> iResCode = new Holder<Integer>();
		Holder<String> szResInfo = new Holder<String>();
		portType.tmkCvt(0, iResCode, szResInfo);
		// 非0表示后台异常
		if (iResCode.value != 0) {
			logger.error("返回码:" + iResCode.value + " 操作详情:" + szResInfo.value);
			throw new BusinessException("转换未成功,请联系管理员");
		}
	}

	@Override
	public String importKeys(List<String> list, String mcr, User user) {
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
        RTerm termq = new RTerm();
		int cntfail = 0;

        for (String s : list) {

            String[] ss = s.split(",");

            if (ss.length != 3) {
                cntfail++;
                continue;
            }
            if (StringUtils.isEmpty(ss[0]) || StringUtils.isEmpty(ss[1]) || StringUtils.isEmpty(ss[2])) {
                cntfail++;
                continue;
            }
            
            RMer rMer = rMerDao.get(mcr, ss[0].substring(1, ss[0].length()-1));
           /* if(rMer == null){
            	throw new BusinessException("导入商户不存在");
            }*/
            String pk = mcr + ss[0].substring(1, ss[0].length()-1) + ss[1].substring(1, ss[1].length()-1);
            termq.setMer(rMer);
            termq.setPk(pk);
            String mid = ss[0].substring(1, ss[0].length()-1);
            String tid = ss[1].trim().substring(1, ss[1].trim().length()-1);
            RTerm rt = rTermDao.get(mcr, mid ,tid);
            if (rt != null) {
                rt.setTmk(ss[2].trim().substring(1, ss[2].trim().length()-1));
                rTermDao.updateTmk(rt);
            } else {
                cntfail++;
                continue;
            }
        }
        return translation.__("导入总数：") + list.size() + translation.__(", 导入成功：") + (list.size() - cntfail);
	}

	@Override
	public List<String> parseFile(String file) throws Exception {
		String content_line = null;
		FileInputStream fis = null;
		BufferedReader br = null;
		List<String> list = new LinkedList<String>();
		try {
			fis = new FileInputStream(new File(file));
			InputStreamReader inputStreamReader = new InputStreamReader(fis, "ISO-8859-1");
			br = new BufferedReader(inputStreamReader);
			content_line = br.readLine();
			while (!StringUtils.isEmpty(content_line)) {
				list.add(content_line);
				content_line = br.readLine();
			}
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return list;
	}


}
