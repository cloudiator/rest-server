package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.LoginCredential;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by Daniel Seybold on 14.03.2018.
 */
public class LoginCredentialConverter implements TwoWayConverter<LoginCredential, IaasEntities.LoginCredential> {

  @Override
  public LoginCredential applyBack(IaasEntities.LoginCredential loginCredential) {
    return null;
  }

  @Override
  public IaasEntities.LoginCredential apply(LoginCredential loginCredential) {
    //from REST to protobuf

    IaasEntities.LoginCredential.Builder builder = IaasEntities.LoginCredential.newBuilder();

    builder
        .setUsername(loginCredential.getUsername())
        .setPassword(loginCredential.getPassword())
        .setPrivateKey(loginCredential.getPrivateKey());
    return builder.build();
  }
}
