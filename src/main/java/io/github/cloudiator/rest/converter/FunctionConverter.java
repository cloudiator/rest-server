package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Function;
import org.cloudiator.messages.entities.FaasEntities;

public class FunctionConverter implements TwoWayConverter<Function, FaasEntities.Function> {

  private final LocationConverter locationConverter = new LocationConverter();

  @Override
  public Function applyBack(FaasEntities.Function function) {
    Function result = new Function();
    result.setId(function.getId());
    result.setCloudId(function.getCloudId());
    result.setLocationId(function.getLocationId());
    return result;
  }

  @Override
  public FaasEntities.Function apply(Function function) {
    return FaasEntities.Function.newBuilder()
        .setId(function.getId())
        .setCloudId(function.getCloudId())
        .setLocationId(function.getLocationId())
        .build();
  }
}
