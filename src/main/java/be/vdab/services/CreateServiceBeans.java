package be.vdab.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// enkele imports
@Configuration
@ComponentScan(basePackageClasses = CreateServiceBeans.class)
@EnableTransactionManagement
public class CreateServiceBeans {
}