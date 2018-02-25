package com.pax.busi.resourcemgr.service;


import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.busi.common.dao.CommonDao;
import com.pax.busi.common.entity.Mcr;
import com.pax.busi.resourcemgr.dao.CposMerDao;
import com.pax.busi.resourcemgr.dao.CposTermDao;
import com.pax.busi.resourcemgr.dao.RMerDao;
import com.pax.busi.resourcemgr.dao.RTermDao;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.busi.resourcemgr.entity.CposTerm;
import com.pax.busi.resourcemgr.input.CposTermAddInput;
import com.pax.busi.resourcemgr.input.MerTermImportInput;
import com.pax.busi.resourcemgr.webservice.ObjectFactory;
import com.pax.busi.resourcemgr.webservice.REQELEM;
import com.pax.busi.resourcemgr.webservice.RESELEM;
import com.pax.busi.resourcemgr.webservice.STUPDATETMKREQ;
import com.pax.busi.resourcemgr.webservice.STUPDATETMKRES;
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
public class CposTermServiceImpl extends BaseService implements CposTermService{

	protected static final Log	logger	= LogFactory.getLog(CposTermServiceImpl.class);

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
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(CposTermAddInput input) {
		CposTerm cposTerm = new CposTerm();
		addConvert(input, cposTerm);
		cposTermDao.save(cposTerm);
	}

	private void addConvert(CposTermAddInput addInput, CposTerm cposTerm) {
		String mcr = addInput.getMcr();
		Mcr m = commonDao.getMcr(mcr);
		if(m == null){
			throw new BusinessException("商户来源不存在");
		}
		String mid = addInput.getMid();
		CposMer cposMer = cposMerDao.get(mcr, mid);
		if(cposMer == null){
			throw new BusinessException("商户号不存在");
		}
		String tid = addInput.getTid();
		CposTerm tmp = cposTermDao.get(mcr,mid,tid);
		if (tmp != null) {
			throw new BusinessException("终端号重复");
		} else {
			cposTerm.setMer(cposMer);
			cposTerm.setTid(tid);
			cposTerm.setDepid(String.valueOf(WebUtils.getUser().getOrg().getId()));
			cposTerm.setBuildoper(WebUtils.getUser().getLoginname());
			cposTerm.setBuilddatetime_short(DateUtils.getCurrentDateString());
			cposTerm.setStatus(BaseEntity.STATUS_0);
			cposTerm.setAuditstatus(BaseEntity.STATUS_0);
		}
				
	}

	
	@Transactional
	private CposTerm get(String id) {
		String[] ids = id.split("-");
		String mcr = ids[0];
		String mid = ids[1];
		String tid = ids[2];
		
		List<CposTerm> list = listAll();
		
		CposTerm cposTerm = cposTermDao.get(mcr,mid,tid);
		
		
		if(cposTerm == null){
			throw new BusinessException("接入终端不存在");
		}
		if (list.contains(cposTerm)) {
			return cposTerm;
		} else {
			throw new BusinessException("你没有权限操作该记录");
		}
		
	}

	@Override
	@Transactional
	public CposTerm detail(String id) {
		CposTerm cposTerm = get(id);
		return cposTerm;
	}

	@Override
	public PageInfo<CposTerm> list(PageQueryParam pageQueryParam) {
		
		initializeFiltering(pageQueryParam);
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<CposTerm> cposTerms = cposTermDao.list(pageQueryParam);
		
		PageInfo<CposTerm> pageInfo = new PageInfo<CposTerm>(cposTerms);
		
		return pageInfo;
	}

	@Override
	@Transactional
	public void delete(String[] ids) {
		for (String id : ids) {
			
			CposTerm cposTerm = get(id);
			
			if(!BaseEntity.STATUS_0.equals(cposTerm.getStatus())){
				throw new BusinessException("只能删除未启用的记录");
			}
			
			cposTerm.setStatus(BaseEntity.STATUS_3);
			
			cposTermDao.delete(cposTerm);
		}		
	}

	@Override
	@Transactional
	public void audit(String id, String audit) {
			CposTerm cposTerm = get(id);
			
			CposMer cposMer = cposTerm.getMer();
			
			if (!BaseEntity.STATUS_2.equals(cposMer.getStatus())) {
				throw new BusinessException("商户未启用，不能进行审核");
			}
			
			//审核通过
			if (BaseEntity.STATUS_1.equals(audit)) {
				if (BaseEntity.STATUS_2.equals(cposTerm.getAuditstatus())) {
					throw new BusinessException("该记录已经是审核通过状态");
				}
				cposTerm.setPassStatus();
			} else {
				if (BaseEntity.STATUS_1.equals(cposTerm.getAuditstatus())) {
					throw new BusinessException("该记录已经是审核不通过状态");
				}
				if (BaseEntity.STATUS_2.equals(cposTerm.getAuditstatus())) {
					throw new BusinessException("该记录已经是审核通过状态");
				}
				cposTerm.setAuditstatus(BaseEntity.STATUS_1);
			}
			
			cposTerm.setAuditoper(WebUtils.getUser().getLoginname());
			cposTerm.setAuditdatetime_short(DateUtils.getCurrentDateString());
			cposTermDao.audit(cposTerm);
	}

	@Override
	@Transactional
	public void frozen(String[] ids) {
		for (String id : ids) {
			
			CposTerm cposTerm = get(id);
			if (!BaseEntity.STATUS_2.equals(cposTerm.getStatus())) {
				throw new BusinessException("该记录不能冻结");
			}
			
			cposTerm.setStatus(BaseEntity.STATUS_1);
			cposTermDao.frozen(cposTerm);
		}
	}

	@Override
	@Transactional
	public void unfrozen(String[] ids) {
		for (String id : ids) {
			
			CposTerm cposTerm = get(id);
			if (!BaseEntity.STATUS_1.equals(cposTerm.getStatus())) {
				throw new BusinessException("该记录不能解冻");
			}
			
			cposTerm.setStatus(BaseEntity.STATUS_2);
			cposTermDao.unfrozen(cposTerm);
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
				CposMer tempMer = cposMerDao.get(mcr, mid);
				if (tempMer == null) {
					throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的商户号不存在"));
				} else {
					
					CposTerm tempTerm = cposTermDao.get(mcr, mid, tid);
					if (tempTerm != null) {
						throw new BusinessException(translation.__("第") + (i + 1) + translation.__("条记录的终端号已经存在"));
					} else {
						CposTerm cposTerm = new CposTerm();
						cposTerm.setMer(tempMer);
						cposTerm.setTid(tid);
						cposTerm.setDepid(tempMer.getDepid());
						cposTerm.setBuildoper(importInput.getBuildoper());
						cposTerm.setBuilddatetime_short(importInput.getBuilddatetime());
						cposTerm.setStatus(BaseEntity.STATUS_2);
						cposTerm.setAuditstatus(BaseEntity.STATUS_2);
						cposTermDao.save(cposTerm);
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
	public List<CposTerm> listAll() {
		
		PageQueryParam pageQueryParam = new PageQueryParam();
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
		
		pageQueryParam.setFilterMap(filterMap);
		
		initializeFiltering(pageQueryParam);
		
		List<CposTerm> list = cposTermDao.list(pageQueryParam);
		
		return list;
	}

	@Override
	public void createKey(String[] ids) {
		com.pax.busi.resourcemgr.webservice.Service service =
				new com.pax.busi.resourcemgr.webservice.Service();
		ObjectFactory factory = new ObjectFactory();
		STUPDATETMKREQ req = factory.createSTUPDATETMKREQ();
		for (String string : ids) {
			String[] id = string.split("-");
			String mcr = id[0];
			String mid = id[1];
			String tid = id[2];
			REQELEM ele = new REQELEM();
			ele.setSzMcr(mcr);
			ele.setSzMid(mid);
			ele.setSzTid(tid);
			CposTerm term = get(string);
			if (!StringUtils.equals(term.getStatus(), "2")) {
				WebUtils.responseFailString("只有启用状态的终端才能生成密钥");
				return;
			}
			ele.setSzSerialNo("");
			req.getItem().add(ele);
			
			List<REQELEM> item = req.getItem();
			
			ServicePortType portType = service.getService();
			STUPDATETMKRES response = portType.updateTMK(req);
			List<RESELEM> rsps = response.getItem();
			StringBuffer msgBuffer = new StringBuffer();
			for (int i = 0; i < rsps.size(); i++) {
				if (rsps.get(i).getIResCode() == 0) {
					msgBuffer.append("终端" + item.get(i).getSzTid() + "密钥生成成功<br/>");
				} else {
					msgBuffer.append("终端" + item.get(i).getSzTid() + rsps.get(i).getSzResInfo()
										+ "<br/>");
				}
			}
			WebUtils.responseSuccessString(msgBuffer.toString(), null);
			
		}
	}

	@Override
	public void exportKey(String[] ids) {
		List<CposTerm> cposterms = null;
		if(ids != null){
			int maxi = ids.length;
			cposterms = new ArrayList<CposTerm>(maxi);
			for (String id : ids) {
				CposTerm term = get(id);
				cposterms.add(term);
			}
		}else{
			cposterms = cposTermDao.listAll();
					
		}
		List<String> list = new LinkedList<String>();
		if (cposterms != null) {
			for (CposTerm c : cposterms) {
				if (StringUtils.isEmpty(c.getTmk())) {
					continue;
				}
				String s = "\"" + this.addBlank(c.getMer().getMid(), 15) + "\",\""
							+ this.addBlank(c.getTid(), 8) + "\",\""
							+ this.addBlank(c.getDepid(), 8) + "\",\""
							+ this.addBlank(c.getTmk(), 48) + "\",\""
							+ this.addBlank(c.getTmk(), 48) + "\"\r\n";
				list.add(s);
			}
		}
		InputStream	keysStream = getTxtInputStream(list);
		String fileName = "keys_" + DateUtils.getCurrentDateString()+ ".txt";
		try {

            HttpServletResponse response = WebUtils.getResponse();
            HttpServletRequest request = WebUtils.getRequest();

            response.setContentType("application/text;charset=utf-8");
            response.setCharacterEncoding("UTF-8");

            String userAgent = request.getHeader("User-Agent");
            //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                //非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            //解决中文乱码
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"",
                    fileName));
            int len = 0;
            byte [] buffer  = new byte[3];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((len = keysStream.read(buffer)) != -1)
            {
                baos.write(buffer, 0,  len);
            }
            byte[] bytes = baos.toByteArray();
            response.setHeader("Content-Length", String.valueOf(bytes.length));
            BufferedOutputStream bos = null;
            bos = new BufferedOutputStream(response.getOutputStream());
            bos.write(bytes);
            bos.close();
            baos.close();

        } catch (Exception e) {
            throw new BusinessException("文件下载失败：" + e.getMessage());
        }
		
		
	}
	
	
	private InputStream getTxtInputStream(List<String> list) {
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s);
		}
		byte[] content = sb.toString().getBytes();
		InputStream is = new ByteArrayInputStream(content);
		return is;
	}
	
	private String addBlank(String str, int len) {
		if (StringUtils.isEmpty(str)) {
			StringBuilder rstr = new StringBuilder();
			for (int i = 0; i < len; i++) {
				rstr.append(" ");
			}
			return rstr.toString();
		} else {
			if (str.length() == len) {
				return str;
			} else {
				StringBuilder rstr = new StringBuilder(str);
				for (int i = str.length(); i < len; i++) {
					rstr.append(" ");
				}
				return rstr.toString();
			}
		}
	}
	
	@Override
	@Transactional
	public void resetKey(String id) {
		CposTerm cposTerm = get(id);
		cposTerm.setGentmk("1");
		cposTermDao.resetKey(cposTerm);
	}

	


}
