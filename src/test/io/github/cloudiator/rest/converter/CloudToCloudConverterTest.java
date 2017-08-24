package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.*;
import io.swagger.Swagger2SpringBoot;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.Entity;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Swagger2SpringBoot.class)
@WebMvcTest(CloudToCloudConverter.class)
@DataJpaTest
public class CloudToCloudConverterTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TestEntityManager testEntityManager;

    private final CloudToCloudConverter cloudConverter = new CloudToCloudConverter();

    IaasEntities.Api iaastestApi = IaasEntities.Api.newBuilder().setProviderName("TestProvider").build();

    @Before
    public void setUp() throws Exception {

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

        testEntityManager.persist(testCloud);
        testEntityManager.flush();


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
                .setApi(iaastestApi)
                .setConfiguration(iaastestConfiguration)
                .setCredential(iaastestCredential).build();

        testEntityManager.persist(iaastestCloud);
        testEntityManager.flush();


    }

    @Test
    public void applyBack() throws Exception {
    }

    @Test
    public void apply() throws Exception {
    }

}