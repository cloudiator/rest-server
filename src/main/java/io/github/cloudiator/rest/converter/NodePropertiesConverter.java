package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.NodeProperties;
import org.cloudiator.messages.NodeEntities;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class NodePropertiesConverter implements
    TwoWayConverter<NodeProperties, NodeEntities.NodeProperties> {

  private final GeoLocationConverter geoLocationConverter = new GeoLocationConverter();
  private final OperatingSystemConverter operatingSystemConverter = new OperatingSystemConverter();

  @Override
  public NodeProperties applyBack(NodeEntities.NodeProperties nodeProperties) {

    NodeProperties rest = new NodeProperties();
    rest.setNumberOfCores(nodeProperties.getNumberOfCores());
    rest.setMemory(nodeProperties.getMemory());
    rest.setDisk((float) nodeProperties.getDisk());
    rest.setOperatingSystem(
        operatingSystemConverter.applyBack(nodeProperties.getOperationSystem()));
    rest.setGeoLocation(geoLocationConverter.applyBack(nodeProperties.getGeoLocation()));

    return rest;
  }

  @Override
  public NodeEntities.NodeProperties apply(NodeProperties nodeProperties) {
    //from REST to protobuf
    NodeEntities.NodeProperties.Builder builder = NodeEntities.NodeProperties.newBuilder();

    builder
        .setNumberOfCores(nodeProperties.getNumberOfCores())
        .setMemory(nodeProperties.getMemory())
        .setDisk(nodeProperties.getDisk())
        .setOperationSystem(operatingSystemConverter.apply(nodeProperties.getOperatingSystem()))
        .setGeoLocation(geoLocationConverter.apply(nodeProperties.getGeoLocation()));

    return builder.build();
  }
}
