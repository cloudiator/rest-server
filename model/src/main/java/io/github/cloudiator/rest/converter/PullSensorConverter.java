package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Interval;
import io.github.cloudiator.rest.model.Interval.UnitEnum;
import io.github.cloudiator.rest.model.PullSensor;
import java.util.HashMap;
import java.util.Map;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.MonitorEntities.Unit;


public class PullSensorConverter implements
    TwoWayConverter<PullSensor, MonitorEntities.PullSensor> {

  @Override
  public PullSensor applyBack(MonitorEntities.PullSensor kafkaSensor) {
    PullSensor result = new PullSensor()
        .className(kafkaSensor.getClassName())
        .interval(new Interval().unit(UnitEnum.valueOf(kafkaSensor.getInterval().getUnit().name()))
            .period(kafkaSensor.getInterval().getPeriod()))
        ._configuration(kafkaSensor.getConfigurationMap());
    result.setType(result.getClass().getSimpleName());
    return result;
  }

  @Override
  public MonitorEntities.PullSensor apply(PullSensor restSensor) {
    MonitorEntities.PullSensor.Builder result = MonitorEntities.PullSensor.newBuilder()
        .setClassName(restSensor.getClassName())
        .setInterval(MonitorEntities.Interval.newBuilder()
            .setUnit(Unit.valueOf(restSensor.getInterval().getUnit().name()))
            .setPeriod(restSensor.getInterval().getPeriod()).build());
    if (!restSensor.getConfiguration().isEmpty()) {
      result.putAllConfiguration(restSensor.getConfiguration());
    } else {
      result.clearConfiguration();
    }

    return result.build();
  }
}
