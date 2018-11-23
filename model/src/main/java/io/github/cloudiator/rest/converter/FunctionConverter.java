package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Function;
import org.cloudiator.messages.entities.FaasEntities;

public class FunctionConverter implements TwoWayConverter<Function, FaasEntities.Function> {

  @Override
  public Function applyBack(FaasEntities.Function function) {
    Function result = new Function();
    result.setId(function.getId());
    result.setCloudId(function.getCloudId());
    result.setLocationId(function.getLocationId());
    result.setMemory(function.getMemory());
    result.setStackId(function.getStackId());
    return result;
  }

  @Override
  public FaasEntities.Function apply(Function function) {
    return FaasEntities.Function.newBuilder()
        .setId(function.getId())
        .setCloudId(function.getCloudId())
        .setLocationId(function.getLocationId())
        .setMemory(function.getMemory())
        .setStackId(function.getStackId())
        .build();
  }
}
