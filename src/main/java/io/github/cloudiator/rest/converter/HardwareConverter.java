package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.CloudType;
import io.github.cloudiator.rest.model.Hardware;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messages.entities.IaasEntities.HardwareFlavor;

/**
 * Created by volker on 29.05.17.
 */
public class HardwareConverter implements TwoWayConverter<Hardware, HardwareFlavor> {

  private final LocationConverter locationConverter = new LocationConverter();


  @Override
  public Hardware applyBack(IaasEntities.HardwareFlavor hardwareFlavor) {
    Hardware result = new Hardware();
    result.setName(hardwareFlavor.getName());
    result.setId(hardwareFlavor.getId());
    result.setCores(hardwareFlavor.getCores());
    result.setDisk(hardwareFlavor.getDisk());
    result.setProviderId(hardwareFlavor.getProviderId());
    result.setRam(hardwareFlavor.getRam());

    if(hardwareFlavor.hasLocation()) {
      result.setLocation(locationConverter.applyBack(hardwareFlavor.getLocation()));
    }

    return result;
  }

  @Override
  public IaasEntities.HardwareFlavor apply(Hardware hardware) {
    IaasEntities.HardwareFlavor.Builder builder = IaasEntities.HardwareFlavor.newBuilder();
    builder.setName(hardware.getName());
    builder.setId(hardware.getId());
    builder.setCores(hardware.getCores());
    builder.setProviderId(hardware.getProviderId());
    builder.setRam(hardware.getRam());
    if (hardware.getDisk() != null) {
      builder.setDisk(hardware.getDisk());
    } else {
      builder.clearDisk();
    }
    if (hardware.getLocation() != null) {
      builder.setLocation(locationConverter.apply(hardware.getLocation()));
    } else {
      builder.clearLocation();
    }

    return builder.build();
  }


}
