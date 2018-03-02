package io.swagger.configuration;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.swagger.configuration.SecurityConstants.HEADER;

import de.uniulm.omi.cloudiator.util.configuration.Configuration;
import io.github.cloudiator.rest.api.ApiException;
import io.github.cloudiator.rest.model.Token;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cloudiator.messages.entities.User.AuthRequest;
import org.cloudiator.messages.entities.User.AuthResponse;
import org.cloudiator.messages.entities.UserEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


public class ApiKeyAuthorizationFilter extends BasicAuthenticationFilter {

  private final UserService userService;

  public ApiKeyAuthorizationFilter(
      AuthenticationManager authenticationManager, UserService userService) {
    super(authenticationManager);
    checkNotNull(userService, "userService is null");
    this.userService = userService;
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

    String token = httpServletRequest.getHeader(HEADER);
    System.out.println(token + "\n");

    try {
      if (token == null) {
        throw new ApiException(500, "tokerError");
      }

      UserEntities.Token kafkaToken = UserEntities.Token.newBuilder().setToken(token)
          .setGenerationTime(1).setExpireTime(5).build();
      AuthRequest req = AuthRequest.newBuilder().setToken(kafkaToken).build();
      System.out.println(req + "\n");
      AuthResponse authResponse;

      authResponse = userService.auth(req);
      System.out.println(authResponse + "\n");
      String user = authResponse.getUser().getEmail();

      return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    } catch (ResponseException ex) {
      throw new ApiException(ex.code(), ex.getMessage() + " omg: " + token);
    }

  }
 /*
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
*/

}
