package com.ucucs.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerSecureApplication {

  public static void main(String[] args) {
    SpringApplication.run(EurekaServerSecureApplication.class, args);
  }
}
