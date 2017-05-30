package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Cloud;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class CloudToCloudConverter implements TwoWayConverter<Cloud,IaasEntities.Cloud>{

  @Override
  public Cloud applyBack(IaasEntities.Cloud cloud) {
    return null;
  }

  @Override
  public IaasEntities.Cloud apply(Cloud cloud) {
    return null;
  }
}
