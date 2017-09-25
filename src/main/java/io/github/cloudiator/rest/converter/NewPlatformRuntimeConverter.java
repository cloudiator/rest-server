package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.NewPlatformRuntime;
import org.cloudiator.messages.entities.PaasEntities;

public class NewPlatformRuntimeConverter implements
    TwoWayConverter<NewPlatformRuntime, PaasEntities.NewRuntime> {

  private final RuntimeLanguageConverter runtimeLanguageConverter = new RuntimeLanguageConverter();
  private final RuntimeTypeConverter runtimeTypeConverter = new RuntimeTypeConverter();

  @Override
  public NewPlatformRuntime applyBack(PaasEntities.NewRuntime runtime) {
    NewPlatformRuntime result = new NewPlatformRuntime()
        .name(runtime.getName())
        .language(runtimeLanguageConverter.applyBack(runtime.getRuntimeLanguage()))
        .runtimeType(runtimeTypeConverter.applyBack(runtime.getRuntimeType()))
        .version(runtime.getRuntimeTypeVersion());
    return result;
  }

  @Override
  public PaasEntities.NewRuntime apply(NewPlatformRuntime platformRuntime) {
    PaasEntities.NewRuntime.Builder result = null;
    result.setRuntimeLanguage(runtimeLanguageConverter.apply(platformRuntime.getLanguage()))
        .setRuntimeType(runtimeTypeConverter.apply(platformRuntime.getRuntimeType()))
        .setRuntimeLanguageVersion(platformRuntime.getVersion())
        .setRuntimeTypeVersion(platformRuntime.getVersion())
        .setName(platformRuntime.getName())
        .clearProviderId();

    return result.build();
  }

  private class RuntimeTypeConverter implements
      TwoWayConverter<NewPlatformRuntime.RuntimeTypeEnum, PaasEntities.RuntimeType> {

    @Override
    public NewPlatformRuntime.RuntimeTypeEnum applyBack(PaasEntities.RuntimeType runtimeType) {
      NewPlatformRuntime.RuntimeTypeEnum result = null;
      switch (runtimeType) {
        case SERVER:
          result = NewPlatformRuntime.RuntimeTypeEnum.SERVER;
          break;
        case STANDALONE:
          result = NewPlatformRuntime.RuntimeTypeEnum.STANDALONE;
          break;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unkown RuntimeType: " + runtimeType);
      }
      return result;
    }

    @Override
    public PaasEntities.RuntimeType apply(NewPlatformRuntime.RuntimeTypeEnum runtimeTypeEnum) {
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
      TwoWayConverter<NewPlatformRuntime.LanguageEnum, PaasEntities.RuntimeLanguage> {

    @Override
    public NewPlatformRuntime.LanguageEnum applyBack(PaasEntities.RuntimeLanguage runtimeLanguage) {
      NewPlatformRuntime.LanguageEnum result = null;
      switch (runtimeLanguage) {
        case PHP:
          result = NewPlatformRuntime.LanguageEnum.PHP;
          break;
        case JAVA:
          result = NewPlatformRuntime.LanguageEnum.JAVA;
          break;
        case RUBY:
          result = NewPlatformRuntime.LanguageEnum.RUBY;
          break;
        case PYTHON:
          result = NewPlatformRuntime.LanguageEnum.PYTHON;
          break;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unkown LanguageType: " + runtimeLanguage);
      }
      return result;
    }

    @Override
    public PaasEntities.RuntimeLanguage apply(NewPlatformRuntime.LanguageEnum languageEnum) {
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
