package com.ha.chargecapture;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class ChargeCaptureDatabaseConfig {

	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;

	@Value("${entitymanager.packagesToScan}")
	private String entityManagerPackagesToScan;

	@Value("${hibernate.connection.autocommit}")
	private String hibernateConnectionAutoCommit;

	private static final Logger LOGGER = LoggerFactory.getLogger(ChargeCaptureDatabaseConfig.class);

	/**
	 *
	 * @return DataSource
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "db.datasource")
	public DataSource ccDataSource() {

		LOGGER.debug("-------------- data source init-----------------");
		return DataSourceBuilder.create().build();

	}

	/**
	 *
	 * @return sessionFactoryBean
	 */
	@Bean(name = "cc")
	@Primary
	public LocalSessionFactoryBean plSessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		LOGGER.debug("************* datasource : {}", ccDataSource());
		sessionFactoryBean.setDataSource(ccDataSource());
		sessionFactoryBean.setPackagesToScan(entityManagerPackagesToScan);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", hibernateDialect);
		hibernateProperties.put("hibernate.show_sql", hibernateShowSql);
		hibernateProperties.put("hibernate.connection.autocommit", hibernateConnectionAutoCommit);
		hibernateProperties.put("hibernate.id.new_generator_mappings", false);
		sessionFactoryBean.setHibernateProperties(hibernateProperties);

		return sessionFactoryBean;
	}

	/**
	 *
	 * @return HibernateTransactionManager
	 */
	@Bean(name = "ccTxn")
	@Primary
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(plSessionFactory().getObject());
		return transactionManager;
	}

}