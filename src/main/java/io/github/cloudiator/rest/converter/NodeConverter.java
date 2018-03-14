package io.github.cloudiator.rest.converter;

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
    return null;
  }

  @Override
  public NodeEntities.Node apply(Node node) {
    //from REST to protobuf
    NodeEntities.Node.Builder builder = NodeEntities.Node.newBuilder();

    builder
        .setId(node.getNodeId())
        .setNodeType(nodeTypeConverter.apply(node.getNodeType()))
        .setNodeProperties(nodePropertiesConverter.apply(node.getNodeProperties()))
        .setLoginCredential(loginCredentialConverter.apply(node.getLoginCredential()));


    //ipAddresses
    for(int i=0; i < node.getIpAddresses().size(); i++){
      builder.setIpAddresses(i,ipAddressConverter.apply(node.getIpAddresses().get(i)));
    }

    return builder.build();
  }
}
