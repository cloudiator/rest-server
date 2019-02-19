package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.NodeRequirementsConverter;
import io.github.cloudiator.rest.model.NodeRequirements;
import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.queue.QueueService;
import io.github.cloudiator.rest.queue.QueueService.QueueItem;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.entities.Matchmaking;
import org.cloudiator.messages.entities.Matchmaking.MatchmakingResponse;
import org.cloudiator.messaging.services.MatchmakingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MatchmakingApiController implements MatchmakingApi {

  private static final Logger log = LoggerFactory.getLogger(MatchmakingApiController.class);

  private final HttpServletRequest request;


  private final MatchmakingService matchmakingService;

  private final QueueService queueService;


  private static final NodeRequirementsConverter NODE_REQUIREMENTS_CONVERTER = new NodeRequirementsConverter();

  @org.springframework.beans.factory.annotation.Autowired
  public MatchmakingApiController(HttpServletRequest request,
      MatchmakingService matchmakingService,
      QueueService queueService) {
    this.request = request;
    this.matchmakingService = matchmakingService;
    this.queueService = queueService;
  }

  public ResponseEntity<Queue> solveMatchmaking(
      @ApiParam(value = "The requirements with respect to nodes", required = true) @Valid @RequestBody NodeRequirements nodeRequirements) {

    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        final String tenant = UserInfo.of(request).tenant();

        final Matchmaking.MatchmakingRequest matchmakingMessage = Matchmaking.MatchmakingRequest
            .newBuilder()
            .setNodeRequirements(
                NODE_REQUIREMENTS_CONVERTER.apply(nodeRequirements))
            .setUserId(tenant)
            .build();

        final QueueItem<MatchmakingResponse> queueItem = queueService
            .queueCallback(tenant,
                matchmakingResponse -> "solution/" + matchmakingResponse.getSolution()
                    .getId());

        matchmakingService
            .requestMatchAsync(matchmakingMessage, queueItem.getCallback());

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

        return new ResponseEntity<>(queueItem.getQueue(), httpHeaders, HttpStatus.ACCEPTED);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
