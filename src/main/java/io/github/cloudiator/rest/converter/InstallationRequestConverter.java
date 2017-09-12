package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.InstallRequest;
import org.cloudiator.messages.Installation.InstallationRequest;
import org.cloudiator.messages.InstallationEntities.Installation;
import org.cloudiator.messages.Node;
import org.cloudiator.messages.NodeEntities;

/**
 * Created by Daniel Seybold on 12.09.2017.
 *
 * converts REST model into protobuf message and vice versa
 */
public class InstallationRequestConverter implements
    TwoWayConverter<InstallRequest, InstallationRequest> {


  @Override
  public InstallRequest applyBack(InstallationRequest installationRequest) {
    //from protobuf to REST
    return null;
  }

  @Override
  public InstallationRequest apply(InstallRequest installRequest) {
    //from REST to protobuf

    //TODO: set user ID

    //TODO: create Installation

    //TODO: Installation: create list of tools, create Node
    NodeEntities.Node.newBuilder().
    Installation.newBuilder().setNode(null);
    //InstallationRequest.newBuilder().setInstallation()


    return null;
  }
}
