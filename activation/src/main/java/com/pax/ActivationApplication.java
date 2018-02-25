package com.pax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ActivationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivationApplication.class, args);
	}
}
