package org.starter.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

public class EnvPostProcessor implements EnvironmentPostProcessor {
    private final YamlPropertySourceLoader propertySourceLoader;

    public EnvPostProcessor() {
        this.propertySourceLoader = new YamlPropertySourceLoader();
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        ClassPathResource resource = new ClassPathResource("default.yaml");
        PropertySource<?> propertySource;

        try {
            List<PropertySource<?>> propertySources = propertySourceLoader.load("default", resource);
            propertySource = propertySources.get(0);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load YAML properties", e);
        }

        environment.getPropertySources().addLast(propertySource);
    }
}
