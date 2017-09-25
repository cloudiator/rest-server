package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.NodeRequest;
import java.util.stream.Collectors;
import org.cloudiator.messages.NodeEntities;

public class NodeRequestConverter implements
    TwoWayConverter<NodeRequest, NodeEntities.NodeRequest> {

  private final RequirementConverter requirementConverter = new RequirementConverter();

  @Override
  public NodeRequest applyBack(NodeEntities.NodeRequest nodeRequest) {
    throw new UnsupportedOperationException();
  }

  @Override
  public NodeEntities.NodeRequest apply(NodeRequest nodeRequest) {
    return NodeEntities.NodeRequest.newBuilder().addAllRequirements(
        nodeRequest.getRequirements().stream()
            .map(requirementConverter::apply).collect(
            Collectors.toList())).build();
  }
}
