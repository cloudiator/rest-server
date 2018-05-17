package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.model.NewPlatformEnvironment;
import io.github.cloudiator.rest.model.PlatformEnvironment;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PlatformEnvironmentApiController implements PlatformEnvironmentApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformEnvironmentApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public PlatformEnvironmentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<PlatformEnvironment> addPlatformEnvironment(
      @ApiParam(value = "PlatformEnvironment to be created ", required = true) @Valid @RequestBody NewPlatformEnvironment platformEnvironment) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<PlatformEnvironment>(
            objectMapper.readValue("\"\"", PlatformEnvironment.class), HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<PlatformEnvironment>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<PlatformEnvironment>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<PlatformEnvironment> findPlatformEnvironment(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<PlatformEnvironment>(
            objectMapper.readValue("\"\"", PlatformEnvironment.class), HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<PlatformEnvironment>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<PlatformEnvironment>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<PlatformEnvironment>> findPlatformEnvironments() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<PlatformEnvironment>>(
            objectMapper.readValue("[ \"\", \"\" ]", List.class), HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<PlatformEnvironment>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<List<PlatformEnvironment>>(HttpStatus.NOT_IMPLEMENTED);
  }

}
