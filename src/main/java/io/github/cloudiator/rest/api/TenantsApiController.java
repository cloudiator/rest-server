package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.converter.TenantToTenantConverter;
import io.github.cloudiator.rest.converter.UserConverter;
import io.github.cloudiator.rest.model.Tenant;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.cloudiator.messages.entities.User.CreateTenantRequest;
import org.cloudiator.messages.entities.User.CreateTenantResponse;
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
public class TenantsApiController implements TenantsApi {

  private static final Logger log = LoggerFactory.getLogger(TenantsApiController.class);

  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  final TenantToTenantConverter T2TConverter;
  final UserConverter userConverter;

  @org.springframework.beans.factory.annotation.Autowired
  public TenantsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.T2TConverter = new TenantToTenantConverter();
    this.userConverter = new UserConverter();
  }

  @Autowired
  private UserService userService;

  public ResponseEntity<Tenant> createTenant(
      @ApiParam(value = "Tenant creation request ") @Valid @RequestBody Tenant tenant) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        CreateTenantResponse response = userService.createTenant(CreateTenantRequest.newBuilder()
            .setTenant(T2TConverter.apply(tenant)).build());

        Tenant backTenant = T2TConverter.applyBack(response.getTenant());

        return new ResponseEntity<Tenant>(backTenant, HttpStatus.OK);
      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
      /*
      catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Tenant>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      */
    }

    return new ResponseEntity<Tenant>(HttpStatus.NOT_IMPLEMENTED);
  }

}
