package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Interval;
import io.github.cloudiator.rest.model.PullSensor;
import io.github.cloudiator.rest.model.TimeUnit;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.MonitorEntities;


public class PullSensorConverter implements
    TwoWayConverter<PullSensor, MonitorEntities.PullSensor> {

  @Override
  public PullSensor applyBack(MonitorEntities.PullSensor kafkaSensor) {
    PullSensor result = new PullSensor()
        .className(kafkaSensor.getClassName())
        .interval(new Interval().unit(TimeUnit.valueOf(kafkaSensor.getInterval().getUnit().name()))
            .period(kafkaSensor.getInterval().getPeriod()))
        ._configuration(kafkaSensor.getConfigurationMap());
    result.setType(result.getClass().getSimpleName());
    return result;
  }

  @Override
  public MonitorEntities.PullSensor apply(PullSensor restSensor) {
    MonitorEntities.PullSensor.Builder result = MonitorEntities.PullSensor.newBuilder()
        .setClassName(restSensor.getClassName())
        .setInterval(CommonEntities.Interval.newBuilder()
            .setUnit(CommonEntities.Unit.valueOf(restSensor.getInterval().getUnit().name()))
            .setPeriod(restSensor.getInterval().getPeriod()).build());
    if (!restSensor.getConfiguration().isEmpty()) {
      result.putAllConfiguration(restSensor.getConfiguration());
    } else {
      result.clearConfiguration();
    }

    return result.build();
  }
}
