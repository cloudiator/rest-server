package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.CloudCredential;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 31.05.17.
 */
public class CloudCredentialConverter implements
    TwoWayConverter<CloudCredential, IaasEntities.Credential> {


  @Override
  public CloudCredential applyBack(IaasEntities.Credential credential) {
    CloudCredential result = new CloudCredential();
    result.setUser(credential.getUser());
    result.setSecret(credential.getSecret());
    return result;
  }

  @Override
  public IaasEntities.Credential apply(CloudCredential cloudCredential) {
    return IaasEntities.Credential.newBuilder().setUser(cloudCredential.getUser())
        .setSecret(cloudCredential.getSecret()).build();
  }
}
