package com.ucucs.cloud.controller;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @GetMapping("/{id}")
  public String getUser(@PathVariable Long id) {
    logger.info("根据id获取信息，用户ID为：{}", id);
    return String.format("%s,%s", id, LocalDateTime.now());
  }
}
