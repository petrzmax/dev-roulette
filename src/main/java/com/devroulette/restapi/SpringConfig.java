package com.devroulette.restapi;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableCaching
//@EnableJpaRepositories(enableDefaultTransactions = false)
//@EnableTransactionManagement
public class SpringConfig {
}
