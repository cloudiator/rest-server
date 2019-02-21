package io.github.cloudiator.rest.converter;

import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.Location;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messages.entities.IaasEntities.DiscoveryItemState;
import org.hamcrest.Matchers;
import org.junit.Test;

public class LocationConverterTest {

  private final LocationConverter locationConverter = new LocationConverter();
  public final Location restLocation;
  public final IaasEntities.Location iaasLocation;
  public final Location restParentLocation;
  public final IaasEntities.Location iaasParentLocation;

  public LocationConverterTest() {
    this.iaasParentLocation = IaasEntities.Location.newBuilder()
        .setId("32chars-long_testID_for_UnitTest")
        .setName("TestName")
        .setLocationScope(CommonEntities.LocationScope.PROVIDER)
        .setProviderId("TestProvider")
        .setIsAssignable(true)
        .clearParent()
        .setState(DiscoveryItemState.DISCOVERY_NEW)
        .build();
    this.restParentLocation = new Location()
        .id("32chars-long_testID_for_UnitTest")
        .name("TestName")
        .locationScope(Location.LocationScopeEnum.PROVIDER)
        .providerId("TestProvider")
        .state(io.github.cloudiator.rest.model.DiscoveryItemState.NEW)
        .isAssignable(true);
    this.iaasLocation = IaasEntities.Location.newBuilder()
        .setId("32chars-long_testID_for_UnitTest")
        .setName("TestName")
        .setLocationScope(CommonEntities.LocationScope.PROVIDER)
        .setProviderId("TestProvider")
        .setIsAssignable(true)
        .setState(DiscoveryItemState.DISCOVERY_NEW)
        .setParent(iaasParentLocation).build();
    this.restLocation = new Location()
        .id("32chars-long_testID_for_UnitTest")
        .name("TestName")
        .locationScope(Location.LocationScopeEnum.PROVIDER)
        .providerId("TestProvider")
        .parent(restParentLocation)
        .isAssignable(true)
        .state(io.github.cloudiator.rest.model.DiscoveryItemState.NEW);
  }

  @Test
  public void applyBack_withoutParent() throws Exception {
    //from iaas to rest
    Location actual = locationConverter.applyBack(iaasParentLocation);
    assertThat(actual, Matchers.is(Matchers.equalTo(restParentLocation)));
  }

  @Test
  public void applyBack_withParent() throws Exception {
    //from iaas to rest
    Location actual = locationConverter.applyBack(iaasLocation);
    assertThat(actual, Matchers.is(Matchers.equalTo(restLocation)));
  }

  @Test
  public void apply_withoutParent() throws Exception {
    //from rest to iaas

    IaasEntities.Location actual = locationConverter.apply(restParentLocation);
    assertThat(actual, Matchers.is(Matchers.equalTo(iaasParentLocation)));
  }

  @Test
  public void apply_withParent() throws Exception {
    //from rest to iaas

    IaasEntities.Location actual = locationConverter.apply(restLocation);
    assertThat(actual, Matchers.is(Matchers.equalTo(iaasLocation)));
  }

}
