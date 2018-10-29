package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.PortProvided;
import io.github.cloudiator.rest.model.PushSensor;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.TaskEntities;

public class PushSensorConverter implements
    TwoWayConverter<PushSensor, MonitorEntities.PushSensor> {

  @Override
  public PushSensor applyBack(MonitorEntities.PushSensor kafkaPushSensor) {

    PushSensor rest = new PushSensor()
        .port(kafkaPushSensor.getPort());
    rest.setType(rest.getClass().getSimpleName());

    return rest;
  }

  @Override
  public MonitorEntities.PushSensor apply(PushSensor restPushSensor) {

    MonitorEntities.PushSensor.Builder result = MonitorEntities.PushSensor.newBuilder()
        .setPort(restPushSensor.getPort());

    return result.build();
  }
}
