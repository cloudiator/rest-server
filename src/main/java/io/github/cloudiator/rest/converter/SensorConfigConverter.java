package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Property;
import io.github.cloudiator.rest.model.Sensor;
import io.github.cloudiator.rest.model.SensorConfiguration;
import org.cloudiator.messages.entities.MonitorEntities;

public class SensorConfigConverter implements
    TwoWayConverter<SensorConfiguration, MonitorEntities.SensorConfiguration> {

  private final SensorPropertyConverter sensorPropertyConverter = new SensorPropertyConverter();


  @Override
  public SensorConfiguration applyBack(MonitorEntities.SensorConfiguration kafkaSensorConfig) {
    SensorConfiguration result = new SensorConfiguration();
    if (!kafkaSensorConfig.getPropertiesList().isEmpty()) {
      for (MonitorEntities.ConfigurationProperties prop : kafkaSensorConfig.getPropertiesList()) {
        result.addPropertiesItem(sensorPropertyConverter.applyBack(prop));
      }
    }

    return result;
  }

  @Override
  public MonitorEntities.SensorConfiguration apply(SensorConfiguration restSensorConfig) {
    MonitorEntities.SensorConfiguration.Builder result = MonitorEntities.SensorConfiguration
        .newBuilder();
    if (!restSensorConfig.getProperties().isEmpty()) {
      for (Property restprop : restSensorConfig.getProperties()) {
        result.addProperties(sensorPropertyConverter.apply(restprop));
      }
    } else {
      result.clearProperties();
    }
    return result.build();
  }

  private class SensorPropertyConverter implements
      TwoWayConverter<Property, MonitorEntities.ConfigurationProperties> {


    @Override
    public Property applyBack(MonitorEntities.ConfigurationProperties kafkaProperty) {
      Property result = new Property()
          .key(kafkaProperty.getKey())
          .value(kafkaProperty.getValue());

      return result;
    }

    @Override
    public MonitorEntities.ConfigurationProperties apply(Property restproperty) {
      MonitorEntities.ConfigurationProperties.Builder result = MonitorEntities.ConfigurationProperties
          .newBuilder()
          .setKey(restproperty.getKey())
          .setValue(restproperty.getValue());

      return result.build();
    }
  }
}
