package be.vdab.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import be.vdab.entities.Cursist;
import be.vdab.entities.Onderwerp;

@EnableJpaRepositories(basePackageClasses={CreateDAOBeans.class}) 
@Configuration
@ComponentScan(basePackageClasses=CreateDAOBeans.class)
public class CreateDAOBeans {
	
	@Autowired
	private DataSource dataSource; 
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
	= new LocalContainerEntityManagerFactoryBean();
	entityManagerFactoryBean.setDataSource(dataSource); 
	entityManagerFactoryBean.setPackagesToScan( 
	Cursist.class.getPackage().getName());
	Onderwerp.class.getPackage().getName();
	HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter(); 
	adapter.setShowSql(true); 
	entityManagerFactoryBean.setJpaVendorAdapter(adapter); 
	return entityManagerFactoryBean;
	}
	@Bean
	JpaTransactionManager transactionManager() { 
	return new JpaTransactionManager(entityManagerFactory().getObject());
	}

}
