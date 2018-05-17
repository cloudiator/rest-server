package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Node.NodeTypeEnum;
import org.cloudiator.messages.NodeEntities.NodeType;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class NodeTypeConverter implements TwoWayConverter<NodeTypeEnum, NodeType> {

  @Override
  public NodeTypeEnum applyBack(NodeType nodeType) {
    return null;
  }

  @Override
  public NodeType apply(NodeTypeEnum nodeTypeEnum) {
    //from REST to protobuf

    switch (nodeTypeEnum){
      case BYON:
        return NodeType.BYON;
      case CONTAINER:
        return NodeType.CONTAINER;
      case UNKNOWN_TYPE:
        return NodeType.UNKNOWN_TYPE;
      case VM:
        return NodeType.VM;
      default:
        throw new AssertionError("Unrecognized nodeType " + nodeTypeEnum);
    }

  }
}
