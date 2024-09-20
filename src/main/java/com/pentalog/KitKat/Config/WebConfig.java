package com.pentalog.KitKat.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://48.209.17.140", "http://localhost:5173", "http://kitkat.onbench.online")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Origin", "X-Requested-With", "Accept", "Authorization", "Access-Control-Allow-Origin")
                .allowCredentials(true);
    }
}
