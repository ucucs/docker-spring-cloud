package com.ucucs.cloud.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

public class CacheCommand extends HystrixCommand<Integer> {

  private final RestTemplate restTemplate;
  private final String userServiceUrl;
  private final Integer id;

  public CacheCommand(Setter setter, RestTemplate restTemplate, Integer id, String userServiceUrl) {
    super(setter);
    this.restTemplate = restTemplate;
    this.id = id;
    this.userServiceUrl = userServiceUrl;
  }

  @Override
  protected Integer run() {
    return restTemplate.getForObject(userServiceUrl + "/user/cache", Integer.class);
  }

  public static void flushRequestCache(String commandKey, Long id) {
    HystrixRequestCache.getInstance(
            HystrixCommandKey.Factory.asKey(commandKey),
            HystrixConcurrencyStrategyDefault.getInstance())
        .clear(String.valueOf(id));
  }

  @Override
  protected String getCacheKey() {
    return String.valueOf(id);
  }
}
