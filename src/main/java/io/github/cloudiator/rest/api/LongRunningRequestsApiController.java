package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.LRRMapService;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.UserServiceOld;
import io.github.cloudiator.rest.model.LongRunningRequest;

import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
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

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;

@Controller
public class LongRunningRequestsApiController implements LongRunningRequestsApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public LongRunningRequestsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Autowired
  private LRRMapService lrrMapService;

  @Autowired
  private UserServiceOld userService;


  public ResponseEntity<List<LongRunningRequest>> findAllLongRunningRequest() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        // List<LongRunningRequest> result = lrrMapService.getAllLRR(userService.getUserId());
        List<LongRunningRequest> result = lrrMapService.getAllLRR(UserInfo.of(request).tenant());

        return new ResponseEntity<List<LongRunningRequest>>(result, HttpStatus.OK);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<LongRunningRequest> findLongRunningRequest(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      LongRunningRequest result = null;
      if (lrrMapService.getLRR(UserInfo.of(request).tenant(), id) != null) {
        result = lrrMapService.getLRR(UserInfo.of(request).tenant()/*userService.getUserId()*/, id);
      } else {
        throw new ApiException(404, "LRR not found. ID: " + id);
      }

      return new ResponseEntity<LongRunningRequest>(result, HttpStatus.OK);

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
