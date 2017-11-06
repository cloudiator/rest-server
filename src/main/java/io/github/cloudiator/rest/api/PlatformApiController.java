package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.NewPlatform;
import io.github.cloudiator.rest.model.Platform;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Controller
public class PlatformApiController implements PlatformApi {

  private final ObjectMapper objectMapper;

  public PlatformApiController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public ResponseEntity<Platform> addPlatform(
      @ApiParam(value = "Platform to add", required = true) @Valid @RequestBody NewPlatform platform,
      @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
    // do some magic!

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<Platform>(objectMapper.readValue("\"\"", Platform.class),
          HttpStatus.OK);
    }

    return new ResponseEntity<Platform>(HttpStatus.OK);
  }

  public ResponseEntity<Void> deletePlatform(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id,
      @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
    // do some magic!
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<Platform> findPlatform(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id,
      @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
    // do some magic!

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<Platform>(objectMapper.readValue("\"\"", Platform.class),
          HttpStatus.OK);
    }

    return new ResponseEntity<Platform>(HttpStatus.OK);
  }

  public ResponseEntity<List<Platform>> findPlatforms(
      @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
    // do some magic!

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<List<Platform>>(
          objectMapper.readValue("[ \"\", \"\" ]", List.class), HttpStatus.OK);
    }

    return new ResponseEntity<List<Platform>>(HttpStatus.OK);
  }

}
