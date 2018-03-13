package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.UserServiceOld;
import io.github.cloudiator.rest.converter.MatchmakingResponseConverter;
import io.github.cloudiator.rest.converter.NodeRequirementsConverter;
import io.github.cloudiator.rest.model.MatchmakingRequest;
import io.github.cloudiator.rest.model.MatchmakingResponse;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.entities.Matchmaking;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.MatchmakingService;
import org.cloudiator.messaging.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MatchmakingApiController implements MatchmakingApi {

  private static final Logger log = LoggerFactory.getLogger(MatchmakingApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;
  private UserInfo userInfo;

  @Autowired
  private MatchmakingService matchmakingService;

  @Autowired
  private UserServiceOld userService;


  private static final NodeRequirementsConverter NODE_REQUIREMENTS_CONVERTER = new NodeRequirementsConverter();
  private static final MatchmakingResponseConverter MATCHMAKING_RESPONSE_CONVERTER = new MatchmakingResponseConverter();

  @org.springframework.beans.factory.annotation.Autowired
  public MatchmakingApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<MatchmakingResponse> solveMatchmaking(
      @ApiParam(value = "The matchmaking request to solve", required = true) @Valid @RequestBody MatchmakingRequest matchmakingRequest) {

    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        userInfo = new UserInfo(request);
        final Matchmaking.MatchmakingRequest request = Matchmaking.MatchmakingRequest
            .newBuilder()
            .setRequirements(
                NODE_REQUIREMENTS_CONVERTER.apply(matchmakingRequest.getRequirements()))
            //.setUserId(userService.getUserId())
            .setUserId(userInfo.currentUserTenant())
            .build();

        Matchmaking.MatchmakingResponse matchmakingResponse = matchmakingService
            .requestMatch(request);

        return new ResponseEntity<>(
            MATCHMAKING_RESPONSE_CONVERTER.apply(matchmakingResponse), HttpStatus.OK);
      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
