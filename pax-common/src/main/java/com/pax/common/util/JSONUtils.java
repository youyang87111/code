package com.pax.common.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class JSONUtils {
	/**
	 * 实体T转化为JSON时，对某些字段进行过滤
	 * @param <T>
	 * @param t	 需要转化的实体
	 * @param filterstrs 需要过滤的字段数组
	 * @return
	 */
	public static <T> JSONObject JSONFilter(T t,final String[] filterstrs){
		JSONObject jsonObject = null;
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			
			public boolean apply(Object source, String name, Object value) {
				if(filterstrs==null)return false;
				for(String filterString:filterstrs){
					if(filterString.equals(name)){
						return true;
					}
				}
				return false;
			}
			
		});
		jsonObject=JSONObject.fromObject(t,jsonConfig);
		return jsonObject;
	}
	public static <T> JSONObject JSONFilter(T t,final String[] filterstrs,Map addmap){
		JSONObject jsonObject=JSONFilter(t, filterstrs);
		jsonObject.putAll(addmap);
		return jsonObject;
	}
	/**
	 * 将保存实体T的集合转化为JSONArray，并对T中的某些字段进行过滤
	 * @param <T>
	 * @param iterable  保存实体T的集合
	 * @param filterstrs 需要过滤的字段数组
	 * @return
	 */
	public static <T> JSONArray JSONArrayFilter(Iterable<T> iterable,String[] filterstrs) {
		JSONArray jsonArray = new JSONArray();
		for(T t:iterable){
			JSONObject jsonObject = JSONFilter(t, filterstrs);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
}
