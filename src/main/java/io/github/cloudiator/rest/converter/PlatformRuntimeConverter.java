package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Platform;
import io.github.cloudiator.rest.model.PlatformRuntime;
import org.cloudiator.messages.entities.PaasEntities;

public class PlatformRuntimeConverter implements
    TwoWayConverter<PlatformRuntime, PaasEntities.Runtime> {

  private final RuntimeLanguageConverter runtimeLanguageConverter = new RuntimeLanguageConverter();
  private final RuntimeTypeConverter runtimeTypeConverter = new RuntimeTypeConverter();

  @Override
  public PlatformRuntime applyBack(PaasEntities.Runtime runtime) {
    PlatformRuntime result = new PlatformRuntime()
        .id(runtime.getId())
        .name(runtime.getName())
        .language(runtimeLanguageConverter.applyBack(runtime.getRuntimeLanguage()))
        .runtimeType(runtimeTypeConverter.applyBack(runtime.getRuntimeType()))
        .version(runtime.getRuntimeTypeVersion());
    return result;
  }

  @Override
  public PaasEntities.Runtime apply(PlatformRuntime platformRuntime) {
    PaasEntities.Runtime.Builder result = null;
    result.setId(platformRuntime.getId())
        .setRuntimeLanguage(runtimeLanguageConverter.apply(platformRuntime.getLanguage()))
        .setRuntimeType(runtimeTypeConverter.apply(platformRuntime.getRuntimeType()))
        .setRuntimeLanguageVersion(platformRuntime.getVersion())
        .setRuntimeTypeVersion(platformRuntime.getVersion())
        .setName(platformRuntime.getName())
        .clearProviderId();

    return result.build();
  }

  private class RuntimeTypeConverter implements
      TwoWayConverter<PlatformRuntime.RuntimeTypeEnum, PaasEntities.RuntimeType> {

    @Override
    public PlatformRuntime.RuntimeTypeEnum applyBack(PaasEntities.RuntimeType runtimeType) {
      PlatformRuntime.RuntimeTypeEnum result = null;
      switch (runtimeType) {
        case SERVER:
          result = PlatformRuntime.RuntimeTypeEnum.SERVER;
          break;
        case STANDALONE:
          result = PlatformRuntime.RuntimeTypeEnum.STANDALONE;
          break;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unkown RuntimeType: " + runtimeType);
      }
      return result;
    }

    @Override
    public PaasEntities.RuntimeType apply(PlatformRuntime.RuntimeTypeEnum runtimeTypeEnum) {
      PaasEntities.RuntimeType result = null;
      switch (runtimeTypeEnum) {
        case SERVER:
          result = PaasEntities.RuntimeType.SERVER;
          break;
        case STANDALONE:
          result = PaasEntities.RuntimeType.STANDALONE;
          break;
        default:
          throw new AssertionError("Unkown RuntimeType: " + runtimeTypeEnum);
      }
      return result;
    }
  }

  private class RuntimeLanguageConverter implements
      TwoWayConverter<PlatformRuntime.LanguageEnum, PaasEntities.RuntimeLanguage> {

    @Override
    public PlatformRuntime.LanguageEnum applyBack(PaasEntities.RuntimeLanguage runtimeLanguage) {
      PlatformRuntime.LanguageEnum result = null;
      switch (runtimeLanguage) {
        case PHP:
          result = PlatformRuntime.LanguageEnum.PHP;
          break;
        case JAVA:
          result = PlatformRuntime.LanguageEnum.JAVA;
          break;
        case RUBY:
          result = PlatformRuntime.LanguageEnum.RUBY;
          break;
        case PYTHON:
          result = PlatformRuntime.LanguageEnum.PYTHON;
          break;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unkown LanguageType: " + runtimeLanguage);
      }
      return result;
    }

    @Override
    public PaasEntities.RuntimeLanguage apply(PlatformRuntime.LanguageEnum languageEnum) {
      PaasEntities.RuntimeLanguage result = null;
      switch (languageEnum) {
        case PHP:
          result = PaasEntities.RuntimeLanguage.PHP;
          break;
        case JAVA:
          result = PaasEntities.RuntimeLanguage.JAVA;
          break;
        case RUBY:
          result = PaasEntities.RuntimeLanguage.RUBY;
          break;
        case PYTHON:
          result = PaasEntities.RuntimeLanguage.PYTHON;
          break;
        default:
          throw new AssertionError("Unkown RuntimeLanguage: " + languageEnum);
      }
      return result;
    }
  }
}
