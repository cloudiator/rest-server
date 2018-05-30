package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Monitor;
import org.cloudiator.messages.entities.MonitorEntities;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class MonitorConverter implements TwoWayConverter<Monitor, MonitorEntities.Monitor> {


  @Override
  public Monitor applyBack(MonitorEntities.Monitor kafkamonitor) {
    Monitor restmonitor = new Monitor();
    return null;
  }

  @Override
  public MonitorEntities.Monitor apply(Monitor restmonitor) {
    //from REST to protobuf

    return null;
  }
}
