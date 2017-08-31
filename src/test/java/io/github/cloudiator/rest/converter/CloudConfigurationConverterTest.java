package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.CloudConfiguration;
import io.github.cloudiator.rest.model.Property;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CloudConfigurationConverterTest {

    private final CloudConfigurationConverter cloudConfigurationConverter = new CloudConfigurationConverter();
    //Cloudconfig
    private final CloudConfiguration restCloudConfig;
    private final IaasEntities.Configuration iaasConfig;
    //Property
    private final Property restProperty;
    private final IaasEntities.Property iaasProperty;

    public CloudConfigurationConverterTest() {
        this.restProperty = new Property().key("TestKey").value("TestValue");
        this.iaasProperty = IaasEntities.Property.newBuilder().setKey("TestKey").setValue("TestValue").build();
        this.restCloudConfig = new CloudConfiguration()
                .nodeGroup("TestNodeGroup").addPropertiesItem(restProperty);
        this.iaasConfig = IaasEntities.Configuration.newBuilder()
                .setNodeGroup("TestNodeGroup").addProperty(iaasProperty).build();
    }

    @Test
    public void applyBack() throws Exception {
        //from iaas to rest
        CloudConfiguration actual = cloudConfigurationConverter.applyBack(iaasConfig);

        assertThat(actual, is(equalTo(restCloudConfig)));
    }

    @Test
    public void apply() throws Exception {
        //from rest to iaas
        IaasEntities.Configuration actual = cloudConfigurationConverter.apply(restCloudConfig);

        assertThat(actual, is(equalTo(iaasConfig)));
    }

}