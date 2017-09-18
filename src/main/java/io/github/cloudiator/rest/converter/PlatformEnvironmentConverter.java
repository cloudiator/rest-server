package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.PlatformEnvironment;
import org.cloudiator.messages.entities.PaasEntities;
import org.cloudiator.messages.entities.PaasEntities.RuntimeService;

public class PlatformEnvironmentConverter implements
    TwoWayConverter<PlatformEnvironment, PaasEntities.Environment> {

  private final PlatformRuntimeConverter runtimeConverter = new PlatformRuntimeConverter();
  private final PlatformHardwareConverter hardwareConverter = new PlatformHardwareConverter();
  private final PlatformServiceConverter serviceConverter = new PlatformServiceConverter();

  @Override
  public PlatformEnvironment applyBack(PaasEntities.Environment environment) {
    PlatformEnvironment result = new PlatformEnvironment()
        .id(environment.getId())
        .name(environment.getName())
        .platformHardware(hardwareConverter.applyBack(environment.getHardwareFlavour()))
        .platformRuntime(runtimeConverter.applyBack(environment.getRuntime()));
    if (!environment.getRuntimeServiceList().isEmpty()) {
      for (RuntimeService service : environment.getRuntimeServiceList()) {
        result.addPlatformServiceItem(serviceConverter.applyBack(service));
      }
    }

    return result;
  }

  @Override
  public PaasEntities.Environment apply(PlatformEnvironment platformEnvironment) {
    PaasEntities.Environment.Builder result = PaasEntities.Environment.newBuilder()
        .setId(platformEnvironment.getId())
        .setName(platformEnvironment.getName())
        .setHardwareFlavour(hardwareConverter.apply(platformEnvironment.getPlatformHardware()))
        .setRuntime(runtimeConverter.apply(platformEnvironment.getPlatformRuntime()))
        .clearProviderId() //obsolete
        .clearPlatform()
        .clearRuntimeService();

    return result.build();
  }
}
