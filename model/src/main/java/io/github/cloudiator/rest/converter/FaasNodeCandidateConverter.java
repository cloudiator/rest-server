package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.NodeCandidate;
import org.cloudiator.messages.entities.MatchmakingEntities;

public class FaasNodeCandidateConverter implements
    TwoWayConverter<NodeCandidate, MatchmakingEntities.NodeCandidate> {

  private final CloudToCloudConverter cloudConverter = new CloudToCloudConverter();
  private final LocationConverter locationConverter = new LocationConverter();
  private final HardwareConverter hardwareConverter = new HardwareConverter();
  private final NodeCandidateTypeConverter nodeCandidateTypeConverter = new NodeCandidateTypeConverter();
  private final EnvironmentConverter environmentConverter = new EnvironmentConverter();

  @Override
  public NodeCandidate applyBack(MatchmakingEntities.NodeCandidate nodeCandidate) {
    return new NodeCandidate()
        .id(nodeCandidate.getId())
        .nodeCandidateType(nodeCandidateTypeConverter.applyBack(nodeCandidate.getType()))
        .cloud(cloudConverter.applyBack(nodeCandidate.getCloud()))
        .location(locationConverter.applyBack(nodeCandidate.getLocation()))
        .hardware(hardwareConverter.applyBack(nodeCandidate.getHardwareFlavor()))
        .pricePerInvocation(nodeCandidate.getPricePerInvocation())
        .memoryPrice(nodeCandidate.getMemoryPrice())
        .environment(environmentConverter.applyBack(nodeCandidate.getEnvironment()));
  }

  @Override
  public MatchmakingEntities.NodeCandidate apply(NodeCandidate nodeCandidate) {
    return MatchmakingEntities.NodeCandidate.newBuilder()
        .setId(nodeCandidate.getId())
        .setType(nodeCandidateTypeConverter.apply(nodeCandidate.getNodeCandidateType()))
        .setCloud(cloudConverter.apply(nodeCandidate.getCloud()))
        .setHardwareFlavor(hardwareConverter.apply(nodeCandidate.getHardware()))
        .setLocation(locationConverter.apply(nodeCandidate.getLocation()))
        .setPricePerInvocation(nodeCandidate.getPricePerInvocation())
        .setMemoryPrice(nodeCandidate.getMemoryPrice())
        .setEnvironment(environmentConverter.apply(nodeCandidate.getEnvironment())).build();
  }
}
