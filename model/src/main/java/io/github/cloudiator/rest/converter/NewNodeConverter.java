package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.NewNode;
import org.cloudiator.messages.Byon;

public class NewNodeConverter implements OneWayConverter<NewNode, Byon.ByonNode> {

  private final IpAddressConverter ipAddressConverter = new IpAddressConverter();
  private final NodeTypeConverter nodeTypeConverter = new NodeTypeConverter();
  private final NodePropertiesConverter nodePropertiesConverter = new NodePropertiesConverter();
  private final LoginCredentialConverter loginCredentialConverter = new LoginCredentialConverter();

  @Override
  public Byon.ByonNode apply(NewNode newNode) {
    //from REST to protobuf
    Byon.ByonData.Builder dataBuilder = Byon.ByonData.newBuilder();
    Byon.ByonNode.Builder nodeBuilder = Byon.ByonNode.newBuilder();

    dataBuilder
        .setName(newNode.getName())
        .setLoginCredentials(loginCredentialConverter.apply(newNode.getLoginCredential()))
        .setProperties(nodePropertiesConverter.apply(newNode.getNodeProperties()))
        .setAllocated(false);

    for (IpAddress ipAddress : newNode.getIpAddresses()) {
      dataBuilder.addIpAddress(ipAddressConverter.apply(ipAddress));
    }

    if (newNode.getNodeCandidate() != null) {
      dataBuilder.setNodeCandidate(newNode.getNodeCandidate());
    }

    if (newNode.getReason() != null) {
      dataBuilder.setReason(newNode.getReason());
    }

    if (newNode.getDiagnostic() != null) {
      dataBuilder.setDiagnostic(newNode.getDiagnostic());
    }

    Byon.ByonData data = dataBuilder.build();
    nodeBuilder.setNodeData(data);
    return nodeBuilder.build();
  }
}
