package io.github.cloudiator.rest.api;

//import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.TenantToTenantConverter;
import io.github.cloudiator.rest.converter.UserConverter;
import io.github.cloudiator.rest.model.User;
import io.github.cloudiator.rest.model.UserNew;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.cloudiator.messages.entities.User.CreateUserRequest;
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
public class UsersApiController implements UsersApi {

  private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  final TenantToTenantConverter T2TConverter;
  final UserConverter userConverter;

  @org.springframework.beans.factory.annotation.Autowired
  public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.T2TConverter = new TenantToTenantConverter();
    this.userConverter = new UserConverter();
  }

  @Autowired
  private UserService userService;

  public ResponseEntity<User> createUser(
      @ApiParam(value = "User creation request ") @Valid @RequestBody UserNew apiUser) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        //MessageEntity
        UserEntities.UserNew userNewOut = UserEntities.UserNew.newBuilder()
            .setEmail(apiUser.getEmail())
            .setPassword(apiUser.getPassword())
            .setPasswordRepeat(apiUser.getPasswordRepeat())
            .setTenant(T2TConverter.apply(apiUser.getTenant()))
            .build();
        User userCreated = null;

        //Kafka
        org.cloudiator.messages.entities.User.CreateUserResponse createResponse = null;
        createResponse = userService.createUser(CreateUserRequest.newBuilder()
            .setNewUser(userNewOut).build());

        userCreated = userConverter.applyBack(createResponse.getUser());

        return new ResponseEntity<User>(userCreated, HttpStatus.OK);
      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
      /*
      catch (IOException ex) {
        log.error("Couldn't serialize response for content type application/json", ex);
        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      */
    }

    return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
  }

}
