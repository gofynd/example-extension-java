package com.fynd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = "classpath:application-${spring.profiles.active}.yml")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.fynd.repository"})
public class DbConfig {
}
