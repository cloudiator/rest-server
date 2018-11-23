package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.CloudConfiguration;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 31.05.17.
 */
public class CloudConfigurationConverter implements
    TwoWayConverter<CloudConfiguration, IaasEntities.Configuration> {

  @Override
  public CloudConfiguration applyBack(IaasEntities.Configuration configuration) {
    CloudConfiguration result = new CloudConfiguration();
    result.nodeGroup(configuration.getNodeGroup());
    if (!configuration.getPropertiesMap().isEmpty()) {
      result.setProperties(configuration.getPropertiesMap());
    }
    return result;
  }

  @Override
  public IaasEntities.Configuration apply(CloudConfiguration cloudConfiguration) {
    IaasEntities.Configuration result = null;
    if (cloudConfiguration != null) {
      IaasEntities.Configuration.Builder builder = IaasEntities.Configuration.newBuilder();
      if (cloudConfiguration.getNodeGroup() != null) {
        builder.setNodeGroup(cloudConfiguration.getNodeGroup());
      } else {
        builder.clearNodeGroup();
      }
      if (cloudConfiguration.getProperties() != null) {
        builder.putAllProperties(cloudConfiguration.getProperties());
      }
      result = builder.build();
    }
    return result;
  }

}
