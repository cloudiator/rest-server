package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.DataSink;
import io.github.cloudiator.rest.model.DataSink.TypeEnum;
import io.github.cloudiator.rest.model.PullSensor;
import io.github.cloudiator.rest.model.PushSensor;
import io.github.cloudiator.rest.model.Sensor;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.MonitorEntities.SinkType;

public class DataSinkConverter implements TwoWayConverter<DataSink, MonitorEntities.Sink> {

  private final SinkTypeConverter sinkTypeConverter = new SinkTypeConverter();

  @Override
  public DataSink applyBack(MonitorEntities.Sink kafkaSink) {

    return null;
  }

  @Override
  public MonitorEntities.Sink apply(DataSink restSink) {
    MonitorEntities.Sensor.Builder result = MonitorEntities.Sensor.newBuilder();

    return null;
  }

  private class SinkTypeConverter implements TwoWayConverter<TypeEnum, MonitorEntities.SinkType> {

    @Override
    public TypeEnum applyBack(SinkType sinkType) {
      switch (sinkType) {
        case INFLUX:
          return TypeEnum.INFLUX;
        case KAIROS_DB:
          return TypeEnum.KAIROS_DB;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("unkown SinkTyp: " + sinkType);
      }
    }

    @Override
    public SinkType apply(TypeEnum typeEnum) {
      switch (typeEnum) {
        case KAIROS_DB:
          return SinkType.KAIROS_DB;
        case INFLUX:
          return SinkType.INFLUX;
        default:
          throw new AssertionError("unkown SinkTyp: " + typeEnum);
      }
    }
  }
}
