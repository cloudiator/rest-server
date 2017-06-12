package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.CloudType;
import io.github.cloudiator.rest.model.Hardware;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class HardwareConverter implements TwoWayConverter<Hardware, IaasEntities.HardwareFlavor> {

    private final LocationConverter locationConverter = new LocationConverter();


    @Override
    public Hardware applyBack(IaasEntities.HardwareFlavor hardwareFlavor) {
        Hardware result = new Hardware();
        result.setName(hardwareFlavor.getName());
        result.setId(hardwareFlavor.getId());
        result.setCores(hardwareFlavor.getCores());
        result.setDisk((double) hardwareFlavor.getDisk());
        result.setProviderId(hardwareFlavor.getProviderId());
        result.setRam(hardwareFlavor.getRam());
        result.setLocation(locationConverter.applyBack(hardwareFlavor.getLocation()));

        return result;
    }

    @Override
    public IaasEntities.HardwareFlavor apply(Hardware hardware) {
        IaasEntities.HardwareFlavor.Builder builder = IaasEntities.HardwareFlavor.newBuilder();
        builder.setName(hardware.getName());
        builder.setId(hardware.getId());
        builder.setCores(hardware.getCores());
        builder.setDisk(hardware.getDisk().floatValue());
        builder.setProviderId(hardware.getProviderId());
        builder.setRam(hardware.getRam());
        builder.setLocation(locationConverter.apply(hardware.getLocation()));

        return builder.build();
    }


}
