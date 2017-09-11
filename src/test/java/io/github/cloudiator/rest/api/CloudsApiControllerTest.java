package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.CloudToCloudConverter;
import io.github.cloudiator.rest.model.*;
import io.swagger.Swagger2SpringBoot;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messaging.services.CloudService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Swagger2SpringBoot.class)
@WebMvcTest(CloudsApiController.class)
public class CloudsApiControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private CloudService mockedCloudService;

    @MockBean
    private UserService mockedUserService;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    Cloud testcloud = new Cloud();
    IaasEntities.Cloud.Builder iaastestcloudbuilder = IaasEntities.Cloud.newBuilder();
    IaasEntities.Cloud iaastestcloud = IaasEntities.Cloud.getDefaultInstance();

    private CloudToCloudConverter cloudConverter = new CloudToCloudConverter();


    @Before
    public void setup() throws Exception {
        Api testApi = new Api().providerName("TestProvider");
        Property testproperty = new Property().key("TestKey").value("TestValue");
        testcloud.setId("1a79a4d60de6718e8e5b326e338atest");
        testcloud.setApi(testApi);
        testcloud.setCloudType(CloudType.PUBLIC);
        testcloud.endpoint("TestEndpoint");
        testcloud.credential(new CloudCredential().user("TestUser").secret("TestSecret"));
        testcloud.cloudConfiguration(new CloudConfiguration().nodeGroup("TestNodeGroup")
                .addPropertiesItem(testproperty));

        iaastestcloud = iaastestcloudbuilder.mergeFrom(cloudConverter.apply(testcloud)).build();

        given(mockedUserService.getUserId()).willReturn("DummyUser");

    }

    @Test
    public void addCloud() throws Exception {
    }

    @Test
    public void deleteCloud() throws Exception {
    }

    @Test
    public void findCloud() throws Exception {
    }

    @Test
    public void findClouds_correct() throws Exception {
        List<Cloud> allClouds = new ArrayList<>();
        org.cloudiator.messages.Cloud.CloudQueryRequest testrequest = org.cloudiator.messages.Cloud.CloudQueryRequest
                .newBuilder().setUserId(mockedUserService.getUserId()).build();
        org.cloudiator.messages.Cloud.CloudQueryResponse testresponse = org.cloudiator.messages.Cloud.CloudQueryResponse.newBuilder().addClouds(iaastestcloud).build();

        given(mockedCloudService.getClouds(testrequest)).willReturn(testresponse);

        MvcResult result = mockmvc.perform(get("/clouds").contentType(contentType)).andExpect(status().isOk())
                .andReturn();

        System.out.println(result);
    }

}
*/