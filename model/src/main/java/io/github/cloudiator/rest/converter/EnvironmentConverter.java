package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Environment;
import io.github.cloudiator.rest.model.Runtime;
import org.cloudiator.messages.entities.MatchmakingEntities;

public class EnvironmentConverter implements TwoWayConverter<Environment, MatchmakingEntities.Environment> {

  @Override
  public Environment applyBack(MatchmakingEntities.Environment env) {
    return new Environment().runtime(convertRuntime(env.getRuntime()));
  }

  private Runtime convertRuntime(MatchmakingEntities.Runtime runtime) {
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
    }
  }

  @Override
  public MatchmakingEntities.Environment apply(Environment env) {
    return MatchmakingEntities.Environment.newBuilder()
        .setRuntime(convertRuntime(env.getRuntime())).build();
  }

  private MatchmakingEntities.Runtime convertRuntime(Runtime runtime) {
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
