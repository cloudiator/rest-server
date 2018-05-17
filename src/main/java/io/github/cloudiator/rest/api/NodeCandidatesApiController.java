package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.UserServiceOld;
import io.github.cloudiator.rest.converter.NodeCandidateConverter;
import io.github.cloudiator.rest.converter.NodeRequirementsConverter;
import io.github.cloudiator.rest.model.NodeCandidate;
import io.github.cloudiator.rest.model.NodeRequirements;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.entities.Matchmaking.NodeCandidateRequestMessage;
import org.cloudiator.messages.entities.MatchmakingEntities;
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
public class NodeCandidatesApiController implements NodeCandidatesApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  private static final NodeCandidateConverter NODE_CANDIDATE_CONVERTER =
      new NodeCandidateConverter();
  private static final NodeRequirementsConverter NODE_REQUIREMENTS_CONVERTER =
      new NodeRequirementsConverter();

  @org.springframework.beans.factory.annotation.Autowired
  public NodeCandidatesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Autowired
  private UserServiceOld userService;

  @Autowired
  private MatchmakingService matchmakingService;


  @Override
  public ResponseEntity<List<NodeCandidate>> findNodeCandidates(
      @ApiParam(value = "Node Request ") @Valid @RequestBody NodeRequirements nodeRequirements) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        final NodeCandidateRequestMessage.Builder builder =
            NodeCandidateRequestMessage.newBuilder()
                //.setUserId(userService.getUserId())
                .setUserId(UserInfo.of(request).tenant());

        if (nodeRequirements != null && nodeRequirements.getRequirements() != null
            && !nodeRequirements.getRequirements().isEmpty()) {
          builder.setRequirements(NODE_REQUIREMENTS_CONVERTER.apply(nodeRequirements));
        }

        final List<MatchmakingEntities.NodeCandidate> candidatesList =
            matchmakingService.requestNodes(builder.build()).getCandidatesList();

        return new ResponseEntity<>(
            candidatesList.stream().map(NODE_CANDIDATE_CONVERTER::applyBack)
                .collect(Collectors.toList()), HttpStatus.OK);
      } catch (ResponseException e) {
        return new ResponseEntity<>(HttpStatus.valueOf(e.code()));
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}
