package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.PlatformService;
import org.cloudiator.messages.entities.PaasEntities;
import org.cloudiator.messages.entities.PaasEntities.RuntimeService;

public class PlatformServiceConverter implements
    TwoWayConverter<PlatformService, PaasEntities.RuntimeService> {

  @Override
  public PlatformService applyBack(RuntimeService runtimeService) {
    PlatformService result = new PlatformService();

    return null;
  }

  @Override
  public RuntimeService apply(PlatformService platformService) {
    return null;
  }
}
