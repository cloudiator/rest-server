package io.github.cloudiator.rest.converter;

import static org.hamcrest.MatcherAssert.assertThat;

import io.github.cloudiator.rest.model.Api;
import org.cloudiator.messages.entities.IaasEntities;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ApiToApiConverterTest {

  private final ApiToApiConverter converter = new ApiToApiConverter();
  private final IaasEntities.Api testMessage;
  private final Api testModel;

  public ApiToApiConverterTest() {
    this.testMessage = IaasEntities.Api.newBuilder().setProviderName("providerName").build();
    this.testModel = new Api().providerName("providerName");
  }

  @Test
  public void applyBack_correct() throws Exception {
    //from message to api
    Api actual = converter.applyBack(testMessage);
    assertThat(actual, Matchers.is(Matchers.equalTo(testModel)));
  }

  @Test
  public void apply_correct() throws Exception {
    //from api to message
    IaasEntities.Api actual = converter.apply(testModel);
    assertThat(actual, Matchers.is(Matchers.equalTo(testMessage)));
  }


}
