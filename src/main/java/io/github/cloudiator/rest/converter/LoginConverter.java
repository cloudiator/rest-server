package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Login;
import org.cloudiator.messages.entities.UserEntities;

public class LoginConverter implements TwoWayConverter<Login, UserEntities.Login>{

  private final TenantToTenantConverter T2TConverter = new TenantToTenantConverter();

  @Override
  public Login applyBack(UserEntities.Login login) {
    Login result = new Login()
        .email(login.getEmail())
        .password(login.getPassword())
        .tenant(T2TConverter.applyBack(login.getTenant()));
    return result;
  }

  @Override
  public UserEntities.Login apply(Login login) {
    UserEntities.Login.Builder result = UserEntities.Login.newBuilder()
        .setEmail(login.getEmail()).setPassword(login.getPassword())
        .setTenant(T2TConverter.apply(login.getTenant()));

    return result.build();
  }
}
