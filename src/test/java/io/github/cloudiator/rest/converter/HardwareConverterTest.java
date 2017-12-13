package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Hardware;
import io.github.cloudiator.rest.model.Location;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messages.entities.PaasEntities;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
        .isAssignable(true);
    this.iaasLocation = IaasEntities.Location.newBuilder()
        .setName("TestName")
        .setId("32chars-long_testID_for_UnitTest")
        .setProviderId("TestProviderId")
        .setLocationScope(CommonEntities.LocationScope.PROVIDER)
        .setIsAssignable(true)
        .clearParent().build();
    //Hardware
    this.restHardware = new Hardware().cores(4).disk((float) 256).ram((long) 2048)
        .name("TestName").id("32chars-long_testID_for_UnitTest")
        .providerId("TestProviderId")
        .location(restLoacation);
    this.iaasHardware = IaasEntities.HardwareFlavor.newBuilder()
        .setCores(4).setDisk(256).setRam(2048)
        .setName("TestName").setId("32chars-long_testID_for_UnitTest")
        .setProviderId("TestProviderId")
        .setLocation(iaasLocation).build();
  }

  @Test
  public void applyBack() throws Exception {
    //from iaas to rest
    Hardware actual = hardwareConverter.applyBack(iaasHardware);
    assertThat(actual, is(equalTo(restHardware)));
  }

  @Test
  public void apply() throws Exception {
    //from rest to iaas
    IaasEntities.HardwareFlavor actual = hardwareConverter.apply(restHardware);
    assertThat(actual, is(equalTo(iaasHardware)));
  }

}