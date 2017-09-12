package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.CloudToCloudConverter;
import io.github.cloudiator.rest.converter.NewCloudConverter;
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


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

  private final CloudToCloudConverter cloudConverter;
  private final NewCloudConverter newCloudConverter;
  private final Cloud resttestcloud;
  private final IaasEntities.Cloud iaastestcloud;
  private final List<Cloud> allClouds;
  private final NewCloud restNewCloud;

  public CloudsApiControllerTest() {
    this.cloudConverter = new CloudToCloudConverter();
    this.newCloudConverter = new NewCloudConverter();
    this.resttestcloud = new Cloud();
    Api testApi = new Api().providerName("TestProvider");
    Property testproperty = new Property().key("TestKey").value("TestValue");
    resttestcloud.setId("1a79a4d60de6718e8e5b326e338atest");
    resttestcloud.setApi(testApi);
    resttestcloud.setCloudType(CloudType.PUBLIC);
    resttestcloud.endpoint("TestEndpoint");
    resttestcloud.credential(new CloudCredential().user("TestUser").secret("TestSecret"));
    resttestcloud.cloudConfiguration(new CloudConfiguration().nodeGroup("TestNodeGroup")
        .addPropertiesItem(testproperty));

    this.iaastestcloud = cloudConverter.apply(resttestcloud);
    this.allClouds = new ArrayList<>();
    this.restNewCloud = new NewCloud();
    restNewCloud.setApi(testApi);
    restNewCloud.setCloudType(CloudType.PUBLIC);
    restNewCloud.setEndpoint("TestEndpoint");
    restNewCloud.credential(new CloudCredential().user("TestUser").secret("TestSecret"));
    restNewCloud.cloudConfiguration(new CloudConfiguration().nodeGroup("TestNodeGroup")
        .addPropertiesItem(testproperty));

  }

/*
  @Test
  public void addCloud() throws Exception {

    given(mockedUserService.getUserId()).willReturn("DummyUser");

    org.cloudiator.messages.Cloud.CreateCloudRequest createCloudRequest = org.cloudiator.messages.Cloud.CreateCloudRequest
        .newBuilder()
        .setUserId(mockedUserService.getUserId())
        .setCloud(newCloudConverter.apply(restNewCloud)).build();
    org.cloudiator.messages.Cloud.CloudCreatedResponse cloudCreatedResponse = org.cloudiator.messages.Cloud.CloudCreatedResponse
        .newBuilder()
        .setCloud(iaastestcloud)
        .setUserId(mockedUserService.getUserId()).build();

    given(mockedCloudService.createCloud(createCloudRequest)).willReturn(cloudCreatedResponse);

    MvcResult mvcResult = mockmvc.perform(post("/clouds"))//.andExpect(status().isCreated())
        .andReturn();

    System.out.println(mvcResult);


  }
  */

  @Test
  public void deleteCloud() throws Exception {
  }

  @Test
  public void findCloud() throws Exception {
  }

  @Test
  public void findClouds_correct() throws Exception {

    given(mockedUserService.getUserId()).willReturn("DummyUser");

    allClouds.add(resttestcloud);
    org.cloudiator.messages.Cloud.CloudQueryRequest testrequest = org.cloudiator.messages.Cloud.CloudQueryRequest
        .newBuilder()
        .setUserId(mockedUserService.getUserId()).build();

    org.cloudiator.messages.Cloud.CloudQueryResponse testresponse = org.cloudiator.messages.Cloud.CloudQueryResponse
        .newBuilder().addClouds(iaastestcloud).build();

    given(mockedCloudService.getClouds(testrequest)).willReturn(testresponse);

    mockmvc.perform(get("/clouds").contentType(contentType)).andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(contentType))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(equalTo(resttestcloud.getId())))).andReturn();

  }

}
