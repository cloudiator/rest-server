package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.PortRequired;
import io.github.cloudiator.rest.model.PullSensor;
import io.github.cloudiator.rest.model.SensorConfiguration;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.TaskEntities;

public class PullSensorConverter implements
    TwoWayConverter<PullSensor, MonitorEntities.PullSensor> {

  private final IntervalConverter intervalConverter = new IntervalConverter();
  private final SensorConfigConverter sensorConfigConverter = new SensorConfigConverter();


  @Override
  public PullSensor applyBack(MonitorEntities.PullSensor kafkaSensor) {
    PullSensor result = new PullSensor();
    result.setClassName(kafkaSensor.getClassName());
    result.setInterval(intervalConverter.applyBack(kafkaSensor.getInterval()));
    result.setConfiguration(sensorConfigConverter.applyBack(kafkaSensor.getConfiguration()));

    return result;
  }

  @Override
  public MonitorEntities.PullSensor apply(PullSensor restSensor) {
    MonitorEntities.PullSensor.Builder result = MonitorEntities.PullSensor.newBuilder()
        .setClassName(restSensor.getClassName())
        .setInterval(intervalConverter.apply(restSensor.getInterval()))
        .setConfiguration(sensorConfigConverter.apply(restSensor.getConfiguration()));

    return result.build();
  }
}
