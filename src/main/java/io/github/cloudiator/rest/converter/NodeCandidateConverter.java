package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.NodeCandidate;
import org.cloudiator.messages.entities.MatchmakingEntities;

public class NodeCandidateConverter implements
    TwoWayConverter<NodeCandidate, MatchmakingEntities.NodeCandidate> {

  public final CloudToCloudConverter cloudConverter = new CloudToCloudConverter();
  public final ImageConverter imageConverter = new ImageConverter();
  public final HardwareConverter hardwareConverter = new HardwareConverter();
  public final LocationConverter locationConverter = new LocationConverter();


  @Override
  public NodeCandidate applyBack(MatchmakingEntities.NodeCandidate nodeCandidate) {

    NodeCandidate result = new NodeCandidate()
        .cloud(cloudConverter.applyBack(nodeCandidate.getCloud()))
        .hardware(hardwareConverter.applyBack(nodeCandidate.getHardwareFlavor()))
        .image(imageConverter.applyBack(nodeCandidate.getImage()))
        .location(locationConverter.applyBack(nodeCandidate.getLocation()))
        .price(nodeCandidate.getPrice());

    return result;
  }

  @Override
  public MatchmakingEntities.NodeCandidate apply(NodeCandidate nodeCandidate) {

    MatchmakingEntities.NodeCandidate.Builder builder = MatchmakingEntities.NodeCandidate
        .newBuilder();

    builder.setCloud(cloudConverter.apply(nodeCandidate.getCloud()))
        .setHardwareFlavor(hardwareConverter.apply(nodeCandidate.getHardware()))
        .setImage(imageConverter.apply(nodeCandidate.getImage()))
        .setLocation(locationConverter.apply(nodeCandidate.getLocation()))
        .setPrice(nodeCandidate.getPrice());

    return builder.build();
  }
}
