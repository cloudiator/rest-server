package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.PullSensor;
import java.util.HashMap;
import java.util.Map;
import org.cloudiator.messages.entities.MonitorEntities;


public class PullSensorConverter implements
    TwoWayConverter<PullSensor, MonitorEntities.PullSensor> {

  private final IntervalConverter intervalConverter = new IntervalConverter();


  @Override
  public PullSensor applyBack(MonitorEntities.PullSensor kafkaSensor) {
    PullSensor result = new PullSensor();
    result.setClassName(kafkaSensor.getClassName());
    result.setInterval(intervalConverter.applyBack(kafkaSensor.getInterval()));
    result.setType(kafkaSensor.getClass().getSimpleName());
    Map configs = new HashMap();
    configs.putAll(kafkaSensor.getConfigurationMap());
    result.setConfiguration(configs);

    return result;
  }

  @Override
  public MonitorEntities.PullSensor apply(PullSensor restSensor) {
    MonitorEntities.PullSensor.Builder result = MonitorEntities.PullSensor.newBuilder()
        .setClassName(restSensor.getType())
        .setInterval(intervalConverter.apply(restSensor.getInterval()))
        .putAllConfiguration(restSensor.getConfiguration());

    return result.build();
  }
}
