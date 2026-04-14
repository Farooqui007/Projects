package com.FarooquiAnas.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// ─────────────────────────────────────────────────────────────────────────────
// WHY CORS?
//
// Browsers block requests from one origin (e.g. http://localhost:3000)
// to a different origin (http://localhost:8080) by default. This is a
// browser security policy called CORS (Cross-Origin Resource Sharing).
//
// This config tells the browser:
//   "Yes, our Spring API allows requests from React/Angular frontends."
//
// @Configuration → Spring treats this class as a config source (like XML config used to be)
// @Bean          → the method returns an object that Spring manages in its container
// ─────────────────────────────────────────────────────────────────────────────
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")          // apply to all /api/* routes
                        .allowedOrigins(
                                "http://localhost:3000",  // React default
                                "http://localhost:4200"   // Angular default
                        )
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600); // browser caches CORS preflight for 1 hour
            }
        };
    }
}