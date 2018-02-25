package com.pax.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class ConfigResourceUtils {
	
	private ResourceBundle	res				= null;
	
	private Properties		p				= new Properties();
	
	private String			propertiesFile	= "config.properties";
	
	private String			separatorChar	= ",";
	
	public ConfigResourceUtils() {
		
		InputStream is = Thread.currentThread().getContextClassLoader()
			.getResourceAsStream(propertiesFile);
		
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*res = ResourceBundle.getBundle(this.propertiesFile, LocaleContextHolder.getLocale());
		p = MapUtils.toProperties(MapUtils.toMap(res));*/
	}
	
	public ConfigResourceUtils(String properties) {
		
		this.propertiesFile = properties;
		
		InputStream is = Thread.currentThread().getContextClassLoader()
			.getResourceAsStream(propertiesFile);
		
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*res = ResourceBundle.getBundle(this.propertiesFile, LocaleContextHolder.getLocale());
		p = MapUtils.toProperties(MapUtils.toMap(res));*/
		
	}
	
	public ConfigResourceUtils(String properties, String separatorChar) {
		this.propertiesFile = properties;
		this.separatorChar = separatorChar;
		
		InputStream is = Thread.currentThread().getContextClassLoader()
			.getResourceAsStream(propertiesFile);
		
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*res = ResourceBundle.getBundle(this.propertiesFile, LocaleContextHolder.getLocale());
		p = MapUtils.toProperties(MapUtils.toMap(res));*/
		
	}
	
	/**
	 * 简单得到properties文件中key对应的value，没找到key时返回null
	 * @param key
	 * @return
	 */
	public String getStringMessage(String key) {
		
		return p.getProperty(key, null);
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
	
}
