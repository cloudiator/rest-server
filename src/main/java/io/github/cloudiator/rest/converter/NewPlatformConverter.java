package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.CloudCredential;
import io.github.cloudiator.rest.model.NewPlatform;
import io.github.cloudiator.rest.model.NewPlatform.TypeEnum;
import org.cloudiator.messages.entities.PaasEntities;
import org.cloudiator.messages.entities.PaasEntities.Platform;

public class NewPlatformConverter implements TwoWayConverter<NewPlatform, Platform> {

  private final PlatformTypeConverter platformTypeConverter = new PlatformTypeConverter();

  @Override
  public NewPlatform applyBack(PaasEntities.Platform platform) {
    NewPlatform result = new NewPlatform()
        .name(platform.getName())
        .type(platformTypeConverter.applyBack(platform.getPlatformType()))
        .api(new Api().providerName(platform.getProviderId()))
        .endpoint(platform.getEndpoint());
    //Dummy, Credential is missing by IaasEntity
    CloudCredential dummy = new CloudCredential();
    dummy.setUser("dummyUser");
    dummy.setSecret("dummySecret");
    result.setCredential(dummy);
    return result;
  }

  @Override
  public PaasEntities.Platform apply(NewPlatform platform) {
    PaasEntities.Platform.Builder result = PaasEntities.Platform.newBuilder();
    result.setName(platform.getName())
        .setProviderId(platform.getApi().getProviderName())
        .setPlatformType(platformTypeConverter.apply(platform.getType()))
        .setEndpoint(platform.getEndpoint());

    return result.build();
  }

  private class PlatformTypeConverter implements
      TwoWayConverter<TypeEnum, PaasEntities.PlatformType> {

    @Override
    public TypeEnum applyBack(PaasEntities.PlatformType platformType) {
      TypeEnum result;
      switch (platformType) {
        case HEROKU:
          result = TypeEnum.HEROKU;
          break;
        case OPENSHIFT:
          result = TypeEnum.OPENSHIFT;
          break;
        case CLOUDFOUNDRY:
          result = TypeEnum.CLOUDFOUNDRY;
          break;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unkown PlatformType: " + platformType);
      }
      return result;
    }

    @Override
    public PaasEntities.PlatformType apply(TypeEnum typeEnum) {
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
