package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.CloudType;
import org.cloudiator.messages.entities.IaasEntities;

public class CloudTypeConverter implements TwoWayConverter<CloudType, IaasEntities.CloudType> {

  @Override
  public CloudType applyBack(IaasEntities.CloudType cloudType) {
    switch (cloudType) {
      case PUBLIC_CLOUD:
        return CloudType.PUBLIC;
      case PRIVATE_CLOUD:
        return CloudType.PRIVATE;
      case SIMULATION_CLOUD:
        return CloudType.SIMULATION;
      case UNRECOGNIZED:
      default:
        throw new AssertionError("Unrecognized cloudType " + cloudType);
    }
  }

  @Override
  public IaasEntities.CloudType apply(CloudType cloudTypeEnum) {
    switch (cloudTypeEnum) {
      case PRIVATE:
        return IaasEntities.CloudType.PRIVATE_CLOUD;
      case PUBLIC:
        return IaasEntities.CloudType.PUBLIC_CLOUD;
      case SIMULATION:
        return IaasEntities.CloudType.SIMULATION_CLOUD;
      default:
        throw new AssertionError("Unrecognized cloudType " + cloudTypeEnum);
    }
  }

}
