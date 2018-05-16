package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.UserNew;
import org.cloudiator.messages.entities.UserEntities;

public class UserNewConverter implements TwoWayConverter<UserNew, UserEntities.UserNew> {

  private final TenantToTenantConverter T2TConverter = new TenantToTenantConverter();

  @Override
  public UserNew applyBack(UserEntities.UserNew protoUserNew) {
    UserNew result = new UserNew()
        .email(protoUserNew.getEmail())
        .password(protoUserNew.getPassword())
        .passwordRepeat(protoUserNew.getPasswordRepeat())
        .tenant(T2TConverter.applyBack(protoUserNew.getTenant()));
    return result;
  }

  @Override
  public UserEntities.UserNew apply(UserNew restUserNew) {
    UserEntities.UserNew.Builder result = UserEntities.UserNew.newBuilder()
        .setEmail(restUserNew.getEmail())
        .setPassword(restUserNew.getPassword())
        .setPasswordRepeat(restUserNew.getPasswordRepeat())
        .setTenant(T2TConverter.apply(restUserNew.getTenant()));
    return result.build();
  }
}
