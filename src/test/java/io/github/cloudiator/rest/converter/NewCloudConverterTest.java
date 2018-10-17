package io.github.cloudiator.rest.converter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.CloudConfiguration;
import io.github.cloudiator.rest.model.CloudCredential;
import io.github.cloudiator.rest.model.CloudType;
import io.github.cloudiator.rest.model.NewCloud;
import java.util.Collections;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Test;

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
  // CloudCredentials
  private final CloudCredential restCloudCredential;
  private final IaasEntities.Credential iaasCloudCredential;


  public NewCloudConverterTest() {
    //Api
    this.iaasApi = IaasEntities.Api.newBuilder().setProviderName("TestProvider").build();
    this.restApi = new Api().providerName("TestProvider");
    //CloudConfigurations

    this.restCloudConfig = new CloudConfiguration()
        .nodeGroup("TestNodeGroup").properties(Collections.singletonMap("TestKey", "TestValue"));
    this.iaasCloudConfig = IaasEntities.Configuration.newBuilder()
        .setNodeGroup("TestNodeGroup").putProperties("TestKey", "TestValue").build();
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
