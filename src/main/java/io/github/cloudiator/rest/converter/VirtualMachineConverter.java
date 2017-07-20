package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.VirtualMachine;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class VirtualMachineConverter implements TwoWayConverter<VirtualMachine, IaasEntities.VirtualMachine>{


  @Override
  public VirtualMachine applyBack(IaasEntities.VirtualMachine virtualMachine) {
    VirtualMachine vm = new VirtualMachine();
    vm.setHardware(virtualMachine.getHardware());
    vm.setImage(virtualMachine.getImage());
    vm.setLocation(virtualMachine.getLocation());
    return vm;
  }

  @Override
  public IaasEntities.VirtualMachine apply(VirtualMachine virtualMachine) {
    IaasEntities.VirtualMachine.Builder builder = IaasEntities.VirtualMachine.newBuilder();

    return null;
  }
}
