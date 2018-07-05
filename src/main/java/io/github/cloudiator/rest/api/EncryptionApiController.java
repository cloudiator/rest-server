package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import org.cloudiator.messages.entities.Encryption.EncryptionRequest;
import org.cloudiator.messages.entities.Encryption.EncryptionResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.EncryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EncryptionApiController implements EncryptionApi {

  private static final Logger log = LoggerFactory.getLogger(EncryptionApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private EncryptionService encryptionService;

  @org.springframework.beans.factory.annotation.Autowired
  public EncryptionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<String> encrypt(
      @ApiParam(value = "Text to encrypt", required = true) @PathVariable("text") String text) {

    final String tenant = UserInfo.of(request).tenant();

    try {
      EncryptionResponse encryptionResponse = encryptionService
          .encrypt(EncryptionRequest.newBuilder().setUserId(tenant).setPlaintext(text).build());

      return new ResponseEntity<>(encryptionResponse.getCiphertext(), HttpStatus.OK);

    } catch (ResponseException e) {
      throw new ApiException(e.code(), e.getMessage());
    }
  }

}
