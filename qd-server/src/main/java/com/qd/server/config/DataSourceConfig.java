package com.qd.server.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import lombok.SneakyThrows;

/**
 * 数据源配置
 */
@Configuration
public class DataSourceConfig {

	@Autowired
	private DataSourceProperties properties;

	/**
	 * 初始化数据源
	 * 
	 * @return
	 */
	@SneakyThrows
	@Bean("druidDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid")
	public DataSource initDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername(properties.getUsername());
		dataSource.setPassword(properties.getPassword());
		dataSource.setUrl(properties.getUrl());
		dataSource.setDriverClassName(properties.getDriverClassName());

		// 其他默认信息配置
		dataSource.setFilters("stat");
		dataSource.setValidationQuery("select 1 from dual ");
		dataSource.setTestWhileIdle(true);
		dataSource.setPoolPreparedStatements(true);
		dataSource.setTestOnBorrow(true);
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

		return dataSource;
	}

//	/**
//	 * 初始化Session工厂
//	 * 
//	 * @return
//	 */
//	@SneakyThrows
//	@Bean
//	public SqlSessionFactory initSqlSessionFactory(@Autowired DataSource dataSource) {
//		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//		factoryBean.setDataSource(dataSource);
//		factoryBean.setMapperLocations(
//				new PathMatchingResourcePatternResolver().getResources("/mybatis/mapper/*Mapper.xml"));
//		factoryBean.setConfigLocation(new ClassPathResource("/mybatis/mybatis-config.xml"));
//		SqlSessionFactory factory = factoryBean.getObject();
//		factory.getConfiguration().setMapUnderscoreToCamelCase(true);
//		return factory;
//	}

	/**
	 * 设置过滤
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean druidStatFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
		// 添加过滤规则
		registrationBean.addUrlPatterns("/*");
		// 添加需要忽略的格式信息
		registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png, *.css,*.ico,/druid/*");
		registrationBean.addInitParameter("session-stat-enable", "true");
		registrationBean.addInitParameter("session-stat-max-count", "100");
		return registrationBean;

	}

	/**
	 * 设置监控
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean druidStatViewServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		// 白名单黑名单设置，如果同时存在则黑名单优于白名单
		registrationBean.addInitParameter("allow", "127.0.0.1");
		// 登录的账号和密码
		registrationBean.addInitParameter("loginUsername", "admin");
		registrationBean.addInitParameter("loginPassword", "admin");
		registrationBean.addInitParameter("resetEnable", "false");
		return registrationBean;
	}
}
