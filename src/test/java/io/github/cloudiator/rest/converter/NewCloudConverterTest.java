package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.CloudConfiguration;
import io.github.cloudiator.rest.model.NewCloud;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewCloudConverterTest {

    private final NewCloudConverter newCloudConverter = new NewCloudConverter();
    private final NewCloud restNewCloud = new NewCloud();
    private final IaasEntities.NewCloud iaasNewCloud;
    //Api
    private final ApiToApiConverter apiConverter = new ApiToApiConverter();
    private final Api restApi = new Api();
    private final IaasEntities.Api iaasApi;
    //CloudConfigurations
    private final CloudConfigurationConverter cloudConfigurationConverter = new CloudConfigurationConverter();
    private final CloudConfiguration restCloudConfig = new CloudConfiguration();
    private final IaasEntities.Configuration iaasConfig;
    // CloudCredentials
    private final CloudCredentialConverter



    public NewCloudConverterTest(){

    }

    @Test
    public void applyBack() throws Exception {
    }

    @Test
    public void apply() throws Exception {
    }

}