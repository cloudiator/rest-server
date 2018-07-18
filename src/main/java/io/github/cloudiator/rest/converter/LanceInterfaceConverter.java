package io.github.cloudiator.rest.converter;

import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.LanceInterface;
import io.github.cloudiator.rest.model.LanceInterface.ContainerTypeEnum;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messages.entities.TaskEntities.ContainerType;

public class LanceInterfaceConverter implements
    TwoWayConverter<LanceInterface, TaskEntities.LanceInterface> {

  private static final ContainerTypeConverter CONTAINER_TYPE_CONVERTER = new ContainerTypeConverter();

  private static class ContainerTypeConverter implements
      TwoWayConverter<ContainerTypeEnum, TaskEntities.ContainerType> {

    @Override
    public ContainerTypeEnum applyBack(ContainerType containerType) {
      switch (containerType) {

        case BOTH:
          return ContainerTypeEnum.BOTH;
        case DOCKER:
          return ContainerTypeEnum.DOCKER;
        case NATIVE:
          return ContainerTypeEnum.NATIVE;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unrecognized container type " + containerType);
      }
    }

    @Override
    public ContainerType apply(ContainerTypeEnum containerTypeEnum) {
      switch (containerTypeEnum) {
        case NATIVE:
          return ContainerType.NATIVE;
        case DOCKER:
          return ContainerType.DOCKER;
        case BOTH:
          return ContainerType.BOTH;
        default:
          throw new AssertionError("Unrecognized container type " + containerTypeEnum);
      }
    }
  }

  @Override
  public LanceInterface applyBack(TaskEntities.LanceInterface lanceInterface) {
    LanceInterface result = new LanceInterface();

    result.setContainerType(CONTAINER_TYPE_CONVERTER.applyBack(lanceInterface.getContainerType()));

    if (Strings.isNullOrEmpty(lanceInterface.getInit())) {
      result.init(null);
    } else {
      result.init(lanceInterface.getInit());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getPreInstall())) {
      result.preInstall(null);
    } else {
      result.preInstall(lanceInterface.getPreInstall());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getInstall())) {
      result.install(null);
    } else {
      result.install(lanceInterface.getInstall());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getPostInstall())) {
      result.postInstall(null);
    } else {
      result.postInstall(lanceInterface.getPostInstall());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getStartDetection())) {
      result.startDetection(null);
    } else {
      result.startDetection(lanceInterface.getStartDetection());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getPreStart())) {
      result.preStart(null);
    } else {
      result.preStart(lanceInterface.getPreStart());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getPostStart())) {
      result.postStart(null);
    } else {
      result.postStart(lanceInterface.getPostStart());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getStopDetection())) {
      result.stopDetection(null);
    } else {
      result.stopDetection(lanceInterface.getStopDetection());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getPreStop())) {
      result.preStop(null);
    } else {
      result.preStop(lanceInterface.getPreStop());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getStop())) {
      result.stop(null);
    } else {
      result.stop(lanceInterface.getStop());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getPostStop())) {
      result.postStop(null);
    } else {
      result.postStop(lanceInterface.getPostStop());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getShutdown())) {
      result.shutdown(null);
    } else {
      result.shutdown(lanceInterface.getShutdown());
    }

    if (Strings.isNullOrEmpty(lanceInterface.getStart())) {
      result.setStart(null);
    } else {
      result.start(lanceInterface.getStart());
    }

    return result;
  }

  @Override
  public TaskEntities.LanceInterface apply(LanceInterface lanceInterface) {
    TaskEntities.LanceInterface.Builder result = TaskEntities.LanceInterface.newBuilder();

    result.setContainerType(CONTAINER_TYPE_CONVERTER.apply(lanceInterface.getContainerType()));

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
