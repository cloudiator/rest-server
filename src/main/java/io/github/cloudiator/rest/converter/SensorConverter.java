package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Port;
import io.github.cloudiator.rest.model.PortProvided;
import io.github.cloudiator.rest.model.PortRequired;
import io.github.cloudiator.rest.model.PullSensor;
import io.github.cloudiator.rest.model.PushSensor;
import io.github.cloudiator.rest.model.Sensor;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.TaskEntities;

public class SensorConverter implements TwoWayConverter<Sensor, MonitorEntities.Sensor> {

  private final PushSensorConverter pushSensorConverter = new PushSensorConverter();
  private final PullSensorConverter pullSensorConverter = new PullSensorConverter();

  @Override
  public Sensor applyBack(MonitorEntities.Sensor kafkaSensor) {
    Sensor result;
    switch (kafkaSensor.getSensorCase()) {

      case PULLSENSOR:
        result = pullSensorConverter.applyBack(kafkaSensor.getPullsensor());
        break;
      case PUSHSENSOR:
        result = pushSensorConverter.applyBack(kafkaSensor.getPushsensor());
        break;
      case SENSOR_NOT_SET:
      default:
        throw new AssertionError("Sensor is invalid: " + kafkaSensor.getSensorCase());
    }

    return result;
  }

  @Override
  public MonitorEntities.Sensor apply(Sensor restSensor) {
    MonitorEntities.Sensor.Builder result = MonitorEntities.Sensor.newBuilder();

    if (restSensor instanceof PullSensor) {
      result.setPullsensor(pullSensorConverter.apply((PullSensor) restSensor));
    } else if (restSensor instanceof PushSensor) {
      result.setPushsensor(pushSensorConverter.apply((PushSensor) restSensor));
    } else {
      throw new AssertionError("unkown Sensortyp: " + restSensor.getType());
    }

    return result.build();
  }
}
