package com.ucucs.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

// 指定Hystrix当前类默认的执行线程池
@DefaultProperties(threadPoolKey = "otherThreadPool")
@Service
public class OtherService {

  @HystrixCommand(fallbackMethod = "getFallbackName")
  public String getName() {
    return "My Other Name";
  }

  public String getFallbackName() {
    return "My Fallback Name";
  }
}
