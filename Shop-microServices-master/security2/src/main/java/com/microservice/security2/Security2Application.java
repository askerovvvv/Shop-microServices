package com.microservice.security2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Security2Application {

	public static void main(String[] args) {
		SpringApplication.run(Security2Application.class, args);
	}

}
