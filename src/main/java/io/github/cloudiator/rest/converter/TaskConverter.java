package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Port;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.Task;
import io.github.cloudiator.rest.model.TaskInterface;
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
    /* not implemented in IaasEntity
    result.setExecutionEnvironment();
    result.setType();
    */

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
    // executionEnvironment and type are missing

    return result.build();
  }
}
