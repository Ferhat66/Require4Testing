package com.ferhatsertkaya.require4testing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final EndpointLogger endpointLogger;

    @Autowired
    public WebConfig(EndpointLogger endpointLogger) {
        this.endpointLogger = endpointLogger;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(endpointLogger);
    }
}