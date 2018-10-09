package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.NewPlatformEnvironment;
import org.cloudiator.messages.entities.PaasEntities;
import org.cloudiator.messages.entities.PaasEntities.NewEnvironment;
import org.cloudiator.messages.entities.PaasEntities.RuntimeService;

public class NewPlatformEnvironmentConverter implements
    TwoWayConverter<NewPlatformEnvironment, NewEnvironment> {

  private final PlatformRuntimeConverter runtimeConverter = new PlatformRuntimeConverter();
  private final PlatformHardwareConverter hardwareConverter = new PlatformHardwareConverter();
  private final PlatformServiceConverter serviceConverter = new PlatformServiceConverter();

  @Override
  public NewPlatformEnvironment applyBack(PaasEntities.NewEnvironment environment) {
    NewPlatformEnvironment result = new NewPlatformEnvironment()
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
  public PaasEntities.NewEnvironment apply(NewPlatformEnvironment platformEnvironment) {
    PaasEntities.NewEnvironment.Builder result = PaasEntities.NewEnvironment.newBuilder()
        .setName(platformEnvironment.getName())
        .setHardwareFlavour(hardwareConverter.apply(platformEnvironment.getPlatformHardware()))
        .setRuntime(runtimeConverter.apply(platformEnvironment.getPlatformRuntime()))
        .clearProviderId() //obsolete
        .clearPlatform()
        .clearRuntimeService();

    return result.build();
  }
}
