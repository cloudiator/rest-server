package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Api;
import org.cloudiator.messages.entities.IaasEntities;


/**
 * Created by volker on 29.05.17.
 */
public class ApiToApiConverter implements TwoWayConverter<Api, IaasEntities.Api> {

  @Override
  public Api applyBack(IaasEntities.Api api) {
    Api result = new Api();
    result.setProviderName(api.getProviderName());
    return result;
  }

  @Override
  public IaasEntities.Api apply(Api api) {
    return IaasEntities.Api.newBuilder().setProviderName(api.getProviderName()).build();
  }
}
