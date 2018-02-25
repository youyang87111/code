package com.pax.common.interceptors;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;


@Intercepts({	@Signature(type = Executor.class, method = "update", args = {	MappedStatement.class,
																			Object.class }),
				@Signature(type = Executor.class, method = "query", args = {	MappedStatement.class,
																				Object.class,
																				RowBounds.class,
																				ResultHandler.class }) })
public class MybatisSqlInterceptor implements Interceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(MybatisSqlInterceptor.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = null;
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
		}
		String sqlId = mappedStatement.getId();
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Configuration configuration = mappedStatement.getConfiguration();
		Object returnValue;
		long start = System.currentTimeMillis();
		returnValue = invocation.proceed();
		long end = System.currentTimeMillis();
		long time = (end - start);
		if (time > 1) {
			String sql = getSql(configuration, boundSql, sqlId, time, returnValue);
			logger.info(sql);
		}
		return returnValue;
	}
	
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}
	
	@Override
	public void setProperties(Properties properties) {
	}
	
	public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId,
								long time, Object returnValue) {
		String sql = showSql(configuration, boundSql);
		StringBuilder str = new StringBuilder();
		str.append("\n");
		str.append(
			supplementAlignLeft("==============================COM.PAX:SQL", "=", 123) + "\n");
		
		str.append(supplementAlignRight("[    SQL   ID]:\t", " ", 15)
					+ supplementAlignLeft(sqlId, " ", 120) + "\n");
		str.append(supplementAlignRight("[         SQL]:\t", " ", 15)
					+ supplementAlignLeft(sql, " ", 120) + "\n");
		if (returnValue instanceof List) {
			List value = (List) returnValue;
			JSONArray jsonArray = JSONArray.fromObject(value);
			str.append(supplementAlignRight("[RETURN TOTAL]:\t", " ", 15)
						+ supplementAlignLeft(String.valueOf(value.size()), " ", 120) + "\n");
			String tmp = jsonArray.toString();
			if (tmp.length() > 256) {
				tmp = tmp.substring(0, 256) + "...";
			}
			str.append(supplementAlignRight("[RETURN  DATA]:\t", " ", 15)
						+ supplementAlignLeft(tmp, " ", 120) + "\n");
		} else if (returnValue instanceof Integer || returnValue instanceof Long) {
			str.append(supplementAlignRight("[RETURN TOTAL]:\t", " ", 15)
						+ supplementAlignLeft(String.valueOf(returnValue), " ", 120) + "\n");
			str.append(supplementAlignRight("[RETURN  DATA]:\t", " ", 15)
						+ supplementAlignLeft(String.valueOf(returnValue), " ", 120) + "\n");
		}
		str.append(supplementAlignRight("[WASTE   TIME]:\t", " ", 15)
					+ supplementAlignLeft(time + "(ms)", " ", 120) + "\n");
		
		str.append(supplementAlignRight("=", "=", 123) + "\n");
		return str.toString();
	}
	
	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
				DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}
			
		}
		return value;
	}
	
	public static String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
				
			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					}
				}
			}
		}
		return sql;
	}
	
	public static final String supplementAlignLeft(String source, String symbol, int length) {
		if (source.length() >= length) {
			return source;
		}
		
		while (source.length() != length) {
			source += symbol;
		}
		return source;
	}
	
	public static final String supplementAlignRight(String source, String symbol, int length) {
		if (source.length() >= length) {
			return source;
		}
		
		while (source.length() != length) {
			source = symbol + source;
		}
		return source;
	}
}
