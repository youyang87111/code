package com.pax.busi.common.service;

import java.util.List;

import com.pax.busi.common.entity.AppClass;
import com.pax.busi.common.entity.BankInfo;
import com.pax.busi.common.entity.Bmf;
import com.pax.busi.common.entity.Branch;
import com.pax.busi.common.entity.CardType;
import com.pax.busi.common.entity.Chintoeng;
import com.pax.busi.common.entity.Inntransdef;
import com.pax.busi.common.entity.Language;
import com.pax.busi.common.entity.Mcr;
import com.pax.busi.common.entity.Mmf;
import com.pax.busi.common.entity.Tmf;

public interface CommonService {
	
	List<AppClass> listAppClass(String classgroup);
	
	List<BankInfo> listBankInfo();
	
	List<Bmf> listBmf();
	
	List<Branch> listBranch(String type);
	
	List<CardType> listCardType();
	
	List<Inntransdef> listInntransdef(String classgroup);
	
	List<Inntransdef> listAllInntransdef();
	
	List<Mcr> listMcr(String type);
	
	List<Mmf> listMmf();
	
	List<Tmf> listTmf();
	
	String changeLocale(String locale);
	
	List<Language> findLocales();
	
	List<Chintoeng> listChintoeng();
	
}
