package io.github.cloudiator.rest.converter;

import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Cloud;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 29.05.17.
 */
public class CloudToCloudConverter implements TwoWayConverter<Cloud, IaasEntities.Cloud> {

  private final ApiToApiConverter apiToApiConverter = new ApiToApiConverter();
  private final CloudTypeConverter cloudTypeConverter = new CloudTypeConverter();
  private final CloudCredentialConverter cloudCredentialConverter = new CloudCredentialConverter();
  private final CloudConfigurationConverter cloudConfigurationConverter = new CloudConfigurationConverter();
  private final CloudStateConverter cloudStateConverter = new CloudStateConverter();

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
    if (!Strings.isNullOrEmpty(cloud.getDiagnostic())) {
      result.setDiagnostic(cloud.getDiagnostic());
    }
    result.setState(cloudStateConverter.apply(cloud.getState()));

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
    builder.setState(cloudStateConverter.applyBack(cloud.getState()));

    if (cloud.getDiagnostic() != null) {
      builder.setDiagnostic(cloud.getDiagnostic());
    }

    return builder.build();
  }
}
