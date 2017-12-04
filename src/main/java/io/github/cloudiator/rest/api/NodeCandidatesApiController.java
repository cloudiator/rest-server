package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.NodeCandidateConverter;
import io.github.cloudiator.rest.model.NodeCandidate;
import io.github.cloudiator.rest.model.NodeRequirements;
import java.util.List;
import java.util.stream.Collectors;
import org.cloudiator.messages.NodeCandidate.NodeCandidateRequestMessage;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.NodeCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class NodeCandidatesApiController implements NodeCandidatesApi {

  private final ObjectMapper objectMapper;
  private static final NodeCandidateConverter NODE_CANDIDATE_CONVERTER = new NodeCandidateConverter();

  @Autowired
  private UserService userService;

  @Autowired
  private NodeCandidateService nodeCandidateService;

  public NodeCandidatesApiController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public ResponseEntity<List<NodeCandidate>> findNodeCandidates(NodeRequirements nodeRequirements,
      String accept) throws Exception {
    if (accept != null && accept.contains("application/json")) {

      try {
        final List<IaasEntities.NodeCandidate> candidatesList = nodeCandidateService.requestNodes(
            NodeCandidateRequestMessage.newBuilder().setUserId(userService.getUserId()).build())
            .getCandidatesList();
        return new ResponseEntity<>(
            candidatesList.stream().map(NODE_CANDIDATE_CONVERTER::applyBack).collect(
                Collectors.toList()), HttpStatus.OK);
      } catch (ResponseException e) {
        return new ResponseEntity<>(HttpStatus.valueOf(e.code()));
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
  }
}
