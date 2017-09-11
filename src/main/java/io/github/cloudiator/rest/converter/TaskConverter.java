package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Task;
import org.cloudiator.messages.entities.TaskEntities;

public class TaskConverter implements TwoWayConverter<Task, TaskEntities.Task>{

  @Override
  public Task applyBack(TaskEntities.Task task) {
    return null;
  }

  @Override
  public TaskEntities.Task apply(Task task) {
    return null;
  }
}
