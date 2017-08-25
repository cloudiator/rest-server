package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.*;
import org.cloudiator.messages.entities.IaasEntities;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class CloudToCloudConverterTest {

    private final CloudToCloudConverter cloudConverter = new CloudToCloudConverter();
    private final Cloud restCloud;
    private final IaasEntities.Cloud iaasCloud;
    //Api
    private final Api restApi;
    private final IaasEntities.Api iaasApi;
    //CloudConfigurations
    private final CloudConfiguration restCloudConfig;
    private final IaasEntities.Configuration iaasCloudConfig;
    private final Property restCloudConfigProperty;
    private final IaasEntities.Property iaasCloudConfigProperty;
    // CloudCredentials
    private final CloudCredential restCloudCredential;
    private final IaasEntities.Credential iaasCloudCredential;

    public CloudToCloudConverterTest() {
        //Api
        this.iaasApi = IaasEntities.Api.newBuilder().setProviderName("TestProvider").build();
        this.restApi = new Api().providerName("TestProvider");
        //CloudConfigurations
        this.restCloudConfigProperty = new Property().key("TestKey").value("TestValue");
        this.iaasCloudConfigProperty = IaasEntities.Property.newBuilder()
                .setKey("TestKey").setValue("TestValue").build();
        this.iaasCloudConfig = IaasEntities.Configuration.newBuilder()
                .setNodeGroup("TestNodeGroup").addProperty(iaasCloudConfigProperty).build();
        this.restCloudConfig = new CloudConfiguration().nodeGroup("TestNodeGroup").addPropertiesItem(restCloudConfigProperty);
        //CloudCredentials
        this.iaasCloudCredential = IaasEntities.Credential.newBuilder()
                .setUser("TestUser").setSecret("TestSecret").build();
        this.restCloudCredential = new CloudCredential().user("TestUser").secret("TestSecret");
        //Cloud
        this.iaasCloud = IaasEntities.Cloud.newBuilder()
                .setApi(iaasApi).setConfiguration(iaasCloudConfig).setCredential(iaasCloudCredential)
                .setEndpoint("www.TestEndpoint.com")
                .setId("32chars-long_testID_for_UnitTest")
                .setCloudType(IaasEntities.CloudType.PRIVATE_CLOUD).build();
        this.restCloud = new Cloud().api(restApi).cloudConfiguration(restCloudConfig)
                .credential(restCloudCredential).endpoint("www.TestEndpoint.com")
                .id("32chars-long_testID_for_UnitTest")
                .cloudType(CloudType.PRIVATE);
    }

    @Test
    public void applyBack_correct() throws Exception {
        //form iaas to rest
        Cloud actual = cloudConverter.applyBack(iaasCloud);

        //assertThat(actual, is(equalTo(restCloud)));
        assertEquals(actual, restCloud);
    }

    @Test
    public void apply_correct() throws Exception {
        //from rest to iaas
        IaasEntities.Cloud actual = cloudConverter.apply(restCloud);

        assertThat(actual, is(equalTo(iaasCloud)));
        // assertEquals(actual, iaasCloud);
    }

}