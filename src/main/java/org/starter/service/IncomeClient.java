package org.starter.service;

import org.springframework.stereotype.Service;
import org.starter.dto.UserIncomeDTO;
import org.starter.properties.IncomeStarterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IncomeClient {

    private final RestTemplate restTemplate;
    private final IncomeStarterProperties incomeStarterProperties;

    @Autowired
    public IncomeClient(RestTemplate restTemplate, IncomeStarterProperties incomeStarterProperties) {
        this.restTemplate = restTemplate;
        this.incomeStarterProperties = incomeStarterProperties;
    }

    public int getUserIncomeById(Long id) {
        for (UserIncomeDTO income : getUserIncomes()) {
            if (income.getId() == id) {
                return income.getIncome();
            }
        }
        return 0;
    }

    private List<UserIncomeDTO> getUserIncomes() {
        ResponseEntity<List<UserIncomeDTO>> responseEntity = restTemplate.exchange(
                incomeStarterProperties.getIncomeURL(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return responseEntity.getBody();
    }
}

