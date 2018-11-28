package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.DataSink;
import io.github.cloudiator.rest.model.Monitor;
import io.github.cloudiator.rest.model.MonitoringTag;
import io.github.cloudiator.rest.model.MonitoringTarget;
import org.cloudiator.messages.entities.MonitorEntities;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class MonitorConverter implements TwoWayConverter<Monitor, MonitorEntities.Monitor> {

  private final MonitorTargetConverter monitorTargetConverter = new MonitorTargetConverter();
  private final SensorConverter sensorConverter = new SensorConverter();
  private final MonitorTagConverter monitorTagConverter = new MonitorTagConverter();
  private final DataSinkConverter sinkConverter = new DataSinkConverter();

  @Override
  public Monitor applyBack(MonitorEntities.Monitor kafkamonitor) {
    Monitor restmonitor = new Monitor()
        .metric(kafkamonitor.getMetric())
        .sensor(sensorConverter.applyBack(kafkamonitor.getSensor()));
    //Targets
    if (!kafkamonitor.getTargetList().isEmpty()) {
      for (MonitorEntities.MonitoringTarget monTarg : kafkamonitor.getTargetList()) {
        if (monTarg != null) {
          restmonitor.addTargetsItem(monitorTargetConverter.applyBack(monTarg));
        }
      }
    }
    //Sinks
    if (!kafkamonitor.getDatasinkList().isEmpty()) {
      for (MonitorEntities.Sink sink : kafkamonitor.getDatasinkList()) {
        restmonitor.addSinksItem(sinkConverter.applyBack(sink));
      }
    }
    //Tags
    if (!kafkamonitor.getTagsList().isEmpty()) {
      for (MonitorEntities.MonitoringTag tag : kafkamonitor.getTagsList()) {
        restmonitor.addTagsItem(monitorTagConverter.applyBack(tag));
      }
    }

    return restmonitor;
  }

  @Override
  public MonitorEntities.Monitor apply(Monitor restmonitor) {
    //from REST to protobuf
    MonitorEntities.Monitor.Builder builder = MonitorEntities.Monitor.newBuilder()
        .setMetric(restmonitor.getMetric())
        .setSensor(sensorConverter.apply(restmonitor.getSensor()));
    //Targets
    if (!restmonitor.getTargets().isEmpty()) {
      for (MonitoringTarget monTarg : restmonitor.getTargets()) {
        builder.addTarget(monitorTargetConverter.apply(monTarg));
      }
    } else {
      builder.clearTarget();
    }
    //Sinks
    if (!restmonitor.getSinks().isEmpty()) {
      for (DataSink sink : restmonitor.getSinks()) {
        builder.addDatasink(sinkConverter.apply(sink));
      }
    } else {
      builder.clearDatasink();
    }
    //Tags
    if (!restmonitor.getTags().isEmpty()) {
      for (MonitoringTag tag : restmonitor.getTags()) {
        builder.addTags(monitorTagConverter.apply(tag));
      }
    } else {
      builder.clearTags();
    }

    return builder.build();
  }
}
