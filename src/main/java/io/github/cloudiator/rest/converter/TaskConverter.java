package io.github.cloudiator.rest.converter;


import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.*;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.TaskEntities;


public class TaskConverter implements TwoWayConverter<Task, TaskEntities.Task> {

  private final PortConverter portConverter = new PortConverter();
  private final RequirementConverter requirementConverter = new RequirementConverter();
  private final TaskInterfaceConverter interfaceConverter = new TaskInterfaceConverter();
  private final OptimizationConverter optimizationConverter = OptimizationConverter.INSTANCE;

  @Override
  public Task applyBack(TaskEntities.Task task) {
    Task result = new Task()
        .name(task.getName());

    for (TaskEntities.Port port : task.getPortsList()) {
      result.addPortsItem(portConverter.applyBack(port));
    }

    for (CommonEntities.Requirement req : task.getRequirementsList()) {
      result.addRequirementsItem(requirementConverter.applyBack(req));
    }

    if (task.hasOptimization()) {
      result.setOptimization(optimizationConverter.applyBack(task.getOptimization()));
    }

    for (TaskEntities.TaskInterface tInterface : task.getInterfacesList()) {
      result.addInterfacesItem(interfaceConverter.applyBack(tInterface));
    }

    switch (task.getTaskType()) {
      case BATCH:
        result.setTaskType(TaskType.BATCH);
        break;
      case SERVICE:
        result.setTaskType(TaskType.SERVICE);
        break;
      case UNRECOGNIZED:
      default:
        throw new AssertionError("TaskType unrecognized: " + task.getTaskType());
    }

    return result;
  }

  @Override
  public TaskEntities.Task apply(Task task) {
    TaskEntities.Task.Builder result = TaskEntities.Task.newBuilder()
        .setName(task.getName());

    if (task.getPorts() != null) {
      for (Port port : task.getPorts()) {
        result.addPorts(portConverter.apply(port));
      }
    }

    if (task.getRequirements() != null) {
      for (Requirement req : task.getRequirements()) {
        result.addRequirements(requirementConverter.apply(req));
      }
    }

    if (task.getOptimization() != null) {
      result.setOptimization(optimizationConverter.apply(task.getOptimization()));
    }

    for (TaskInterface tInterface : task.getInterfaces()) {
      result.addInterfaces(interfaceConverter.apply(tInterface));
    }

    switch (task.getTaskType()) {
      case BATCH:
        result.setTaskType(TaskEntities.TaskType.BATCH);
        break;
      case SERVICE:
        result.setTaskType(TaskEntities.TaskType.SERVICE);
        break;
      default:
        throw new AssertionError("TaskType unknown: " + task.getTaskType());
    }

    return result.build();
  }
}
