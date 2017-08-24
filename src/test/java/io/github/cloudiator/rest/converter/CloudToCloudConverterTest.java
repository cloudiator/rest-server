package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.*;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Before;
import org.junit.Test;

public class CloudToCloudConverterTest {

    private final CloudToCloudConverter cloudConverter = new CloudToCloudConverter();

    IaasEntities.Api testApiMessage;



    @Before
    public void setUp() throws Exception {

        this.testApiMessage = IaasEntities.Api.newBuilder().setProviderName("TestProvider").build();

        Api testApi = new Api();
        CloudCredential testCloudCredential = new CloudCredential();
        Property testProperty = new Property();
        CloudConfiguration testCloudConfiguration = new CloudConfiguration();
        Cloud testCloud = new Cloud();

        testApi.setProviderName("TestProvider");
        testCloudCredential.setUser("TestUser");
        testCloudCredential.setSecret("TestSecret");
        testProperty.setKey("TestProperty.TestKey");
        testProperty.setValue("TestProperty.TestValue");
        testCloudConfiguration.setNodeGroup("TestNodeGroup");
        testCloudConfiguration.addPropertiesItem(testProperty);
        testCloud.setEndpoint("https://TestEndpoint.com");
        //testCloud.setId("1a79a4d60de6718e8e5b326e338ae533");
        testCloud.setId("32_chars_long_id_as_testcloud_id");
        testCloud.setCloudType(CloudType.PRIVATE);
        testCloud.setApi(testApi);
        testCloud.setCloudConfiguration(testCloudConfiguration);
        testCloud.setCredential(testCloudCredential);

        IaasEntities.Credential iaastestCredential = IaasEntities.Credential.newBuilder()
                .setUser("TestUser")
                .setSecret("TestSecret").build();
        IaasEntities.Property iaastestproperty = IaasEntities.Property.newBuilder()
                .setKey("TestProperty.TestKey")
                .setValue("TestProperty.TestValue").build();
        IaasEntities.Configuration iaastestConfiguration = IaasEntities.Configuration.newBuilder()
                .setNodeGroup("TestNodeGroup")
                .addProperty(iaastestproperty).build();
        IaasEntities.Cloud iaastestCloud = IaasEntities.Cloud.newBuilder()
                .setEndpoint("https://TestEndpoint.com")
                .setId("32_chars_long_id_as_testcloud_id")
                .setCloudType(IaasEntities.CloudType.PRIVATE_CLOUD)
                .setApi(testApiMessage)
                .setConfiguration(iaastestConfiguration)
                .setCredential(iaastestCredential).build();
    }

    @Test
    public void applyBack() throws Exception {
    }

    @Test
    public void apply() throws Exception {
    }

}