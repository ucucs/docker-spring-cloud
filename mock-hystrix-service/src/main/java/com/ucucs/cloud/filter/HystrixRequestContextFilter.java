package com.ucucs.cloud.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/*", asyncSupported = true)
public class HystrixRequestContextFilter implements Filter {
  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    try (HystrixRequestContext ignored = HystrixRequestContext.initializeContext()) {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }
}
