package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.SolutionConverter;
import io.github.cloudiator.rest.model.Solution;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import org.cloudiator.messages.entities.Matchmaking.MatchmakingResponse;
import org.cloudiator.messages.entities.Matchmaking.SolutionRequest;
import org.cloudiator.messages.entities.MatchmakingEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.MatchmakingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SolutionApiController implements SolutionApi {

  private static final Logger log = LoggerFactory.getLogger(SolutionApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  private final MatchmakingService matchmakingService;
  private final SolutionConverter solutionConverter = new SolutionConverter();

  @org.springframework.beans.factory.annotation.Autowired
  public SolutionApiController(ObjectMapper objectMapper, HttpServletRequest request,
      MatchmakingService matchmakingService) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.matchmakingService = matchmakingService;
  }

  public ResponseEntity<Solution> getSolution(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      if (Strings.isNullOrEmpty(id)) {
        throw new ApiException(400, "id not provided.");
      }

      final String tenant = UserInfo.of(request).tenant();

      try {
        final MatchmakingResponse matchmakingResponse = matchmakingService.retrieveSolution(
            SolutionRequest.newBuilder().setUserId(tenant).setSolution(id).build());

        final MatchmakingEntities.Solution solution = matchmakingResponse.getSolution();

        return new ResponseEntity<>(
            solutionConverter.apply(solution), HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}
