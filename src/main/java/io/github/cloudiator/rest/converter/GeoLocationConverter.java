package io.github.cloudiator.rest.converter;


import com.google.common.base.Strings;
import io.github.cloudiator.rest.model.GeoLocation;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class GeoLocationConverter implements
    TwoWayConverter<GeoLocation, IaasEntities.GeoLocation> {


  @Override
  public GeoLocation applyBack(IaasEntities.GeoLocation geolocation) {
    if (geolocation == null) {
      return null;
    }
    GeoLocation result = new GeoLocation();

    if (!Strings.isNullOrEmpty(geolocation.getCity())) {
      result.city(geolocation.getCity());
    }

    if (!Strings.isNullOrEmpty(geolocation.getCountry())) {
      result.country(geolocation.getCountry());
    }

    if (geolocation.getLatitude() != 0) {
      result.latitude(geolocation.getLatitude());
    }

    if (geolocation.getLongitude() != 0) {
      result.longitude(geolocation.getLongitude());
    }

    return result;
  }

  @Override
  public IaasEntities.GeoLocation apply(GeoLocation geolocation) {
    IaasEntities.GeoLocation.Builder builder = IaasEntities.GeoLocation.newBuilder();

    if (geolocation.getCity() != null) {
      builder.setCity(geolocation.getCity());
    }else{
      builder.clearCity();
    }

    if (geolocation.getCountry() != null) {
      builder.setCountry(geolocation.getCountry());
    }else{
      builder.clearCountry();
    }

    if (geolocation.getLatitude() != null) {
      builder.setLatitude(geolocation.getLatitude());
    }else{
      builder.clearLatitude();
    }

    if (geolocation.getLongitude() != null) {
      builder.setLongitude(geolocation.getLongitude());
    }else{
      builder.clearLongitude();
    }

    return builder.build();
  }


}
