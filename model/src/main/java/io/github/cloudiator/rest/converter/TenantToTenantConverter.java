package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Tenant;
import org.cloudiator.messages.entities.UserEntities;

public class TenantToTenantConverter  implements
    TwoWayConverter<Tenant, UserEntities.Tenant> {


  @Override
  public Tenant applyBack(UserEntities.Tenant protoTenant) {
    Tenant result = new Tenant().tenant(protoTenant.getTenant());
    return result;
  }

  @Override
  public UserEntities.Tenant apply(Tenant restTenant) {

    UserEntities.Tenant.Builder result = UserEntities.Tenant.newBuilder()
        .setTenant(restTenant.getTenant());

    return result.build();
  }
}
