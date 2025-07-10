package com.ferhatsertkaya.require4testing.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Leitet /testers auf /testers/ weiter
        registry.addRedirectViewController("/testers", "/testers/");
        registry.addRedirectViewController("/users", "/users/");
        registry.addRedirectViewController("/requirements", "/requirements/");
        registry.addRedirectViewController("/testruns", "/testruns/");
    }
}