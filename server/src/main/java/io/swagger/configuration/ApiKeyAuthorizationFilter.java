package io.swagger.configuration;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.swagger.configuration.SecurityConstants.HEADER;

import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cloudiator.messages.entities.User.AuthRequest;
import org.cloudiator.messages.entities.User.AuthResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.UserService;
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

    if (header != null) {
      final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = checkToken(
          header);
      if (usernamePasswordAuthenticationToken != null) {
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }

    chain.doFilter(request, response);

  }


  @Nullable
  private UsernamePasswordAuthenticationToken checkToken(String handedToken) {

    try {
      final AuthRequest req = AuthRequest.newBuilder().setHandedToken(handedToken).build();
      final AuthResponse authResponse = userService.auth(req);
      String user = authResponse.getUser().getEmail();
      String userTenant = authResponse.getUser().getTenant().getTenant();
      return new UsernamePasswordAuthenticationToken(user, userTenant, new ArrayList<>());
    } catch (ResponseException e) {
      return null;
    }
  }
}
