package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Monitor;
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

    if (!kafkamonitor.getTargetList().isEmpty()) {
      for (MonitorEntities.MonitoringTarget monTag : kafkamonitor.getTargetList()) {
        restmonitor.addTargetsItem(monitorTargetConverter.applyBack(monTag));
      }
    }
   
    return null;
  }

  @Override
  public MonitorEntities.Monitor apply(Monitor restmonitor) {
    //from REST to protobuf

    return null;
  }
}
