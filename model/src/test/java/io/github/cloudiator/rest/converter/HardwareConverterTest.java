package io.github.cloudiator.rest.converter;

import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.DiscoveryItemState;
import io.github.cloudiator.rest.model.Hardware;
import io.github.cloudiator.rest.model.Location;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.IaasEntities;
import org.hamcrest.Matchers;
import org.junit.Test;

public class HardwareConverterTest {

  private final HardwareConverter hardwareConverter = new HardwareConverter();
  //hardware
  public final Hardware restHardware;
  public final IaasEntities.HardwareFlavor iaasHardware;
  //Location
  private final Location restLoacation;
  private final IaasEntities.Location iaasLocation;

  public HardwareConverterTest() {
    //Location
    this.restLoacation = new Location().name("TestName")
        .id("32chars-long_testID_for_UnitTest")
        .providerId("TestProviderId")
        .locationScope(Location.LocationScopeEnum.PROVIDER)
        .isAssignable(true)
        .state(DiscoveryItemState.NEW);
    this.iaasLocation = IaasEntities.Location.newBuilder()
        .setName("TestName")
        .setId("32chars-long_testID_for_UnitTest")
        .setProviderId("TestProviderId")
        .setLocationScope(CommonEntities.LocationScope.PROVIDER)
        .setIsAssignable(true)
        .clearParent()
        .setState(IaasEntities.DiscoveryItemState.DISCOVERY_NEW)
        .build();
    //Hardware
    this.restHardware = new Hardware().cores(4).disk(256d).ram(2048L)
        .name("TestName").id("32chars-long_testID_for_UnitTest")
        .providerId("TestProviderId")
        .state(DiscoveryItemState.NEW)
        .location(restLoacation);
    this.iaasHardware = IaasEntities.HardwareFlavor.newBuilder()
        .setCores(4).setDisk(256).setRam(2048)
        .setName("TestName").setId("32chars-long_testID_for_UnitTest")
        .setProviderId("TestProviderId")
        .setLocation(iaasLocation)
        .setState(IaasEntities.DiscoveryItemState.DISCOVERY_NEW)
        .build();
  }

  @Test
  public void applyBack() throws Exception {
    //from iaas to rest
    Hardware actual = hardwareConverter.applyBack(iaasHardware);
    assertThat(actual, Matchers.is(Matchers.equalTo(restHardware)));
  }

  @Test
  public void apply() throws Exception {
    //from rest to iaas
    IaasEntities.HardwareFlavor actual = hardwareConverter.apply(restHardware);
    assertThat(actual, Matchers.is(Matchers.equalTo(iaasHardware)));
  }

}
