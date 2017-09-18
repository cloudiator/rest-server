package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.CloudCredential;
import io.github.cloudiator.rest.model.Platform;
import org.cloudiator.messages.entities.PaasEntities;

public class PlatformConverter implements TwoWayConverter<Platform, PaasEntities.Platform> {

  private final PlatformTypeConverter platformTypeConverter = new PlatformTypeConverter();

  @Override
  public Platform applyBack(PaasEntities.Platform platform) {
    Platform result = new Platform()
        .name(platform.getName())
        .id(platform.getId())
        .type(platformTypeConverter.applyBack(platform.getPlatformType()))
        .api(new Api().providerName(platform.getProviderId()))
        .endpoint(platform.getEndpoint());

    CloudCredential dummy = new CloudCredential();
    dummy.setUser("dummyUser");
    dummy.setSecret("dummySecret");
    result.setCredential(dummy);
    return result;
  }

  @Override
  public PaasEntities.Platform apply(Platform platform) {
    PaasEntities.Platform.Builder result = PaasEntities.Platform.newBuilder();
    result.setId(platform.getId())
        .setName(platform.getName())
        .setProviderId(platform.getApi().getProviderName())
        .setPlatformType(platformTypeConverter.apply(platform.getType()));
    result.clearEndpoint();
    return result.build();
  }

  private class PlatformTypeConverter implements
      TwoWayConverter<Platform.TypeEnum, PaasEntities.PlatformType> {

    @Override
    public Platform.TypeEnum applyBack(PaasEntities.PlatformType platformType) {
      Platform.TypeEnum result;
      switch (platformType) {
        case HEROKU:
          result = Platform.TypeEnum.HEROKU;
          break;
        case OPENSHIFT:
          result = Platform.TypeEnum.OPENSHIFT;
          break;
        case CLOUDFOUNDRY:
          result = Platform.TypeEnum.CLOUDFOUNDRY;
          break;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unkown PlatformType: " + platformType);
      }
      return result;
    }

    @Override
    public PaasEntities.PlatformType apply(Platform.TypeEnum typeEnum) {
      PaasEntities.PlatformType result;
      switch (typeEnum) {
        case HEROKU:
          result = PaasEntities.PlatformType.HEROKU;
          break;
        case OPENSHIFT:
          result = PaasEntities.PlatformType.OPENSHIFT;
          break;
        case CLOUDFOUNDRY:
          result = PaasEntities.PlatformType.CLOUDFOUNDRY;
          break;
        default:
          throw new AssertionError("Unkown PlatformType: " + typeEnum);
      }
      return result;
    }
  }
}
