package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.CloudType;
import io.github.cloudiator.rest.model.Location;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class LocationConverter implements TwoWayConverter<Location, IaasEntities.Location> {

  private final LocationScopeConverter locationScopeConverter = new LocationScopeConverter();

  @Override
  public Location applyBack(IaasEntities.Location location) {
    if (location == null) {
      return null;
    }
    Location result = new Location();
    result.setName(location.getName());
    result.setId(location.getId());
    result.setProviderId(location.getProviderId());
    result.setLocationScope(locationScopeConverter.applyBack(location.getLocationScope()));
    result.setIsAssignable(location.getIsAssignable());
    if (location.hasParent()) {
      result.setParent(applyBack(location.getParent()));
    }
    return result;
  }

  @Override
  public IaasEntities.Location apply(Location location) {
    IaasEntities.Location.Builder builder = IaasEntities.Location.newBuilder();
    builder.setName(location.getName());
    builder.setId(location.getId());
    builder.setProviderId(location.getProviderId());
    builder.setLocationScope(locationScopeConverter.apply(location.getLocationScope()));
    builder.setIsAssignable(location.isIsAssignable());

    if (location.getParent() != null) {
      builder.setParent(apply(location.getParent()));
    } else {
      builder.clearParent();
    }

    return builder.build();
  }


  private class LocationScopeConverter implements
      TwoWayConverter<Location.LocationScopeEnum, CommonEntities.LocationScope> {

    @Override
    public Location.LocationScopeEnum applyBack(CommonEntities.LocationScope locationScope) {
      switch (locationScope) {
        case PROVIDER:
          return Location.LocationScopeEnum.PROVIDER;
        case REGION:
          return Location.LocationScopeEnum.REGION;
        case HOST:
          return Location.LocationScopeEnum.HOST;
        case ZONE:
          return Location.LocationScopeEnum.ZONE;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unrecognized locationScope " + locationScope);
      }
    }

    @Override
    public CommonEntities.LocationScope apply(Location.LocationScopeEnum locationScopeEnum) {
      switch (locationScopeEnum) {
        case PROVIDER:
          return CommonEntities.LocationScope.PROVIDER;
        case REGION:
          return CommonEntities.LocationScope.REGION;
        case HOST:
          return CommonEntities.LocationScope.HOST;
        case ZONE:
          return CommonEntities.LocationScope.ZONE;
        default:
          throw new AssertionError("Unrecognized locationScope " + locationScopeEnum);
      }
    }

  }
}
