package com.pax.busi.common.service;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.pax.busi.common.dao.CommonDao;
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
import com.pax.core.util.WebUtils;

@Service
public class CommonServiceImpl implements CommonService {
	@Resource
	private CommonDao commonDao;
	
	@Override
	public List<AppClass> listAppClass(String classgroup) {
		return commonDao.listAppClass(classgroup);
	}
	
	@Override
	public List<BankInfo> listBankInfo() {
		return commonDao.listBankInfo();
	}
	
	@Override
	public List<Bmf> listBmf() {
		return commonDao.listBmf();
	}
	
	@Override
	public List<Branch> listBranch(String type) {
		return commonDao.listBranch(type);
	}
	
	@Override
	public List<CardType> listCardType() {
		return commonDao.listCardType();
	}
	
	@Override
	public List<Inntransdef> listInntransdef(String classgroup) {
		return commonDao.listInntransdef(classgroup);
	}
	
	@Override
	public List<Inntransdef> listAllInntransdef() {
		return commonDao.listAllInntransdef();
	}
	
	@Override
	public List<Mcr> listMcr(String type) {
		return commonDao.listMcr(type);
	}
	
	@Override
	public List<Mmf> listMmf() {
		return commonDao.listMmf();
	}
	
	@Override
	public List<Tmf> listTmf() {
		return commonDao.listTmf();
	}
	
	@Override
	public List<Language> findLocales() {
		
		return commonDao.findLocales();
	}
	
	@Override
	public String changeLocale(String locale) {
		Locale $locale = new Locale(StringUtils.split(locale, "_")[0],
			StringUtils.split(locale, "_")[1]);
		WebUtils.getSession().setAttribute("locale", $locale);
		return locale;
	}
	
	@Override
	public List<Chintoeng> listChintoeng() {
		return commonDao.listChintoeng();
	}
	
}
