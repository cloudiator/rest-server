package io.github.cloudiator.rest.converter;


import static java.lang.Double.valueOf;
import static org.junit.Assert.*;

import io.github.cloudiator.rest.model.GeoLocation;
import org.cloudiator.messages.entities.IaasEntities;

import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class GeoLocationConverterTest {

  private final GeoLocationConverter geoLocationConverter = new GeoLocationConverter();

  private final GeoLocation restGeoLocation;
  private final IaasEntities.GeoLocation iaasGeoLocation;

  public GeoLocationConverterTest(){
    this.restGeoLocation = new GeoLocation()
        .city("Ulm")
        .country("Germany")
        .latitude(valueOf(40.741895).floatValue())
        .longitude(valueOf(-73.989308).floatValue());
    this.iaasGeoLocation = IaasEntities.GeoLocation.newBuilder()
        .setCity("Ulm")
        .setCountry("Germany")
        .setLatitude(40.741895)
        .setLongitude(-73.989308).build();
  }



  @Test
  public void applyBack() throws Exception {
    //from iaas to rest
    GeoLocation actual = geoLocationConverter.applyBack(iaasGeoLocation);
    assertThat(actual, is(equalTo(restGeoLocation)));

  }

  @Test
  public void apply() throws Exception {
    IaasEntities.GeoLocation actual = geoLocationConverter.apply(restGeoLocation);
    assertThat(actual, is(equalTo(iaasGeoLocation)));
  }

}