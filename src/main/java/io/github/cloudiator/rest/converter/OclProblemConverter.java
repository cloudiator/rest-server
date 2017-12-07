package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.OclProblem;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.cloudiator.messages.entities.CommonEntities.OclRequirement;
import org.cloudiator.messages.entities.MatchmakingEntities;

public class OclProblemConverter implements
    OneWayConverter<OclProblem, MatchmakingEntities.OclProblem> {

  @Nullable
  @Override
  public MatchmakingEntities.OclProblem apply(@Nullable OclProblem oclProblem) {

    if (oclProblem == null) {
      return null;
    }

    List<OclRequirement> requirements = oclProblem.getRequirements().stream()
        .map(oclRequirement -> OclRequirement.newBuilder()
            .setConstraint(oclRequirement.getConstraint()).build()).collect(
            Collectors.toList());

    return MatchmakingEntities.OclProblem.newBuilder()
        .addAllRequirements(requirements).build();
  }
}
