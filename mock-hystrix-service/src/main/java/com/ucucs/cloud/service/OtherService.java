package com.ucucs.cloud.service;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ucucs.cloud.command.CacheCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// 指定Hystrix当前类默认的执行线程池
@DefaultProperties(threadPoolKey = "otherThreadPool")
@Service
public class OtherService {

  private static final Logger logger = LoggerFactory.getLogger(OtherService.class);

  @Autowired private RestTemplate restTemplate;

  @Value("${service-url.user-service}")
  private String userServiceUrl;

  @HystrixCommand(fallbackMethod = "getFallbackName")
  public String getName(Long id) {
    return "My Other Name" + id;
  }

  public String getFallbackName(Long id) {
    return "My Fallback Name" + id;
  }

  public String getUserCommand(Long id) {
    CacheCommand cacheCommand1 =
        new CacheCommand(
            com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                    HystrixCommandGroupKey.Factory.asKey("group"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("test")),
            restTemplate,
            1,
            userServiceUrl);

    // 这里比较恶心的是，这个Command只能执行一次，不能复用进行Execute调用

    CacheCommand cacheCommand2 =
        new CacheCommand(
            com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                    HystrixCommandGroupKey.Factory.asKey("group"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("test")),
            restTemplate,
            1,
            userServiceUrl);

    Integer result1 = cacheCommand1.execute();
    Integer result2 = cacheCommand2.execute();
    logger.info("first request result is:{} ,and secend request result is: {}", result1, result2);
    return String.format("%s_%s", result1, result2);
  }
}
