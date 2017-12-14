package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.OclProblemConverter;
import io.github.cloudiator.rest.converter.OclSolutionConverter;
import io.github.cloudiator.rest.model.OclProblem;
import io.github.cloudiator.rest.model.OclSolution;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.entities.Matchmaking.MatchmakingResponse;
import org.cloudiator.messages.entities.Matchmaking.OclSolutionRequest;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.MatchmakingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OclApiController implements OclApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public OclApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  private final OclProblemConverter oclProblemConverter = new OclProblemConverter();
  private final OclSolutionConverter oclSolutionConverter = new OclSolutionConverter();

  @Autowired
  private MatchmakingService matchmakingService;

  @Autowired
  private UserService userService;


  public ResponseEntity<OclSolution> solveOCL(
      @ApiParam(value = "OCL Problem to solve", required = true) @Valid @RequestBody OclProblem oclProblem) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        final OclSolutionRequest oclSolutionRequest = OclSolutionRequest.newBuilder()
            .setProblem(oclProblemConverter.apply(oclProblem))
            .setUserId(userService.getUserId()).build();

        MatchmakingResponse matchmakingResponse = matchmakingService
            .solveOCLProblem(oclSolutionRequest);

        return new ResponseEntity<>(
            oclSolutionConverter.apply(matchmakingResponse), HttpStatus.OK);
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
