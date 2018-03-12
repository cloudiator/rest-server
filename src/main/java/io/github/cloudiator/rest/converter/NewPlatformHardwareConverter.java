package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.NewPlatformHardware;
import org.cloudiator.messages.entities.PaasEntities;

public class NewPlatformHardwareConverter implements
    TwoWayConverter<NewPlatformHardware, PaasEntities.NewHardwareFlavour> {

  @Override
  public NewPlatformHardware applyBack(PaasEntities.NewHardwareFlavour hardwareFlavour) {
    NewPlatformHardware result = new NewPlatformHardware();
    result.setName(hardwareFlavour.getName());
    result.setCores(hardwareFlavour.getCores());
    result.setRam(hardwareFlavour.getRam());
    result.setDisk(hardwareFlavour.getDisk());
    return result;
  }

  @Override
  public PaasEntities.NewHardwareFlavour apply(NewPlatformHardware platformHardware) {
    PaasEntities.NewHardwareFlavour.Builder result = PaasEntities.NewHardwareFlavour.newBuilder();
    result.setName(platformHardware.getName())
        .setCores(platformHardware.getCores())
        .setRam(platformHardware.getRam())
        .setDisk(platformHardware.getDisk());
    return result.build();
  }
}