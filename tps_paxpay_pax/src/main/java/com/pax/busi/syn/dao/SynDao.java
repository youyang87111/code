package com.pax.busi.syn.dao;

import com.pax.busi.syn.entity.Amcamsg;
import com.pax.busi.syn.entity.Amcrmsg;
import com.pax.busi.syn.entity.MidToAppid;
import com.pax.busi.syn.entity.MidToSubmid;
import com.pax.busi.syn.entity.Qrcode;

public interface SynDao {
	
	MidToAppid getMidToAppid(String mid);
	
	void saveMidToAppid(MidToAppid midToAppid);
	
	void updateMidToAppid(MidToAppid tempMidToAppid);
	
	MidToSubmid getMidToSubmid(String bid, String mid, String classid);
	
	void saveMidToSubmid(MidToSubmid midToSubmid);
	
	void updateMidToSubmid(MidToSubmid tempMidToSubmid);
	
	void deleteMidToSubmid(MidToSubmid tempMidToSubmid);
	
	Amcamsg getAmcamsg(String bid, String mid);
	
	void saveAmcamsg(Amcamsg amcamsg);
	
	void updateAmcamsg(Amcamsg tempAmcamsg);
	
	Amcrmsg getAmcrmsg(String bid, String mid);
	
	void saveAmcrmsg(Amcrmsg amcrmsg);
	
	void updateAmcrmsg(Amcrmsg tempAmcrmsg);
	
	Qrcode getQrcode(String qrtoken);
	
	void saveQrcode(Qrcode qrcode);
	
}
