package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.MonitoringTag;
import org.cloudiator.messages.entities.MonitorEntities;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class MonitorTagConverter implements
    TwoWayConverter<MonitoringTag, MonitorEntities.MonitoringTag> {


  @Override
  public MonitoringTag applyBack(MonitorEntities.MonitoringTag kafkaMonitoringTag) {
    MonitoringTag restMonitorTag = new MonitoringTag()
        .key(kafkaMonitoringTag.getKey())
        .value(kafkaMonitoringTag.getValue());
    return restMonitorTag;
  }

  @Override
  public MonitorEntities.MonitoringTag apply(MonitoringTag restMonitorTag) {
    MonitorEntities.MonitoringTag.Builder result = MonitorEntities.MonitoringTag.newBuilder()
        .setKey(restMonitorTag.getKey())
        .setValue(restMonitorTag.getValue());

    return result.build();
  }
}
