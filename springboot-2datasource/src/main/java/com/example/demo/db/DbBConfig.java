package com.example.demo.db;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = {DbBConfig.scan_package}, sqlSessionFactoryRef = "sqlSessionFactory2")
public class DbBConfig {
	
		static final String scan_package = "com.example.demo.dao2";  
		static final String mapper_location = "classpath:mapping/*.xml"; 
		static final String type_aliases_package = "com.example.demo.domain"; 
		
		@Bean
	    public SqlSessionFactory sqlSessionFactory2(@Qualifier("test2DS") DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	        factoryBean.setDataSource(dataSource); // 使用titan数据源, 连接titan库
	        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_location));  
	        factoryBean.setTypeAliasesPackage(type_aliases_package);
	        return factoryBean.getObject();

	    }
		
		@Bean
	    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2")SqlSessionFactory sqlSessionFactory) throws Exception {
	        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
	        return template;
	    }
		
		@Bean(name = "test2TransactionManager")
		public DataSourceTransactionManager testTransactionManager(@Qualifier("test2DS") DataSource dataSource) {
			return new DataSourceTransactionManager(dataSource);
		}
}
