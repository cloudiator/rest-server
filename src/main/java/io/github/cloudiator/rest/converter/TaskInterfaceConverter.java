package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.DockerInterface;
import io.github.cloudiator.rest.model.LanceInterface;
import io.github.cloudiator.rest.model.PlatformInterface;
import io.github.cloudiator.rest.model.TaskInterface;
import org.cloudiator.messages.entities.TaskEntities;

public class TaskInterfaceConverter implements
    TwoWayConverter<TaskInterface, TaskEntities.TaskInterface> {

  private final DockerInterfaceConverter dockerInterfaceConverter = new DockerInterfaceConverter();
  private final LanceInterfaceConverter lanceInterfaceConverter = new LanceInterfaceConverter();
  private final PlatformInterfaceConverter platformInterfaceConverter = new PlatformInterfaceConverter();

  @Override
  public TaskInterface applyBack(TaskEntities.TaskInterface taskInterface) {
    TaskInterface result;
    switch (taskInterface.getTaskInterfaceCase()) {
      case LANCEINTERFACE:
        result = lanceInterfaceConverter.applyBack(taskInterface.getLanceInterface());
        result.setType(taskInterface.getLanceInterface().getClass().getSimpleName());
        break;
      case DOCKERINTERFACE:
        result = dockerInterfaceConverter.applyBack(taskInterface.getDockerInterface());
        result.setType(taskInterface.getDockerInterface().getClass().getSimpleName());
        break;
      case TASKINTERFACE_NOT_SET:
      default:
        throw new AssertionError(
            "TaskInterface not set or unkown: " + taskInterface.getTaskInterfaceCase());
    }
    return result;
  }

  @Override
  public TaskEntities.TaskInterface apply(TaskInterface taskInterface) {
    TaskEntities.TaskInterface.Builder result = TaskEntities.TaskInterface.newBuilder();

    if (taskInterface instanceof LanceInterface) {
      result.setLanceInterface(lanceInterfaceConverter.apply((LanceInterface) taskInterface));
    } else if (taskInterface instanceof DockerInterface) {
      result.setDockerInterface(dockerInterfaceConverter.apply((DockerInterface) taskInterface));
    } else if (taskInterface instanceof PlatformInterface) {
      throw new AssertionError("Not implemented!");
    } else {
      throw new AssertionError(
          "InterfaceType not known: " + taskInterface.getClass().getSimpleName());
    }

    /*
    String interfaceType = taskInterface.getClass().getSimpleName();
    switch (interfaceType) {
      case "LanceInterface":
        result.setLanceInterface(lanceInterfaceConverter.apply((LanceInterface) taskInterface));
        break;
      case "DockerInterface":
        result
            .setDockerInterface(dockerInterfaceConverter.apply((DockerInterface) taskInterface));
        break;
      case "PlatformInterface":
        throw new AssertionError("Not implemented!");

      default:
        throw new AssertionError("InterfaceType not known: " + interfaceType);

    }
    */
    return result.build();
  }
}
