package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.CloudType;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class CloudToCloudConverter implements TwoWayConverter<Cloud, IaasEntities.Cloud> {

  private final ApiToApiConverter apiToApiConverter = new ApiToApiConverter();
  private final CloudTypeConverter cloudTypeConverter = new CloudTypeConverter();
  private final CloudCredentialConverter cloudCredentialConverter = new CloudCredentialConverter();
  private final CloudConfigurationConverter cloudConfigurationConverter = new CloudConfigurationConverter();

  @Override
  public Cloud applyBack(IaasEntities.Cloud cloud) {
    Cloud result = new Cloud();
    result.setId(cloud.getId());

    if (cloud.getEndpoint().equals("")) {
      result.setEndpoint(null);
    } else {
      result.setEndpoint(cloud.getEndpoint());
    }
    result.setCloudType(cloudTypeConverter.applyBack(cloud.getCloudType()));
    result.setApi(apiToApiConverter.applyBack(cloud.getApi()));
    result.setCloudConfiguration(cloudConfigurationConverter.applyBack(cloud.getConfiguration()));
    result.setCredential(cloudCredentialConverter.applyBack(cloud.getCredential()));
    return result;
  }

  @Override
  public IaasEntities.Cloud apply(Cloud cloud) {
    IaasEntities.Cloud.Builder builder = IaasEntities.Cloud.newBuilder();

    builder.setId(cloud.getId());
    if (cloud.getEndpoint() != null) {
      builder.setEndpoint(cloud.getEndpoint());
    } else {
      builder.clearEndpoint();
    }
    builder.setCloudType(cloudTypeConverter.apply(cloud.getCloudType()));
    builder.setApi(apiToApiConverter.apply(cloud.getApi()));
    if (cloud.getCloudConfiguration() != null) {
      builder.setConfiguration(cloudConfigurationConverter.apply(cloud.getCloudConfiguration()));
    } else {
      builder.clearConfiguration();
    }
    builder.setCredential(cloudCredentialConverter.apply(cloud.getCredential()));

    return builder.build();
  }


  private class CloudTypeConverter implements TwoWayConverter<CloudType, IaasEntities.CloudType> {

    @Override
    public CloudType applyBack(IaasEntities.CloudType cloudType) {
      switch (cloudType) {
        case PUBLIC_CLOUD:
          return CloudType.PUBLIC;
        case PRIVATE_CLOUD:
          return CloudType.PRIVATE;
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
        default:
          throw new AssertionError("Unrecognized cloudType " + cloudTypeEnum);
      }
    }

  }
}
