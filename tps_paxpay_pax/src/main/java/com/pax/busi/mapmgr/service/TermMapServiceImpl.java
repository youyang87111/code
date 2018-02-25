package com.pax.busi.mapmgr.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.busi.common.dao.CommonDao;
import com.pax.busi.common.entity.Branch;
import com.pax.busi.common.entity.Tmf;
import com.pax.busi.mapmgr.dao.MerMapDao;
import com.pax.busi.mapmgr.dao.TermMapDao;
import com.pax.busi.mapmgr.entity.MerMap;
import com.pax.busi.mapmgr.entity.TermMapView;
import com.pax.busi.mapmgr.input.TermMapAddInput;
import com.pax.busi.resourcemgr.dao.CposMerDao;
import com.pax.busi.resourcemgr.dao.CposTermDao;
import com.pax.busi.resourcemgr.dao.RMerDao;
import com.pax.busi.resourcemgr.dao.RTermDao;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.busi.resourcemgr.entity.CposTerm;
import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.busi.resourcemgr.entity.RTerm;
import com.pax.core.entity.BaseEntity;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.DateUtils;
import com.pax.core.util.WebUtils;

@Service
public class TermMapServiceImpl implements TermMapService {
	
	@Resource
	private TermMapDao	termMapDao;
	
	@Resource
	private CommonDao	commonDao;
	
	@Resource
	private CposMerDao	cposMerDao;
	
	@Resource
	private CposTermDao	cposTermDao;
	
	@Resource
	private RMerDao		rMerDao;
	
	@Resource
	private RTermDao	rTermDao;
	
	@Resource
	private MerMapDao	merMapDao;
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(TermMapAddInput input) {
		
		Branch lbranch = commonDao.getBranch(input.getLbid());
		if (lbranch == null) {
			throw new BusinessException("接入渠道不存在");
		}
		
		CposMer cposMer = cposMerDao.get(lbranch.getMcr(), input.getLmid());
		if (cposMer == null) {
			throw new BusinessException("接入商户不存在");
		}
		
		Branch rbranch = commonDao.getBranch(input.getRbid());
		if (rbranch == null) {
			throw new BusinessException("转出渠道不存在");
		}
		
		RMer rMer = rMerDao.get(rbranch.getMcr(), input.getRmid());
		if (rMer == null) {
			throw new BusinessException("转出商户不存在");
		}
		
		Tmf tmf = commonDao.getTmf(input.getTmf());
		if (tmf == null) {
			throw new BusinessException("映射模式不存在");
		}
		
		//查看商户映射模式是否存在
		List<MerMap> merMapModes = merMapDao.getMerMapModes(input.getLbid(), input.getLmid(),
			input.getRbid());
		if (merMapModes.isEmpty()) {
			throw new BusinessException("商户映射不存在，请先添加商户映射");
		} else {
			boolean flag = false;
			for (MerMap merMapMode : merMapModes) {
				MerMap merMap = merMapDao.get(input.getLbid(), input.getLmid(), merMapMode.getCls(),
					input.getRbid(), merMapMode.getMmf());
				if (merMap != null) {
					if (MerMap.MMF_OTO.equals(merMapMode.getMmf())) {
						if (merMap.getRmid().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
						}
					} else if (MerMap.MMF_CARDTYPE.equals(merMapMode.getMmf())) {
						if (merMap.getRmid1().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid2().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid3().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid4().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid5().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
					} else if (MerMap.MMF_AMT.equals(merMapMode.getMmf())) {
						
						if (merMap.getRmid1().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid2().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid3().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid4().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid5().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
					} else if (MerMap.MMF_CARDTYPEAMT.equals(merMapMode.getMmf())) {
						if (merMap.getRmid1().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid2().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid3().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid4().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid5().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
					} else if (MerMap.MMF_ISSUER_CARDTYPE.equals(merMapMode.getMmf())) {
						if (merMap.getRmid1().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid2().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid3().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid4().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
						if (merMap.getRmid5().equals(input.getRmid())) {
							if (merMap.getStatus().equals("2")) {
								flag = true;
								break;
							} else {
								throw new BusinessException("商户映射未启用");
							}
							
						}
					}
				}
			}
			
			if (!flag) {
				throw new BusinessException("商户映射不存在，请先添加商户映射");
			}
		}
		
		TermMapView termMapView = new TermMapView();
		
		termMapView.setLbid(lbranch.getId());
		termMapView.setLmcr(lbranch.getMcr());
		termMapView.setLmid(input.getLmid());
		termMapView.setRbid(rbranch.getId());
		termMapView.setRmcr(rbranch.getId());
		termMapView.setRmid(input.getRmid());
		termMapView.setTmf(tmf.getId());
		
		termMapView.setBuildoper(input.getBuildoper());
		termMapView.setBuilddatetime_short(input.getBuilddatetime());
		
		//一一映射的时候，设置mode表为审核通过状态
		if (TermMapView.TMF_OTO.equals(tmf.getId())) {
			termMapView.setStatus(BaseEntity.STATUS_2);
			termMapView.setAuditstatus(BaseEntity.STATUS_2);
		} else if (TermMapView.TMF_SUIJI.equals(tmf.getId())) {
			termMapView.setStatus(BaseEntity.STATUS_0);
			termMapView.setAuditstatus(BaseEntity.STATUS_0);
		}
		
		TermMapView tempTermMapMode = termMapDao.getTermMapMode(lbranch.getId(), input.getLmid(),
			rbranch.getId(), input.getRmid());
		
		if (tmf.getId().equals(TermMapView.TMF_OTO)) {
			
			if (tempTermMapMode == null) {
				termMapDao.saveTermMapMode(termMapView);
			}
			
			if (StringUtils.isBlank(input.getLtid())) {
				throw new BusinessException("接入终端不能为空");
			}
			if (StringUtils.isBlank(input.getRtid())) {
				throw new BusinessException("转出终端不能为空");
			}
			
			CposTerm cposTerm = cposTermDao.get(lbranch.getMcr(), input.getLmid(), input.getLtid());
			
			if (cposTerm == null) {
				throw new BusinessException("接入终端不存在");
			} else {
				termMapView.setLtid(input.getLtid());
			}
			
			RTerm rTerm = rTermDao.get(rbranch.getMcr(), input.getRmid(), input.getRtid());
			
			if (rTerm == null) {
				throw new BusinessException("转出终端不存在");
			} else {
				termMapView.setRtid(input.getRtid());
			}
			
			TermMapView tempTermMap = termMapDao.getTermMap(lbranch.getId(), lbranch.getMcr(),
				input.getLmid(), rbranch.getId(), rbranch.getMcr(), input.getRmid(),
				input.getLtid(), input.getRtid());
			
			if (tempTermMap != null) {
				throw new BusinessException("映射已经存在");
			}
			
			termMapView.setStatus(BaseEntity.STATUS_0);
			termMapView.setAuditstatus(BaseEntity.STATUS_0);
			
			termMapDao.save(termMapView);
			
		} else {
			
			if (tempTermMapMode == null) {
				termMapDao.saveTermMapMode(termMapView);
			} else {
				throw new BusinessException("映射已经存在");
			}
			
		}
	}
	
	@Override
	public PageInfo<TermMapView> list(PageQueryParam pageQueryParam) {
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<TermMapView> list = termMapDao.list(pageQueryParam);
		
		PageInfo<TermMapView> pageInfo = new PageInfo<TermMapView>(list);
		
		return pageInfo;
	}
	
	@Override
	public TermMapView get(String id) {
		
		String[] ss = id.split("-");
		
		String tmf = ss[0];
		
		String lbid = ss[1];
		String lmid = ss[2];
		String rbid = ss[3];
		String rmid = ss[4];
		String ltid = ss[5];
		String rtid = ss[6];
		
		TermMapView termMapView = termMapDao.get(lbid, lmid, ltid, rbid, rmid, rtid, tmf);
		
		Tmf tmfObj = commonDao.getTmf(tmf);
		
		termMapView.setTmf(tmf);
		termMapView.setTmf_name(tmfObj.getName());
		
		return termMapView;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void frozen(String[] ids) {
		for (String id : ids) {
			
			TermMapView termMapView = get(id);
			
			if (!BaseEntity.STATUS_2.equals(termMapView.getStatus())) {
				throw new BusinessException("该记录不能冻结");
			}
			
			termMapDao.frozen(termMapView);
			
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void unfrozen(String[] ids) {
		for (String id : ids) {
			
			TermMapView termMapView = get(id);
			
			if (!BaseEntity.STATUS_1.equals(termMapView.getStatus())) {
				throw new BusinessException("该记录不能解冻");
			}
			
			termMapDao.unfrozen(termMapView);
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(String[] ids) {
		for (String id : ids) {
			
			TermMapView termMapView = get(id);
			
			if (!BaseEntity.STATUS_0.equals(termMapView.getStatus())) {
				throw new BusinessException("只能删除未启用的记录");
			}
			
			termMapDao.delete(termMapView);
			
			//是一一映射，还要删除mode表
			if (TermMapView.TMF_OTO.equals(termMapView.getTmf())) {
				List<TermMapView> termMapList = termMapDao.getTermMaps(termMapView);
				if (CollectionUtils.isEmpty(termMapList)) {
					termMapDao.deleteTermMapMode(termMapView);
				}
			}
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void audit(Map<String, Object> filterMap) {
		
		String passStatu = filterMap.get("passStatu").toString();
		
		String idsStr = filterMap.get("ids").toString();
		String[] ids = idsStr.split(",");
		
		for (String id : ids) {
			
			String[] ss = id.split("-");
			
			String tmf = ss[0];
			
			String lbid = ss[1];
			String lmid = ss[2];
			String rbid = ss[3];
			String rmid = ss[4];
			String ltid = ss[5];
			String rtid = ss[6];
			
			filterMap.put("auditoper", WebUtils.getUser().getName());
			filterMap.put("auditdatetime_short", DateUtils.getCurrentDateString());
			
			filterMap.put("lbid", lbid);
			filterMap.put("lmid", lmid);
			filterMap.put("ltid", ltid);
			filterMap.put("rbid", rbid);
			filterMap.put("rmid", rmid);
			filterMap.put("rtid", rtid);
			filterMap.put("tmf", tmf);
			
			TermMapView termMapView = get(id);
			
			// 审核通过
			if (BaseEntity.STATUS_1.equals(passStatu)) {
				if (BaseEntity.STATUS_2.equals(termMapView.getAuditstatus())) {
					throw new BusinessException("所选记录中包含已经是审核通过状态");
				}
				
				filterMap.put("status", BaseEntity.STATUS_2);
				filterMap.put("auditstatus", BaseEntity.STATUS_2);
				
			} else {
				if (BaseEntity.STATUS_1.equals(termMapView.getAuditstatus())) {
					throw new BusinessException("所选记录中包含已经是审核不通过状态");
				}
				if (BaseEntity.STATUS_2.equals(termMapView.getAuditstatus())) {
					throw new BusinessException("所选记录中包含已经是审核通过状态");
				}
				filterMap.put("status", BaseEntity.STATUS_0);
				filterMap.put("auditstatus", BaseEntity.STATUS_1);
				
			}
			
			termMapDao.audit(filterMap);
			
		}
	}
}
