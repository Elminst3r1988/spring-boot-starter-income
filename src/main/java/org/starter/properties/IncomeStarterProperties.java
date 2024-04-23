package org.starter.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "income-starter")
public class IncomeStarterProperties {
    private String incomeURL;
}
