package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.VirtualMachine;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class VirtualMachineConverter /*implements TwoWayConverter<VirtualMachine, IaasEntities.VirtualMachine>*/{

 // @Override
  public Api applyBack(IaasEntities.Api api) {
    Api result = new Api();
    result.setProviderName(api.getProviderName());
    return result;
  }

  //@Override
  public IaasEntities.Api apply(Api api) {
    return IaasEntities.Api.newBuilder().setProviderName(api.getProviderName()).build();
  }
}
