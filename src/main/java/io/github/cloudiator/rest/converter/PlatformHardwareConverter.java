package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.PlatformHardware;
import org.cloudiator.messages.entities.PaasEntities;

public class PlatformHardwareConverter implements
    TwoWayConverter<PlatformHardware, PaasEntities.HardwareFlavour> {

  @Override
  public PlatformHardware applyBack(PaasEntities.HardwareFlavour hardwareFlavour) {
    PlatformHardware result = new PlatformHardware();
    result.setId(hardwareFlavour.getId());
    result.setName(hardwareFlavour.getName());
    result.setCores(hardwareFlavour.getCores());
    result.setRam(hardwareFlavour.getRam());
    result.setDisk(hardwareFlavour.getDisk());
    return result;
  }

  @Override
  public PaasEntities.HardwareFlavour apply(PlatformHardware platformHardware) {
    PaasEntities.HardwareFlavour.Builder result = PaasEntities.HardwareFlavour.newBuilder();
    result.setId(platformHardware.getId())
        .setName(platformHardware.getName())
        .setCores(platformHardware.getCores())
        .setRam(platformHardware.getRam())
        .setDisk(platformHardware.getDisk());
    return result.build();
  }
}
