package io.github.cloudiator.rest.converter;


import io.github.cloudiator.rest.model.ExecutionEnvironment;
import io.github.cloudiator.rest.model.Port;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.Task;
import io.github.cloudiator.rest.model.TaskInterface;
import io.github.cloudiator.rest.model.TaskType;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.TaskEntities;


public class TaskConverter implements TwoWayConverter<Task, TaskEntities.Task> {

  private final PortConverter portConverter = new PortConverter();
  private final RequirementConverter requirementConverter = new RequirementConverter();
  private final TaskInterfaceConverter interfaceConverter = new TaskInterfaceConverter();

  @Override
  public Task applyBack(TaskEntities.Task task) {
    Task result = new Task()
        .name(task.getName());
    if (!task.getPortsList().isEmpty()) {
      for (TaskEntities.Port port : task.getPortsList()) {
        result.addPortsItem(portConverter.applyBack(port));
      }
    }
    if (!task.getRequirementsList().isEmpty()) {
      for (CommonEntities.Requirement req : task.getRequirementsList()) {
        result.addRequirementsItem(requirementConverter.applyBack(req));
      }
    }
    if (!task.getInterfacesList().isEmpty()) {
      for (TaskEntities.TaskInterface tInterface : task.getInterfacesList()) {
        result.addInterfacesItem(interfaceConverter.applyBack(tInterface));
      }
    }

    switch (task.getExecutionEnvironment()) {
      case LANCE:
        result.setExecutionEnvironment(ExecutionEnvironment.LANCE);
        break;
      case SPARK:
        result.setExecutionEnvironment(ExecutionEnvironment.SPARK);
        break;
      case NATIVE:
        result.setExecutionEnvironment(ExecutionEnvironment.NATIVE);
        break;
      case CONTAINER:
        result.setExecutionEnvironment(ExecutionEnvironment.CONTAINER);
        break;
      case UNRECOGNIZED:
      default:
        throw new AssertionError("ExecutionEnvironment unkown: " + task.getExecutionEnvironment());
    }
    switch (task.getTaskType()) {
      case BATCH:
        result.setType(TaskType.BATCH);
        break;
      case SERVICE:
        result.setType(TaskType.SERVICE);
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
    if (!task.getPorts().isEmpty()) {
      for (Port port : task.getPorts()) {
        result.addPorts(portConverter.apply(port));
      }
    } else {
      result.clearPorts();
    }
    if (!task.getRequirements().isEmpty()) {
      for (Requirement req : task.getRequirements()) {
        result.addRequirements(requirementConverter.apply(req));
      }
    } else {
      result.clearRequirements();
    }
    if (!task.getInterfaces().isEmpty()) {
      for (TaskInterface tInterface : task.getInterfaces()) {
        result.addInterfaces(interfaceConverter.apply(tInterface));
      }
    } else {
      result.clearInterfaces();
    }

    switch (task.getType()) {
      case BATCH:
        result.setTaskType(TaskEntities.TaskType.BATCH);
        break;
      case SERVICE:
        result.setTaskType(TaskEntities.TaskType.SERVICE);
        break;
      default:
        throw new AssertionError("TaskType unkown: " + task.getType());
    }
    switch (task.getExecutionEnvironment()) {
      case LANCE:
        result.setExecutionEnvironment(TaskEntities.ExecutionEnvironment.LANCE);
        break;
      case SPARK:
        result.setExecutionEnvironment(TaskEntities.ExecutionEnvironment.SPARK);
        break;
      case NATIVE:
        result.setExecutionEnvironment(TaskEntities.ExecutionEnvironment.NATIVE);
        break;
      case CONTAINER:
        result.setExecutionEnvironment(TaskEntities.ExecutionEnvironment.CONTAINER);
        break;
      default:
        throw new AssertionError("ExecutionEnvironment unkown: " + task.getExecutionEnvironment());
    }

    return result.build();
  }
}
