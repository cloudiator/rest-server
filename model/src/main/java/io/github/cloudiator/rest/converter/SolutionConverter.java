package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.Solution;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import org.cloudiator.messages.entities.MatchmakingEntities;
import org.cloudiator.messages.entities.MatchmakingEntities.NodeCandidate;

public class SolutionConverter implements OneWayConverter<MatchmakingEntities.Solution, Solution> {

  private static final NodeCandidateConverter NODE_CANDIDATE_CONVERTER = new NodeCandidateConverter();

  @Nullable
  @Override
  public Solution apply(@Nullable MatchmakingEntities.Solution solution) {
    if (solution == null) {
      return null;
    }

    Solution rest = new Solution();
    rest.id(solution.getId()).costs(solution.getCosts()).time(solution.getTime())
        .valid(solution.getIsValid()).isOptimal(solution.getIsOptimal());

    solution.getNodeCandidatesList().forEach(new Consumer<NodeCandidate>() {
      @Override
      public void accept(NodeCandidate nodeCandidate) {
        rest.addNodeCandidatesItem(NODE_CANDIDATE_CONVERTER.applyBack(nodeCandidate));
      }
    });

    return rest;
  }
}
