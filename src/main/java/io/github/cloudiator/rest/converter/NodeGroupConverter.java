package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.NodeGroup;
import org.cloudiator.messages.NodeEntities;
import org.cloudiator.messages.NodeEntities.NodeGroup.Builder;

public class NodeGroupConverter implements TwoWayConverter<NodeGroup, NodeEntities.NodeGroup> {

  private final NodeConverter nodeConverter = new NodeConverter();

  @Override
  public NodeGroup applyBack(NodeEntities.NodeGroup nodeGroup) {

    NodeGroup rest = new NodeGroup();
    rest.setId(nodeGroup.getId());

    nodeGroup.getNodesList().stream().map(nodeConverter::applyBack).forEach(rest::addNodesItem);

    return rest;
  }

  @Override
  public NodeEntities.NodeGroup apply(NodeGroup nodeGroup) {

    final Builder builder = NodeEntities.NodeGroup.newBuilder();
    builder.setId(nodeGroup.getId());

    nodeGroup.getNodes().stream().map(nodeConverter::apply).forEach(builder::addNodes);

    return builder.build();
  }
}
