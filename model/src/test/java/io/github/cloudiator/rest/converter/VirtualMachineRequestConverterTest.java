package io.github.cloudiator.rest.converter;

import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.VirtualMachineRequest;
import org.cloudiator.messages.entities.IaasEntities;
import org.hamcrest.Matchers;
import org.junit.Test;

public class VirtualMachineRequestConverterTest {

  private final VirtualMachineRequestConverter virtualMachineRequestConverter = new VirtualMachineRequestConverter();
  private final VirtualMachineRequest restVMRequest;
  private final IaasEntities.VirtualMachineRequest iaasVMRequest;

  public VirtualMachineRequestConverterTest() {
    this.restVMRequest = new VirtualMachineRequest()
        .hardware("TestHardware")
        .image("TestImage")
        .location("TestLocation");
    this.iaasVMRequest = IaasEntities.VirtualMachineRequest.newBuilder()
        .setHardware("TestHardware")
        .setImage("TestImage")
        .setLocation("TestLocation").build();
  }

  @Test
  public void apply() throws Exception {
    //from rest to iaas
    IaasEntities.VirtualMachineRequest actual = virtualMachineRequestConverter.apply(restVMRequest);
    assertThat(actual, Matchers.is(Matchers.equalTo(iaasVMRequest)));
  }

}
