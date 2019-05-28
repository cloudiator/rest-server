package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.DockerInterface;
import io.github.cloudiator.rest.model.FaasInterface;
import io.github.cloudiator.rest.model.HdfsInterface;
import io.github.cloudiator.rest.model.LanceInterface;
import io.github.cloudiator.rest.model.PlatformInterface;
import io.github.cloudiator.rest.model.SimulationInterface;
import io.github.cloudiator.rest.model.SparkInterface;
import io.github.cloudiator.rest.model.TaskInterface;
import org.cloudiator.messages.entities.TaskEntities;

public class TaskInterfaceConverter implements
    TwoWayConverter<TaskInterface, TaskEntities.TaskInterface> {

  private static final DockerInterfaceConverter DOCKER_INTERFACE_CONVERTER = new DockerInterfaceConverter();
  private static final LanceInterfaceConverter LANCE_INTERFACE_CONVERTER = new LanceInterfaceConverter();
  private static final FaasInterfaceConverter FAAS_INTERFACE_CONVERTER = new FaasInterfaceConverter();
  private static final SparkInterfaceConverter SPARK_INTERFACE_CONVERTER = new SparkInterfaceConverter();
  private static final HdfsInterfaceConverter HDFS_INTERFACE_CONVERTER = new HdfsInterfaceConverter();
  private static final SimulationInterfaceConverter SIMULATION_INTERFACE_CONVERTER = new SimulationInterfaceConverter();

  @Override
  public TaskInterface applyBack(TaskEntities.TaskInterface taskInterface) {
    TaskInterface result;
    switch (taskInterface.getTaskInterfaceCase()) {
      case LANCEINTERFACE:
        result = LANCE_INTERFACE_CONVERTER.applyBack(taskInterface.getLanceInterface());
        result.setType(taskInterface.getLanceInterface().getClass().getSimpleName());
        break;
      case DOCKERINTERFACE:
        result = DOCKER_INTERFACE_CONVERTER.applyBack(taskInterface.getDockerInterface());
        result.setType(taskInterface.getDockerInterface().getClass().getSimpleName());
        break;
      case FAASINTERFACE:
        result = FAAS_INTERFACE_CONVERTER.applyBack(taskInterface.getFaasInterface());
        result.setType(taskInterface.getFaasInterface().getClass().getSimpleName());
        break;
      case SPARKINTERFACE:
        result = SPARK_INTERFACE_CONVERTER.applyBack(taskInterface.getSparkInterface());
        result.setType(taskInterface.getSparkInterface().getClass().getSimpleName());
        break;
      case HDFSINTERFACE:
        result = HDFS_INTERFACE_CONVERTER.applyBack(taskInterface.getHdfsInterface());
        result.setType(taskInterface.getHdfsInterface().getClass().getSimpleName());
        break;
      case SIMULATIONINTERFACE:
        result = SIMULATION_INTERFACE_CONVERTER.applyBack(taskInterface.getSimulationInterface());
        result.setType(taskInterface.getSimulationInterface().getClass().getSimpleName());
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
      result.setLanceInterface(LANCE_INTERFACE_CONVERTER.apply((LanceInterface) taskInterface));
    } else if (taskInterface instanceof DockerInterface) {
      result.setDockerInterface(DOCKER_INTERFACE_CONVERTER.apply((DockerInterface) taskInterface));
    } else if (taskInterface instanceof SparkInterface) {
      result.setSparkInterface(SPARK_INTERFACE_CONVERTER.apply((SparkInterface) taskInterface));
    } else if (taskInterface instanceof PlatformInterface) {
      throw new AssertionError("Not implemented!");
    } else if (taskInterface instanceof FaasInterface) {
      result.setFaasInterface(FAAS_INTERFACE_CONVERTER.apply((FaasInterface) taskInterface));
    } else if (taskInterface instanceof HdfsInterface) {
      result.setHdfsInterface(HDFS_INTERFACE_CONVERTER.apply((HdfsInterface) taskInterface));
    } else if (taskInterface instanceof SimulationInterface) {
      result.setSimulationInterface(SIMULATION_INTERFACE_CONVERTER.apply((SimulationInterface) taskInterface));
    } else {
      throw new AssertionError(
          "InterfaceType not known: " + taskInterface.getClass().getSimpleName());
    }

    return result.build();
  }
}
