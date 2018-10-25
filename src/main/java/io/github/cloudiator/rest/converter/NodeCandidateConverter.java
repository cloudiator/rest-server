package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.NodeCandidate;
import org.cloudiator.messages.entities.MatchmakingEntities;

public class NodeCandidateConverter implements
    TwoWayConverter<NodeCandidate, MatchmakingEntities.NodeCandidate> {

  private final CloudToCloudConverter cloudConverter = new CloudToCloudConverter();
  private final ImageConverter imageConverter = new ImageConverter();
  private final HardwareConverter hardwareConverter = new HardwareConverter();
  private final LocationConverter locationConverter = new LocationConverter();


  @Override
  public NodeCandidate applyBack(MatchmakingEntities.NodeCandidate nodeCandidate) {

    return new NodeCandidate()
        .id(nodeCandidate.getId())
        .cloud(cloudConverter.applyBack(nodeCandidate.getCloud()))
        .hardware(hardwareConverter.applyBack(nodeCandidate.getHardwareFlavor()))
        .image(imageConverter.applyBack(nodeCandidate.getImage()))
        .location(locationConverter.applyBack(nodeCandidate.getLocation()))
        .price(nodeCandidate.getPrice());
  }

  @Override
  public MatchmakingEntities.NodeCandidate apply(NodeCandidate nodeCandidate) {

    MatchmakingEntities.NodeCandidate.Builder builder = MatchmakingEntities.NodeCandidate
        .newBuilder();

    builder.setId(nodeCandidate.getId())
        .setCloud(cloudConverter.apply(nodeCandidate.getCloud()))
        .setHardwareFlavor(hardwareConverter.apply(nodeCandidate.getHardware()))
        .setImage(imageConverter.apply(nodeCandidate.getImage()))
        .setLocation(locationConverter.apply(nodeCandidate.getLocation()))
        .setPrice(nodeCandidate.getPrice());

    return builder.build();
  }
}
