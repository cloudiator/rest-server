package io.swagger.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
      HttpServletResponse res) throws AuthenticationException {
    /*
    try {

      ApplicationUser creds = new ObjectMapper()
          .readValue(req.getInputStream(), ApplicationUser.class);

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              creds.getUsername(),
              creds.getPassword(),
              new ArrayList<>())
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }*/
    return null;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain,
      Authentication auth) throws IOException, ServletException {
/*
    String token = Jwts.builder()
        .setSubject(((User) auth.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
        .compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    */
  }

}
