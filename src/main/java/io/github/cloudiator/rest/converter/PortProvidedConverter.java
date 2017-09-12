package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.PortProvided;
import org.cloudiator.messages.entities.TaskEntities;

public class PortProvidedConverter implements
    TwoWayConverter<PortProvided, TaskEntities.PortProvided> {

  @Override
  public PortProvided applyBack(TaskEntities.PortProvided portProvided) {
    PortProvided result = new PortProvided();
    result.setName(portProvided.getName());
    result.setPort(portProvided.getPort());
    result.setType(result.getClass().getSimpleName());

    return result;
  }

  @Override
  public TaskEntities.PortProvided apply(PortProvided portProvided) {

    TaskEntities.PortProvided.Builder result = TaskEntities.PortProvided.newBuilder();
    result.setName(portProvided.getName()).setPort(portProvided.getPort());

    return result.build();
  }
}
