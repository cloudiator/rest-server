package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.InstallationRequest;
import org.cloudiator.messages.InstallationEntities;

/**
 * Created by Daniel Seybold on 12.09.2017.
 *
 * converts REST model into protobuf message and vice versa
 */
public class InstallationRequestConverter implements
    TwoWayConverter<InstallationRequest, InstallationEntities.Installation> {

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

    //setting tools
    for (int i = 0; i < installationRequest.getTools().size(); i++) {

      builder.setTool(i, toolConverter.apply(installationRequest.getTools().get(i)));
    }

    return builder.build();
  }


}
