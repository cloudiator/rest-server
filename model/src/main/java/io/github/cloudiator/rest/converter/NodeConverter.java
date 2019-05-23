package io.github.cloudiator.rest.converter;

import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.Node;
import io.github.cloudiator.rest.model.Node.StateEnum;
import org.cloudiator.messages.NodeEntities;
import org.cloudiator.messages.NodeEntities.NodeState;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class NodeConverter implements TwoWayConverter<Node, NodeEntities.Node> {

  private final IpAddressConverter ipAddressConverter = new IpAddressConverter();
  private final NodeTypeConverter nodeTypeConverter = new NodeTypeConverter();
  private final NodePropertiesConverter nodePropertiesConverter = new NodePropertiesConverter();
  private final LoginCredentialConverter loginCredentialConverter = new LoginCredentialConverter();

  @Override
  public Node applyBack(NodeEntities.Node node) {

    Node rest = new Node();
    rest.setId(node.getId());
    rest.setOriginId(node.getOriginId());
    rest.setUserId(node.getUserId());
    rest.setName(node.getName());
    rest.setNodeType(nodeTypeConverter.applyBack(node.getNodeType()));
    rest.setLoginCredential(loginCredentialConverter.applyBack(node.getLoginCredential()));
    rest.setNodeProperties(nodePropertiesConverter.applyBack(node.getNodeProperties()));
    rest.setState(NodeStateConverter.INSTANCE.apply(node.getState()));

    if (!Strings.isNullOrEmpty(node.getDiagnostic())) {
      rest.setDiagnostic(node.getDiagnostic());
    }

    if (!Strings.isNullOrEmpty(node.getReason())) {
      rest.setReason(node.getReason());
    }

    node.getIpAddressesList().stream().map(ipAddressConverter::applyBack).forEach(
        rest::addIpAddressesItem);

    return rest;
  }

  @Override
  public NodeEntities.Node apply(Node node) {
    //from REST to protobuf
    NodeEntities.Node.Builder builder = NodeEntities.Node.newBuilder();

    builder
        .setState(NodeStateConverter.INSTANCE.applyBack(node.getState()))
        .setId(node.getId())
        .setOriginId(node.getOriginId())
        .setUserId(node.getUserId())
        .setName(node.getName())
        .setNodeType(nodeTypeConverter.apply(node.getNodeType()))
        .setNodeProperties(nodePropertiesConverter.apply(node.getNodeProperties()))
        .setLoginCredential(loginCredentialConverter.apply(node.getLoginCredential()));

    for (IpAddress ipAddress : node.getIpAddresses()) {
      builder.addIpAddresses(ipAddressConverter.apply(ipAddress));
    }

    if (node.getReason() != null) {
      builder.setReason(node.getReason());
    }

    if (node.getDiagnostic() != null) {
      builder.setDiagnostic(node.getDiagnostic());
    }

    return builder.build();
  }

  private static final class NodeStateConverter implements
      TwoWayConverter<NodeState, Node.StateEnum> {

    private static final NodeStateConverter INSTANCE = new NodeStateConverter();

    private NodeStateConverter() {

    }

    @Override
    public NodeState applyBack(StateEnum stateEnum) {
      switch (stateEnum) {
        case PENDING:
          return NodeState.NODE_STATE_PENDING;
        case ERROR:
          return NodeState.NODE_STATE_ERROR;
        case DELETED:
          return NodeState.NODE_STATE_DELETED;
        case RUNNING:
          return NodeState.NODE_STATE_RUNNING;
        default:
          throw new AssertionError("Unknown node state " + stateEnum);
      }
    }

    @Override
    public StateEnum apply(NodeState nodeState) {
      switch (nodeState) {
        case NODE_STATE_RUNNING:
          return StateEnum.RUNNING;
        case NODE_STATE_PENDING:
          return StateEnum.PENDING;
        case NODE_STATE_ERROR:
          return StateEnum.ERROR;
        case NODE_STATE_DELETED:
          return StateEnum.DELETED;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Illegal or unknown node state " + nodeState);
      }
    }
  }

}
