package com.example.bookms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@EnableWebMvc
@EnableEurekaClient
public class BookmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmsApplication.class, args);
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
      return new InternalResourceViewResolver();
    }
	
}
