package io.github.cloudiator.rest.converter;

import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.ByonNode;
import io.github.cloudiator.rest.model.IpAddress;
import org.cloudiator.messages.Byon;

public class ByonConverter implements TwoWayConverter<ByonNode, Byon.ByonNode> {

  private final IpAddressConverter ipAddressConverter = new IpAddressConverter();
  private final NodeTypeConverter nodeTypeConverter = new NodeTypeConverter();
  private final NodePropertiesConverter nodePropertiesConverter = new NodePropertiesConverter();
  private final LoginCredentialConverter loginCredentialConverter = new LoginCredentialConverter();

  @Override
  public ByonNode applyBack(Byon.ByonNode byonNode) {
    Byon.ByonData data = byonNode.getNodeData();

    ByonNode rest = new ByonNode();
    rest.setId(byonNode.getId());
    rest.setName(data.getName());
    rest.setLoginCredential(loginCredentialConverter.applyBack(data.getLoginCredentials()));
    rest.setNodeProperties(nodePropertiesConverter.applyBack(data.getProperties()));
    rest.setNodeCandidate(data.getNodeCandidate());
    rest.setAllocated(data.getAllocated());

    if (!Strings.isNullOrEmpty(data.getDiagnostic())) {
      rest.setDiagnostic(data.getDiagnostic());
    }

    if (!Strings.isNullOrEmpty(data.getReason())) {
      rest.setReason(data.getReason());
    }

    data.getIpAddressList().stream().map(ipAddressConverter::applyBack).forEach(
        rest::addIpAddressesItem);

    return rest;
  }

  @Override
  public Byon.ByonNode apply(ByonNode byonNode) {
    //from REST to protobuf
    Byon.ByonData.Builder dataBuilder = Byon.ByonData.newBuilder();
    Byon.ByonNode.Builder nodeBuilder = Byon.ByonNode.newBuilder();

    dataBuilder
        .setName(byonNode.getName())
        .setLoginCredentials(loginCredentialConverter.apply(byonNode.getLoginCredential()))
        .setProperties(nodePropertiesConverter.apply(byonNode.getNodeProperties()))
        .setNodeCandidate(byonNode.getNodeCandidate())
        .setAllocated(byonNode.isAllocated());

    for (IpAddress ipAddress : byonNode.getIpAddresses()) {
      dataBuilder.addIpAddress(ipAddressConverter.apply(ipAddress));
    }

    if (byonNode.getReason() != null) {
      dataBuilder.setReason(byonNode.getReason());
    }

    if (byonNode.getDiagnostic() != null) {
      dataBuilder.setDiagnostic(byonNode.getDiagnostic());
    }

    Byon.ByonData data = dataBuilder.build();
    nodeBuilder.setId(byonNode.getId()).setNodeData(data);

    return nodeBuilder.build();
  }
}