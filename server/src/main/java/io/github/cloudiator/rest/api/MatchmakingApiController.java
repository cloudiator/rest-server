package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.NodeCandidateConverter;
import io.github.cloudiator.rest.converter.NodeRequirementsConverter;
import io.github.cloudiator.rest.model.NodeCandidate;
import io.github.cloudiator.rest.model.NodeRequirements;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.entities.Matchmaking;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.MatchmakingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MatchmakingApiController implements MatchmakingApi {

  private static final Logger log = LoggerFactory.getLogger(MatchmakingApiController.class);

  private final HttpServletRequest request;


  private final MatchmakingService matchmakingService;


  private static final NodeRequirementsConverter NODE_REQUIREMENTS_CONVERTER = new NodeRequirementsConverter();
  private static final NodeCandidateConverter NODE_CANDIDATE_CONVERTER = new NodeCandidateConverter();

  @org.springframework.beans.factory.annotation.Autowired
  public MatchmakingApiController(HttpServletRequest request,
      MatchmakingService matchmakingService) {
    this.request = request;
    this.matchmakingService = matchmakingService;
  }

  public ResponseEntity<List<NodeCandidate>> solveMatchmaking(
      @ApiParam(value = "The requirements with respect to nodes", required = true) @Valid @RequestBody NodeRequirements nodeRequirements) {

    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        final Matchmaking.MatchmakingRequest matchmakingMessage = Matchmaking.MatchmakingRequest
            .newBuilder()
            .setNodeRequirements(
                NODE_REQUIREMENTS_CONVERTER.apply(nodeRequirements))
            .setUserId(UserInfo.of(request).tenant())
            .build();

        Matchmaking.MatchmakingResponse matchmakingResponse = matchmakingService
            .requestMatch(matchmakingMessage);

        List<NodeCandidate> response = matchmakingResponse.getCandidatesList().stream()
            .map(NODE_CANDIDATE_CONVERTER::applyBack).collect(
                Collectors.toList());

        return new ResponseEntity<>(
            response, HttpStatus.OK);
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
