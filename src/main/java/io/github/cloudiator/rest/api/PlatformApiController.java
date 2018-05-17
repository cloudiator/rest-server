package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.model.NewPlatform;
import io.github.cloudiator.rest.model.Platform;
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
public class PlatformApiController implements PlatformApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public PlatformApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Platform> addPlatform(
      @ApiParam(value = "Platform to add", required = true) @Valid @RequestBody NewPlatform platform) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<Platform>(objectMapper.readValue("\"\"", Platform.class),
            HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Platform>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Platform>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Void> deletePlatform(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Platform> findPlatform(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<Platform>(objectMapper.readValue("\"\"", Platform.class),
            HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Platform>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Platform>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Platform>> findPlatforms() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<Platform>>(
            objectMapper.readValue("[ \"\", \"\" ]", List.class), HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<Platform>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<List<Platform>>(HttpStatus.NOT_IMPLEMENTED);
  }

}
