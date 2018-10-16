package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.PlatformService;
import org.cloudiator.messages.entities.PaasEntities;
import org.cloudiator.messages.entities.PaasEntities.RuntimeService;

public class PlatformServiceConverter implements
    TwoWayConverter<PlatformService, RuntimeService> {

  @Override
  public PlatformService applyBack(RuntimeService runtimeService) {
    PlatformService result = new PlatformService()
        .id(runtimeService.getId())
        .name(runtimeService.getName());

    return result;
  }

  @Override
  public RuntimeService apply(PlatformService platformService) {
    PaasEntities.RuntimeService.Builder result = RuntimeService.newBuilder()
        .setId(platformService.getId())
        .setName(platformService.getName());

    return result.build();
  }
}
