package com.ucucs.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired private RestTemplate restTemplate;

  @Autowired private OtherService otherService;

  @Value("${service-url.user-service}")
  private String userServiceUrl;

  @HystrixCommand(fallbackMethod = "getDefaultUser")
  public String getUser(Long id) {
    logger.info("get user with fallback default:{}", id);
    return restTemplate.getForObject(userServiceUrl + "/user/{1}", String.class, id);
  }

  @HystrixCommand(
      fallbackMethod = "getDefaultUser",
      commandKey = "getUserWithGroup",
      groupKey = "getUserGroup",
      threadPoolKey = "getUserThreadPool")
  public String getUserWithGroup(Long id) {
    return restTemplate.getForObject(userServiceUrl + "/user/{1}", String.class, id);
  }

  @HystrixCommand(
      fallbackMethod = "getDefaultUser",
      ignoreExceptions = {NullPointerException.class})
  public String getUserException(Long id) {
    if (id == 1) {
      throw new IndexOutOfBoundsException();
    } else if (id == 2) {
      throw new NullPointerException();
    }
    return restTemplate.getForObject(userServiceUrl + "/user/{1}", String.class, id);
  }

  @CacheResult(cacheKeyMethod = "getCacheKey")
  @HystrixCommand(fallbackMethod = "getDefaultUser", commandKey = "getUserCache")
  public String getUserCache(Long id) {
    logger.info("get user skip cache:{}", id);
    return restTemplate.getForObject(userServiceUrl + "/user/cache", Integer.class).toString();
  }

  /** 为缓存生成key的方法. */
  public String getCacheKey(Long id) {
    return String.valueOf(id);
  }

  @CacheRemove(commandKey = "getUserCache", cacheKeyMethod = "getCacheKey")
  @HystrixCommand
  public String removeCache(Long id) {
    logger.info("remove user cache:{}", id);
    return "removed" + id;
  }

  public String getDefaultUser(Long id) {
    return "Fallback " + id;
  }
}
