package com.ucucs.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MockUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockUserServiceApplication.class, args);
	}

}
