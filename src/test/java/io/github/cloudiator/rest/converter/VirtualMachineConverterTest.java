package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.*;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class VirtualMachineConverterTest {

    private final VirtualMachineConverter virtualMachineConverter = new VirtualMachineConverter();
    //VM
    private final VirtualMachine restVirtualMachine;
    private final IaasEntities.VirtualMachine iaasVirtualMachine;
    //IP
    private final IpAddress restIpAddress;
    private final IaasEntities.IpAddress iaasIpAddress;

    /**
     * LoginCredential - platzhalter
     * private final LoginCredential restLoginCredential;
     * private final IaasEntities.LoginCredential iaasLoginCredential;
     */

    public VirtualMachineConverterTest() {
        this.restIpAddress = new IpAddress()
                .ipVersion(IpVersion.V4).ipAddressType(IpAddressType.PRIVATE_IP);
        this.iaasIpAddress = IaasEntities.IpAddress.newBuilder()
                .setVersion(IaasEntities.IpVersion.V4).setType(IaasEntities.IpAddressType.PRIVATE_IP).build();
        /*
        this.restLoginCredential = new LoginCredential();
        this.iaasLoginCredential = IaasEntities.LoginCredential.newBuilder()
                .setPassword("TestPassword").setUsername("TestUsername").setPrivateKey("TestPrivateKey").build();
        */
        this.restVirtualMachine = new VirtualMachine()
                .hardware("TestHardware")
                .image("TestImage")
                .id("32chars-long_testID_for_UnitTest")
                .location("TestLocation")
                .addIpaddressesItem(restIpAddress);
        this.iaasVirtualMachine = IaasEntities.VirtualMachine.newBuilder()
                .setHardware("TestHardware")
                .setImage("TestImage")
                .setId("32chars-long_testID_for_UnitTest")
                .setLocation("TestLocation")
                .addIpAddresses(iaasIpAddress).build();
    }

    @Test
    public void applyBack() throws Exception {
        //from iaas to rest
        VirtualMachine actual = virtualMachineConverter.applyBack(iaasVirtualMachine);
        assertThat(actual, is(equalTo(restVirtualMachine)));
    }

    @Test
    public void apply() throws Exception {
        //from rest to iaas
        IaasEntities.VirtualMachine actual = virtualMachineConverter.apply(restVirtualMachine);
        assertThat(actual, is(equalTo(iaasVirtualMachine)));
    }

}