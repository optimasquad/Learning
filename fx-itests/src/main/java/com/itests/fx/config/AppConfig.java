package com.itests.fx.config;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.itests.fx.common.PropertyLoader;
import com.itests.fx.dao.ItestsDao;
import com.itests.fx.dao.ItestsDaoImpl;

/**
 * @author jatinma
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.amdocs" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class AppConfig {

	static final org.slf4j.Logger logger = LoggerFactory.getLogger(AppConfig.class.getName());

	@Bean
	public DataSource dataSource() {
		logger.info("dataSource");
		PropertyLoader.loadPropValues();
		DriverManagerDataSource bds = new DriverManagerDataSource();
		bds.setDriverClassName(PropertyLoader.getPropValue("DB.driver"));
		bds.setUrl(PropertyLoader.getPropValue("DB.url"));
		bds.setUsername(PropertyLoader.getPropValue("DB.username"));
		bds.setPassword(PropertyLoader.getPropValue("DB.password"));
		return bds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}

	@Bean
	public ItestsDao itestsDao() {
		ItestsDaoImpl itestsDao = new ItestsDaoImpl();
		itestsDao.setJdbcTemplate(jdbcTemplate());
		return itestsDao;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}