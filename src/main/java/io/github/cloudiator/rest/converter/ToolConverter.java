package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Tool;
import org.cloudiator.messages.InstallationEntities;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class ToolConverter implements TwoWayConverter<Tool, InstallationEntities.Tool> {

  @Override
  public Tool applyBack(InstallationEntities.Tool tool) {
    //TODO: implement
    return null;
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
      default:
        throw new AssertionError("Unrecognized toolType " + tool);
    }


  }
}
