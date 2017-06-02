package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.CloudType;
import io.github.cloudiator.rest.model.NewCloud;
import org.cloudiator.messages.entities.IaasEntities;

/**
 * Created by volker on 01.06.17.
 */
public class NewCloudConverter implements TwoWayConverter<NewCloud, IaasEntities.NewCloud>{

    private final ApiToApiConverter apiToApiConverter = new ApiToApiConverter();
    private final NewCloudConverter.CloudTypeConverter cloudTypeConverter = new NewCloudConverter.CloudTypeConverter();
    private final CloudCredentialConverter cloudCredentialConverter = new CloudCredentialConverter();
    private final CloudConfigurationConverter cloudConfigurationConverter = new CloudConfigurationConverter();

    @Override
    public NewCloud applyBack(IaasEntities.NewCloud cloud) {
        NewCloud result = new NewCloud();
        result.setName(cloud.getName());
        result.setEndpoint(cloud.getEndpoint());
        result.setCloudType(cloudTypeConverter.applyBack(cloud.getCloudType()));
        result.setApi(apiToApiConverter.applyBack(cloud.getApi()));
        result.setCloudConfiguration(cloudConfigurationConverter.applyBack(cloud.getConfiguration()));
        result.setCredential(cloudCredentialConverter.applyBack(cloud.getCredential()));
        return result;
    }

    @Override
    public IaasEntities.NewCloud apply(NewCloud cloud) {
        IaasEntities.NewCloud.Builder builder = IaasEntities.NewCloud.newBuilder();
        builder.setName(cloud.getName());
        builder.setEndpoint(cloud.getEndpoint());
        builder.setCloudType(cloudTypeConverter.apply(cloud.getCloudType()));
        builder.setApi(apiToApiConverter.apply(cloud.getApi()));
        builder.setConfiguration(cloudConfigurationConverter.apply(cloud.getCloudConfiguration()));
        builder.setCredential(cloudCredentialConverter.apply(cloud.getCredential()));

        return builder.build();
    }


    private class CloudTypeConverter implements TwoWayConverter<CloudType, IaasEntities.CloudType> {

        @Override
        public CloudType applyBack(IaasEntities.CloudType cloudType) {
            switch (cloudType) {
                case PUBLIC:
                    return CloudType.PUBLIC;
                case PRIVATE:
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
                    return IaasEntities.CloudType.PRIVATE;
                case PUBLIC:
                    return IaasEntities.CloudType.PUBLIC;
                default:
                    throw new AssertionError("Unrecognized cloudType " + cloudTypeEnum);
            }
        }

    }
}
