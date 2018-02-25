package com.pax.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;

import com.pax.common.exception.BusinessException;

public class MapUtils {
	
	@SuppressWarnings("unchecked")
	public static Object convertMap(Class type, Map map) {
		
		try {
			
			BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
			
			Object obj = type.newInstance(); // 创建 JavaBean 对象
			
			// 给 JavaBean 对象的属性赋值
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				
				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
					Object value = map.get(propertyName);
					
					if (value != null && !String.valueOf(value).equals("(null)")) {
						Object[] args = new Object[1];
						args[0] = String.valueOf(value);
						
						descriptor.getWriteMethod().invoke(obj, args);
					}
					
				}
			}
			
			return obj;
			
		} catch (Exception e) {
			throw new BusinessException("map转对象失败");
		}
		
	}
	
	//将javabean实体类转为map类型，然后返回一个map类型的值
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name)) {
					Object value = propertyUtilsBean.getNestedProperty(obj, name);
					if (value == null || "null".equals(value)) {
						params.put(name, "");
					} else {
						params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
}
