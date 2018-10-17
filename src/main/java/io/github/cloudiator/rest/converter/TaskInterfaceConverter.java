package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.*;
import org.cloudiator.messages.entities.TaskEntities;

public class TaskInterfaceConverter implements
    TwoWayConverter<TaskInterface, TaskEntities.TaskInterface> {

  private final DockerInterfaceConverter dockerInterfaceConverter = new DockerInterfaceConverter();
  private final LanceInterfaceConverter lanceInterfaceConverter = new LanceInterfaceConverter();
  private final PlatformInterfaceConverter platformInterfaceConverter = new PlatformInterfaceConverter();
  private final FaasInterfaceConverter faasInterfaceConverter = new FaasInterfaceConverter();
  private final SparkInterfaceConverter sparkInterfaceConverter = new SparkInterfaceConverter();

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
      case FAASINTERFACE:
        result = faasInterfaceConverter.applyBack(taskInterface.getFaasInterface());
        result.setType(taskInterface.getFaasInterface().getClass().getSimpleName());
        break;
      case SPARKINTERFACE:
        result = sparkInterfaceConverter.applyBack(taskInterface.getSparkInterface());
        result.setType(taskInterface.getSparkInterface().getClass().getSimpleName());
        break;
      case TASKINTERFACE_NOT_SET:
      default:
        throw new AssertionError(
            "TaskInterface not set or unknown: " + taskInterface.getTaskInterfaceCase());
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
    } else if (taskInterface instanceof SparkInterface) {
      result.setSparkInterface(sparkInterfaceConverter.apply((SparkInterface) taskInterface));
    } else if (taskInterface instanceof PlatformInterface) {
      throw new AssertionError("Not implemented!");
    } else if (taskInterface instanceof FaasInterface) {
      result.setFaasInterface(faasInterfaceConverter.apply((FaasInterface) taskInterface));
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
