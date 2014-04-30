package be.vdab.datasource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// enkele imports
@Configuration
public class CreateTestDataSourceBean {
@Bean
public DataSource dataSource() {
return new DriverManagerDataSource("jdbc:mysql://localhost/vdabspring",
"root", "vdab"); 
}
}