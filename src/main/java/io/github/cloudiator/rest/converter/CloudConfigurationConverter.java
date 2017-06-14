package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.CloudConfiguration;
import io.github.cloudiator.rest.model.Property;
import org.cloudiator.messages.entities.IaasEntities;

import java.util.List;

/**
 * Created by volker on 31.05.17.
 */
public class CloudConfigurationConverter implements
    TwoWayConverter<CloudConfiguration, IaasEntities.Configuration> {

  private final PropertyConverter propertyConverter = new PropertyConverter();

  @Override
  public CloudConfiguration applyBack(IaasEntities.Configuration configuration) {
    CloudConfiguration result = new CloudConfiguration();
    result.nodeGroup(configuration.getNodeGroup());

    for (IaasEntities.Property property : configuration.getPropertyList()) {
      result.addPropertiesItem(propertyConverter.applyBack(property));
    }
    return result;
  }

  @Override
  public IaasEntities.Configuration apply(CloudConfiguration cloudConfiguration) {
    IaasEntities.Configuration.Builder builder = IaasEntities.Configuration.newBuilder();
    System.out.println(cloudConfiguration);
    builder.setNodeGroup(cloudConfiguration.getNodeGroup());
    if (cloudConfiguration.getProperties() != null) {
      for (Property property : cloudConfiguration.getProperties()) {
        builder.addProperty(propertyConverter.apply(property));
      }
    }
    return builder.build();
  }


  private class PropertyConverter implements TwoWayConverter<Property, IaasEntities.Property> {


    @Override
    public Property applyBack(IaasEntities.Property property) {
      Property result = new Property();
      result.setKey(property.getKey());
      result.setValue(property.getValue());
      return result;
    }

    @Override
    public IaasEntities.Property apply(Property property) {
      return IaasEntities.Property.newBuilder().setKey(property.getKey())
          .setValue(property.getValue()).build();
    }
  }

}
