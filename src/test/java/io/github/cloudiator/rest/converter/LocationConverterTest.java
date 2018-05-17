package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Location;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
        .clearParent().build();
    this.restParentLocation = new Location()
        .id("32chars-long_testID_for_UnitTest")
        .name("TestName")
        .locationScope(Location.LocationScopeEnum.PROVIDER)
        .providerId("TestProvider")
        .isAssignable(true);
    this.iaasLocation = IaasEntities.Location.newBuilder()
        .setId("32chars-long_testID_for_UnitTest")
        .setName("TestName")
        .setLocationScope(CommonEntities.LocationScope.PROVIDER)
        .setProviderId("TestProvider")
        .setIsAssignable(true)
        .setParent(iaasParentLocation).build();
    this.restLocation = new Location()
        .id("32chars-long_testID_for_UnitTest")
        .name("TestName")
        .locationScope(Location.LocationScopeEnum.PROVIDER)
        .providerId("TestProvider")
        .parent(restParentLocation)
        .isAssignable(true);
  }

  @Test
  public void applyBack_withoutParent() throws Exception {
    //from iaas to rest
    Location actual = locationConverter.applyBack(iaasParentLocation);
    assertThat(actual, is(equalTo(restParentLocation)));
  }

  @Test
  public void applyBack_withParent() throws Exception {
    //from iaas to rest
    Location actual = locationConverter.applyBack(iaasLocation);
    assertThat(actual, is(equalTo(restLocation)));
  }

  @Test
  public void apply_withoutParent() throws Exception {
    //from rest to iaas

    IaasEntities.Location actual = locationConverter.apply(restParentLocation);
    assertThat(actual, is(equalTo(iaasParentLocation)));
  }

  @Test
  public void apply_withParent() throws Exception {
    //from rest to iaas

    IaasEntities.Location actual = locationConverter.apply(restLocation);
    assertThat(actual, is(equalTo(iaasLocation)));
  }

}