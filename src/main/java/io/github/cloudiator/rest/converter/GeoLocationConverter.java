package io.github.cloudiator.rest.converter;



import static java.lang.Double.valueOf;

import io.github.cloudiator.rest.model.GeoLocation;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class GeoLocationConverter implements TwoWayConverter<GeoLocation, IaasEntities.GeoLocation> {



  @Override
  public GeoLocation applyBack(IaasEntities.GeoLocation geolocation) {
    if (geolocation == null) {
      return null;
    }
    GeoLocation result = new GeoLocation()
        .city(geolocation.getCity())
        .country(geolocation.getCountry())
        .latitude(valueOf(geolocation.getLatitude()).floatValue())
        .longitude(valueOf(geolocation.getLongitude()).floatValue());

    return result;
  }

  @Override
  public IaasEntities.GeoLocation apply(GeoLocation geolocation) {
    IaasEntities.GeoLocation.Builder builder = IaasEntities.GeoLocation.newBuilder();

    builder.setCity(geolocation.getCity())
        .setCountry(geolocation.getCountry())
        .setLatitude(valueOf(geolocation.getLatitude()))
        .setLongitude(valueOf(geolocation.getLongitude()));


    return builder.build();
  }


}
