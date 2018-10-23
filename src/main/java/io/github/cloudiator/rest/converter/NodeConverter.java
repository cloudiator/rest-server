package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.Node;
import org.cloudiator.messages.NodeEntities;

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
    rest.setNodeId(node.getId());
    rest.setName(node.getName());
    rest.setNodeType(nodeTypeConverter.applyBack(node.getNodeType()));
    rest.setLoginCredential(loginCredentialConverter.applyBack(node.getLoginCredential()));
    rest.setNodeProperties(nodePropertiesConverter.applyBack(node.getNodeProperties()));

    node.getIpAddressesList().stream().map(ipAddressConverter::applyBack).forEach(
        rest::addIpAddressesItem);

    return rest;
  }

  @Override
  public NodeEntities.Node apply(Node node) {
    //from REST to protobuf
    NodeEntities.Node.Builder builder = NodeEntities.Node.newBuilder();

    builder
        .setId(node.getNodeId())
        .setName(node.getName())
        .setNodeType(nodeTypeConverter.apply(node.getNodeType()))
        .setNodeProperties(nodePropertiesConverter.apply(node.getNodeProperties()))
        .setLoginCredential(loginCredentialConverter.apply(node.getLoginCredential()));

    for (IpAddress ipAddress : node.getIpAddresses()) {
      builder.addIpAddresses(ipAddressConverter.apply(ipAddress));
    }

    return builder.build();
  }
}
