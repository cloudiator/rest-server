package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.MatchmakingResponse;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.cloudiator.messages.entities.Matchmaking;


public class MatchmakingResponseConverter implements
    OneWayConverter<Matchmaking.MatchmakingResponse, MatchmakingResponse> {

  private final VirtualMachineRequestConverter virtualMachineRequestConverter = new VirtualMachineRequestConverter();

  @Nullable
  @Override
  public MatchmakingResponse apply(@Nullable Matchmaking.MatchmakingResponse matchmakingResponse) {

    MatchmakingResponse response = new MatchmakingResponse();
    response.setNodes(
        matchmakingResponse.getNodesList().stream().map(virtualMachineRequestConverter::applyBack)
            .collect(
                Collectors.toList()));

    return response;
  }
}
