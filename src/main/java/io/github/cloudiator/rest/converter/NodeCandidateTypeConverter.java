package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.NodeCandidate;
import org.cloudiator.messages.entities.MatchmakingEntities;

import static io.github.cloudiator.rest.model.NodeCandidate.NodeCandidateTypeEnum.FAAS;
import static io.github.cloudiator.rest.model.NodeCandidate.NodeCandidateTypeEnum.IAAS;

public class NodeCandidateTypeConverter implements
    TwoWayConverter<NodeCandidate.NodeCandidateTypeEnum, MatchmakingEntities.NodeCandidateType> {

  @Override
  public NodeCandidate.NodeCandidateTypeEnum applyBack(MatchmakingEntities.NodeCandidateType type) {
    switch (type) {
      case NC_IAAS:
        return IAAS;
      case NC_FAAS:
        return FAAS;
      case NC_PAAS:
      case NC_BYON:
      case UNRECOGNIZED:
      default:
        throw new IllegalStateException("Unknown node candidate type " + type);
    }
  }

  @Override
  public MatchmakingEntities.NodeCandidateType apply(NodeCandidate.NodeCandidateTypeEnum type) {
    switch (type) {
      case IAAS:
        return MatchmakingEntities.NodeCandidateType.NC_IAAS;
      case FAAS:
        return MatchmakingEntities.NodeCandidateType.NC_FAAS;
      case PAAS:
      case BYON:
      default:
        throw new IllegalStateException("Unknown node candidate type " + type);
    }
  }
}
