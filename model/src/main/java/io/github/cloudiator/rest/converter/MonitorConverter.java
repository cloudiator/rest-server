package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.DataSink;
import io.github.cloudiator.rest.model.Monitor;
import io.github.cloudiator.rest.model.MonitoringTarget;
import java.util.HashMap;
import java.util.Map;
import org.cloudiator.messages.entities.MonitorEntities;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class MonitorConverter implements TwoWayConverter<Monitor, MonitorEntities.Monitor> {

  private final MonitorTargetConverter monitorTargetConverter = new MonitorTargetConverter();
  private final SensorConverter sensorConverter = new SensorConverter();
  private final DataSinkConverter sinkConverter = new DataSinkConverter();

  @Override
  public Monitor applyBack(MonitorEntities.Monitor kafkamonitor) {
    Monitor restmonitor = new Monitor()
        .metric(kafkamonitor.getMetric());
    //Sensor
    restmonitor.setSensor(sensorConverter.applyBack(kafkamonitor.getSensor()));
    //Targets
    if (!kafkamonitor.getTargetList().isEmpty()) {
      for (MonitorEntities.MonitoringTarget monTarg : kafkamonitor.getTargetList()) {
        if (monTarg != null) {
          restmonitor.addTargetsItem(monitorTargetConverter.applyBack(monTarg));
        }
      }
    }
    //Sinks
    for (MonitorEntities.Sink sink : kafkamonitor.getDatasinkList()) {
      restmonitor.addSinksItem(sinkConverter.applyBack(sink));
    }
    //Tags
    Map<String, String> tags = new HashMap<>();
    if (!kafkamonitor.getTagsMap().isEmpty()) {
      tags.putAll(kafkamonitor.getTagsMap());
    }
    restmonitor.setTags(tags);

    return restmonitor;
  }

  @Override
  public MonitorEntities.Monitor apply(Monitor restmonitor) {
    //from REST to protobuf
    MonitorEntities.Monitor.Builder builder = MonitorEntities.Monitor.newBuilder()
        .setMetric(restmonitor.getMetric());
    //Targets
    if (!restmonitor.getTargets().isEmpty()) {
      for (MonitoringTarget monTarg : restmonitor.getTargets()) {
        builder.addTarget(monitorTargetConverter.apply(monTarg));
      }
    } else {
      builder.clearTarget();
    }
    //Sensor
    builder.setSensor(sensorConverter.apply(restmonitor.getSensor()));
    //Sinks
    if (!restmonitor.getSinks().isEmpty()) {
      for (DataSink sink : restmonitor.getSinks()) {
        builder.addDatasink(sinkConverter.apply(sink));
      }
    } else {
      builder.clearDatasink();
    }
    //Tags
    if (restmonitor.getTags() != null) {
      builder.putAllTags(restmonitor.getTags());
    } else {
      builder.clearTags();
    }

    return builder.build();
  }
}
