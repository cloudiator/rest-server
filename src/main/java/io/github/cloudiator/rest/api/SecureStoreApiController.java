package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.model.Text;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.entities.SecureStore.SecureStoreDeleteRequest;
import org.cloudiator.messages.entities.SecureStore.SecureStoreRequest;
import org.cloudiator.messages.entities.SecureStore.SecureStoreResponse;
import org.cloudiator.messages.entities.SecureStore.SecureStoreRetrieveRequest;
import org.cloudiator.messages.entities.SecureStore.SecureStoreRetrieveResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.SecureStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SecureStoreApiController implements SecureStoreApi {

  private static final Logger log = LoggerFactory.getLogger(SecureStoreApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;
  private final SecureStoreService secureStoreService;

  @org.springframework.beans.factory.annotation.Autowired
  public SecureStoreApiController(ObjectMapper objectMapper, HttpServletRequest request,
      SecureStoreService secureStoreService) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.secureStoreService = secureStoreService;
  }

  public ResponseEntity<Void> deleteSecure(
      @ApiParam(value = "Key of the stored variable ", required = true) @PathVariable("key") String key) {
    String accept = request.getHeader("Accept");

    if (Strings.isNullOrEmpty(key)) {
      throw new ApiException(400, "Key must not be null or empty");
    }

    final String tenant = UserInfo.of(request).tenant();

    try {
      secureStoreService
          .deleteSecret(
              SecureStoreDeleteRequest.newBuilder().setKey(key).setUserId(tenant).build());
    } catch (ResponseException e) {
      throw new ApiException(e.code(), e.getMessage());
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Text> retrieveSecure(
      @ApiParam(value = "Key of the stored variable ", required = true) @PathVariable("key") String key) {
    String accept = request.getHeader("Accept");

    if (accept != null && accept.contains("application/json")) {

      if (Strings.isNullOrEmpty(key)) {
        throw new ApiException(400, "Key must not be null or empty");
      }

      final String tenant = UserInfo.of(request).tenant();

      try {
        final SecureStoreRetrieveResponse secureStoreRetrieveResponse = secureStoreService
            .retrieveSecret(
                SecureStoreRetrieveRequest.newBuilder().setUserId(tenant).setKey(key).build());

        return new ResponseEntity<>(new Text().content(secureStoreRetrieveResponse.getValue()),
            HttpStatus.OK);

      } catch (ResponseException responseException) {
        throw new ApiException(responseException.code(), responseException.getMessage());
      }
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Text> storeSecure(
      @ApiParam(value = "Key of the stored variable ", required = true) @PathVariable("key") String key,
      @ApiParam(value = "Value of the stored variable ", required = true) @Valid @RequestBody Text value) {

    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      if (Strings.isNullOrEmpty(key)) {
        throw new ApiException(400, "Key must not be null or empty");
      }

      if (value == null || Strings.isNullOrEmpty(value.getContent())) {
        throw new ApiException(400, "Value must not be null or empty");
      }

      try {
        final SecureStoreResponse secureStoreResponse = secureStoreService.storeSecurely(
            SecureStoreRequest.newBuilder().setKey(key).setValue(value.getContent()).build());

        return new ResponseEntity<>(new Text().content(secureStoreResponse.getEncryptedValue()),
            HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
