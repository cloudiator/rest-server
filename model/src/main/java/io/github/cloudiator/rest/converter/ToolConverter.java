package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Tool;
import org.cloudiator.messages.InstallationEntities;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class ToolConverter implements TwoWayConverter<Tool, InstallationEntities.Tool> {

  @Override
  public Tool applyBack(InstallationEntities.Tool tool) {

    switch (tool) {
      case VISOR:
        return Tool.VISOR;
      case AXE:
        return Tool.AXE;
      case LANCE:
        return Tool.LANCE;
      case KAIROSDB:
        return Tool.KAIROSDB;
      case DOCKER:
        return Tool.DOCKER;
      case SPARK_WORKER:
        return Tool.SPARK_WORKER;
      case DLMS_AGENT:
        return Tool.DLMS_AGENT;
      case ALLUXIO_CLIENT:
        return Tool.ALLUXIO_CLIENT;
      case EMS_CLIENT:
        return Tool.EMS_CLIENT;
      case UNRECOGNIZED:
      default:
        throw new AssertionError("Unrecognized toolType " + tool);
    }

  }

  @Override
  public InstallationEntities.Tool apply(Tool tool) {
    //from REST to protobuf

    switch (tool) {
      case AXE:
        return InstallationEntities.Tool.AXE;
      case DOCKER:
        return InstallationEntities.Tool.DOCKER;
      case KAIROSDB:
        return InstallationEntities.Tool.KAIROSDB;
      case LANCE:
        return InstallationEntities.Tool.LANCE;
      case VISOR:
        return InstallationEntities.Tool.VISOR;
      case SPARK_WORKER:
        return InstallationEntities.Tool.SPARK_WORKER;
      case ALLUXIO_CLIENT:
        return InstallationEntities.Tool.ALLUXIO_CLIENT;
      case DLMS_AGENT:
        return InstallationEntities.Tool.DLMS_AGENT;
      case EMS_CLIENT:
        return InstallationEntities.Tool.EMS_CLIENT;
      default:
        throw new AssertionError("Unrecognized toolType " + tool);
    }


  }
}
