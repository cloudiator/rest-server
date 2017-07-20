package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.VirtualMachine;
import org.cloudiator.messages.entities.IaasEntities;
import io.github.cloudiator.rest.model.IpAddress;

/**
 * Created by volker on 29.05.17.
 */
public class VirtualMachineConverter implements
    TwoWayConverter<VirtualMachine, IaasEntities.VirtualMachine> {

  private final IpAddressConverter ipAddressConverter = new IpAddressConverter();

  @Override
  public VirtualMachine applyBack(IaasEntities.VirtualMachine virtualMachine) {
    VirtualMachine vm = new VirtualMachine();
    vm.setHardware(virtualMachine.getHardware());
    vm.setImage(virtualMachine.getImage());
    vm.setLocation(virtualMachine.getLocation());
    vm.setId(virtualMachine.getId());
    for (IaasEntities.IpAddress ipAddress : virtualMachine.getIpAddressesList()) {
      vm.addIpaddressesItem(ipAddressConverter.applyBack(ipAddress));
    }

    return vm;
  }

  @Override
  public IaasEntities.VirtualMachine apply(VirtualMachine virtualMachine) {
    IaasEntities.VirtualMachine.Builder builder = IaasEntities.VirtualMachine.newBuilder();
    if (virtualMachine.getHardware() != null) {
      builder.setHardware(virtualMachine.getHardware());
    } else {
      builder.clearHardware();
    }
    if (virtualMachine.getId() != null) {
      builder.setId(virtualMachine.getId());
    } else {
      builder.clearId();
    }
    if (virtualMachine.getImage() != null) {
      builder.setImage(virtualMachine.getImage());
    } else {
      builder.clearImage();
    }
    for (IpAddress ip : virtualMachine.getIpaddresses()) {
      builder.addIpAddresses(ipAddressConverter.apply(ip));

    }
    //LoginCredential ignorieren
    builder.clearLoginCredential();

    return builder.build();
  }
}
