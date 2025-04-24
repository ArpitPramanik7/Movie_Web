package com.arpit.SpringMovies.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;

// @Configuration
// public class CorsGlobalConfig {

//     @Bean
//     public CorsFilter corsFilter() {
//         CorsConfiguration corsConfig = new CorsConfiguration();
//         corsConfig.addAllowedOrigin("http://192.168.167.190:3000");
//         corsConfig.addAllowedOrigin("http://localhost:3000"); // Just in case
//         corsConfig.addAllowedHeader("*");
//         corsConfig.addAllowedMethod("*");
//         corsConfig.setAllowCredentials(true);
//         corsConfig.setMaxAge(3600L);
        
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/api/**", corsConfig);
//         return new CorsFilter(source);
//     }
// }

