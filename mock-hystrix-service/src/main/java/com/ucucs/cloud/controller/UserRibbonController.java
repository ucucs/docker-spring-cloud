package com.ucucs.cloud.controller;

import com.ucucs.cloud.service.OtherService;
import com.ucucs.cloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRibbonController {

  private static final Logger logger = LoggerFactory.getLogger(UserRibbonController.class);

  @Autowired private UserService userService;

  @Autowired private OtherService otherService;

  @GetMapping("/{id}")
  public String getUser(@PathVariable Long id) {
    return userService.getUser(id);
  }

  @GetMapping("/group/{id}")
  public String getUserWithGroup(@PathVariable Long id) {
    return userService.getUserWithGroup(id);
  }

  @GetMapping("/exception/{id}")
  public String getUserException(@PathVariable Long id) {
    return userService.getUserException(id);
  }

  @GetMapping("/other/{id}")
  public String getUserOther(@PathVariable Long id) {
    return otherService.getName(id);
  }

  @GetMapping("/command/{id}")
  public String getUserCommand(@PathVariable Long id) {
    return otherService.getUserCommand(id);
  }

  @GetMapping("/cache/{id}")
  public String getUserCache(@PathVariable Long id) {
    String data = userService.getUserCache(id);
    logger.info("User Cache Return {}", data);
    data = userService.getUserCache(id);
    logger.info("User Cache Return {}", data);
    data = userService.removeCache(id);
    logger.info("User Cache Return {}", data);
    data = userService.getUserCache(id);
    logger.info("User Cache Return {}", data);
    return "操作成功";
  }
}
