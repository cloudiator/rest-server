package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.OclSolution;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.cloudiator.messages.entities.SolutionEntities;

public class OclSolutionConverter implements
    OneWayConverter<SolutionEntities.OclSolution, OclSolution> {

  private final VirtualMachineRequestConverter virtualMachineRequestConverter = new VirtualMachineRequestConverter();

  @Nullable
  @Override
  public OclSolution apply(@Nullable SolutionEntities.OclSolution oclSolutionMessage) {
    if (oclSolutionMessage == null) {
      return null;
    }

    OclSolution oclSolution = new OclSolution();
    oclSolution.setNodes(
        oclSolutionMessage.getNodesList().stream().map(virtualMachineRequestConverter::applyBack).collect(
            Collectors.toList()));

    return oclSolution;
  }
}
