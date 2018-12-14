package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Environment;
import io.github.cloudiator.rest.model.Runtime;
import org.cloudiator.messages.entities.MatchmakingEntities;

public class EnvironmentConverter implements TwoWayConverter<Environment, MatchmakingEntities.Environment> {

  private final RuntimeConverter runtimeConverter = new RuntimeConverter();

  @Override
  public Environment applyBack(MatchmakingEntities.Environment env) {
    return new Environment().runtime(runtimeConverter.applyBack(env.getRuntime()));
  }

  @Override
  public MatchmakingEntities.Environment apply(Environment env) {
    return MatchmakingEntities.Environment.newBuilder()
        .setRuntime(runtimeConverter.apply(env.getRuntime())).build();
  }
}
