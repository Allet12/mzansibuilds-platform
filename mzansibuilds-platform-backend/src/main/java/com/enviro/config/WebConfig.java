package com.enviro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // Frontend (React Vite)
                        .allowedOrigins("http://localhost:5175")

                        // Allowed HTTP methods
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")

                        // Allow headers needed for JWT
                        .allowedHeaders("Authorization", "Content-Type")

                        // Allow cookies/auth headers if needed
                        .allowCredentials(true);
            }
        };
    }
}