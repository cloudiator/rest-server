package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.InstallationRequest;
import io.github.cloudiator.rest.model.Tool;
import org.cloudiator.messages.InstallationEntities;
import org.cloudiator.messages.InstallationEntities.Installation;

/**
 * Created by Daniel Seybold on 12.09.2017.
 *
 * converts REST model into protobuf message and vice versa
 */
public class InstallationRequestConverter implements
    TwoWayConverter<InstallationRequest, Installation> {

  private final ToolConverter toolConverter = new ToolConverter();
  private final NodeConverter nodeConverter = new NodeConverter();

  @Override
  public InstallationRequest applyBack(InstallationEntities.Installation nstallation) {
    //from protobuf to REST
    throw new UnsupportedOperationException();
  }

  @Override
  public InstallationEntities.Installation apply(InstallationRequest installationRequest) {
    //from REST to protobuf
    InstallationEntities.Installation.Builder builder = InstallationEntities.Installation
        .newBuilder();

    //setting node
    builder.setNode(nodeConverter.apply(installationRequest.getNode()));

    for(Tool tool : installationRequest.getTools()){
      builder.addTool(toolConverter.apply(tool));
    }

    return builder.build();
  }


}
