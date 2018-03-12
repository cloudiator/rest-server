package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.InstallationRequest;
import org.cloudiator.messages.Installation;

/**
 * Created by Daniel Seybold on 12.09.2017.
 *
 * converts REST model into protobuf message and vice versa
 */
public class InstallationRequestConverter implements
    TwoWayConverter<InstallationRequest, Installation.InstallationRequest> {


  @Override
  public InstallationRequest applyBack(Installation.InstallationRequest installationRequest) {
    //from protobuf to REST
    throw new UnsupportedOperationException();
  }

  @Override
  public Installation.InstallationRequest apply(InstallationRequest installationRequest) {
    //from REST to protobuf

    //TODO: should return an Installation message rather then an InstallationRequest?



    //TODO: set user ID

    //TODO: create Installation

    //TODO: Installation: create list of tools, create Node
    //NodeEntities.Node.newBuilder().
     //   Installation.newBuilder().setNode(null);
    //InstallationRequest.newBuilder().setInstallation()


    return null;
  }
}
