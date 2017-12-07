package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.OclSolution;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.cloudiator.messages.entities.Matchmaking.MatchmakingResponse;


public class OclSolutionConverter implements
    OneWayConverter<MatchmakingResponse, OclSolution> {

  private final VirtualMachineRequestConverter virtualMachineRequestConverter = new VirtualMachineRequestConverter();

  @Nullable
  @Override
  public OclSolution apply(@Nullable MatchmakingResponse matchmakingResponse) {
    OclSolution oclSolution = new OclSolution();
    oclSolution.setNodes(
        matchmakingResponse.getNodesList().stream().map(virtualMachineRequestConverter::applyBack)
            .collect(
                Collectors.toList()));

    return oclSolution;
  }
}
