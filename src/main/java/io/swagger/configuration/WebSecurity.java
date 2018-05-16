package io.swagger.configuration;

import org.cloudiator.messaging.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Enables web security
 *
 * @see <a href=https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot />
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  private final UserService userService;

  @Autowired
  public WebSecurity(UserService userService) {
    this.userService = userService;
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers("/webjars/**").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/swagger-ui.html").permitAll()
        .antMatchers("/swagger-resources/**").permitAll()
        .antMatchers("/api-docs/**").permitAll()
        //.antMatchers("/user/print**").permitAll()
        .antMatchers("/login**").permitAll()
        .anyRequest().authenticated().and()
        .addFilter(new ApiKeyAuthorizationFilter(authenticationManager(), userService))
        .sessionManagement()
        .sessionCreationPolicy(
            SessionCreationPolicy.STATELESS);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
}
