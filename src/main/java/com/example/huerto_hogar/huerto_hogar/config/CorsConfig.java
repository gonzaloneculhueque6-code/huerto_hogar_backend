package com.example.huerto_hogar.huerto_hogar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica a todas las URLs que empiecen con /api
                .allowedOrigins("*")   // Permite cualquier origen (React)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Permite estos métodos
                .allowedHeaders("*");
    }
}