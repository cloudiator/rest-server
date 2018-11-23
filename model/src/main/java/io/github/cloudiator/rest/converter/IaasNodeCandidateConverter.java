package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.NodeCandidate;
import org.cloudiator.messages.entities.MatchmakingEntities;

public class IaasNodeCandidateConverter implements
    TwoWayConverter<NodeCandidate, MatchmakingEntities.NodeCandidate> {

  private final CloudToCloudConverter cloudConverter = new CloudToCloudConverter();
  private final ImageConverter imageConverter = new ImageConverter();
  private final HardwareConverter hardwareConverter = new HardwareConverter();
  private final LocationConverter locationConverter = new LocationConverter();
  private final NodeCandidateTypeConverter nodeCandidateTypeConverter = new NodeCandidateTypeConverter();

  @Override
  public NodeCandidate applyBack(MatchmakingEntities.NodeCandidate nodeCandidate) {
    return new NodeCandidate()
        .id(nodeCandidate.getId())
        .nodeCandidateType(nodeCandidateTypeConverter.applyBack(nodeCandidate.getType()))
        .cloud(cloudConverter.applyBack(nodeCandidate.getCloud()))
        .location(locationConverter.applyBack(nodeCandidate.getLocation()))
        .hardware(hardwareConverter.applyBack(nodeCandidate.getHardwareFlavor()))
        .image(imageConverter.applyBack(nodeCandidate.getImage()))
        .price(nodeCandidate.getPrice());
  }

  @Override
  public MatchmakingEntities.NodeCandidate apply(NodeCandidate nodeCandidate) {
    return MatchmakingEntities.NodeCandidate.newBuilder()
        .setId(nodeCandidate.getId())
        .setType(nodeCandidateTypeConverter.apply(nodeCandidate.getNodeCandidateType()))
        .setCloud(cloudConverter.apply(nodeCandidate.getCloud()))
        .setHardwareFlavor(hardwareConverter.apply(nodeCandidate.getHardware()))
        .setImage(imageConverter.apply(nodeCandidate.getImage()))
        .setLocation(locationConverter.apply(nodeCandidate.getLocation()))
        .setPrice(nodeCandidate.getPrice())
        .build();
  }
}
