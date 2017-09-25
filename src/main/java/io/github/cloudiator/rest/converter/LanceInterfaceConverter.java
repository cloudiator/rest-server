package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.LanceInterface;
import org.cloudiator.messages.entities.TaskEntities;

public class LanceInterfaceConverter implements
    TwoWayConverter<LanceInterface, TaskEntities.LanceInterface> {

  @Override
  public LanceInterface applyBack(TaskEntities.LanceInterface lanceInterface) {
    LanceInterface result = new LanceInterface()
        .init(lanceInterface.getInit())
        .preInstall(lanceInterface.getPreInstall())
        .install(lanceInterface.getInstall())
        .postInstall(lanceInterface.getPostInstall())
        .startDetection(lanceInterface.getStartDetection())
        .preStart(lanceInterface.getPreStart())
        .start(lanceInterface.getStart())
        .postStart(lanceInterface.getPostStart())
        .stopDetection(lanceInterface.getStopDetection())
        .preStop(lanceInterface.getPreStop())
        .stop(lanceInterface.getStop())
        .postStop(lanceInterface.getPostStop())
        .shutdown(lanceInterface.getShutdown());

    return result;
  }

  @Override
  public TaskEntities.LanceInterface apply(LanceInterface lanceInterface) {
    TaskEntities.LanceInterface.Builder result = TaskEntities.LanceInterface.newBuilder()
        .setInit(lanceInterface.getInit())
        .setPreInstall(lanceInterface.getPreInstall())
        .setInstall(lanceInterface.getInstall())
        .setPostInstall(lanceInterface.getPostInstall())
        .setStartDetection(lanceInterface.getStartDetection())
        .setPreStart(lanceInterface.getPreStart())
        .setStart(lanceInterface.getStart())
        .setPostStart(lanceInterface.getPostStart())
        .setStopDetection(lanceInterface.getStopDetection())
        .setPreStop(lanceInterface.getPreStop())
        .setStop(lanceInterface.getStop())
        .setPostStop(lanceInterface.getPostStop())
        .setShutdown(lanceInterface.getShutdown());

    return result.build();
  }
}
