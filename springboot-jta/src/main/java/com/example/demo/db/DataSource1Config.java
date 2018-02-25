package com.example.demo.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = {DataSource1Config.scan_package}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSource1Config {
	
		static final String scan_package = "com.example.demo.dao1";  
		static final String mapper_location = "classpath:mapping/*.xml"; 
		static final String type_aliases_package = "com.example.demo.domain"; 
	
		
		@Bean(name = "ds1")
	    public DataSource dataSource(@Qualifier("db1Config")DB1Config db1Config) {
			
			MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
			mysqlXaDataSource.setUrl(db1Config.getUrl());
			mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
			mysqlXaDataSource.setPassword(db1Config.getPassword());
			mysqlXaDataSource.setUser(db1Config.getUsername());
			mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

			AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
			xaDataSource.setXaDataSource(mysqlXaDataSource);
			xaDataSource.setUniqueResourceName("ds1");

			xaDataSource.setMinPoolSize(db1Config.getMinPoolSize());
			xaDataSource.setMaxPoolSize(db1Config.getMaxPoolSize());
			xaDataSource.setMaxLifetime(db1Config.getMaxLifetime());
			xaDataSource.setBorrowConnectionTimeout(db1Config.getBorrowConnectionTimeout());
			try {
				xaDataSource.setLoginTimeout(db1Config.getLoginTimeout());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			xaDataSource.setMaintenanceInterval(db1Config.getMaintenanceInterval());
			xaDataSource.setMaxIdleTime(db1Config.getMaxIdleTime());
			xaDataSource.setTestQuery(db1Config.getTestQuery());
			return xaDataSource;
	    }
		
		@Bean(name="sqlSessionFactory1")
	    public SqlSessionFactory sqlSessionFactory(@Qualifier("ds1")DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	        factoryBean.setDataSource(dataSource); 
	        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_location));  
	        factoryBean.setTypeAliasesPackage(type_aliases_package);
	        return factoryBean.getObject();

	    }
		
		@Bean
	    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory1")SqlSessionFactory sqlSessionFactory) throws Exception {
	        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
	        return template;
	    }
		
}
