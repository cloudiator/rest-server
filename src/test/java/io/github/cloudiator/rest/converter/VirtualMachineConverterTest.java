package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.*;
import org.cloudiator.messages.entities.CommonEntities;
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
  //Hardware
  private final Hardware restHardware;
  private final IaasEntities.HardwareFlavor iaasHardware;
  private final Location restHardwareLocation;
  private final IaasEntities.Location iaasHardwareLocation;
  //Image
  private final Image restImage;
  private final IaasEntities.Image iaasImage;
  private final Location restImageLocation;
  private final IaasEntities.Location iaasImageLocation;
  private final OperatingSystem restOperatingSystem;
  private final CommonEntities.OperatingSystem iaasOperatingSystem;
  //Location
  private final Location restLocation;
  private final IaasEntities.Location iaasLocation;

  /**
   * LoginCredential - platzhalter private final LoginCredential restLoginCredential; private final
   * IaasEntities.LoginCredential iaasLoginCredential;
   */

  public VirtualMachineConverterTest() {
    this.restIpAddress = new IpAddress()
        .ipVersion(IpVersion.V4).ipAddressType(IpAddressType.PRIVATE_IP);
    this.iaasIpAddress = IaasEntities.IpAddress.newBuilder()
        .setVersion(IaasEntities.IpVersion.V4).setType(IaasEntities.IpAddressType.PRIVATE_IP)
        .build();
        /*
        this.restLoginCredential = new LoginCredential();
        this.iaasLoginCredential = IaasEntities.LoginCredential.newBuilder()
                .setPassword("TestPassword").setUsername("TestUsername").setPrivateKey("TestPrivateKey").build();
        */
    this.restHardwareLocation = new Location()
        .id("32chars-long_testID_for_Location")
        .name("HardwareLocationName")
        .locationScope(Location.LocationScopeEnum.PROVIDER)
        .providerId("HardwareLocationProviderId")
        .isAssignable(true);
    this.iaasHardwareLocation = IaasEntities.Location.newBuilder()
        .setId("32chars-long_testID_for_Location")
        .setName("HardwareLocationName")
        .setLocationScope(CommonEntities.LocationScope.PROVIDER)
        .setProviderId("HardwareLocationProviderId")
        .setIsAssignable(true)
        .clearParent().build();
    this.restHardware = new Hardware()
        .name("HardwareName")
        .id("32chars-long_testID_for_Hardware")
        .cores(4)
        .disk((float) 100)
        .ram((long) 2048)
        .providerId("HardwareProvider")
        .location(restHardwareLocation);
    this.iaasHardware = IaasEntities.HardwareFlavor.newBuilder()
        .setId("32chars-long_testID_for_Hardware")
        .setName("HardwareName")
        .setCores(4)
        .setDisk((float) 100)
        .setRam((long) 2048)
        .setProviderId("HardwareProvider")
        .setLocation(iaasHardwareLocation).build();

    this.restImageLocation = new Location()
        .id("32char-long_ID_for_ImageLocation")
        .name("ImageLocationName")
        .locationScope(Location.LocationScopeEnum.PROVIDER)
        .providerId("ImageLocationProviderId")
        .isAssignable(true);
    this.iaasImageLocation = IaasEntities.Location.newBuilder()
        .setId("32char-long_ID_for_ImageLocation")
        .setName("ImageLocationName")
        .setLocationScope(CommonEntities.LocationScope.PROVIDER)
        .setProviderId("ImageLocationProviderId")
        .setIsAssignable(true)
        .clearParent().build();
    this.restOperatingSystem = new OperatingSystem()
        .operatingSystemArchitecture(OperatingSystemArchitecture.AMD64)
        .operatingSystemFamily(OperatingSystemFamily.UBUNTU)
        .operatingSystemType(OperatingSystemType.UNIX)
        .operatingSystemVersion("1.test");
    this.iaasOperatingSystem = CommonEntities.OperatingSystem.newBuilder()
        .setOperatingSystemArchitecture(CommonEntities.OperatingSystemArchitecture.AMD64)
        .setOperatingSystemFamily(CommonEntities.OperatingSystemFamily.UBUNTU)
        .setOperatingSystemType(CommonEntities.OperatingSystemType.UNIX)
        .setOperatingSystemVersion("1.test").build();
    this.restImage = new Image()
        .id("32chars-long_testID_forImageTest")
        .name("ImageName")
        .providerId("ImageProviderId")
        .location(restImageLocation)
        .operatingSystem(restOperatingSystem);
    this.iaasImage = IaasEntities.Image.newBuilder()
        .setId("32chars-long_testID_forImageTest")
        .setName("ImageName")
        .setProviderId("ImageProviderId")
        .setLocation(iaasImageLocation)
        .setOperationSystem(iaasOperatingSystem).build();

    this.restLocation = new Location()
        .id("32char-long_lvl1Test_Location_ID")
        .name("LocationName")
        .locationScope(Location.LocationScopeEnum.PROVIDER)
        .providerId("LocationProviderId")
        .isAssignable(true);
    this.iaasLocation = IaasEntities.Location.newBuilder()
        .setId("32char-long_lvl1Test_Location_ID")
        .setName("LocationName")
        .setLocationScope(CommonEntities.LocationScope.PROVIDER)
        .setProviderId("LocationProviderId")
        .setIsAssignable(true)
        .clearParent().build();

    this.restVirtualMachine = new VirtualMachine()
        .id("32chars-long_testID_for_UnitTest")
        .hardware(restHardware)
        .image(restImage)
        .location(restLocation)
        .addIpaddressesItem(restIpAddress);
    this.iaasVirtualMachine = IaasEntities.VirtualMachine.newBuilder()
        .setId("32chars-long_testID_for_UnitTest")
        .setHardware(iaasHardware)
        .setImage(iaasImage)
        .setLocation(iaasLocation)
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