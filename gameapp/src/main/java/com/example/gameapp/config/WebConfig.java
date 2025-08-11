package com.example.gameapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
    registry.addMapping("/api/**")
                .allowedOrigins("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    registry.addMapping("/api/v1/**")
        .allowedOrigins("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("*");
    }

    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {
        registry.addViewController("/game-list.html").setViewName("game-list");
        registry.addViewController("/game-registration.html").setViewName("game-registration");
    }
}




