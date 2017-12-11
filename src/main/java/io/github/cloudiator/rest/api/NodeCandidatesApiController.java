package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.NodeCandidateConverter;
import io.github.cloudiator.rest.converter.NodeRequirementsConverter;
import io.github.cloudiator.rest.model.NodeCandidate;
import io.github.cloudiator.rest.model.NodeRequirements;
import org.cloudiator.messages.entities.Matchmaking.NodeCandidateRequestMessage;
import org.cloudiator.messages.entities.MatchmakingEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.MatchmakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller public class NodeCandidatesApiController implements NodeCandidatesApi {

    private final ObjectMapper objectMapper;
    private static final NodeCandidateConverter NODE_CANDIDATE_CONVERTER =
        new NodeCandidateConverter();
    private static final NodeRequirementsConverter NODE_REQUIREMENTS_CONVERTER =
        new NodeRequirementsConverter();

    @Autowired private UserService userService;

    @Autowired private MatchmakingService matchmakingService;

    public NodeCandidatesApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<List<NodeCandidate>> findNodeCandidates(NodeRequirements nodeRequirements,
        String accept) throws Exception {
        if (accept != null && accept.contains("application/json")) {

            try {

                final NodeCandidateRequestMessage.Builder builder =
                    NodeCandidateRequestMessage.newBuilder().setUserId(userService.getUserId());

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
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
