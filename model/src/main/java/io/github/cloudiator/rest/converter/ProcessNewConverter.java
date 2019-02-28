package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.CloudiatorProcessNew;
import org.cloudiator.messages.entities.ProcessEntities;
import org.cloudiator.messages.entities.ProcessEntities.NodeGroup;

public class ProcessNewConverter implements
    OneWayConverter<CloudiatorProcessNew, ProcessEntities.ProcessNew> {

  public static final ProcessNewConverter INSTANCE = new ProcessNewConverter();

  private ProcessNewConverter() {
  }

  @Override
  public ProcessEntities.ProcessNew apply(CloudiatorProcessNew processNew) {
    return ProcessEntities.ProcessNew.newBuilder()
        .addAllNodes(processNew.getNodes())
        .setSchedule(processNew.getSchedule())
        .setTask(processNew.getTask()).build();
  }
}
