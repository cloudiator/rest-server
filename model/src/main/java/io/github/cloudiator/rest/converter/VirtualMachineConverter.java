package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.VirtualMachine;
import io.github.cloudiator.rest.model.VirtualMachine.StateEnum;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messages.entities.IaasEntities.VirtualMachineState;

/**
 * Created by volker on 29.05.17.
 */
public class VirtualMachineConverter implements
    TwoWayConverter<VirtualMachine, IaasEntities.VirtualMachine> {

  private final IpAddressConverter ipAddressConverter = new IpAddressConverter();
  private final HardwareConverter hardwareConverter = new HardwareConverter();
  private final ImageConverter imageConverter = new ImageConverter();
  private final LocationConverter locationConverter = new LocationConverter();
  private static final VirtualMachineStateConverter VIRTUAL_MACHINE_STATE_CONVERTER = new VirtualMachineStateConverter();

  private static class VirtualMachineStateConverter implements
      TwoWayConverter<VirtualMachine.StateEnum, IaasEntities.VirtualMachineState> {

    @Override
    public StateEnum applyBack(VirtualMachineState virtualMachineState) {
      switch (virtualMachineState) {
        case VM_STATE_ERROR:
          return StateEnum.ERROR;
        case VM_STATE_RUNNING:
          return StateEnum.RUNNING;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unknown virtualMachineState " + virtualMachineState);
      }
    }

    @Override
    public VirtualMachineState apply(StateEnum stateEnum) {
      switch (stateEnum) {
        case RUNNING:
          return VirtualMachineState.VM_STATE_RUNNING;
        case ERROR:
          return VirtualMachineState.VM_STATE_ERROR;
        default:
          throw new AssertionError("Unknown virtualMachineState " + stateEnum);
      }
    }
  }

  @Override
  public VirtualMachine applyBack(IaasEntities.VirtualMachine virtualMachine) {
    VirtualMachine vm = new VirtualMachine();
    vm.setHardware(hardwareConverter.applyBack(virtualMachine.getHardware()));
    vm.setImage(imageConverter.applyBack(virtualMachine.getImage()));
    vm.setLocation(locationConverter.applyBack(virtualMachine.getLocation()));
    vm.setId(virtualMachine.getId());
    vm.setOwner(virtualMachine.getUserId());
    vm.setState(VIRTUAL_MACHINE_STATE_CONVERTER.applyBack(virtualMachine.getState()));
    for (IaasEntities.IpAddress ipAddress : virtualMachine.getIpAddressesList()) {
      vm.addIpaddressesItem(ipAddressConverter.applyBack(ipAddress));
    }

    return vm;
  }

  @Override
  public IaasEntities.VirtualMachine apply(VirtualMachine virtualMachine) {
    IaasEntities.VirtualMachine.Builder builder = IaasEntities.VirtualMachine.newBuilder();
    builder.setUserId(virtualMachine.getOwner());
    builder.setState(VIRTUAL_MACHINE_STATE_CONVERTER.apply(virtualMachine.getState()));

    if (virtualMachine.getHardware() != null) {
      builder.setHardware(hardwareConverter.apply(virtualMachine.getHardware()));
    } else {
      builder.clearHardware();
    }
    if (virtualMachine.getId() != null) {
      builder.setId(virtualMachine.getId());
    } else {
      builder.clearId();
    }
    if (virtualMachine.getImage() != null) {
      builder.setImage(imageConverter.apply(virtualMachine.getImage()));
    } else {
      builder.clearImage();
    }
    if (virtualMachine.getIpaddresses() != null) {
      for (IpAddress ip : virtualMachine.getIpaddresses()) {
        builder.addIpAddresses(ipAddressConverter.apply(ip));

      }
    } else {
      builder.clearIpAddresses();
    }
    if (virtualMachine.getLocation() != null) {
      builder.setLocation(locationConverter.apply(virtualMachine.getLocation()));
    } else {
      builder.clearLocation();
    }
    //LoginCredential ignorieren
    builder.clearLoginCredential();

    return builder.build();
  }
}
