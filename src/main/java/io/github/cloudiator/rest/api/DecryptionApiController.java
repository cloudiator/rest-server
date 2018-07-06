package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.model.Text;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import org.cloudiator.messages.entities.Encryption.DecryptionRequest;
import org.cloudiator.messages.entities.Encryption.DecryptionResponse;
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
public class DecryptionApiController implements DecryptionApi {

  private static final Logger log = LoggerFactory.getLogger(DecryptionApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private EncryptionService encryptionService;

  @org.springframework.beans.factory.annotation.Autowired
  public DecryptionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Text> decrypt(
      @ApiParam(value = "Text to decrypt", required = true) @PathVariable("text") String text) {

    final String tenant = UserInfo.of(request).tenant();

    try {
      DecryptionResponse decryptionResponse = encryptionService
          .decrypt(DecryptionRequest.newBuilder().setUserId(tenant).setCiphertext(text).build());

      Text result = new Text();
      result.setContent(decryptionResponse.getPlaintext());

      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (ResponseException e) {
      throw new ApiException(e.code(), e.getMessage());
    }
  }

}
