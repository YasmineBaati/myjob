package com.anis.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
            .defaultContentType(MediaType.APPLICATION_JSON) // Default to JSON
            .mediaType("json", MediaType.APPLICATION_JSON) // Support JSON format
            .mediaType("xml", MediaType.APPLICATION_XML);   // Support XML format
    }
}
