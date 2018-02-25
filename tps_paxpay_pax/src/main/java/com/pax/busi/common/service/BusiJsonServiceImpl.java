package com.pax.busi.common.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.pax.busi.common.entity.AppClass;
import com.pax.busi.common.entity.BankInfo;
import com.pax.busi.common.entity.Bmf;
import com.pax.busi.common.entity.Branch;
import com.pax.busi.common.entity.CardType;
import com.pax.busi.common.entity.Inntransdef;
import com.pax.busi.common.entity.Language;
import com.pax.busi.common.entity.Mcr;
import com.pax.busi.common.entity.Mmf;
import com.pax.busi.common.entity.Tmf;
import com.pax.busi.mapmgr.entity.BranchMapView;
import com.pax.busi.mapmgr.entity.MerMap;
import com.pax.busi.mapmgr.entity.TermMapView;
import com.pax.busi.resourcemgr.entity.CposMer;
import com.pax.busi.resourcemgr.entity.CposTerm;
import com.pax.busi.resourcemgr.entity.RMer;
import com.pax.busi.resourcemgr.entity.RTerm;
import com.pax.busi.transreport.entity.Cposhislog;
import com.pax.busi.transreport.entity.CposhislogForExcel;
import com.pax.busi.transreport.entity.Cposhistrans;
import com.pax.busi.transreport.entity.CpostransForExcel;
import com.pax.busi.transreport.entity.RealtimeNotion;
import com.pax.core.util.DateUtils;
import com.pax.core.util.JSONUtils;
import com.pax.core.util.TranslationUtils;
import com.pax.core.util.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class BusiJsonServiceImpl implements BusiJsonService {
	
	@Resource
	private CommonService commonService;
	
	@Override
	public JSONArray getJSONArrayForMap(List<Map<String, String>> list) {
		JSONArray jsonArray = new JSONArray();
		for (Map<String, String> map : list) {
			JSONObject jsonObject = JSONObject.fromObject(map);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONArray getJSONArrayForAppClass(List<AppClass> list) {
		JSONArray jsonArray = new JSONArray();
		for (AppClass appClass : list) {
			JSONObject jsonObject = getJson(appClass);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(AppClass appClass) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(appClass, new String[] {});
		if (StringUtils.isNotBlank(appClass.getName())) {
			jsonObject.put("name", translation.__(appClass.getName()));
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForBankInfo(List<BankInfo> list) {
		JSONArray jsonArray = new JSONArray();
		for (BankInfo bankInfo : list) {
			JSONObject jsonObject = getJson(bankInfo);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(BankInfo bankInfo) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(bankInfo, new String[] {});
		if (StringUtils.isNotBlank(bankInfo.getName())) {
			jsonObject.put("name", translation.__(bankInfo.getName()));
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForBmf(List<Bmf> list) {
		JSONArray jsonArray = new JSONArray();
		for (Bmf bmf : list) {
			JSONObject jsonObject = getJson(bmf);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(Bmf bmf) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(bmf, new String[] {});
		if (StringUtils.isNotBlank(bmf.getName())) {
			jsonObject.put("name", translation.__(bmf.getName()));
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForBranch(List<Branch> list) {
		JSONArray jsonArray = new JSONArray();
		for (Branch branch : list) {
			JSONObject jsonObject = getJson(branch);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(Branch branch) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(branch, new String[] {});
		if (StringUtils.isNotBlank(branch.getName())) {
			jsonObject.put("name", translation.__(branch.getName()));
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForCardType(List<CardType> list) {
		JSONArray jsonArray = new JSONArray();
		for (CardType cardType : list) {
			JSONObject jsonObject = getJson(cardType);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(CardType cardType) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(cardType, new String[] {});
		if (StringUtils.isNotBlank(cardType.getName())) {
			jsonObject.put("name", translation.__(cardType.getName()));
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForInntransdef(List<Inntransdef> list) {
		JSONArray jsonArray = new JSONArray();
		for (Inntransdef inntransdef : list) {
			JSONObject jsonObject = getJson(inntransdef);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(Inntransdef inntransdef) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(inntransdef, new String[] {});
		if (StringUtils.isNotBlank(inntransdef.getName())) {
			jsonObject.put("name", translation.__(inntransdef.getName()));
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForMcr(List<Mcr> list) {
		JSONArray jsonArray = new JSONArray();
		for (Mcr mcr : list) {
			JSONObject jsonObject = getJson(mcr);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(Mcr mcr) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(mcr, new String[] {});
		if (StringUtils.isNotBlank(mcr.getName())) {
			jsonObject.put("name", translation.__(mcr.getName()));
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForMmf(List<Mmf> list) {
		JSONArray jsonArray = new JSONArray();
		for (Mmf mmf : list) {
			JSONObject jsonObject = getJson(mmf);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(Mmf mmf) {
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(mmf, new String[] {});
		if (StringUtils.isNotBlank(mmf.getName())) {
			jsonObject.put("name", translation.__(mmf.getName()));
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForTmf(List<Tmf> list) {
		JSONArray jsonArray = new JSONArray();
		for (Tmf tmf : list) {
			JSONObject jsonObject = getJson(tmf);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	public JSONObject getJson(Tmf tmf) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(tmf, new String[] {});
		if (StringUtils.isNotBlank(tmf.getName())) {
			jsonObject.put("name", translation.__(tmf.getName()));
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForCposMer(List<CposMer> list) {
		JSONArray jsonArray = new JSONArray();
		for (CposMer cposMer : list) {
			JSONObject jsonObject = getJson(cposMer);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(CposMer cposMer) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(cposMer, new String[] {});
		jsonObject.put("id", cposMer.getMcr() + "-" + cposMer.getMid());
		jsonObject.put("mcrName", translation.__(cposMer.getMcrName()));
		jsonObject.put("orgName", translation.__(cposMer.getOrgName()));
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForCposTerm(List<CposTerm> list) {
		JSONArray jsonArray = new JSONArray();
		for (CposTerm cposTerm : list) {
			JSONObject jsonObject = getJson(cposTerm);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(CposTerm cposTerm) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(cposTerm, new String[] {});
		jsonObject.put("id", cposTerm.getMer().getMcr()	+ "-" + cposTerm.getMer().getMid() + "-"
								+ cposTerm.getTid());
		if("null".equals(cposTerm.getPosition())){
			jsonObject.put("position","");
		}
		jsonObject.put("mcrName", translation.__(cposTerm.getMcrName()));
		jsonObject.put("orgName", translation.__(cposTerm.getOrgName()));
		if (StringUtils.isNotBlank(cposTerm.getGentmk())) {
			if ("1".equals(cposTerm.getGentmk())) {
				jsonObject.put("gentmk", translation.__("需要生成密钥"));
			} else if ("0".equals(cposTerm.getGentmk())) {
				jsonObject.put("gentmk", translation.__("不需要生成密钥"));
			}
		}
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForRMer(List<RMer> list) {
		JSONArray jsonArray = new JSONArray();
		for (RMer rMer : list) {
			JSONObject jsonObject = getJson(rMer);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(RMer rMer) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(rMer, new String[] {});
		jsonObject.put("id", rMer.getMcr() + "-" + rMer.getMid());
		jsonObject.put("mcrName", translation.__(rMer.getMcrName()));
		jsonObject.put("orgName", translation.__(rMer.getOrgName()));
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForRTerm(List<RTerm> list) {
		JSONArray jsonArray = new JSONArray();
		for (RTerm rTerm : list) {
			JSONObject jsonObject = getJson(rTerm);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(RTerm rTerm) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(rTerm, new String[] {});
		jsonObject.put("id",
			rTerm.getMer().getMcr() + "-" + rTerm.getMer().getMid() + "-" + rTerm.getTid());
		jsonObject.put("mcrName", translation.__(rTerm.getMcrName()));
		jsonObject.put("orgName", translation.__(rTerm.getOrgName()));
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForBranchMap(List<BranchMapView> list) {
		JSONArray jsonArray = new JSONArray();
		for (BranchMapView branchMap : list) {
			JSONObject jsonObject = getJson(branchMap);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(BranchMapView branchMap) {
		
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(branchMap, new String[] {});
		
		jsonObject.put("id", branchMap.getRid() + "-" + branchMap.getBmf());
		jsonObject.put("lbid_name", translation.__(branchMap.getLbid_name()));
		jsonObject.put("cls_name", translation.__(branchMap.getCls_name()));
		jsonObject.put("rbid_name", translation.__(branchMap.getRbid_name()));
		jsonObject.put("bmf_name", translation.__(branchMap.getBmf_name()));
		jsonObject.put("cardtype_name", translation.__(branchMap.getCardtype_name()));
		jsonObject.put("issuerid_name", translation.__(branchMap.getIssuerid_name()));
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForMerMap(List<MerMap> list) {
		JSONArray jsonArray = new JSONArray();
		for (MerMap merMap : list) {
			JSONObject jsonObject = getJson(merMap);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(MerMap merMap) {
		
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(merMap, new String[] {});
		jsonObject.put("id", merMap.getLbid()	+ "-" + merMap.getLmid() + "-" + merMap.getCls() + "-"
								+ merMap.getRbid() + "-" + merMap.getMmf());
		
		jsonObject.put("lbid_name", translation.__(merMap.getLbid_name()));
		jsonObject.put("cls_name", translation.__(merMap.getCls_name()));
		jsonObject.put("rbid_name", translation.__(merMap.getRbid_name()));
		jsonObject.put("mmf_name", translation.__(merMap.getMmf_name()));
		jsonObject.put("cardtype1_name", translation.__(merMap.getCardtype1_name()));
		jsonObject.put("cardtype2_name", translation.__(merMap.getCardtype2_name()));
		jsonObject.put("cardtype3_name", translation.__(merMap.getCardtype3_name()));
		jsonObject.put("cardtype4_name", translation.__(merMap.getCardtype4_name()));
		jsonObject.put("cardtype5_name", translation.__(merMap.getCardtype5_name()));
		jsonObject.put("issuerid1_name", translation.__(merMap.getIssuerid1_name()));
		jsonObject.put("issuerid2_name", translation.__(merMap.getIssuerid2_name()));
		jsonObject.put("issuerid3_name", translation.__(merMap.getIssuerid3_name()));
		jsonObject.put("issuerid4_name", translation.__(merMap.getIssuerid4_name()));
		jsonObject.put("issuerid5_name", translation.__(merMap.getIssuerid5_name()));
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForTermMapView(List<TermMapView> list) {
		JSONArray jsonArray = new JSONArray();
		for (TermMapView termMapView : list) {
			JSONObject jsonObject = getJson(termMapView);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(TermMapView termMapView) {
		
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(termMapView, new String[] {});
		jsonObject.put("id",
			termMapView.getTmf()	+ "-" + termMapView.getLbid() + "-" + termMapView.getLmid() + "-"
								+ termMapView.getRbid() + "-" + termMapView.getRmid() + "-"
								+ termMapView.getLtid() + "-" + termMapView.getRtid() + "-"
								+ termMapView.getTmf());
		
		jsonObject.put("lbid_name", translation.__(termMapView.getLbid_name()));
		jsonObject.put("rbid_name", translation.__(termMapView.getRbid_name()));
		jsonObject.put("tmf_name", translation.__(termMapView.getTmf_name()));
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForCposhistrans(List<Cposhistrans> list) {
		JSONArray jsonArray = new JSONArray();
		for (Cposhistrans cposhistrans : list) {
			JSONObject jsonObject = getJson(cposhistrans);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(Cposhistrans cposhistrans) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		JSONObject jsonObject = JSONUtils.JSONFilter(cposhistrans, new String[] {});
		
		if (cposhistrans.getCposMer() != null) {
			JSONObject cposMeJosn = JSONUtils.JSONFilter(cposhistrans.getCposMer(),
					new String[] { });
			
			cposMeJosn.put("orgName", translation.__(cposhistrans.getCposMer().getOrgName()));
			jsonObject.put("cposMer",cposMeJosn);
		}
		
		if (StringUtils.isNotBlank(cposhistrans.getTraceno())) {
			jsonObject.put("traceno", StringUtils.leftPad(cposhistrans.getTraceno(), 6, "0"));
		}
		
		if (StringUtils.isNotBlank(cposhistrans.getAmount())) {
			BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(cposhistrans.getAmount()));
			amount = amount.divide(BigDecimal.valueOf(100l));
			
			NumberFormat f = new DecimalFormat("#0.00");
			
			jsonObject.put("amount", f.format(amount.doubleValue()));
		}
		
		if (cposhistrans.getRbid() != null) {
			if (StringUtils.isNotBlank(cposhistrans.getRbid().getName())) {
				jsonObject.put("rbid", translation.__(cposhistrans.getRbid().getName()));
			}else{
				jsonObject.put("rbid", "");
			}
		}
		if (cposhistrans.getLbid() != null) {
			if (StringUtils.isNotBlank(cposhistrans.getLbid().getName())) {
				jsonObject.put("lbid", translation.__(cposhistrans.getLbid().getName()));
			}else{
				jsonObject.put("lbid", "");
			}
		}

		if (StringUtils.isNotBlank(cposhistrans.getInnidName())) {
			jsonObject.put("innidName", translation.__(cposhistrans.getInnidName()));
		}
		
		if (StringUtils.isNotBlank(cposhistrans.getTransdate())) {
			jsonObject.put("transdate",
				DateUtils.stringDateFormat(cposhistrans.getTransdate(), "yyyymmdd", "yyyy-mm-dd"));
		}
		
		if (StringUtils.isNotBlank(cposhistrans.getTranstime())) {
			jsonObject.put("transtime",
				DateUtils.stringDateFormat(cposhistrans.getTranstime(), "HHmmss", "HH:mm:ss"));
		}
		
		if (StringUtils.isNotBlank(cposhistrans.getBatchno())) {
			jsonObject.put("batchno", StringUtils.leftPad(cposhistrans.getBatchno(), 6, "0"));
		}
		
		//冲正标志 '0'-未冲正 '1'-已冲正
		if (StringUtils.isNotBlank(cposhistrans.getRevflag())) {
			String revflag = "";
			if ("1".equals(cposhistrans.getRevflag())) {
				revflag = translation.__("已冲正");
			} else {
				revflag = translation.__("未冲正");
			}
			
			jsonObject.put("revflag", revflag);
		}
		
		//撤销标志 '0'-未撤销 '1'-已撤销
		if (StringUtils.isNotBlank(cposhistrans.getVoidflag())) {
			String voidflag = "";
			if ("0".equals(cposhistrans.getVoidflag())) {
				voidflag = translation.__("未撤销");
			} else {
				voidflag = translation.__("已撤销");
			}
			jsonObject.put("voidflag", voidflag);
		}
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForCpostransForExcel(List<CpostransForExcel> list) {
		JSONArray jsonArray = new JSONArray();
		for (CpostransForExcel cpostransForExcel : list) {
			JSONObject jsonObject = getJson(cpostransForExcel);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(CpostransForExcel cpostransForExcel) {
		TranslationUtils translation = TranslationUtils
			.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
		
		JSONObject jsonObject = JSONUtils.JSONFilter(cpostransForExcel, new String[] {});
		
		
		if (StringUtils.isNotBlank(cpostransForExcel.getTraceno())) {
			jsonObject.put("traceno", StringUtils.leftPad(cpostransForExcel.getTraceno(), 6, "0"));
		}
		
		if (StringUtils.isNotBlank(cpostransForExcel.getAmount())) {
			BigDecimal amount = BigDecimal
				.valueOf(Double.parseDouble(cpostransForExcel.getAmount()));
			amount = amount.divide(BigDecimal.valueOf(100l));
			
			NumberFormat f = new DecimalFormat("#0.00");
			
			jsonObject.put("amount", f.format(amount.doubleValue()));
		}
		if (StringUtils.isNotBlank(cpostransForExcel.getRbid())) {
			jsonObject.put("rbid", translation.__(cpostransForExcel.getRbid()));
		}
		if (StringUtils.isNotBlank(cpostransForExcel.getBid())) {
			jsonObject.put("bid", translation.__(cpostransForExcel.getBid()));
		}
		if (StringUtils.isNotBlank(cpostransForExcel.getInnid())) {
			jsonObject.put("innid", translation.__(cpostransForExcel.getInnid()));
		}
		
		if (StringUtils.isNotBlank(cpostransForExcel.getTransdate())) {
			jsonObject.put("transdate", DateUtils.stringDateFormat(cpostransForExcel.getTransdate(),
				"yyyymmdd", "yyyy-mm-dd"));
		}
		
		if (StringUtils.isNotBlank(cpostransForExcel.getTranstime())) {
			jsonObject.put("transtime",
				DateUtils.stringDateFormat(cpostransForExcel.getTranstime(), "HHmmss", "HH:mm:ss"));
		}
		
		if (StringUtils.isNotBlank(cpostransForExcel.getBatchno())) {
			jsonObject.put("batchno", StringUtils.leftPad(cpostransForExcel.getBatchno(), 6, "0"));
		}
		
		//冲正标志 '0'-未冲正 '1'-已冲正
		if (StringUtils.isNotBlank(cpostransForExcel.getRevflag())) {
			String revflag = "";
			if ("1".equals(cpostransForExcel.getRevflag())) {
				revflag = translation.__("已冲正");
			} else {
				revflag = translation.__("未冲正");
			}
			
			jsonObject.put("revflag", revflag);
		}
		
		//撤销标志 '0'-未撤销 '1'-已撤销
		if (StringUtils.isNotBlank(cpostransForExcel.getVoidflag())) {
			String voidflag = "";
			if ("0".equals(cpostransForExcel.getVoidflag())) {
				voidflag = translation.__("未撤销");
			} else {
				voidflag = translation.__("已撤销");
			}
			jsonObject.put("voidflag", voidflag);
		}
		
		return jsonObject;
	}
	
	@Override
	public JSONArray getJSONArrayForLanguage(List<Language> list) {
		JSONArray jsonArray = new JSONArray();
		for (Language language : list) {
			JSONObject jsonObject = getJson(language);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public JSONObject getJson(Language language) {
		
		JSONObject jsonObject = JSONUtils.JSONFilter(language, new String[] {});
		
		return jsonObject;
	}

	@Override
	public JSONArray getJSONArrayForCposhislog(List<Cposhislog> list) {
		JSONArray jsonArray = new JSONArray();
		for (Cposhislog cposhislog : list) {
			JSONObject jsonObject = getJson(cposhislog);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONObject getJson(Cposhislog cposhislog) {
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));
			
			JSONObject jsonObject = JSONUtils.JSONFilter(cposhislog, new String[] {});
			if (cposhislog.getCposMer() != null) {
				JSONObject cposMeJosn = JSONUtils.JSONFilter(cposhislog.getCposMer(),
						new String[] { });
				
				cposMeJosn.put("orgName", translation.__(cposhislog.getCposMer().getOrgName()));
				jsonObject.put("cposMer",cposMeJosn);
			}
			if (StringUtils.isNotBlank(cposhislog.getTraceno())) {
				jsonObject.put("traceno", StringUtils.leftPad(cposhislog.getTraceno(), 6, "0"));
			}
			
			if (StringUtils.isNotBlank(cposhislog.getAmount())) {
				BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(cposhislog.getAmount()));
				amount = amount.divide(BigDecimal.valueOf(100l));
				
				NumberFormat f = new DecimalFormat("#0.00");
				
				jsonObject.put("amount", f.format(amount.doubleValue()));
			}
			if (cposhislog.getRbid() != null) {
				if (StringUtils.isNotBlank(cposhislog.getRbid().getName())) {
					jsonObject.put("rbid", translation.__(cposhislog.getRbid().getName()));
				}else{
					jsonObject.put("rbid", "");
				}
			}
			if (cposhislog.getLbid() != null) {
				if (StringUtils.isNotBlank(cposhislog.getLbid().getName())) {
					jsonObject.put("lbid", translation.__(cposhislog.getLbid().getName()));
				}
				else{
					jsonObject.put("lbid", "");
				}
			}

			if (StringUtils.isNotBlank(cposhislog.getInnidName())) {
				jsonObject.put("innidName", translation.__(cposhislog.getInnidName()));
			}
			if (StringUtils.isNotBlank(cposhislog.getTransdate())) {
				jsonObject.put("transdate",
					DateUtils.stringDateFormat(cposhislog.getTransdate(), "yyyymmdd", "yyyy-mm-dd"));
			}
			
			if (StringUtils.isNotBlank(cposhislog.getTranstime())) {
				jsonObject.put("transtime",
					DateUtils.stringDateFormat(cposhislog.getTranstime(), "HHmmss", "HH:mm:ss"));
			}
			
			if (StringUtils.isNotBlank(cposhislog.getBatchno())) {
				jsonObject.put("batchno", StringUtils.leftPad(cposhislog.getBatchno(), 6, "0"));
			}
			
			return jsonObject;
	}

	@Override
	public JSONArray getJSONArrayForRealtimeNotion(List<RealtimeNotion> list) {
		JSONArray jsonArray = new JSONArray();
		for (RealtimeNotion realtimeNotion : list) {
			JSONObject jsonObject = getJson(realtimeNotion);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONObject getJson(RealtimeNotion realtimeNotion) {
		
		JSONObject jsonObject = JSONUtils.JSONFilter(realtimeNotion, new String[] {});
		
		if (StringUtils.isNotBlank(realtimeNotion.getTotalAmount())) {
			BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(realtimeNotion.getTotalAmount()));
			amount = amount.divide(BigDecimal.valueOf(100l));
			
			NumberFormat f = new DecimalFormat("#0.00");
			
			jsonObject.put("totalAmount", f.format(amount.doubleValue()));
		}
		
		return jsonObject;
	}

	@Override
	public JSONArray getJSONArrayForCposhislogForExcel(
			List<CposhislogForExcel> list) {
		JSONArray jsonArray = new JSONArray();
		for (CposhislogForExcel cposhislogForExcel : list) {
			JSONObject jsonObject = getJson(cposhislogForExcel);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONObject getJson(CposhislogForExcel cposhislogForExcel) {
		TranslationUtils translation = TranslationUtils
				.getInstance((Locale) WebUtils.getSession().getAttribute("locale"));	
		JSONObject jsonObject = JSONUtils.JSONFilter(cposhislogForExcel, new String[] {});
			
		if (StringUtils.isNotBlank(cposhislogForExcel.getTraceno())) {
			jsonObject.put("traceno", StringUtils.leftPad(cposhislogForExcel.getTraceno(), 6, "0"));
		}
		
		if (StringUtils.isNotBlank(cposhislogForExcel.getAmount())) {
			BigDecimal amount = BigDecimal
				.valueOf(Double.parseDouble(cposhislogForExcel.getAmount()));
			amount = amount.divide(BigDecimal.valueOf(100l));
			
			NumberFormat f = new DecimalFormat("#0.00");
			
			jsonObject.put("amount", f.format(amount.doubleValue()));
		}
		if (StringUtils.isNotBlank(cposhislogForExcel.getRbid())) {
			jsonObject.put("rbid", translation.__(cposhislogForExcel.getRbid()));
		}
		if (StringUtils.isNotBlank(cposhislogForExcel.getBid())) {
			jsonObject.put("bid", translation.__(cposhislogForExcel.getBid()));
		}
		if (StringUtils.isNotBlank(cposhislogForExcel.getInnid())) {
			jsonObject.put("innid", translation.__(cposhislogForExcel.getInnid()));
		}
		
		if (StringUtils.isNotBlank(cposhislogForExcel.getTransdate())) {
			jsonObject.put("transdate", DateUtils.stringDateFormat(cposhislogForExcel.getTransdate(),
				"yyyymmdd", "yyyy-mm-dd"));
		}
		
		if (StringUtils.isNotBlank(cposhislogForExcel.getTranstime())) {
			jsonObject.put("transtime",
				DateUtils.stringDateFormat(cposhislogForExcel.getTranstime(), "HHmmss", "HH:mm:ss"));
		}
		
		if (StringUtils.isNotBlank(cposhislogForExcel.getBatchno())) {
			jsonObject.put("batchno", StringUtils.leftPad(cposhislogForExcel.getBatchno(), 6, "0"));
		}
		
		return jsonObject;
	}
	
}
