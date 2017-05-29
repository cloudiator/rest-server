package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Api;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class ApiToApiConverter implements TwoWayConverter<Api,IaasEntities.Api>{

  @Override
  public Api applyBack(IaasEntities.Api api) {
    return null;
  }

  @Override
  public IaasEntities.Api apply(Api api) {
    return null;
  }
}
