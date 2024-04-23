package org.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.starter.properties.IncomeStarterProperties;
import org.starter.service.IncomeClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(IncomeStarterProperties.class)
public class IncomeClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public IncomeClient incomeClient(RestTemplate restTemplate, IncomeStarterProperties incomeStarterProperties) {
        return new IncomeClient(restTemplate, incomeStarterProperties);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}