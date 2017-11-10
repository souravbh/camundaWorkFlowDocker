package com.eybpm.notifier;

import javax.sql.DataSource;

import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.camunda.bpm.extension.reactor.CamundaReactor;
import org.camunda.bpm.extension.reactor.bus.CamundaEventBus;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.eybpm.notifier.taskListeners.CamundaTaskListenerComplete;
import com.eybpm.notifier.taskListeners.CamundaTaskListenerCreate;

/*
 * https://github.com/camunda/camunda-bpm-examples/blob/master/deployment/spring-boot/README.md
 * https://gist.github.com/Daij-Djan/bb40e0ce9b687716de147b9d2cf968b0
 */
@SpringBootApplication
@EnableProcessApplication
public class CamundaWorkflowDockerApplication {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(CamundaWorkflowDockerApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
	@Bean
	StandaloneProcessEngineConfiguration getStandaloneProcessEngineConfiguration() {
		StandaloneProcessEngineConfiguration config = new StandaloneProcessEngineConfiguration() {
			{
				this.databaseSchemaUpdate = "true";
				this.jdbcDriver = "org.postgresql.Driver";
				this.jdbcUrl = "jdbc:postgresql://postgres.cttavxkscptw.us-east-1.rds.amazonaws.com:5432/postgres";
				this.jdbcUsername = "postgres";
				this.jdbcPassword = "postgres";
				this.getProcessEnginePlugins().add(CamundaReactor.plugin());
				this.setDataSource(dataSource);
				// this.jobExecutorActivate = false;
				// this.isDbMetricsReporterActivate = false;
			}

		};
		return config;
	}

	@Bean
	CamundaEventBus getCamundaEventBus() {
		CamundaEventBus eventBus = CamundaReactor.eventBus();
		return eventBus;
	}

	@Bean
	CamundaTaskListenerCreate getCamundaTaskListenerCreate(CamundaEventBus eventBus) {
		return new CamundaTaskListenerCreate(eventBus);
	}

	@Bean
	CamundaTaskListenerComplete getCamundaTaskListenerComplete(CamundaEventBus eventBus) {
		return new CamundaTaskListenerComplete(eventBus);
	}

	@Bean
	CamundaExecutionListener getCamundaExecutionListener(CamundaEventBus eventBus) {
		return new CamundaExecutionListener(eventBus);
	}

	/*
	 * @Bean public DataSource getDataSource() {
	 * 
	 * BasicDataSource dataSourceConfig = new BasicDataSource();
	 * dataSourceConfig.setDriverClassName("org.postgresql.Driver");
	 * dataSourceConfig.setUrl(
	 * "jdbc:postgresql://postgres.cttavxkscptw.us-east-1.rds.amazonaws.com:5432/postgres"
	 * ); dataSourceConfig.setUsername("postgres");
	 * dataSourceConfig.setValidationQuery("SELECT 1");
	 * dataSourceConfig.setPassword("postgres");
	 * 
	 * return dataSourceConfig; }
	 * 
	 * @Bean public EntityManagerFactory entityManagerFactory() {
	 * 
	 * HibernateJpaVendorAdapter vendorAdapter = new
	 * HibernateJpaVendorAdapter(); vendorAdapter.setGenerateDdl(true);
	 * 
	 * Properties jpaProperties = new Properties();
	 * jpaProperties.put("spring.jpa.hibernate.ddl-auto", "create-drop");
	 * jpaProperties.put("spring.jpa.show-sql", "true");
	 * //jpaProperties.put("databaseSchemaUpdate", "true");
	 * 
	 * LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new
	 * LocalContainerEntityManagerFactoryBean();
	 * entityManagerFactoryBean.setDataSource(getDataSource());
	 * entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
	 * entityManagerFactoryBean.setJpaProperties(jpaProperties);
	 * entityManagerFactoryBean.afterPropertiesSet(); return
	 * entityManagerFactoryBean.getObject(); }
	 * 
	 * 
	 * 
	 * @Bean StandaloneInMemProcessEngineConfiguration
	 * getStandaloneInMemProcessEngineConfiguration() { return new
	 * StandaloneInMemProcessEngineConfiguration() { { this.databaseSchemaUpdate
	 * = DB_SCHEMA_UPDATE_DROP_CREATE;
	 * this.getProcessEnginePlugins().add(CamundaReactor.plugin());
	 * this.jobExecutorActivate = false; this.isDbMetricsReporterActivate =
	 * false; } }; }
	 */
}
