package io.github.cloudiator.rest.api;

//import io.github.cloudiator.rest.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.converter.TenantToTenantConverter;
import io.github.cloudiator.rest.converter.UserConverter;
import io.github.cloudiator.rest.converter.UserNewConverter;
import io.github.cloudiator.rest.model.User;
import io.github.cloudiator.rest.model.UserNew;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UsersApiController implements UsersApi {

  private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  final TenantToTenantConverter T2TConverter;
  final UserConverter userConverter;
  final UserNewConverter userNewConverter;

  @org.springframework.beans.factory.annotation.Autowired
  public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.T2TConverter = new TenantToTenantConverter();
    this.userConverter = new UserConverter();
    this.userNewConverter = new UserNewConverter();
  }

  @Autowired
  private UserService userService;

  public ResponseEntity<User> createUser(
      @ApiParam(value = "User creation request ") @Valid @RequestBody UserNew apiUser) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        if (!apiUser.getPassword().matches(apiUser.getPasswordRepeat())) {
          throw new ApiException(400, "Passwords do not match");
        }

        UserEntities.UserNew userNewOut = userNewConverter.apply(apiUser);

        //Kafka
        org.cloudiator.messages.entities.User.CreateUserResponse createResponse = userService
            .createUser(CreateUserRequest.newBuilder()
                .setNewUser(userNewOut).build());

        User userCreated = userConverter.applyBack(createResponse.getUser());

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
