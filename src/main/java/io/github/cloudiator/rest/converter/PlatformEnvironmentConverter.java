package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.PlatformEnvironment;
import org.cloudiator.messages.entities.PaasEntities;

public class PlatformEnvironmentConverter implements TwoWayConverter<PlatformEnvironment, PaasEntities.Environment> {

    private final PlatformRuntimeConverter runtimeConverter = new PlatformRuntimeConverter();
    private final PlatformHardwareConverter hardwareConverter = new PlatformHardwareConverter();

    @Override
    public PlatformEnvironment applyBack(PaasEntities.Environment environment) {
        PlatformEnvironment result = new PlatformEnvironment();
        result.setId(environment.getId());
        result.setName(environment.getName());
        result.setPlatformHardware(hardwareConverter.applyBack(environment.getHardwareFlavour()));
        result.setPlatformRuntime(runtimeConverter.applyBack(environment.getRuntime()));
        return result;
    }

    @Override
    public PaasEntities.Environment apply(PlatformEnvironment platformEnvironment) {
        PaasEntities.Environment.Builder result = PaasEntities.Environment.newBuilder();
        result.setId(platformEnvironment.getId())
                .setName(platformEnvironment.getName())
                .setHardwareFlavour(hardwareConverter.apply(platformEnvironment.getPlatformHardware()))
                .setRuntime(runtimeConverter.apply(platformEnvironment.getPlatformRuntime()))
                .clearProviderId()
                .clearPlatform()
                .clearRuntimeService();

        return result.build();
    }
}
