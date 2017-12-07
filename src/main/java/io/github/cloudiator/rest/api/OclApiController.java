package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.OclProblemConverter;
import io.github.cloudiator.rest.converter.OclSolutionConverter;
import io.github.cloudiator.rest.model.OclProblem;
import io.github.cloudiator.rest.model.OclSolution;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import org.cloudiator.messages.entities.Matchmaking.MatchmakingResponse;
import org.cloudiator.messages.entities.Matchmaking.OclSolutionRequest;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.MatchmakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OclApiController implements OclApi {

  private final ObjectMapper objectMapper;

  public OclApiController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  private final OclProblemConverter oclProblemConverter = new OclProblemConverter();
  private final OclSolutionConverter oclSolutionConverter = new OclSolutionConverter();

  @Autowired
  private MatchmakingService matchmakingService;

  @Autowired
  private UserService userService;


  @Override
  public ResponseEntity<OclSolution> solveOCL(
      @ApiParam(value = "OCL Problem to solve", required = true) @Valid @RequestBody OclProblem oclProblem,
      String accept) {

    try {

      final OclSolutionRequest oclSolutionRequest = OclSolutionRequest.newBuilder()
          .setProblem(oclProblemConverter.apply(oclProblem))
          .setUserId(userService.getUserId()).build();

      MatchmakingResponse matchmakingResponse = matchmakingService
          .solveOCLProblem(oclSolutionRequest);

      if (accept != null && accept.contains("application/json")) {
        return new ResponseEntity<>(
            oclSolutionConverter.apply(matchmakingResponse), HttpStatus.OK);
      }
      return new ResponseEntity<>(
          oclSolutionConverter.apply(matchmakingResponse), HttpStatus.OK);
    } catch (ResponseException e) {
      throw new ApiException(e.code(), e.getMessage());
    }
  }

}
