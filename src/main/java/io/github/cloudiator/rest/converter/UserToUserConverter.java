package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.User;
import org.cloudiator.messages.entities.UserEntities;

public class UserToUserConverter implements
    TwoWayConverter<User, UserEntities.User>{

  private final TenantToTenantConverter T2TConverter = new TenantToTenantConverter();

  @Override
  public User applyBack(UserEntities.User user) {
     User back = new User();
     back.setEmail(user.getEmail());
     back.setTenant(T2TConverter.applyBack(user.getTenant()));
    return null;
  }

  @Override
  public UserEntities.User apply(User user) {
    UserEntities.User.Builder result = UserEntities.User.newBuilder()
        .setEmail(user.getEmail()).setTenant(T2TConverter.apply(user.getTenant()));

    return result.build();
  }
}
