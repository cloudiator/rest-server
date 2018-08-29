package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.MatchmakingResponse;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.cloudiator.messages.entities.Matchmaking;


public class MatchmakingResponseConverter implements
    OneWayConverter<Matchmaking.MatchmakingResponse, MatchmakingResponse> {

  private final NodeCandidateConverter nodeCandidateConverter = new NodeCandidateConverter();

  @Override
  public MatchmakingResponse apply(Matchmaking.MatchmakingResponse matchmakingResponse) {

    MatchmakingResponse response = new MatchmakingResponse();
    response.setNodes(
        matchmakingResponse.getCandidatesList().stream().map(nodeCandidateConverter::applyBack)
            .collect(
                Collectors.toList()));

    return response;
  }
}
