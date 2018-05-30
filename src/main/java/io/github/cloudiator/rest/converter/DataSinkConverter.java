package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.DataSink;
import io.github.cloudiator.rest.model.DataSink.TypeEnum;
import io.github.cloudiator.rest.model.DataSinkConfiguration;
import io.github.cloudiator.rest.model.PullSensor;
import io.github.cloudiator.rest.model.PushSensor;
import io.github.cloudiator.rest.model.Sensor;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.MonitorEntities.SinkConfiguration;
import org.cloudiator.messages.entities.MonitorEntities.SinkType;

public class DataSinkConverter implements TwoWayConverter<DataSink, MonitorEntities.Sink> {

  private final SinkTypeConverter sinkTypeConverter = new SinkTypeConverter();
  private final SinkConfigConverter sinkConfigConverter = new SinkConfigConverter();

  @Override
  public DataSink applyBack(MonitorEntities.Sink kafkaSink) {
    DataSink dataSink = new DataSink()
        .type(sinkTypeConverter.applyBack(kafkaSink.getType()))
        ._configuration(sinkConfigConverter.applyBack(kafkaSink.getConfiguration()));

    return dataSink;
  }

  @Override
  public MonitorEntities.Sink apply(DataSink restSink) {
    MonitorEntities.Sink.Builder result = MonitorEntities.Sink.newBuilder()
        .setType(sinkTypeConverter.apply(restSink.getType()))
        .setConfiguration(sinkConfigConverter.apply(restSink.getConfiguration()));

    return result.build();
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

  private class SinkConfigConverter implements
      TwoWayConverter<DataSinkConfiguration, MonitorEntities.SinkConfiguration> {

    @Override
    public DataSinkConfiguration applyBack(SinkConfiguration kafkaSinkConfig) {
      DataSinkConfiguration result = new DataSinkConfiguration()
          .key(kafkaSinkConfig.getKey())
          .value(kafkaSinkConfig.getValue());

      return result;
    }

    @Override
    public MonitorEntities.SinkConfiguration apply(DataSinkConfiguration restSinkConfig) {
      MonitorEntities.SinkConfiguration.Builder result = MonitorEntities.SinkConfiguration
          .newBuilder()
          .setKey(restSinkConfig.getKey())
          .setValue(restSinkConfig.getValue());

      return result.build();
    }
  }


}
