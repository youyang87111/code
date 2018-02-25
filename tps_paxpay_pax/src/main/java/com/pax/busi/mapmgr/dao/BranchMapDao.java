package com.pax.busi.mapmgr.dao;

import java.util.List;
import java.util.Map;

import com.pax.busi.mapmgr.entity.BranchMapView;
import com.pax.core.model.PageQueryParam;

public interface BranchMapDao {
	
	BranchMapView getBranchMapMode(String lbid, String lmid, String cls);
	
	void saveBranchMapMode(BranchMapView branchMapView);
	
	void deleteBranchMapMode(String lbid, String lmid, String cls);
	
	List<BranchMapView> list(PageQueryParam pageQueryParam);
	
	BranchMapView get(String id);
	
	//查看具体映射表里面是否还有记录，没得就删除映射mode
	List<BranchMapView> getBranchMaps(String lbid, String lmid, String cls);
	
	//查看具体映射表里面是否有记录，没得就不能做商户映射---新增商户映射时使用
	List<BranchMapView> getBranchMapsByRbid(String lbid, String lmid, String cls, String rbid);
	
	void frozen(String bmf, String rid);
	
	void unfrozen(String bmf, String rid);
	
	void audit(Map<String, Object> filterMap);
	
	void delete(String bmf, String rid);
	
	void deleteO2O(String lbid, String lmid, String cls);
	
	void update(String id, String rbid, String rbid2);
	
	String getMaxRidFromMode(String sysdate);
	
	String getMaxRid(String bmf, String sysdate);
	
	void save(BranchMapView branchMapView);
	
	BranchMapView getBranchMapView(	String bmf, String lbid, String lmid, String cls,
									String cardType, String amt1, String amt2, String issuerid);
	
	//得到不等于某种卡类型的记录
	List<BranchMapView> getBranchMapCardTypesNotEq(	String lbid, String lmid, String cls,
													String cardType);
	
	//得到等于某种卡类型的记录
	List<BranchMapView> getBranchMapCardTypesEq(String lbid, String lmid, String cls,
												String cardType);
	
	//得到所有相关的金额分段
	List<BranchMapView> getBranchMapAmts(String lbid, String lmid, String cls);
	
	//查询不等于某种卡的记录
	List<BranchMapView> getBranchMapCardTypeAmtsNotEq(	String lbid, String lmid, String cls,
														String cardType);
	
	//得到等于某种卡类型的记录
	List<BranchMapView> getBranchMapCardTypeAmtsEq(	String lbid, String lmid, String cls,
													String cardType);
	
	//得到不等于某种卡类型的记录
	List<BranchMapView> getBranchMapIssuerCardTypesNotEq(	String lbid, String lmid, String cls,
															String issuer, String cardType);
	
	//得到等于某种卡类型的记录
	List<BranchMapView> getBranchMapIssuerCardTypesEq(	String lbid, String lmid, String cls,
														String issuer, String cardType);
	
}
