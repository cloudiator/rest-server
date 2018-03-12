package io.swagger.configuration;

import static io.swagger.configuration.SecurityConstants.HEADER;

import de.uniulm.omi.cloudiator.util.configuration.Configuration;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


public class ApiKeyAuthorizationFilter extends BasicAuthenticationFilter {


  public ApiKeyAuthorizationFilter(
      AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    String header = request.getHeader(HEADER);

    if (header == null || !checkToken(header)) {
      chain.doFilter(request, response);
      return;
    }

    SecurityContextHolder.getContext().setAuthentication(getToken(request));
    chain.doFilter(request, response);

  }

  private boolean checkToken(String token) {
    final String validToken = Configuration.conf().getString(SecurityConstants.TOKEN_CONFIG);
    if (validToken == null) {
      return false;
    }
    return validToken.equals(token);
  }


  private UsernamePasswordAuthenticationToken getToken(HttpServletRequest httpServletRequest) {
    return new UsernamePasswordAuthenticationToken("dummy_user_id", null, new ArrayList<>());
  }

}
