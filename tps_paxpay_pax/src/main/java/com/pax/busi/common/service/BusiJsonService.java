package com.pax.busi.common.service;

import java.util.List;
import java.util.Map;

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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface BusiJsonService {
	
	JSONArray getJSONArrayForMap(List<Map<String, String>> list);
	
	JSONArray getJSONArrayForAppClass(List<AppClass> list);
	
	JSONObject getJson(AppClass appClass);
	
	JSONArray getJSONArrayForBankInfo(List<BankInfo> list);
	
	JSONObject getJson(BankInfo bankInfo);
	
	JSONArray getJSONArrayForBmf(List<Bmf> list);
	
	JSONObject getJson(Bmf bmf);
	
	JSONArray getJSONArrayForBranch(List<Branch> list);
	
	JSONObject getJson(Branch branch);
	
	JSONArray getJSONArrayForCardType(List<CardType> list);
	
	JSONObject getJson(CardType cardType);
	
	JSONArray getJSONArrayForInntransdef(List<Inntransdef> list);
	
	JSONObject getJson(Inntransdef inntransdef);
	
	JSONArray getJSONArrayForMcr(List<Mcr> list);
	
	JSONObject getJson(Mcr mcr);
	
	JSONArray getJSONArrayForMmf(List<Mmf> list);
	
	JSONObject getJson(Mmf mmf);
	
	JSONArray getJSONArrayForTmf(List<Tmf> list);
	
	JSONObject getJson(Tmf tmf);
	
	JSONArray getJSONArrayForCposMer(List<CposMer> list);
	
	JSONObject getJson(CposMer cposMer);
	
	JSONArray getJSONArrayForCposTerm(List<CposTerm> list);
	
	JSONObject getJson(CposTerm cposTerm);
	
	JSONArray getJSONArrayForRMer(List<RMer> list);
	
	JSONObject getJson(RMer rMer);
	
	JSONArray getJSONArrayForRTerm(List<RTerm> list);
	
	JSONObject getJson(RTerm rTerm);
	
	JSONArray getJSONArrayForBranchMap(List<BranchMapView> list);
	
	JSONObject getJson(BranchMapView branchMap);
	
	JSONArray getJSONArrayForCposhistrans(List<Cposhistrans> list);
	
	JSONArray getJSONArrayForMerMap(List<MerMap> list);
	
	JSONObject getJson(MerMap merMap);
	
	JSONArray getJSONArrayForTermMapView(List<TermMapView> list);
	
	JSONObject getJson(TermMapView termMapView);
	
	JSONObject getJson(Cposhistrans cposhistrans);
	
	JSONArray getJSONArrayForCpostransForExcel(List<CpostransForExcel> list);
	
	JSONObject getJson(CpostransForExcel postransForExcel);

	JSONArray getJSONArrayForLanguage(List<Language> list);
	
	JSONObject getJson(Language language);

	JSONArray getJSONArrayForCposhislog(List<Cposhislog> list);
	
	JSONObject getJson(Cposhislog cposhislog);

	JSONArray getJSONArrayForRealtimeNotion(List<RealtimeNotion> list);
	
	JSONObject getJson(RealtimeNotion realtimeNotion);

	JSONArray getJSONArrayForCposhislogForExcel(List<CposhislogForExcel> list);
	
	JSONObject getJson(CposhislogForExcel cposhislogForExcel);
}
