package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.ProcessNew;
import org.cloudiator.messages.entities.ProcessEntities;

public class ProcessNewConverter implements
    OneWayConverter<ProcessNew, ProcessEntities.ProcessNew> {

  public static final ProcessNewConverter INSTANCE = new ProcessNewConverter();

  private ProcessNewConverter() {
  }

  @Override
  public ProcessEntities.ProcessNew apply(ProcessNew processNew) {
    return ProcessEntities.ProcessNew.newBuilder()
        .setNodeGroup(processNew.getNodeGroup())
        .setSchedule(processNew.getSchedule())
        .setTask(processNew.getTask()).build();
  }
}
