package com.pax.core.common.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

/**
 * @author zhuxl@paxsz.com
 * @Description: mybatis自动扫描别名路径（新增通配符匹配功能）
 */
public class PaxSqlSessionFactoryBean extends SqlSessionFactoryBean {
	private static final Logger	logger						= LoggerFactory
		.getLogger(PaxSqlSessionFactoryBean.class);
	
	static final String			DEFAULT_RESOURCE_PATTERN	= "**/*.class";
	
	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		try {
			List<String> result = new ArrayList<String>();
			
			String[] packages = typeAliasesPackage.split(",|;");
			for (String pkg : packages) {
				ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
				MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
					resolver);
				pkg = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
							+ ClassUtils.convertClassNameToResourcePath(pkg) + "/"
						+ DEFAULT_RESOURCE_PATTERN;
				
				//将加载多个绝对匹配的所有Resource
				//将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分
				//然后进行遍历模式匹配
				
				Resource[] resources = resolver.getResources(pkg);
				if (resources != null && resources.length > 0) {
					MetadataReader metadataReader = null;
					for (Resource resource : resources) {
						if (resource.isReadable()) {
							metadataReader = metadataReaderFactory.getMetadataReader(resource);
							try {
								result.add(
									Class.forName(metadataReader.getClassMetadata().getClassName())
										.getPackage().getName());
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			if (result.size() > 0) {
				super.setTypeAliasesPackage(StringUtils.join(result.toArray(), ","));
			} else {
				logger.warn("参数typeAliasesPackage:" + typeAliasesPackage + "，未找到任何包");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}