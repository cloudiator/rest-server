package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
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

    if (loginCredential.getUsername() != null) {
      builder.setUsername(loginCredential.getUsername());
    }

    if (loginCredential.getPassword() != null) {
      builder.setPassword(loginCredential.getPassword());
    }

    if (loginCredential.getPrivateKey() != null) {
      builder.setPrivateKey(loginCredential.getPrivateKey());
    }

    return builder.build();
  }
}
