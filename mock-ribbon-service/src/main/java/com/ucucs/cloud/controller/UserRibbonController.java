package com.ucucs.cloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserRibbonController {

  private static final Logger logger = LoggerFactory.getLogger(UserRibbonController.class);

  @Autowired private RestTemplate restTemplate;

  @Value("${service-url.user-service}")
  private String userServiceUrl;

  @GetMapping("/{id}")
  public String getUser(@PathVariable Long id) {
    return restTemplate.getForObject(userServiceUrl + "/user/{1}", String.class, id);
  }
}
