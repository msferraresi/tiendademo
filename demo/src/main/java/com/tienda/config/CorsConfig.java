package com.tienda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Permite todos los endpoints
                        .allowedOrigins("http://localhost:3000")  // Origen permitido (e.g., frontend)
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                        .allowedHeaders("*");
            }
        };
    }
}
