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

    switch (tool.getNumber()){
      case 0:
        return Tool.VISOR;
      case 1:
        return Tool.AXE;
      case 2:
        return Tool.LANCE;
      case 3:
        return Tool.KAIROSDB;
      case 4:
        return Tool.DOCKER;
      case 5:
        return Tool.SPARK_WORKER;
      default:
        throw new AssertionError("Unrecognized toolType " + tool);
    }

  }

  @Override
  public InstallationEntities.Tool apply(Tool tool) {
    //from REST to protobuf

    switch (tool){
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
      default:
        throw new AssertionError("Unrecognized toolType " + tool);
    }


  }
}
