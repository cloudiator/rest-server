package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.NodeCandidate;
import org.cloudiator.messages.entities.MatchmakingEntities;

public class NodeCandidateConverter implements
    TwoWayConverter<NodeCandidate, MatchmakingEntities.NodeCandidate> {

  private final IaasNodeCandidateConverter iaasNodeCandidateConverter = new IaasNodeCandidateConverter();
  private final FaasNodeCandidateConverter faasNodeCandidateConverter = new FaasNodeCandidateConverter();


  @Override
  public NodeCandidate applyBack(MatchmakingEntities.NodeCandidate nodeCandidate) {
    switch (nodeCandidate.getType()) {
      case IAAS:
        return iaasNodeCandidateConverter.applyBack(nodeCandidate);
      case FAAS:
        return faasNodeCandidateConverter.applyBack(nodeCandidate);
      case UNRECOGNIZED:
      default:
        throw new IllegalStateException(
            "Unknown node candidate type " + nodeCandidate.getType());
    }
  }

  @Override
  public MatchmakingEntities.NodeCandidate apply(NodeCandidate nodeCandidate) {
    switch (nodeCandidate.getNodeCandidateType()) {
      case IAAS:
        return iaasNodeCandidateConverter.apply(nodeCandidate);
      case FAAS:
        return faasNodeCandidateConverter.apply(nodeCandidate);
      default:
        throw new IllegalStateException(
            "Unknown node candidate type " + nodeCandidate.getNodeCandidateType());
    }
  }
}
