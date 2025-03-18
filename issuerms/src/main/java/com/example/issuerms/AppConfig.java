package com.example.issuerms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
//@LoadBalancerClient(name = "issuerms")
public class AppConfig {
   // @LoadBalanced
    @Bean
    public WebClient.Builder getWebClient(){
        return WebClient.builder();
    }

}