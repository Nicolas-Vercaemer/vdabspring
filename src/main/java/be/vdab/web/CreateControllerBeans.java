package be.vdab.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// enkele imports
@Configuration
@EnableWebMvc 
@ComponentScan(basePackageClasses = CreateControllerBeans.class) 
public class CreateControllerBeans extends WebMvcConfigurerAdapter{ 
@Bean
InternalResourceViewResolver viewResolver() { 
InternalResourceViewResolver resolver=new InternalResourceViewResolver();
resolver.setPrefix("/WEB-INF/JSP/"); 
resolver.setSuffix(".jsp"); 
return resolver;
}
}