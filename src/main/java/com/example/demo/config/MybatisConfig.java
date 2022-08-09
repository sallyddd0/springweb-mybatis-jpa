package com.example.demo.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration

public class MybatisConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ApplicationContext ac;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		System.out.println(dataSource);
		SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		String loctaionPattern="classpath:sql/**/*-mapper.xml";
		
		Resource[] mapperLocations = ac.getResources(loctaionPattern);
		factoryBean.setMapperLocations(mapperLocations);
		factoryBean.setConfiguration(con());
		
		return factoryBean.getObject();
	}
	@Bean
	public SqlSessionTemplate sessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration con(){
		return new org.apache.ibatis.session.Configuration();
	}
}
