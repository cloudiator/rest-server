package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import org.cloudiator.messages.entities.IaasEntities;
import io.github.cloudiator.rest.model.NodeCandidate;

public class NodeCandidateConverter implements
    TwoWayConverter<NodeCandidate, IaasEntities.NodeCandidate> {

  public final CloudToCloudConverter cloudConverter = new CloudToCloudConverter();
  public final ImageConverter imageConverter = new ImageConverter();
  public final HardwareConverter hardwareConverter = new HardwareConverter();
  public final LocationConverter locationConverter = new LocationConverter();


  @Override
  public NodeCandidate applyBack(IaasEntities.NodeCandidate nodeCandidate) {

    NodeCandidate result = new NodeCandidate()
        .cloud(cloudConverter.applyBack(nodeCandidate.getCloud()))
        .hardware(hardwareConverter.applyBack(nodeCandidate.getHardwareFlavor()))
        .image(imageConverter.applyBack(nodeCandidate.getImage()))
        .location(locationConverter.applyBack(nodeCandidate.getLocation()));

    return result;
  }

  @Override
  public IaasEntities.NodeCandidate apply(NodeCandidate nodeCandidate) {

    IaasEntities.NodeCandidate.Builder builder = IaasEntities.NodeCandidate.newBuilder();

    builder.setCloud(cloudConverter.apply(nodeCandidate.getCloud()))
        .setHardwareFlavor(hardwareConverter.apply(nodeCandidate.getHardware()))
        .setImage(imageConverter.apply(nodeCandidate.getImage()))
        .setLocation(locationConverter.apply(nodeCandidate.getLocation()));

    return builder.build();
  }
}
