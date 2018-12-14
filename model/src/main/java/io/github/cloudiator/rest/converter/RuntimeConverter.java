package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Runtime;
import org.cloudiator.messages.entities.MatchmakingEntities;

public class RuntimeConverter implements TwoWayConverter<Runtime, MatchmakingEntities.Runtime> {

  @Override
  public Runtime applyBack(MatchmakingEntities.Runtime runtime) {
    switch (runtime) {
      case NODEJS:
        return Runtime.NODEJS;
      case PYTHON:
        return Runtime.PYTHON;
      case JAVA:
        return Runtime.JAVA;
      case DOTNET:
        return Runtime.DOTNET;
      case GO:
        return Runtime.GO;
      case UNRECOGNIZED:
      default:
        throw new IllegalStateException("Unknown runtime " + runtime);
    }  }

  @Override
  public MatchmakingEntities.Runtime apply(Runtime runtime) {
    switch (runtime) {
      case NODEJS:
        return MatchmakingEntities.Runtime.NODEJS;
      case PYTHON:
        return MatchmakingEntities.Runtime.PYTHON;
      case JAVA:
        return MatchmakingEntities.Runtime.JAVA;
      case DOTNET:
        return MatchmakingEntities.Runtime.DOTNET;
      case GO:
        return MatchmakingEntities.Runtime.GO;
      default:
        throw new IllegalStateException("Unknown runtime " + runtime);
    }
  }
}
