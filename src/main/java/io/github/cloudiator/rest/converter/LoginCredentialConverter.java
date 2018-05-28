package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.LoginCredential;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by Daniel Seybold on 14.03.2018.
 */
public class LoginCredentialConverter implements
    TwoWayConverter<LoginCredential, IaasEntities.LoginCredential> {

  @Override
  public LoginCredential applyBack(IaasEntities.LoginCredential loginCredential) {

    LoginCredential rest = new LoginCredential();

    if (loginCredential.getUsername() != null && !loginCredential.getUsername().isEmpty()) {
      rest.setUsername(loginCredential.getUsername());
    }
    if (loginCredential.getPassword() != null && !loginCredential.getPassword().isEmpty()) {
      rest.setPassword(loginCredential.getPassword());
    }
    if (loginCredential.getPrivateKey() != null && !loginCredential.getPrivateKey().isEmpty()) {
      rest.setPrivateKey(loginCredential.getPrivateKey());
    }

    return rest;
  }

  @Override
  public IaasEntities.LoginCredential apply(LoginCredential loginCredential) {
    //from REST to protobuf

    IaasEntities.LoginCredential.Builder builder = IaasEntities.LoginCredential.newBuilder();

    builder
        .setUsername(loginCredential.getUsername());

    if (loginCredential.getPassword() == null || loginCredential.getPassword().isEmpty()
        && loginCredential.getPrivateKey() != null && !loginCredential.getPrivateKey().isEmpty()) {
      builder.setPrivateKey(loginCredential.getPrivateKey());
    } else if (loginCredential.getPassword() != null || !loginCredential.getPassword().isEmpty()
        && loginCredential.getPrivateKey() == null && loginCredential.getPrivateKey().isEmpty()) {
      builder.setPassword(loginCredential.getPassword());
    } else {
      throw new IllegalStateException(
          "PrivateKey and Password are set! Only one credential should be set!");
    }

    return builder.build();
  }
}
