package io.github.cloudiator.rest;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserInfo {

  private final Authentication authentication;

  public static UserInfo of(HttpServletRequest request) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (!(auth instanceof UsernamePasswordAuthenticationToken)) {
      throw new IllegalStateException(
          "Did expect auth to be of type UsernamePasswordAuthenticationToken.");
    }
    return new UserInfo(auth);
  }

  private UserInfo(Authentication authentication) {
    this.authentication = authentication;
  }

  @Nullable
  public String username() {
    return this.authentication.getName();
  }

  public String tenant() {
    return this.authentication.getCredentials().toString();
  }

}
