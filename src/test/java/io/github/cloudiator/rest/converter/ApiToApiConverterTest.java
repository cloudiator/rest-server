package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Api;
import org.cloudiator.messages.entities.IaasEntities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ApiToApiConverterTest {

    private final ApiToApiConverter converter = new ApiToApiConverter();
    private final IaasEntities.Api testMessage;
    private final Api testModel;

    public ApiToApiConverterTest() {
        this.testMessage = IaasEntities.Api.newBuilder().setProviderName("providerName").build();
        this.testModel = new Api().providerName("providerName");
    }

    @Test
    public void applyBack() throws Exception {
        //from message to api
        Api actual = converter.applyBack(testMessage);
        assertThat(actual, is(equalTo(testModel)));
    }

    @Test
    public void apply() throws Exception {
        //from api to message
        IaasEntities.Api actual = converter.apply(testModel);
        assertThat(actual, is(equalTo(testMessage)));
    }


}