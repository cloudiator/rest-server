package io.github.cloudiator.rest.converter;




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
        .latitude(geolocation.getLatitude())
        .longitude(geolocation.getLongitude());

    return result;
  }

  @Override
  public IaasEntities.GeoLocation apply(GeoLocation geolocation) {
    IaasEntities.GeoLocation.Builder builder = IaasEntities.GeoLocation.newBuilder();

    builder.setCity(geolocation.getCity())
        .setCountry(geolocation.getCountry())
        .setLatitude(geolocation.getLatitude())
        .setLongitude(geolocation.getLongitude());


    return builder.build();
  }


}
