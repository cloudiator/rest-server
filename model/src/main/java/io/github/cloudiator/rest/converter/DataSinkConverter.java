package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.DataSink;
import io.github.cloudiator.rest.model.DataSink.TypeEnum;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.MonitorEntities.SinkType;

public class DataSinkConverter implements TwoWayConverter<DataSink, MonitorEntities.Sink> {

  @Override
  public DataSink applyBack(MonitorEntities.Sink kafkaDataSink) {
    DataSink result = new DataSink().type(TypeEnum.valueOf(kafkaDataSink.getType().name()));
    if (!kafkaDataSink.getConfigurationMap().isEmpty()) {
      result.setConfiguration(kafkaDataSink.getConfigurationMap());
    }
    return result;
  }

  @Override
  public MonitorEntities.Sink apply(DataSink restDataSink) {
    MonitorEntities.Sink.Builder result = MonitorEntities.Sink.newBuilder()
        .setType(SinkType.valueOf(restDataSink.getType().name()));
    if (restDataSink.getConfiguration() != null) {
      result.putAllConfiguration(restDataSink.getConfiguration());
    }
    return result.build();
  }
}
