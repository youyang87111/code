package com.pax.busi.mapmgr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.busi.common.dao.CommonDao;
import com.pax.busi.common.entity.AppClass;
import com.pax.busi.common.entity.BankInfo;
import com.pax.busi.common.entity.Branch;
import com.pax.busi.common.entity.CardType;
import com.pax.busi.common.entity.Mmf;
import com.pax.busi.mapmgr.dao.BranchMapDao;
import com.pax.busi.mapmgr.dao.MerMapDao;
import com.pax.busi.mapmgr.entity.BranchMapView;
import com.pax.busi.mapmgr.entity.MerMap;
import com.pax.busi.mapmgr.input.MerMapAddInput;
import com.pax.busi.mapmgr.input.MerMapUpdateInput;
import com.pax.busi.resourcemgr.dao.CposMerDao;
import com.pax.busi.resourcemgr.dao.RMerDao;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.core.entity.BaseEntity;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.BeanUtils;
import com.pax.core.util.DateUtils;
import com.pax.core.util.RegexUtils;
import com.pax.core.util.TranslationUtils;
import com.pax.core.util.WebUtils;

@Service
public class MerMapServiceImpl implements MerMapService {
	
	@Resource
	private MerMapDao		merMapDao;
	
	@Resource
	private BranchMapDao	branchMapDao;
	
	@Resource
	private CposMerDao		cposMerDao;
	
	@Resource
	private RMerDao			rMerDao;
	
	@Resource
	private CommonDao		commonDao;
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(MerMapAddInput input) {
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		Branch lbranch = commonDao.getBranch(input.getLbid());
		if (lbranch == null) {
			throw new BusinessException("接入渠道不存在");
		}
		
		AppClass appClass = commonDao.getAppClass(input.getCls());
		if (appClass == null) {
			throw new BusinessException("交易类别不存在");
		}
		
		Branch rbranch = commonDao.getBranch(input.getRbid());
		if (rbranch == null) {
			throw new BusinessException("转出渠道不存在");
		}
		
		CposMer cposMer = null;
		if (StringUtils.isNotBlank(input.getLmid())) {
			cposMer = cposMerDao.get(lbranch.getMcr(), input.getLmid());
			
			if (cposMer == null) {
				throw new BusinessException("接入商户不存在");
			}
		}
		
		Mmf mmf = commonDao.getMmf(input.getMmf());
		if (mmf == null) {
			throw new BusinessException("映射模式不存在");
		}
		
		//查看渠道映射mode存在不，先使用商户个性化去查询
		BranchMapView branchMapMode = branchMapDao.getBranchMapMode(input.getLbid(),
			input.getLmid(), input.getCls());
		
		if (branchMapMode == null) {
			//不用商户个性化再去查询
			branchMapMode = branchMapDao.getBranchMapMode(input.getLbid(), "", input.getCls());
		}
		
		if (branchMapMode == null) {
			throw new BusinessException("渠道映射不存在，请先添加渠道映射");
		} else {
			
			List<BranchMapView> branchMaps = null;
			//看branchMapMode是否是个性化
			String lmid = branchMapMode.getLmid();
			if (StringUtils.isNotBlank(lmid)) {
				if (!lmid.equals(input.getLmid())) {
					throw new BusinessException("渠道映射不存在，请先添加渠道映射");
				} else {
					//再查看具体映射表中，是否存在lbid+class--->rbid的映射
					branchMaps = branchMapDao.getBranchMapsByRbid(input.getLbid(), input.getLmid(),
						input.getCls(), input.getRbid());
				}
			} else {
				
				//再查看具体映射表中，是否存在lbid+class--->rbid的映射
				branchMaps = branchMapDao.getBranchMapsByRbid(input.getLbid(), "", input.getCls(),
					input.getRbid());
			}
			
			if (branchMaps.isEmpty()) {
				throw new BusinessException("渠道映射不存在，请先添加渠道映射");
			} else {
				//查看审核通过没得
				for (BranchMapView branchMap : branchMaps) {
					if (!branchMap.getStatus().equals("2")) {
						throw new BusinessException("渠道映射未启用");
					}
				}
			}
			
		}
		
		//先查看T_B_MERMAP_MODE表中是否已经存在映射记录
		MerMap merMapMode = merMapDao.getMerMapMode(input.getLbid(), input.getLmid(),
			input.getCls(), input.getRbid());
		if (merMapMode == null) {
			//不存在，就新建，保存
			merMapMode = createNewMerMapMode(lbranch.getMcr(), input);
			merMapDao.saveMerMapMode(merMapMode);
		} else {
			
			throw new BusinessException("映射已经存在");
			
		}
		
		MerMap merMap = new MerMap();
		
		if (MerMap.MMF_OTO.equals(input.getMmf())) {
			
			RMer rmer = null;
			if (StringUtils.isNotBlank(input.getRmid())) {
				rmer = rMerDao.get(rbranch.getMcr(), input.getRmid());
				if (rmer == null) {
					throw new BusinessException("转出商户不存在");
				}
				
			} else {
				throw new BusinessException("转出商户号不能为空");
			}
			
			merMap.setRmid(input.getRmid());
			
		} else if (MerMap.MMF_CARDTYPE.equals(input.getMmf())) {
			
			if (StringUtils.isBlank(input.getC_cardType1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getC_rmid1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			
			for (int i = 1; i <= 5; i++) {
				try {
					String cardTypeId = (String) BeanUtils.forceGetProperty(input,
						"c_cardType" + i);
					String rmid = (String) BeanUtils.forceGetProperty(input, "c_rmid" + i);
					
					if (StringUtils.isNotBlank(cardTypeId) && StringUtils.isNotBlank(rmid)) {
						
						if (map.containsKey(cardTypeId)) {
							throw new BusinessException("卡不能重复");
						} else {
							map.put(cardTypeId, cardTypeId);
						}
						
						CardType cardType = commonDao.getCardType(cardTypeId);
						if (cardType == null) {
							throw new BusinessException("卡片类型不存在");
						} else {
							BeanUtils.forceSetProperty(merMap, "cardtype" + i, cardTypeId);
						}
						
						RMer rmer = rMerDao.get(rbranch.getMcr(), rmid);
						if (rmer == null) {
							throw new BusinessException(
									translation.__("转出渠道:") + rbranch.getName() + translation.__(",商户号:") + rmid + translation.__("的组合不存在"));
						} else {
							BeanUtils.forceSetProperty(merMap, "rmid" + i, rmid);
						}
					}
					
				} catch (NoSuchFieldException e) {
					throw new BusinessException("系统异常");
				}
			}
			
			if (map.containsKey("N")) {
				if (map.size() != 1) {
					throw new BusinessException("所有类型卡和其他卡类型不能同时存在");
				}
			}
			
		} else if (MerMap.MMF_AMT.equals(input.getMmf())) {
			if (StringUtils.isBlank(input.getA_amt11())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getA_amt12())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getA_rmid1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			
			List<Integer> amtList = new ArrayList<Integer>();
			
			for (int i = 1; i <= 5; i++) {
				try {
					String amt1 = (String) BeanUtils.forceGetProperty(input, "a_amt" + i + "1");
					String amt2 = (String) BeanUtils.forceGetProperty(input, "a_amt" + i + "2");
					String rmid = (String) BeanUtils.forceGetProperty(input, "a_rmid" + i);
					
					if (StringUtils.isNotBlank(amt1)	&& StringUtils.isNotBlank(amt2)
						&& StringUtils.isNotBlank(rmid)) {
						if (!RegexUtils.validate("^[0-9]{1,7}$", amt1)) {
							throw new BusinessException("开始金额格式不正确，格式：最多7位正整数");
						}
						if (!RegexUtils.validate("^[0-9]{1,7}$", amt2)) {
							throw new BusinessException("结束金额格式不正确，格式：最多7位正整数");
						}
						if (Integer.parseInt(amt1) >= Integer.parseInt(amt2)) {
							throw new BusinessException("结束金额必须大于开始金额");
						}
						
						amtList.add(Integer.parseInt(amt1));
						amtList.add(Integer.parseInt(amt2));
						
						BeanUtils.forceSetProperty(merMap, "amt" + i + "1", amt1);
						BeanUtils.forceSetProperty(merMap, "amt" + i + "2", amt2);
						
						RMer rmer = rMerDao.get(rbranch.getMcr(), rmid);
						if (rmer == null) {
							throw new BusinessException(
									translation.__("转出渠道:") + translation.__(rbranch.getName().replace("[", "").replace("]", "")) + translation.__(",商户号:") + rmid + translation.__("的组合不存在"));	
						} else {
							BeanUtils.forceSetProperty(merMap, "rmid" + i, rmid);
						}
					}
					
				} catch (NoSuchFieldException e) {
					throw new BusinessException("系统异常");
				}
			}
			
			if (!checkAmts(amtList)) {
				throw new BusinessException("金额分段中有交集");
			}
			
			Object[] intArray = amtList.toArray();
			
			// 先排序
			sort(intArray);
			
			Integer first = (Integer) intArray[0];
			
			if (first != 0) {
				throw new BusinessException("金额分段中必须包含开始金额0");
			}
			
			if (!checkAmtsIsContinuous(intArray)) {
				throw new BusinessException("金额分段不连续");
			}
			
		} else if (MerMap.MMF_CARDTYPEAMT.equals(input.getMmf())) {
			
			if (StringUtils.isBlank(input.getCa_cardType1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getCa_amt11())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getCa_amt12())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getCa_rmid1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			
			Map<String, List<Integer>> cardMap = new HashMap<String, List<Integer>>();
			
			for (int i = 1; i <= 5; i++) {
				try {
					String cardTypeId = (String) BeanUtils.forceGetProperty(input,
						"ca_cardType" + i);
					String amt1 = (String) BeanUtils.forceGetProperty(input, "ca_amt" + i + "1");
					String amt2 = (String) BeanUtils.forceGetProperty(input, "ca_amt" + i + "2");
					String rmid = (String) BeanUtils.forceGetProperty(input, "ca_rmid" + i);
					
					if (StringUtils.isNotBlank(cardTypeId)	&& StringUtils.isNotBlank(amt1)
						&& StringUtils.isNotBlank(amt2) && StringUtils.isNotBlank(rmid)) {
						
						CardType cardType = commonDao.getCardType(cardTypeId);
						if (cardType == null) {
							throw new BusinessException("卡片类型不存在");
						} else {
							BeanUtils.forceSetProperty(merMap, "cardtype" + i, cardTypeId);
						}
						
						if (!RegexUtils.validate("^[0-9]{1,7}$", amt1)) {
							throw new BusinessException("开始金额格式不正确，格式：最多7位正整数");
						}
						if (!RegexUtils.validate("^[0-9]{1,7}$", amt2)) {
							throw new BusinessException("结束金额格式不正确，格式：最多7位正整数");
						}
						if (Integer.parseInt(amt1) >= Integer.parseInt(amt2)) {
							throw new BusinessException("结束金额必须大于开始金额");
						}
						
						BeanUtils.forceSetProperty(merMap, "amt" + i + "1", amt1);
						BeanUtils.forceSetProperty(merMap, "amt" + i + "2", amt2);
						
						if (cardMap.containsKey(cardTypeId)) {
							cardMap.get(cardTypeId).add(Integer.parseInt(amt1));
							cardMap.get(cardTypeId).add(Integer.parseInt(amt2));
						} else {
							List<Integer> list = new ArrayList<Integer>();
							list.add(Integer.parseInt(amt1));
							list.add(Integer.parseInt(amt2));
							cardMap.put(cardTypeId, list);
						}
						
						RMer rmer = rMerDao.get(rbranch.getMcr(), rmid);
						if (rmer == null) {
							throw new BusinessException(
									translation.__("转出渠道:") + translation.__(rbranch.getName().replace("[", "").replace("]", "")) + translation.__(",商户号:") + rmid + translation.__("的组合不存在"));
						} else {
							BeanUtils.forceSetProperty(merMap, "rmid" + i, rmid);
						}
					}
					
				} catch (NoSuchFieldException e) {
					throw new BusinessException("系统异常");
				}
			}
			
			if (cardMap.containsKey("N")) {
				if (cardMap.size() != 1) {
					throw new BusinessException("所有类型卡和其他卡类型不能同时存在");
				}
			}
			
			for (Map.Entry<String, List<Integer>> entry : cardMap.entrySet()) {
				if (!checkAmts(entry.getValue())) {
					throw new BusinessException("同一种卡的金额分段中有交集");
				}
				
				Object[] intArray = entry.getValue().toArray();
				
				// 先排序
				sort(intArray);
				
				Integer first = (Integer) intArray[0];
				
				if (first != 0) {
					throw new BusinessException("同一种卡，金额分段中必须包含开始金额0");
				}
				
				if (!checkAmtsIsContinuous(intArray)) {
					throw new BusinessException("同一种卡的金额分段不连续");
				}
			}
			
		} else if (MerMap.MMF_ISSUER_CARDTYPE.equals(input.getMmf())) {
			
			if (StringUtils.isBlank(input.getIc_issuerid1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getIc_cardType1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getIc_rmid1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			
			Map<String, Map<String, String>> cardMap = new HashMap<String, Map<String, String>>();
			
			for (int i = 1; i <= 5; i++) {
				try {
					String issuerid = (String) BeanUtils.forceGetProperty(input, "ic_issuerid" + i);
					String cardTypeId = (String) BeanUtils.forceGetProperty(input,
						"ic_cardType" + i);
					
					String rmid = (String) BeanUtils.forceGetProperty(input, "ic_rmid" + i);
					
					if (StringUtils.isNotBlank(issuerid)	&& StringUtils.isNotBlank(cardTypeId)
						&& StringUtils.isNotBlank(rmid)) {
						
						BankInfo bankInfo = commonDao.getBank(issuerid);
						if (bankInfo == null) {
							throw new BusinessException("发卡行不存在");
						}
						
						BeanUtils.forceSetProperty(merMap, "issuerid" + i, issuerid);
						
						CardType cardType = commonDao.getCardType(cardTypeId);
						if (cardType == null) {
							throw new BusinessException("卡片类型不存在");
						} else {
							BeanUtils.forceSetProperty(merMap, "cardtype" + i, cardTypeId);
						}
						
						if (cardMap.containsKey(issuerid)) {
							Map<String, String> map = cardMap.get(issuerid);
							if (map.containsKey(cardTypeId)) {
								throw new BusinessException("同一个发卡行中的卡不能重复");
							} else {
								map.put(cardTypeId, cardTypeId);
							}
						} else {
							Map<String, String> map = new HashMap<String, String>();
							map.put(cardTypeId, cardTypeId);
							cardMap.put(issuerid, map);
						}
						
						RMer rmer = rMerDao.get(rbranch.getMcr(), rmid);
						if (rmer == null) {
							throw new BusinessException(
									translation.__("转出渠道:") + translation.__(rbranch.getName().replace("[", "").replace("]", "")) + translation.__(",商户号:") + rmid + translation.__("的组合不存在"));
						} else {
							BeanUtils.forceSetProperty(merMap, "rmid" + i, rmid);
						}
					}
					
				} catch (NoSuchFieldException e) {
					throw new BusinessException("系统异常");
				}
			}
			
			for (Map.Entry<String, Map<String, String>> entry : cardMap.entrySet()) {
				Map<String, String> map = entry.getValue();
				if (map.containsKey("N")) {
					if (map.size() != 1) {
						throw new BusinessException("所有类型卡和其他卡类型不能同时存在");
					}
				}
			}
			
		}
		
		merMap.setLbid(input.getLbid());
		
		merMap.setLmcr(lbranch.getMcr());
		merMap.setLmid(input.getLmid());
		
		merMap.setCls(input.getCls());
		
		merMap.setMmf(mmf.getId());
		
		merMap.setRbid(input.getRbid());
		
		merMap.setRmcr(rbranch.getMcr());
		
		merMap.setBuildoper(input.getBuildoper());
		merMap.setBuilddatetime_short(input.getBuilddatetime());
		merMap.setStatus(BaseEntity.STATUS_0);
		merMap.setAuditstatus(BaseEntity.STATUS_0);
		
		merMapDao.save(merMap);
		
	}
	
	private MerMap createNewMerMapMode(String lmcr, MerMapAddInput addInput) {
		
		MerMap merMapMode = new MerMap();
		
		merMapMode.setLbid(addInput.getLbid());
		
		merMapMode.setLmcr(lmcr);
		merMapMode.setLmid(addInput.getLmid());
		
		merMapMode.setCls(addInput.getCls());
		
		merMapMode.setMmf(addInput.getMmf());
		
		merMapMode.setRbid(addInput.getRbid());
		
		merMapMode.setBuildoper(addInput.getBuildoper());
		merMapMode.setBuilddatetime_short(addInput.getBuilddatetime());
		
		merMapMode.setStatus(BaseEntity.STATUS_0);
		merMapMode.setAuditstatus(BaseEntity.STATUS_0);
		
		return merMapMode;
	}
	
	//检查金额是否存在交集
	private boolean checkAmts(List<Integer> amtList) {
		
		int len = amtList.size();
		
		for (int i = 0; i < len;) {
			
			int b1 = amtList.get(i);
			int e1 = amtList.get(i + 1);
			
			for (int j = i + 2; j < len;) {
				int b2 = amtList.get(j);
				int e2 = amtList.get(j + 1);
				if (b1 > b2 && b1 >= e2) {
					j = j + 2;
					continue;
				}
				if (b1 < b2 && e1 <= b2) {
					j = j + 2;
					continue;
				}
				
				return false;
				
			}
			
			i = i + 2;
		}
		
		return true;
	}
	
	//对金额进行排序
	private void sort(Object[] objects) {
		
		int len = objects.length;
		
		for (int i = 0; i < len - 1; i++) {
			
			for (int j = i + 1; j < len; j++) {
				int a = (Integer) objects[i];
				int b = (Integer) objects[j];
				if (a > b) {
					Object tmp = objects[i];
					objects[i] = objects[j];
					objects[j] = tmp;
				}
				
			}
			
		}
	}
	
	//检查金额分段是否连续
	private boolean checkAmtsIsContinuous(Object[] objects) {
		int len = objects.length;
		for (int i = 1; i < len - 2;) {
			int a = (Integer) objects[i];
			int b = (Integer) objects[i + 1];
			if (a != b) {
				return false;
			}
			i = i + 2;
		}
		
		return true;
		
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(MerMapUpdateInput input) {
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		MerMap merMapBase = get(input.getId());
		
		String mmf = merMapBase.getMmf();
		
		if ("2".equals(merMapBase.getStatus())) {
			throw new BusinessException("该记录处于启用状态，不能被修改");
		}
		
		if (MerMap.MMF_OTO.equals(mmf)) {
			
			RMer rmer = null;
			if (StringUtils.isNotBlank(input.getRmid())) {
				rmer = rMerDao.get(merMapBase.getRmcr(), input.getRmid());
				if (rmer == null) {
					throw new BusinessException("转出商户不存在");
				}
				
			} else {
				throw new BusinessException("转出商户号不能为空");
			}
			
			try {
				BeanUtils.forceSetProperty(merMapBase, "rmid", input.getRmid());
			} catch (NoSuchFieldException e) {
				throw new BusinessException("系统异常");
			}
			
		} else if (MerMap.MMF_CARDTYPE.equals(mmf)) {
			
			if (StringUtils.isBlank(input.getC_cardType1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getC_rmid1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			
			for (int i = 1; i <= 5; i++) {
				try {
					String cardTypeId = (String) BeanUtils.forceGetProperty(input,
						"c_cardType" + i);
					String rmid = (String) BeanUtils.forceGetProperty(input, "c_rmid" + i);
					
					if (StringUtils.isNotBlank(cardTypeId) && StringUtils.isNotBlank(rmid)) {
						
						if (map.containsKey(cardTypeId)) {
							throw new BusinessException("卡不能重复");
						} else {
							map.put(cardTypeId, cardTypeId);
						}
						
						CardType cardType = commonDao.getCardType(cardTypeId);
						if (cardType == null) {
							throw new BusinessException("卡片类型不存在");
						} else {
							BeanUtils.forceSetProperty(merMapBase, "cardtype" + i, cardTypeId);
						}
						
						RMer rmer = rMerDao.get(merMapBase.getRbid(), rmid);
						if (rmer == null) {
							throw new BusinessException(
									translation.__("转出渠道:") + translation.__(merMapBase.getRbid_name().replace("[", "").replace("]", "")) + translation.__(",商户号:") + rmid + translation.__("的组合不存在"));
						} else {
							BeanUtils.forceSetProperty(merMapBase, "rmid" + i, rmid);
						}
					} else {
						BeanUtils.forceSetProperty(merMapBase, "cardtype" + i, null);
						BeanUtils.forceSetProperty(merMapBase, "rmid" + i, null);
					}
					
				} catch (NoSuchFieldException e) {
					throw new BusinessException("系统异常");
				}
			}
			
			if (map.containsKey("N")) {
				if (map.size() != 1) {
					throw new BusinessException("所有类型卡和其他卡类型不能同时存在");
				}
			}
			
		} else if (MerMap.MMF_AMT.equals(mmf)) {
			if (StringUtils.isBlank(input.getA_amt11())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getA_amt12())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getA_rmid1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			
			List<Integer> amtList = new ArrayList<Integer>();
			
			for (int i = 1; i <= 5; i++) {
				try {
					String amt1 = (String) BeanUtils.forceGetProperty(input, "a_amt" + i + "1");
					String amt2 = (String) BeanUtils.forceGetProperty(input, "a_amt" + i + "2");
					String rmid = (String) BeanUtils.forceGetProperty(input, "a_rmid" + i);
					
					if (StringUtils.isNotBlank(amt1)	&& StringUtils.isNotBlank(amt2)
						&& StringUtils.isNotBlank(rmid)) {
						if (!RegexUtils.validate("^[0-9]{1,7}$", amt1)) {
							throw new BusinessException("开始金额格式不正确，格式：最多7位正整数");
						}
						if (!RegexUtils.validate("^[0-9]{1,7}$", amt2)) {
							throw new BusinessException("结束金额格式不正确，格式：最多7位正整数");
						}
						if (Integer.parseInt(amt1) >= Integer.parseInt(amt2)) {
							throw new BusinessException("结束金额必须大于开始金额");
						}
						
						amtList.add(Integer.parseInt(amt1));
						amtList.add(Integer.parseInt(amt2));
						
						BeanUtils.forceSetProperty(merMapBase, "amt" + i + "1", amt1);
						BeanUtils.forceSetProperty(merMapBase, "amt" + i + "2", amt2);
						
						RMer rmer = rMerDao.get(merMapBase.getRbid(), rmid);
						if (rmer == null) {
							throw new BusinessException(
									translation.__("转出渠道:") + translation.__(merMapBase.getRbid_name().replace("[", "").replace("]", "")) + translation.__(",商户号:") + rmid + translation.__("的组合不存在"));
						} else {
							BeanUtils.forceSetProperty(merMapBase, "rmid" + i, rmid);
						}
					} else {
						BeanUtils.forceSetProperty(merMapBase, "amt" + i + "1", null);
						BeanUtils.forceSetProperty(merMapBase, "amt" + i + "2", null);
						BeanUtils.forceSetProperty(merMapBase, "rmid" + i, null);
					}
					
				} catch (NoSuchFieldException e) {
					throw new BusinessException("系统异常");
				}
			}
			
			if (!checkAmts(amtList)) {
				throw new BusinessException("金额分段中有交集");
			}
			
			Object[] intArray = amtList.toArray();
			
			// 先排序
			sort(intArray);
			
			Integer first = (Integer) intArray[0];
			
			if (first != 0) {
				throw new BusinessException("金额分段中必须包含开始金额0");
			}
			
			if (!checkAmtsIsContinuous(intArray)) {
				throw new BusinessException("金额分段不连续");
			}
			
		} else if (MerMap.MMF_CARDTYPEAMT.equals(mmf)) {
			
			if (StringUtils.isBlank(input.getCa_cardType1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getCa_amt11())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getCa_amt12())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getCa_rmid1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			
			Map<String, List<Integer>> cardMap = new HashMap<String, List<Integer>>();
			
			for (int i = 1; i <= 5; i++) {
				try {
					String cardTypeId = (String) BeanUtils.forceGetProperty(input,
						"ca_cardType" + i);
					String amt1 = (String) BeanUtils.forceGetProperty(input, "ca_amt" + i + "1");
					String amt2 = (String) BeanUtils.forceGetProperty(input, "ca_amt" + i + "2");
					String rmid = (String) BeanUtils.forceGetProperty(input, "ca_rmid" + i);
					
					if (StringUtils.isNotBlank(cardTypeId)	&& StringUtils.isNotBlank(amt1)
						&& StringUtils.isNotBlank(amt2) && StringUtils.isNotBlank(rmid)) {
						
						CardType cardType = commonDao.getCardType(cardTypeId);
						if (cardType == null) {
							throw new BusinessException("卡片类型不存在");
						} else {
							BeanUtils.forceSetProperty(merMapBase, "cardtype" + i, cardTypeId);
						}
						
						if (!RegexUtils.validate("^[0-9]{1,7}$", amt1)) {
							throw new BusinessException("开始金额格式不正确，格式：最多7位正整数");
						}
						if (!RegexUtils.validate("^[0-9]{1,7}$", amt2)) {
							throw new BusinessException("结束金额格式不正确，格式：最多7位正整数");
						}
						if (Integer.parseInt(amt1) >= Integer.parseInt(amt2)) {
							throw new BusinessException("结束金额必须大于开始金额");
						}
						
						BeanUtils.forceSetProperty(merMapBase, "amt" + i + "1", amt1);
						BeanUtils.forceSetProperty(merMapBase, "amt" + i + "2", amt2);
						
						if (cardMap.containsKey(cardTypeId)) {
							cardMap.get(cardTypeId).add(Integer.parseInt(amt1));
							cardMap.get(cardTypeId).add(Integer.parseInt(amt2));
						} else {
							List<Integer> list = new ArrayList<Integer>();
							list.add(Integer.parseInt(amt1));
							list.add(Integer.parseInt(amt2));
							cardMap.put(cardTypeId, list);
						}
						
						RMer rmer = rMerDao.get(merMapBase.getRbid(), rmid);
						if (rmer == null) {
							throw new BusinessException(
									translation.__("转出渠道:") + translation.__(merMapBase.getRbid_name().replace("[", "").replace("]", "")) + translation.__(",商户号:") + rmid + translation.__("的组合不存在"));
						} else {
							BeanUtils.forceSetProperty(merMapBase, "rmid" + i, rmid);
						}
					} else {
						BeanUtils.forceSetProperty(merMapBase, "cardtype" + i, null);
						BeanUtils.forceSetProperty(merMapBase, "amt" + i + "1", null);
						BeanUtils.forceSetProperty(merMapBase, "amt" + i + "2", null);
						BeanUtils.forceSetProperty(merMapBase, "rmid" + i, null);
					}
					
				} catch (NoSuchFieldException e) {
					throw new BusinessException("系统异常");
				}
			}
			
			if (cardMap.containsKey("N")) {
				if (cardMap.size() != 1) {
					throw new BusinessException("所有类型卡和其他卡类型不能同时存在");
				}
			}
			
			for (Map.Entry<String, List<Integer>> entry : cardMap.entrySet()) {
				if (!checkAmts(entry.getValue())) {
					throw new BusinessException("同一种卡的金额分段中有交集");
				}
				
				Object[] intArray = entry.getValue().toArray();
				
				// 先排序
				sort(intArray);
				
				Integer first = (Integer) intArray[0];
				
				if (first != 0) {
					throw new BusinessException("同一种卡，金额分段中必须包含开始金额0");
				}
				
				if (!checkAmtsIsContinuous(intArray)) {
					throw new BusinessException("同一种卡的金额分段不连续");
				}
			}
			
		} else if (MerMap.MMF_ISSUER_CARDTYPE.equals(mmf)) {
			
			if (StringUtils.isBlank(input.getIc_issuerid1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getIc_cardType1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			if (StringUtils.isBlank(input.getIc_rmid1())) {
				throw new BusinessException("映射1中的元素不能为空");
			}
			
			Map<String, Map<String, String>> cardMap = new HashMap<String, Map<String, String>>();
			
			for (int i = 1; i <= 5; i++) {
				try {
					String issuerid = (String) BeanUtils.forceGetProperty(input, "ic_issuerid" + i);
					String cardTypeId = (String) BeanUtils.forceGetProperty(input,
						"ic_cardType" + i);
					
					String rmid = (String) BeanUtils.forceGetProperty(input, "ic_rmid" + i);
					
					if (StringUtils.isNotBlank(issuerid)	&& StringUtils.isNotBlank(cardTypeId)
						&& StringUtils.isNotBlank(rmid)) {
						
						BankInfo bankInfo = commonDao.getBank(issuerid);
						if (bankInfo == null) {
							throw new BusinessException("发卡行不存在");
						}
						
						BeanUtils.forceSetProperty(merMapBase, "issuerid" + i, issuerid);
						
						CardType cardType = commonDao.getCardType(cardTypeId);
						if (cardType == null) {
							throw new BusinessException("卡片类型不存在");
						} else {
							BeanUtils.forceSetProperty(merMapBase, "cardtype" + i, cardTypeId);
						}
						
						if (cardMap.containsKey(issuerid)) {
							Map<String, String> map = cardMap.get(issuerid);
							if (map.containsKey(cardTypeId)) {
								throw new BusinessException("同一个发卡行中的卡不能重复");
							} else {
								map.put(cardTypeId, cardTypeId);
							}
						} else {
							Map<String, String> map = new HashMap<String, String>();
							map.put(cardTypeId, cardTypeId);
							cardMap.put(issuerid, map);
						}
						
						RMer rmer = rMerDao.get(merMapBase.getRmcr(), rmid);
						if (rmer == null) {
							throw new BusinessException(
									translation.__("转出渠道:") + translation.__(merMapBase.getRbid_name().replace("[", "").replace("]", "")) + translation.__(",商户号:") + rmid + translation.__("的组合不存在"));
						} else {
							BeanUtils.forceSetProperty(merMapBase, "rmid" + i, rmid);
						}
					} else {
						BeanUtils.forceSetProperty(merMapBase, "issuerid" + i, null);
						BeanUtils.forceSetProperty(merMapBase, "cardtype" + i, null);
						BeanUtils.forceSetProperty(merMapBase, "rmid" + i, null);
					}
					
				} catch (NoSuchFieldException e) {
					throw new BusinessException("系统异常");
				}
			}
			
			for (Map.Entry<String, Map<String, String>> entry : cardMap.entrySet()) {
				Map<String, String> map = entry.getValue();
				if (map.containsKey("N")) {
					if (map.size() != 1) {
						throw new BusinessException("所有类型卡和其他卡类型不能同时存在");
					}
				}
			}
		}
		
		merMapBase.setModifyoper(input.getModifyoper());
		merMapBase.setModifydatetime_short(input.getModifydatetime());
		
		merMapDao.update(merMapBase);
	}
	
	@Override
	public PageInfo<MerMap> list(PageQueryParam pageQueryParam) {
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<MerMap> list = merMapDao.list(pageQueryParam);
		
		PageInfo<MerMap> pageInfo = new PageInfo<MerMap>(list);
		
		return pageInfo;
	}
	
	@Override
	public MerMap get(String id) {
		String[] ss = id.split("-");
		String lbid = ss[0];
		String lmid = ss[1];
		String cls = ss[2];
		String rbid = ss[3];
		String mmf = ss[4];
		
		MerMap merMap = merMapDao.get(lbid, lmid, cls, rbid, mmf);
		merMap.setMmf(mmf);
		merMap.setMmf_name(commonDao.getMmf(mmf).getName());
		
		return merMap;
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void frozen(String[] ids) {
		for (String id : ids) {
			
			String[] ss = id.split("-");
			String lbid = ss[0];
			String lmid = ss[1];
			String cls = ss[2];
			String rbid = ss[3];
			String mmf = ss[4];
			
			MerMap merMapMode = merMapDao.getMerMapMode(lbid, lmid, cls, rbid);
			
			if (!BaseEntity.STATUS_2.equals(merMapMode.getStatus())) {
				throw new BusinessException("该记录不能冻结");
			}
			
			merMapDao.frozenMerMapMode(lbid, lmid, cls, rbid);
			merMapDao.frozen(lbid, lmid, cls, rbid, mmf);
			
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void unfrozen(String[] ids) {
		for (String id : ids) {
			
			String[] ss = id.split("-");
			String lbid = ss[0];
			String lmid = ss[1];
			String cls = ss[2];
			String rbid = ss[3];
			String mmf = ss[4];
			
			MerMap merMapMode = merMapDao.getMerMapMode(lbid, lmid, cls, rbid);
			
			if (!BaseEntity.STATUS_1.equals(merMapMode.getStatus())) {
				throw new BusinessException("该记录不能解冻");
			}
			
			merMapDao.unfrozenMerMapMode(lbid, lmid, cls, rbid);
			merMapDao.unfrozen(lbid, lmid, cls, rbid, mmf);
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(String[] ids) {
		for (String id : ids) {
			
			String[] ss = id.split("-");
			String lbid = ss[0];
			String lmid = ss[1];
			String cls = ss[2];
			String rbid = ss[3];
			String mmf = ss[4];
			
			MerMap merMapMode = merMapDao.getMerMapMode(lbid, lmid, cls, rbid);
			
			if (!BaseEntity.STATUS_0.equals(merMapMode.getStatus())) {
				throw new BusinessException("只能删除未启用的记录");
			}
			
			merMapDao.deleteMerMapMode(lbid, lmid, cls, rbid);
			merMapDao.delete(lbid, lmid, cls, rbid, mmf);
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
			String lbid = ss[0];
			String lmid = ss[1];
			String cls = ss[2];
			String rbid = ss[3];
			String mmf = ss[4];
			
			MerMap merMapMode = merMapDao.getMerMapMode(lbid, lmid, cls, rbid);
			
			// 审核通过
			if (BaseEntity.STATUS_1.equals(passStatu)) {
				if (BaseEntity.STATUS_2.equals(merMapMode.getAuditstatus())) {
					throw new BusinessException("所选记录中包含已经是审核通过状态");
				}
				filterMap.put("status", BaseEntity.STATUS_2);
				filterMap.put("auditstatus", BaseEntity.STATUS_2);
			} else {
				if (BaseEntity.STATUS_1.equals(merMapMode.getAuditstatus())) {
					throw new BusinessException("所选记录中包含已经是审核不通过状态");
				}
				if (BaseEntity.STATUS_2.equals(merMapMode.getAuditstatus())) {
					throw new BusinessException("所选记录中包含已经是审核通过状态");
				}
				filterMap.put("status", BaseEntity.STATUS_0);
				filterMap.put("auditstatus", BaseEntity.STATUS_1);
			}
			
			filterMap.put("auditoper", WebUtils.getUser().getName());
			filterMap.put("auditdatetime_short", DateUtils.getCurrentDateString());
			filterMap.put("lbid", lbid);
			filterMap.put("lmid", lmid);
			filterMap.put("cls", cls);
			filterMap.put("mmf", mmf);
			filterMap.put("rbid", rbid);
			
			merMapDao.auditMerMapMode(filterMap);
			merMapDao.audit(filterMap);
		}
	}
}
