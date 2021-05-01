package com.hms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableCircuitBreaker
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class HmsgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmsgatewayApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	 
	    RestTemplate restTemplate = new RestTemplate();
	    return restTemplate;
	}
}
