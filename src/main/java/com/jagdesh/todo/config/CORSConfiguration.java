package com.jagdesh.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CORSConfiguration {

    @Bean
    public CorsFilter corsFilter(){

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("*");
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","PATCH"));
        config.setAllowedHeaders(Arrays.asList("authorization","content-type"));

        source.registerCorsConfiguration("/api/**",config);
        source.registerCorsConfiguration("/v2/api-docs",config);

        return new CorsFilter(source);

    }

}
