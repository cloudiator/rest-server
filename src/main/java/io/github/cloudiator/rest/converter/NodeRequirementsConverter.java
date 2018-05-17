package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.NodeRequirements;
import java.util.stream.Collectors;
import org.cloudiator.messages.NodeEntities;

public class NodeRequirementsConverter implements
    TwoWayConverter<NodeRequirements, NodeEntities.NodeRequirements> {

  private final RequirementConverter requirementConverter = new RequirementConverter();

  @Override
  public NodeRequirements applyBack(NodeEntities.NodeRequirements nodeRequirements) {
    throw new UnsupportedOperationException();
  }

  @Override
  public NodeEntities.NodeRequirements apply(NodeRequirements nodeRequirements) {
    return NodeEntities.NodeRequirements.newBuilder().addAllRequirements(
        nodeRequirements.getRequirements().stream()
            .map(requirementConverter).collect(
            Collectors.toList())).build();
  }
}
