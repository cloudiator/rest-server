package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.User;
import org.cloudiator.messages.entities.UserEntities;

public class UserConverter implements
    TwoWayConverter<User, UserEntities.User>{

  private final TenantToTenantConverter T2TConverter = new TenantToTenantConverter();

  @Override
  public User applyBack(UserEntities.User kafkaUser) {
     User back = new User();
     back.setEmail(kafkaUser.getEmail());
     back.setTenant(T2TConverter.applyBack(kafkaUser.getTenant()));
    return back;
  }

  @Override
  public UserEntities.User apply(User restUser) {
    UserEntities.User.Builder result = UserEntities.User.newBuilder()
        .setEmail(restUser.getEmail()).setTenant(T2TConverter.apply(restUser.getTenant()));

    return result.build();
  }
}
