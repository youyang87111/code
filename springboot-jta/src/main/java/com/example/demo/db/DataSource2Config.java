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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = {DataSource2Config.scan_package}, sqlSessionFactoryRef = "sqlSessionFactory2")
public class DataSource2Config {
	
		static final String scan_package = "com.example.demo.dao2";  
		static final String mapper_location = "classpath:mapping/*.xml"; 
		static final String type_aliases_package = "com.example.demo.domain"; 
	
		
		@Bean(name = "ds2")
	    public DataSource dataSource(@Qualifier("db2Config")DB2Config db2Config) {
			
			MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
			mysqlXaDataSource.setUrl(db2Config.getUrl());
			mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
			mysqlXaDataSource.setPassword(db2Config.getPassword());
			mysqlXaDataSource.setUser(db2Config.getUsername());
			mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

			AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
			xaDataSource.setXaDataSource(mysqlXaDataSource);
			xaDataSource.setUniqueResourceName("ds2");

			xaDataSource.setMinPoolSize(db2Config.getMinPoolSize());
			xaDataSource.setMaxPoolSize(db2Config.getMaxPoolSize());
			xaDataSource.setMaxLifetime(db2Config.getMaxLifetime());
			xaDataSource.setBorrowConnectionTimeout(db2Config.getBorrowConnectionTimeout());
			try {
				xaDataSource.setLoginTimeout(db2Config.getLoginTimeout());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			xaDataSource.setMaintenanceInterval(db2Config.getMaintenanceInterval());
			xaDataSource.setMaxIdleTime(db2Config.getMaxIdleTime());
			xaDataSource.setTestQuery(db2Config.getTestQuery());
			return xaDataSource;
	    }
		
		@Bean(name="sqlSessionFactory2")
	    public SqlSessionFactory sqlSessionFactory(@Qualifier("ds2")DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	        factoryBean.setDataSource(dataSource); 
	        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_location));  
	        factoryBean.setTypeAliasesPackage(type_aliases_package);
	        return factoryBean.getObject();

	    }
		
		@Bean
	    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory2")SqlSessionFactory sqlSessionFactory) throws Exception {
	        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
	        return template;
	    }
		
}
