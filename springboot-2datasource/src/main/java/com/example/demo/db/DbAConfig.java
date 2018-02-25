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
@MapperScan(basePackages = {DbAConfig.scan_package}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class DbAConfig {
	
		static final String scan_package = "com.example.demo.dao1";  
		static final String mapper_location = "classpath:mapping/*.xml"; 
		static final String type_aliases_package = "com.example.demo.domain"; 
	
		@Resource(name="test1DS")
	    private DataSource ds1;
		
		@Bean
	    public SqlSessionFactory sqlSessionFactory1() throws Exception {
	        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	        factoryBean.setDataSource(ds1); // 使用titan数据源, 连接titan库
	        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_location));  
	        factoryBean.setTypeAliasesPackage(type_aliases_package);
	        return factoryBean.getObject();

	    }
		
		@Bean
	    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
	        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1()); // 使用上面配置的Factory
	        return template;
	    }
		
		@Bean(name = "test1TransactionManager")
		@Primary
		public DataSourceTransactionManager testTransactionManager() {
			return new DataSourceTransactionManager(ds1);
		}
}
