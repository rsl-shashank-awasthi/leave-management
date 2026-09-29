package com.assignment.leave.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppConfig {

    @Value("${app.environment:Default}")
    private String environment;

    @PostConstruct
    public void logEnvironment() {
        log.info("Application is running in: {}", environment);
    }
}