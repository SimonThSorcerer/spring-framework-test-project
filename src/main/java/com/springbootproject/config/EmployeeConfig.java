package com.springbootproject.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeConfig {

    @Value("${addressservice.base.url}")
    private String addressBaseURL;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
//  // This is for RESTTempalte
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    // This is for Reactive Web Services, use one or the other, this one is more professional and faster

    @Bean
    public WebClient webClient() {

        return WebClient.builder()
                .baseUrl(addressBaseURL)
                .build();
    }

}