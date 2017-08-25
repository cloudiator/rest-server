package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.*;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NewCloudConverterTest {

    private final NewCloudConverter newCloudConverter = new NewCloudConverter();
    private final NewCloud restNewCloud;
    private final IaasEntities.NewCloud iaasNewCloud;
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


    public NewCloudConverterTest() {
        //Api
        this.iaasApi = IaasEntities.Api.newBuilder().setProviderName("TestProvider").build();
        this.restApi = new Api().providerName("TestProvider");
        //CloudConfigurations
        this.restCloudConfigProperty = new Property().key("TestKey").value("TestValue");
        this.iaasCloudConfigProperty = IaasEntities.Property.newBuilder()
                .setKey("TestKey").setValue("TestValue").build();
        this.iaasCloudConfig = IaasEntities.Configuration.newBuilder()
                .addProperty(iaasCloudConfigProperty).setNodeGroup("TestNodeGroup").build();
        this.restCloudConfig = new CloudConfiguration()
                .nodeGroup("TestNodeGroup").addPropertiesItem(restCloudConfigProperty);
        //CloudCredentials
        this.iaasCloudCredential = IaasEntities.Credential.newBuilder()
                .setUser("TestUser").setSecret("TestSecret").build();
        this.restCloudCredential = new CloudCredential().user("TestUser").secret("TestSecret");
        //NewCloud
        this.iaasNewCloud = IaasEntities.NewCloud.newBuilder()
                .setApi(iaasApi).setConfiguration(iaasCloudConfig).setCredential(iaasCloudCredential)
                .setEndpoint("www.TestEndpoint.com")
                .setCloudType(IaasEntities.CloudType.PRIVATE_CLOUD).build();
        this.restNewCloud = new NewCloud().api(restApi).cloudConfiguration(restCloudConfig)
                .credential(restCloudCredential).endpoint("www.TestEndpoint.com")
                .cloudType(CloudType.PRIVATE);
    }

    @Test
    public void applyBack_correct() throws Exception {
        //from iaas to rest
        NewCloud result = newCloudConverter.applyBack(iaasNewCloud);

        assertEquals(result, restNewCloud);
        //assertThat(result, is(equalTo(restNewCloud)));
    }

    @Test
    public void apply_correct() throws Exception {
        // from rest to iaas
        IaasEntities.NewCloud result = newCloudConverter.apply(restNewCloud);

        assertThat(result, is(equalTo(iaasNewCloud)));
    }

}