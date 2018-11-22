package io.github.cloudiator.rest.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class LoggingFilter implements Filter {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse res = (HttpServletResponse) servletResponse;
    LOGGER.debug(String.format("Receiving request %s: %s", req.getMethod(), req.getRequestURI()));
    filterChain.doFilter(servletRequest, servletResponse);
    LOGGER.debug(String.format("Returning response with status: %s", res.getStatus()));

  }

  @Override
  public void destroy() {

  }
}
