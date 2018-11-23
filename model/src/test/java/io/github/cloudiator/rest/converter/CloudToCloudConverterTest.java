package io.github.cloudiator.rest.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.Cloud.StateEnum;
import io.github.cloudiator.rest.model.CloudConfiguration;
import io.github.cloudiator.rest.model.CloudCredential;
import io.github.cloudiator.rest.model.CloudType;
import java.util.Collections;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messages.entities.IaasEntities.CloudState;
import org.hamcrest.Matchers;
import org.junit.Test;

public class CloudToCloudConverterTest {

  private final CloudToCloudConverter cloudConverter = new CloudToCloudConverter();
  public final Cloud restCloud;
  public final IaasEntities.Cloud iaasCloud;
  //Api
  private final Api restApi;
  private final IaasEntities.Api iaasApi;
  //CloudConfigurations
  private final CloudConfiguration restCloudConfig;
  private final IaasEntities.Configuration iaasCloudConfig;


  // CloudCredentials
  private final CloudCredential restCloudCredential;
  private final IaasEntities.Credential iaasCloudCredential;

  public CloudToCloudConverterTest() {
    //Api
    this.iaasApi = IaasEntities.Api.newBuilder().setProviderName("TestProvider").build();
    this.restApi = new Api().providerName("TestProvider");
    //CloudConfigurations
    this.iaasCloudConfig = IaasEntities.Configuration.newBuilder()
        .setNodeGroup("TestNodeGroup").putProperties("TestKey", "TestValue").build();
    this.restCloudConfig = new CloudConfiguration().nodeGroup("TestNodeGroup").properties(
        Collections.singletonMap("TestKey", "TestValue"));
    //CloudCredentials
    this.iaasCloudCredential = IaasEntities.Credential.newBuilder()
        .setUser("TestUser").setSecret("TestSecret").build();
    this.restCloudCredential = new CloudCredential().user("TestUser").secret("TestSecret");
    //Cloud
    this.iaasCloud = IaasEntities.Cloud.newBuilder()
        .setApi(iaasApi).setConfiguration(iaasCloudConfig).setCredential(iaasCloudCredential)
        .setEndpoint("www.TestEndpoint.com")
        .setId("32chars-long_testID_for_UnitTest")
        .setCloudType(IaasEntities.CloudType.PRIVATE_CLOUD)
        .setState(CloudState.CLOUD_STATE_OK)
        .setDiagnostic("diagnostic")
        .build();
    this.restCloud = new Cloud().api(restApi).cloudConfiguration(restCloudConfig)
        .credential(restCloudCredential).endpoint("www.TestEndpoint.com")
        .id("32chars-long_testID_for_UnitTest")
        .cloudType(CloudType.PRIVATE).state(StateEnum.OK).diagnostic("diagnostic");
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

    assertThat(actual, Matchers.is(Matchers.equalTo(iaasCloud)));
    // assertEquals(actual, iaasCloud);
  }

}
