package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.converter.LoginConverter;
import io.github.cloudiator.rest.converter.TenantToTenantConverter;
import io.github.cloudiator.rest.converter.TokenConverter;
import io.github.cloudiator.rest.model.Login;
import io.github.cloudiator.rest.model.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.model.User;
import io.swagger.annotations.*;
import java.util.Base64;
import org.cloudiator.messages.entities.User.LoginRequest;
import org.cloudiator.messages.entities.User.LoginResponse;
import org.cloudiator.messages.entities.UserEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class LoginApiController implements LoginApi {

  private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  final LoginConverter loginConverter;
  final TenantToTenantConverter T2TConverter;
  final TokenConverter tokenConverter;

  @org.springframework.beans.factory.annotation.Autowired
  public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.loginConverter = new LoginConverter();
    this.T2TConverter = new TenantToTenantConverter();
    this.tokenConverter = new TokenConverter();
  }

  @Autowired
  private UserService userService;

  public ResponseEntity<Token> login(
      @ApiParam(value = "User login request ") @Valid @RequestBody Login apilogin) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        if((apilogin.getPassword()==null)||(apilogin.getPassword().isEmpty())){
          throw new ApiException(400,"PasswordInputError");
        }

        UserEntities.Login kafkaLogin = UserEntities.Login.newBuilder()
            .setEmail(apilogin.getEmail())
            .setPassword(apilogin.getPassword())
            .setTenant(T2TConverter.apply(apilogin.getTenant()))
            .build();

        LoginResponse response = userService
            .login(LoginRequest.newBuilder().setLogin(kafkaLogin).build());
        Token gotToken = tokenConverter.applyBack(response.getToken());


        return new ResponseEntity<Token>(gotToken, HttpStatus.OK);
      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
      /*
      catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Token>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      */
    }

    return new ResponseEntity<Token>(HttpStatus.NOT_IMPLEMENTED);
  }

}
