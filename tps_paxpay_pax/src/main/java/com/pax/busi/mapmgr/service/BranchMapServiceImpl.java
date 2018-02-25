package com.pax.busi.mapmgr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pax.busi.common.dao.CommonDao;
import com.pax.busi.common.entity.AppClass;
import com.pax.busi.common.entity.BankInfo;
import com.pax.busi.common.entity.Bmf;
import com.pax.busi.common.entity.Branch;
import com.pax.busi.common.entity.CardType;
import com.pax.busi.mapmgr.dao.BranchMapDao;
import com.pax.busi.mapmgr.entity.BranchMapView;
import com.pax.busi.mapmgr.input.BranchMapAddInput;
import com.pax.busi.mapmgr.input.BranchMapUpdateInput;
import com.pax.busi.resourcemgr.dao.CposMerDao;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.core.entity.BaseEntity;
import com.pax.core.exception.BusinessException;
import com.pax.core.model.PageQueryParam;
import com.pax.core.util.DateUtils;
import com.pax.core.util.RegexUtils;
import com.pax.core.util.TranslationUtils;
import com.pax.core.util.WebUtils;

@Service
public class BranchMapServiceImpl implements BranchMapService {
	
	@Resource
	private BranchMapDao	branchMapDao;
	
	@Resource
	private CommonDao		commonDao;
	
	@Resource
	private CposMerDao		cposMerDao;
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void save(BranchMapAddInput input) {
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		String date = DateUtils.date2string(new Date(), DateUtils.LONG_FORMAT_YY);
		
		String lbid = input.getLbid();
		String merPersonalized = input.getMerPersonalized();
		String lmid = input.getLmid();
		String cls = input.getCls();
		String bmfStr = input.getBmf();
		
		Branch lbranch = commonDao.getBranch(lbid);
		
		if (lbranch == null) {
			throw new BusinessException("接入渠道不存在");
		}
		
		AppClass appClass = commonDao.getAppClass(cls);
		if (appClass == null) {
			throw new BusinessException("交易类别不存在");
		}
		
		if ("2".equals(merPersonalized)) {
			if (StringUtils.isBlank(lmid)) {
				throw new BusinessException("接入商户号不能为空");
			}
		}
		
		CposMer cposMer = null;
		if (StringUtils.isNotBlank(lmid)) {
			
			cposMer = cposMerDao.get(lbranch.getMcr(), lmid);
			
			if (cposMer == null) {
				throw new BusinessException("接入商户不存在");
			}
			
		}
		
		Bmf bmf = commonDao.getBmf(bmfStr);
		if (bmf == null) {
			throw new BusinessException("映射模式不存在");
		}
		
		//先查看T_B_BRANCHMAP_MODE表中是否已经存在映射记录
		BranchMapView branchMapMode = branchMapDao.getBranchMapMode(lbid, lmid, cls);
		if (branchMapMode == null) {
			//不存在，就新建，保存
			branchMapMode = createNewBranchMapMode(lbranch, lmid, cls, bmfStr, date, input);
			branchMapDao.saveBranchMapMode(branchMapMode);
		} else {
			if (!bmf.getId().equals(branchMapMode.getBmf())) {
				throw new BusinessException(
						translation.__("接入渠道（")				+ translation.__(lbranch.getName().replace("[","").replace("]", "")) + translation.__("）+商户号（") + lmid + translation.__("）+交易类别（") + translation.__( appClass.getName().replace("[","").replace("]", ""))
											+ translation.__("）已经映射成") + translation.__(branchMapMode.getBmf_name().replace("[","").replace("]", "")) + translation.__("模式，不能再映射成")
											+ translation.__(bmf.getName().replace("[","").replace("]", "")) + translation.__("模式"));
			}
		}
		
		BranchMapView branchMapView = null;
		
		String rid = getMaxRid(bmfStr, date);
		
		if (BranchMapView.BMF_OTO.equals(bmfStr)) {
			
			String rbid = input.getRbid_01();
			if (StringUtils.isBlank(rbid)) {
				throw new BusinessException("转出渠道不能为空");
			}
			
			Branch rbranch = commonDao.getBranch(rbid);
			if (rbranch == null) {
				throw new BusinessException("转出渠道不存在");
			}
			
			//先查看T_B_BRANCHMAP表中是否已经存在映射记录
			branchMapView = branchMapDao.getBranchMapView(bmfStr, lbid, lmid, cls, null, null, null,
				null);
			if (branchMapView == null) {
				branchMapView = new BranchMapView();
				branchMapView.setRbid(rbid);
			} else {
				throw new BusinessException("映射已经存在");
			}
			
		} else if (BranchMapView.BMF_CARDTYPE.equals(bmfStr)) {
			
			String rbid = input.getRbid_02();
			if (StringUtils.isBlank(rbid)) {
				throw new BusinessException("转出渠道不能为空");
			}
			
			Branch rbranch = commonDao.getBranch(rbid);
			if (rbranch == null) {
				throw new BusinessException("转出渠道不存在");
			}
			
			String cardTypeStr = input.getCardType_02();
			
			if (StringUtils.isBlank(cardTypeStr)) {
				throw new BusinessException("卡片类型不能为空");
			}
			
			CardType cardType = commonDao.getCardType(cardTypeStr);
			if (cardType == null) {
				throw new BusinessException("卡片类型不存在");
			}
			
			if ("N".equals(cardTypeStr)) {
				//N，代表所有卡，所以数据库就不能拥有其他类型的卡
				//查询不等于N的记录
				List<BranchMapView> branchMapCardTypeList = branchMapDao
					.getBranchMapCardTypesNotEq(lbid, lmid, cls, "N");
				if (CollectionUtils.isNotEmpty(branchMapCardTypeList)) {
					throw new BusinessException("已经有其他卡类型映射存在，不能选择所有类型卡进行映射");
				}
			} else {
				//查询等于N的记录
				List<BranchMapView> branchMapCardTypeList = branchMapDao
					.getBranchMapCardTypesEq(lbid, lmid, cls, "N");
				if (CollectionUtils.isNotEmpty(branchMapCardTypeList)) {
					throw new BusinessException("已经有所有类型卡映射存在，不能选择其他卡类型进行映射");
				}
			}
			
			//先查看T_B_BRANCHMAP_CARDTYPE表中是否已经存在映射记录
			branchMapView = branchMapDao.getBranchMapView(bmfStr, lbid, lmid, cls, cardTypeStr,
				null, null, null);
			if (branchMapView == null) {
				branchMapView = new BranchMapView();
				branchMapView.setCardtype(cardTypeStr);
				branchMapView.setRbid(rbid);
			} else {
				throw new BusinessException("映射已经存在");
			}
			
		} else if (BranchMapView.BMF_AMT.equals(bmfStr)) {
			
			String rbid = input.getRbid_03();
			if (StringUtils.isBlank(rbid)) {
				throw new BusinessException("转出渠道不能为空");
			}
			
			Branch rbranch = commonDao.getBranch(rbid);
			if (rbranch == null) {
				throw new BusinessException("转出渠道不存在");
			}
			String amt1 = input.getAmt1_03();
			String amt2 = input.getAmt2_03();
			if (StringUtils.isBlank(amt1)) {
				throw new BusinessException("开始金额不能为空");
			}
			if (StringUtils.isBlank(amt2)) {
				throw new BusinessException("结束金额不能为空");
			}
			if (!RegexUtils.validate("^[0-9]{1,7}$", amt1)) {
				throw new BusinessException("开始金额格式不正确");
			}
			if (!RegexUtils.validate("^[0-9]{1,7}$", amt2)) {
				throw new BusinessException("结束金额格式不正确");
			}
			
			if (Integer.parseInt(amt1) >= Integer.parseInt(amt2)) {
				throw new BusinessException("结束金额必须大于开始金额");
			}
			
			// ==================判断是否有交集=====================
			List<Integer> amtList = new ArrayList<Integer>();
			
			amtList.add(Integer.parseInt(amt1));
			amtList.add(Integer.parseInt(amt2));
			
			List<BranchMapView> list = branchMapDao.getBranchMapAmts(lbid, lmid, cls);
			for (BranchMapView branchMapAmt : list) {
				amtList.add(Integer.parseInt(branchMapAmt.getAmt1()));
				amtList.add(Integer.parseInt(branchMapAmt.getAmt2()));
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
			
			//先查看T_B_BRANCHMAP_amt表中是否已经存在映射记录
			branchMapView = branchMapDao.getBranchMapView(bmfStr, lbid, lmid, cls, null, amt1, amt2,
				null);
			if (branchMapView == null) {
				branchMapView = new BranchMapView();
				branchMapView.setAmt1(amt1);
				branchMapView.setAmt2(amt2);
				branchMapView.setRbid(rbid);
			} else {
				throw new BusinessException("映射已经存在");
			}
			
		} else if (BranchMapView.BMF_CARDTYPEAMT.equals(bmfStr)) {
			
			String rbid = input.getRbid_04();
			if (StringUtils.isBlank(rbid)) {
				throw new BusinessException("转出渠道不能为空");
			}
			
			Branch rbranch = commonDao.getBranch(rbid);
			if (rbranch == null) {
				throw new BusinessException("转出渠道不存在");
			}
			
			String cardTypeStr = input.getCardType_04();
			
			if (StringUtils.isBlank(cardTypeStr)) {
				throw new BusinessException("卡片类型不能为空");
			}
			
			CardType cardType = commonDao.getCardType(cardTypeStr);
			if (cardType == null) {
				throw new BusinessException("卡片类型不存在");
			}
			
			if ("N".equals(cardTypeStr)) {
				//N，代表所有卡，所以数据库就不能拥有其他类型的卡
				//查询不等于N的记录
				List<BranchMapView> branchMapCardTypeAmtList = branchMapDao
					.getBranchMapCardTypeAmtsNotEq(lbid, lmid, cls, "N");
				if (CollectionUtils.isNotEmpty(branchMapCardTypeAmtList)) {
					throw new BusinessException("已经有其他卡类型映射存在，不能选择所有类型卡进行映射");
				}
			} else {
				//查询等于N的记录
				List<BranchMapView> branchMapCardTypeAmtList = branchMapDao
					.getBranchMapCardTypeAmtsEq(lbid, lmid, cls, "N");
				if (CollectionUtils.isNotEmpty(branchMapCardTypeAmtList)) {
					throw new BusinessException("已经有所有类型卡映射存在，不能选择其他卡类型进行映射");
				}
			}
			
			String amt1 = input.getAmt1_04();
			String amt2 = input.getAmt2_04();
			
			if (StringUtils.isBlank(amt1)) {
				throw new BusinessException("开始金额不能为空");
			}
			if (StringUtils.isBlank(amt2)) {
				throw new BusinessException("结束金额不能为空");
			}
			if (!RegexUtils.validate("^[0-9]{1,7}$", amt1)) {
				throw new BusinessException("开始金额格式不正确");
			}
			if (!RegexUtils.validate("^[0-9]{1,7}$", amt2)) {
				throw new BusinessException("结束金额格式不正确");
			}
			
			if (Integer.parseInt(amt1) >= Integer.parseInt(amt2)) {
				throw new BusinessException("结束金额必须大于开始金额");
			}
			
			// ==================判断是否有交集=====================
			List<Integer> amtList = new ArrayList<Integer>();
			
			amtList.add(Integer.parseInt(amt1));
			amtList.add(Integer.parseInt(amt2));
			
			List<BranchMapView> list = branchMapDao.getBranchMapCardTypeAmtsEq(lbid, lmid, cls,
				cardTypeStr);
			for (BranchMapView branchMapCardTypeAmt : list) {
				amtList.add(Integer.parseInt(branchMapCardTypeAmt.getAmt1()));
				amtList.add(Integer.parseInt(branchMapCardTypeAmt.getAmt2()));
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
			
			//先查看T_B_BRANCHMAP_CARDTYPE_AMT表中是否已经存在映射记录
			branchMapView = branchMapDao.getBranchMapView(bmfStr, lbid, lmid, cls, cardTypeStr,
				amt1, amt2, null);
			if (branchMapView == null) {
				branchMapView = new BranchMapView();
				branchMapView.setCardtype(cardTypeStr);
				branchMapView.setAmt1(amt1);
				branchMapView.setAmt2(amt2);
				branchMapView.setRbid(rbid);
			} else {
				throw new BusinessException("映射已经存在");
			}
		} else if (BranchMapView.BMF_ISSUER.equals(bmfStr)) {
			
			String rbid = input.getRbid_06();
			if (StringUtils.isBlank(rbid)) {
				throw new BusinessException("转出渠道不能为空");
			}
			
			Branch rbranch = commonDao.getBranch(rbid);
			if (rbranch == null) {
				throw new BusinessException("转出渠道不存在");
			}
			
			String issuerid = input.getIssuerid_06();
			
			if (StringUtils.isBlank(issuerid)) {
				throw new BusinessException("发卡行不能为空");
			}
			
			BankInfo bankInfo = commonDao.getBank(issuerid);
			if (bankInfo == null) {
				throw new BusinessException("发卡行不存在");
			}
			
			//先查看T_B_BRANCHMAP_ISSUER表中是否已经存在映射记录
			branchMapView = branchMapDao.getBranchMapView(bmfStr, lbid, lmid, cls, null, null, null,
				issuerid);
			if (branchMapView == null) {
				branchMapView = new BranchMapView();
				branchMapView.setIssuerid(issuerid);
				branchMapView.setRbid(rbid);
			} else {
				throw new BusinessException("映射已经存在");
			}
		} else if (BranchMapView.BMF_ISSUER_CARDTYPE.equals(bmfStr)) {
			
			String rbid = input.getRbid_05();
			if (StringUtils.isBlank(rbid)) {
				throw new BusinessException("转出渠道不能为空");
			}
			
			Branch rbranch = commonDao.getBranch(rbid);
			if (rbranch == null) {
				throw new BusinessException("转出渠道不存在");
			}
			
			String issuerid = input.getIssuerid_05();
			
			if (StringUtils.isBlank(issuerid)) {
				throw new BusinessException("发卡行不能为空");
			}
			
			BankInfo bankInfo = commonDao.getBank(issuerid);
			if (bankInfo == null) {
				throw new BusinessException("发卡行不存在");
			}
			
			String cardTypeStr = input.getCardType_05();
			
			if (StringUtils.isBlank(cardTypeStr)) {
				throw new BusinessException("卡片类型不能为空");
			}
			
			CardType cardType = commonDao.getCardType(cardTypeStr);
			if (cardType == null) {
				throw new BusinessException("卡片类型不存在");
			}
			
			if ("N".equals(cardTypeStr)) {
				//N，代表所有卡，所以数据库就不能拥有其他类型的卡
				//查询不等于N的记录
				List<BranchMapView> branchMapIssuerCardTypeList = branchMapDao
					.getBranchMapIssuerCardTypesNotEq(lbid, lmid, cls, issuerid, "N");
				if (CollectionUtils.isNotEmpty(branchMapIssuerCardTypeList)) {
					throw new BusinessException("已经有其他卡类型映射存在，不能选择所有类型卡进行映射");
				}
			} else {
				//查询等于N的记录
				List<BranchMapView> branchMapIssuerCardTypeList = branchMapDao
					.getBranchMapIssuerCardTypesEq(lbid, lmid, cls, issuerid, "N");
				if (CollectionUtils.isNotEmpty(branchMapIssuerCardTypeList)) {
					throw new BusinessException("已经有所有类型卡映射存在，不能选择其他卡类型进行映射");
				}
			}
			
			//先查看T_B_BRANCHMAP_ISSUER_CARDTYPE表中是否已经存在映射记录
			branchMapView = branchMapDao.getBranchMapView(bmfStr, lbid, lmid, cls, cardTypeStr,
				null, null, issuerid);
			if (branchMapView == null) {
				branchMapView = new BranchMapView();
				branchMapView.setIssuerid(issuerid);
				branchMapView.setCardtype(cardTypeStr);
				branchMapView.setRbid(rbid);
			} else {
				throw new BusinessException("映射已经存在");
			}
		}
		
		branchMapView.setRid(date + rid);
		
		branchMapView.setLbid(lbid);
		
		branchMapView.setLmcr(lbranch.getMcr());
		if (StringUtils.isNotBlank(input.getLmid())) {
			branchMapView.setLmid(input.getLmid());
		}
		
		branchMapView.setCls(cls);
		
		branchMapView.setBmf(bmfStr);
		
		branchMapView.setBuildoper(input.getBuildoper());
		branchMapView.setBuilddatetime_short(input.getBuilddatetime());
		branchMapView.setStatus(BaseEntity.STATUS_0);
		branchMapView.setAuditstatus(BaseEntity.STATUS_0);
		
		branchMapDao.save(branchMapView);
		
	}
	
	private BranchMapView createNewBranchMapMode(	Branch lbranch, String lmid, String appClass,
													String bmf, String date,
													BranchMapAddInput addInput) {
		
		BranchMapView branchMapMode = new BranchMapView();
		
		String rid = branchMapDao.getMaxRidFromMode(date.substring(0, 6) + "____________");
		
		if (StringUtils.isBlank(rid)) {
			rid = "000001";
		}
		
		branchMapMode.setRid(date + rid);
		
		branchMapMode.setLbid(lbranch.getId());
		
		branchMapMode.setLmcr(lbranch.getMcr());
		if (StringUtils.isNotBlank(lmid)) {
			branchMapMode.setLmid(lmid);
		}
		
		branchMapMode.setCls(appClass);
		
		branchMapMode.setBmf(bmf);
		
		branchMapMode.setBuildoper(addInput.getBuildoper());
		branchMapMode.setBuilddatetime_short(addInput.getBuilddatetime());
		//直接让其变成启用和审核通过状态
		branchMapMode.setStatus(BaseEntity.STATUS_2);
		branchMapMode.setAuditstatus(BaseEntity.STATUS_2);
		
		return branchMapMode;
	}
	
	private String getMaxRid(String bmf, String sysdate) {
		
		String maxRid = branchMapDao.getMaxRid(bmf, sysdate.substring(0, 6) + "____________");
		
		if (StringUtils.isBlank(maxRid)) {
			return "000001";
		} else {
			
			if (maxRid.equals("999999")) {
				return "000001";
			}
			maxRid = StringUtils.leftPad(String.valueOf(Integer.parseInt(maxRid) + 1), 6, '0');
			return maxRid;
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
		
	};
	
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
	
	@Override
	public PageInfo<BranchMapView> list(PageQueryParam pageQueryParam) {
		
		PageHelper.startPage(pageQueryParam.getPageNo(), pageQueryParam.getPageSize(), true);
		
		List<BranchMapView> list = branchMapDao.list(pageQueryParam);
		
		PageInfo<BranchMapView> pageInfo = new PageInfo<BranchMapView>(list);
		
		return pageInfo;
	}
	
	@Override
	public BranchMapView get(String id) {
		String[] ids = id.split("-");
		return branchMapDao.get(ids[0]);
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void frozen(String[] ids) {
		for (String id : ids) {
			String[] ss = id.split("-");
			String rid = ss[0];
			
			BranchMapView branchMap = branchMapDao.get(rid);
			
			if (!BaseEntity.STATUS_2.equals(branchMap.getStatus())) {
				throw new BusinessException("该记录不能冻结");
			}
			
			String bmf = ss[1];
			
			branchMapDao.frozen(bmf, rid);
			
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void unfrozen(String[] ids) {
		for (String id : ids) {
			String[] ss = id.split("-");
			String rid = ss[0];
			
			BranchMapView branchMap = branchMapDao.get(rid);
			
			if (!BaseEntity.STATUS_1.equals(branchMap.getStatus())) {
				throw new BusinessException("该记录不能解冻");
			}
			
			String bmf = ss[1];
			
			branchMapDao.unfrozen(bmf, rid);
			
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(String[] ids) {
		for (String id : ids) {
			String[] ss = id.split("-");
			String rid = ss[0];
			
			BranchMapView branchMap = branchMapDao.get(rid);
			
			if (!BaseEntity.STATUS_0.equals(branchMap.getStatus())) {
				throw new BusinessException("只能删除未启用的记录");
			}
			
			String bmf = ss[1];
			
			branchMapDao.delete(bmf, rid);
			
			//查看对应表里面还有记录没得，没得记录了就删除对应T_B_BRANCHMAP_MODE表里面的记录
			List<BranchMapView> branchMapList = branchMapDao.getBranchMaps(branchMap.getLbid(),
				branchMap.getLmid(), branchMap.getCls());
			if (CollectionUtils.isEmpty(branchMapList)) {
				branchMapDao.deleteBranchMapMode(branchMap.getLbid(), branchMap.getLmid(),
					branchMap.getCls());
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
			String rid = ss[0];
			String bmf = ss[1];
			
			BranchMapView branchMap = branchMapDao.get(rid);
			
			// 审核通过
			if (BaseEntity.STATUS_1.equals(passStatu)) {
				if (BaseEntity.STATUS_2.equals(branchMap.getAuditstatus())) {
					throw new BusinessException("rid=" + rid + "的记录已经是审核通过状态");
				}
				filterMap.put("status", BaseEntity.STATUS_2);
				filterMap.put("auditstatus", BaseEntity.STATUS_2);
			} else {
				if (BaseEntity.STATUS_1.equals(branchMap.getAuditstatus())) {
					throw new BusinessException("rid=" + rid + "的记录已经是审核不通过状态");
				}
				if (BaseEntity.STATUS_2.equals(branchMap.getAuditstatus())) {
					throw new BusinessException("rid=" + rid + "的记录已经是审核通过状态");
				}
				filterMap.put("status", BaseEntity.STATUS_0);
				filterMap.put("auditstatus", BaseEntity.STATUS_1);
			}
			
			filterMap.put("auditoper", WebUtils.getUser().getName());
			filterMap.put("auditdatetime_short", DateUtils.getCurrentDateString());
			filterMap.put("id", rid);
			filterMap.put("bmf", bmf);
			
			branchMapDao.audit(filterMap);
			
		}
	}
	
	@Override
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public void update(BranchMapUpdateInput input) {
		
		String[] ss = input.getId().split("-");
		String rid = ss[0];
		String bmf = ss[1];
		
		BranchMapView branchMap = branchMapDao.get(rid);
		
		if (BaseEntity.STATUS_2.equals(branchMap.getStatus())) {
			throw new BusinessException("该记录处于启用状态，不能被修改");
		}
		
		String rbid = input.getRbid();
		
		Branch rBranch = commonDao.getBranch(rbid);
		
		if (rBranch == null) {
			throw new BusinessException("转出渠道不存在");
		}
		
		branchMapDao.update(bmf, rid, rbid);
		
	}
	
}
