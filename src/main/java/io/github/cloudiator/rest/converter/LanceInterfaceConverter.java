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
        .postStart(lanceInterface.getPostStart())
        .stopDetection(lanceInterface.getStopDetection())
        .preStop(lanceInterface.getPreStop())
        .stop(lanceInterface.getStop())
        .postStop(lanceInterface.getPostStop())
        .shutdown(lanceInterface.getShutdown());

    if (!lanceInterface.getStart().isEmpty()) {
      result.setStart(lanceInterface.getStart());
    } else {
      throw new AssertionError("LanceInterface.Start had to be set. ");
    }

    return result;
  }

  @Override
  public TaskEntities.LanceInterface apply(LanceInterface lanceInterface) {
    TaskEntities.LanceInterface.Builder result = TaskEntities.LanceInterface.newBuilder();

    if (lanceInterface.getInit() != null) {
      result.setInit(lanceInterface.getInit());
    } else {
      result.clearInit();
    }

    if (lanceInterface.getInstall() != null) {
      result.setPreInstall(lanceInterface.getPreInstall());
    } else {
      result.clearPreInstall();
    }

    if (lanceInterface.getInstall() != null) {
      result.setInstall(lanceInterface.getInstall());
    } else {
      result.clearInstall();
    }

    if (lanceInterface.getPostInstall() != null) {
      result.setPostInstall(lanceInterface.getPostInstall());
    } else {
      result.clearPostInstall();
    }

    if (lanceInterface.getStartDetection() != null) {
      result.setStartDetection(lanceInterface.getStartDetection());
    } else {
      result.clearStartDetection();
    }
    if (lanceInterface.getPreStart() != null) {
      result.setPreStart(lanceInterface.getPreStart());
    } else {
      result.clearPreStart();
    }

    if ((lanceInterface.getStart() != null) && (!lanceInterface.getStart().isEmpty())) {
      result.setStart(lanceInterface.getStart());
    } else {
      throw new AssertionError("LanceInterface.start had to be set. ");
    }
    if (lanceInterface.getPostStart() != null) {
      result.setPostStart(lanceInterface.getPostStart());
    } else {
      result.clearPostStart();
    }
    if (lanceInterface.getStopDetection() != null) {
      result.setStopDetection(lanceInterface.getStopDetection());
    } else {
      result.clearStopDetection();
    }
    if (lanceInterface.getPreStop() != null) {
      result.setPreStop(lanceInterface.getPreStop());
    } else {
      result.clearPreStop();
    }
    if (lanceInterface.getStop() != null) {
      result.setStop(lanceInterface.getStop());
    } else {
      result.clearStop();
    }
    if (lanceInterface.getPostStop() != null) {
      result.setPostStop(lanceInterface.getPostStop());
    } else {
      result.clearPostStop();
    }
    if (lanceInterface.getShutdown() != null) {
      result.setShutdown(lanceInterface.getShutdown());
    } else {
      result.clearShutdown();
    }

    return result.build();
  }
}
