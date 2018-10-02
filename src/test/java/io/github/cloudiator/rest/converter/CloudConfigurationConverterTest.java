package io.github.cloudiator.rest.converter;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.CloudConfiguration;
import java.util.Collections;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Test;

public class CloudConfigurationConverterTest {

  private final CloudConfigurationConverter cloudConfigurationConverter = new CloudConfigurationConverter();
  //Cloudconfig
  private final CloudConfiguration restCloudConfig;
  private final IaasEntities.Configuration iaasConfig;

  public CloudConfigurationConverterTest() {
    this.restCloudConfig = new CloudConfiguration()
        .nodeGroup("TestNodeGroup").properties(Collections.singletonMap("TestKey", "TestValue"));
    this.iaasConfig = IaasEntities.Configuration.newBuilder()
        .setNodeGroup("TestNodeGroup").putProperties("TestKey", "TestValue").build();
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
