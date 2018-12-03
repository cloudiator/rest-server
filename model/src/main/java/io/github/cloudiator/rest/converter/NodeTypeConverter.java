package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Node.NodeTypeEnum;
import org.cloudiator.messages.NodeEntities.NodeType;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class NodeTypeConverter implements TwoWayConverter<NodeTypeEnum, NodeType> {

  @Override
  public NodeTypeEnum applyBack(NodeType nodeType) {

    switch (nodeType) {
      case VM:
        return NodeTypeEnum.VM;
      case BYON:
        return NodeTypeEnum.BYON;
      case CONTAINER:
        return NodeTypeEnum.CONTAINER;
      case UNKNOWN_TYPE:
        return NodeTypeEnum.UNKNOWN_TYPE;
      case FAAS:
        return NodeTypeEnum.FAAS;
      case UNRECOGNIZED:
      default:
        throw new AssertionError("Unrecognized nodeType " + nodeType);
    }
  }

  @Override
  public NodeType apply(NodeTypeEnum nodeTypeEnum) {
    //from REST to protobuf

    switch (nodeTypeEnum) {
      case BYON:
        return NodeType.BYON;
      case CONTAINER:
        return NodeType.CONTAINER;
      case UNKNOWN_TYPE:
        return NodeType.UNKNOWN_TYPE;
      case VM:
        return NodeType.VM;
      case FAAS:
        return NodeType.FAAS;
      default:
        throw new AssertionError("Unrecognized nodeType " + nodeTypeEnum);
    }

  }
}
