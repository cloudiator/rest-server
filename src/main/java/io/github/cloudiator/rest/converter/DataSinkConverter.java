package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.DataSink;
import io.github.cloudiator.rest.model.DataSink.TypeEnum;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.MonitorEntities.Sink;

import org.cloudiator.messages.entities.MonitorEntities.SinkType;

public class DataSinkConverter implements TwoWayConverter<DataSink, Sink> {

  private final SinkTypeConverter sinkTypeConverter = new SinkTypeConverter();


  @Override
  public DataSink applyBack(MonitorEntities.Sink kafkaSink) {
    DataSink dataSink = new DataSink()
        .type(sinkTypeConverter.applyBack(kafkaSink.getType()))
        ._configuration(kafkaSink.getConfigurationMap());

    return dataSink;
  }

  @Override
  public MonitorEntities.Sink apply(DataSink restSink) {
    MonitorEntities.Sink.Builder result = MonitorEntities.Sink.newBuilder()
        .setType(sinkTypeConverter.apply(restSink.getType()))
        .putAllConfiguration(restSink.getConfiguration());

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
}
