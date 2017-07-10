package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.VirtualMachineRequest;
import javax.annotation.Nullable;
import org.cloudiator.messages.entities.IaasEntities;


/**
 * Created by daniel on 27.06.17.
 */
public class VirtualMachineRequestConverter implements
    OneWayConverter<VirtualMachineRequest, IaasEntities.VirtualMachineRequest> {

  @Nullable
  @Override
  public IaasEntities.VirtualMachineRequest apply(
      @Nullable VirtualMachineRequest virtualMachineRequest) {
    return IaasEntities.VirtualMachineRequest.newBuilder()
        .setHardware(virtualMachineRequest.getHardware())
        .setLocation(virtualMachineRequest.getLocation())
        .setImage(virtualMachineRequest.getImage())
        .build();
  }
}
