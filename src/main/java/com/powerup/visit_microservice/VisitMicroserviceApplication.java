package com.powerup.visit_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VisitMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitMicroserviceApplication.class, args);
	}

}
