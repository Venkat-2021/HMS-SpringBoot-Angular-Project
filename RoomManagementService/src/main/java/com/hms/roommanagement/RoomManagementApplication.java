package com.hms.roommanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@OpenAPIDefinition(info=@Info(title="HMS - Rooms Management Service", version="1.0",description="Rooms Management Service"))
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class RoomManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomManagementApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
