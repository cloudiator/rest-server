package io.github.cloudiator.rest;

import io.github.cloudiator.rest.api.ApiException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class UserInfo {

  Authentication authentication;
  HttpServletRequest request;

  public UserInfo(HttpServletRequest request) {
    this.authentication = SecurityContextHolder.getContext().getAuthentication();
    this.request = request;
  }

  public String currentUserame() {
    /*
    if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
     throw new ApiException (403, "Authentication Error");
    }
    */
    return request.getUserPrincipal().getName();

    //return authentication.getName();
  }

  public String currentUserTenant() {
    /*
    if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
      throw new ApiException(403, "Authentication Error");
    }
    */
    System.out.println("\n Authentication: " + authentication + "\n");

    return authentication.getCredentials().toString();
  }

}
