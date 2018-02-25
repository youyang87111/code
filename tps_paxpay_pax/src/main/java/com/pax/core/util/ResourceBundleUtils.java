package com.pax.core.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

@SuppressWarnings("unchecked")
public class ResourceBundleUtils {
	
	private ResourceBundle				res				= null;
	
	private String						propertiesFile	= "i18n";
	
	private String						separatorChar	= ",";
	
	private final Map<String, String>	map				= new HashMap<String, String>();
	
	public ResourceBundleUtils(Locale locale) {
		res = ResourceBundle.getBundle(this.propertiesFile, locale);
		
		Map<String, String> m = MapUtils.toMap(res);
		for (Entry<String, String> entry : m.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
	}
	
	public ResourceBundleUtils(String properties) {
		this.propertiesFile = properties;
		res = ResourceBundle.getBundle(this.propertiesFile);
		
		Map<String, String> m = MapUtils.toMap(res);
		for (Entry<String, String> entry : m.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
	}
	
	public ResourceBundleUtils(String properties, Locale locale) {
		this.propertiesFile = properties;
		res = ResourceBundle.getBundle(this.propertiesFile, locale);
		
		Map<String, String> m = MapUtils.toMap(res);
		for (Entry<String, String> entry : m.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
	}
	
	public ResourceBundleUtils(String properties, Locale locale, String separatorChar) {
		this.propertiesFile = properties;
		res = ResourceBundle.getBundle(this.propertiesFile, locale);
		
		Map<String, String> m = MapUtils.toMap(res);
		for (Entry<String, String> entry : m.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		this.separatorChar = separatorChar;
	}
	
	/**
	 * 简单得到properties文件中key对应的value，没找到key时返回null
	 * @param key
	 * @return
	 */
	public String getStringMessage(String key) {
		
		return map.get(key);
	}
	
	public String getStringMessage(String key, String[] args) {
		
		String val = map.get(key);
		for (int i = 0; i < args.length; i++) {
			val = val.replaceAll("\\{" + i + "\\}", args[i]);
		}
		return val;
	}
	
	/**
	 * 得到properties文件中的key对应的value,转换成Map
	 * value中每条记录的分隔符默认为","
	 * Map中key和value的分隔符为":"
	 * @param key
	 * @return
	 */
	public Map<String, String> getMapMessage(String key) {
		try {
			String[] strs = StringUtils.split(getStringMessage(key), this.separatorChar);
			if (ArrayUtils.isEmpty(strs)) {
				return null;
			}
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (String str : strs) {
				String[] buf = StringUtils.split(str, ":");
				if (ArrayUtils.isEmpty(buf)) {
					return null;
				}
				map.put(buf[0], buf[1]);
			}
			return map;
		} catch (MissingResourceException e) {
			return null;
		}
	}
	
	/**
	 * 得到properties文件中的key对应的value,转换成JsonArray
	 * 先转成Map,然后再转成JsonArray
	 * @param key
	 * @return
	 */
	public String getJsonArrayMessage(String key) {
		Map<String, String> map = getMapMessage(key);
		return getJsonArrayMessage(map);
		
	}
	
	public String getJsonArrayMessage(Map<String, String> map) {
		if (MapUtils.isNotEmpty(map)) {
			StringBuffer buf = new StringBuffer();
			buf.append("[");
			for (Map.Entry<String, String> entry : map.entrySet()) {
				buf.append("['" + entry.getKey() + "',");
				buf.append("'" + entry.getValue() + "'],");
			}
			buf.deleteCharAt(buf.length() - 1);
			buf.append("]");
			return buf.toString();
		} else {
			return null;
		}
		
	}
	
	public Map<String, String> getMap() {
		return map;
	}
	
}
