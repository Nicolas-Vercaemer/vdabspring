package be.vdab.services;

import java.util.Arrays;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// enkele imports
@Configuration
@ComponentScan(basePackageClasses = CreateServiceBeans.class)
@EnableTransactionManagement
@EnableCaching
public class CreateServiceBeans {
	@Bean
	public SimpleCacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(
				"onderwerpen"), new ConcurrentMapCache("cursisten")));
		return cacheManager;
	}
}