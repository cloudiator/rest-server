package io.github.cloudiator.rest.api;


import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.UserServiceImpl;
import io.github.cloudiator.rest.converter.CloudToCloudConverter;
import io.github.cloudiator.rest.converter.NewCloudConverter;
import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.NewCloud;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.CloudService;
import org.cloudiator.messaging.services.CloudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class CloudsApiController implements CloudsApi {


  @Autowired
  private UserService userService;

  @Autowired
  private CloudService cloudService;

  public ResponseEntity<Cloud> addCloud(
      @ApiParam(value = "Cloud to add", required = true) @Valid @RequestBody NewCloud cloud)
      throws Exception {

    //validate input
    System.out.println("------------------ addCloud --------------------");
    System.out.println("input: \n" + cloud);
    //preparation
    Cloud generated = new Cloud();
    generated.setCloudType(cloud.getCloudType());
    generated.setEndpoint(cloud.getEndpoint());
    generated.setApi(cloud.getApi());
    generated.setCredential(cloud.getCredential());
    generated.setCloudConfiguration(cloud.getCloudConfiguration());

    NewCloudConverter newCloudConverter = new NewCloudConverter();
    CloudToCloudConverter cloudToCloudConverter = new CloudToCloudConverter();
    IaasEntities.NewCloud newCloud = newCloudConverter.apply(cloud);
    org.cloudiator.messages.Cloud.CreateCloudRequest.Builder builder = org.cloudiator.messages.Cloud.CreateCloudRequest
        .newBuilder();
    builder.setCloud(newCloud);
    builder.setUserId(userService.getUserId());

    //to kafka
    System.out.println("--------- to kafka -------------");

    org.cloudiator.messages.Cloud.CloudCreatedResponse response = cloudService
        .createCloud(builder.build());
    System.out.println("response: \n" + response);

    generated = cloudToCloudConverter.applyBack(response.getCloud());

    System.out.println("--------- done ------------");
    return new ResponseEntity<Cloud>(generated, HttpStatus.OK);
  }

  public ResponseEntity<Void> deleteCloud(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id)
      throws Exception {
    // inputvalidation+preparation
    if (id.length() != 32) {
      throw new ResponseException(406, "ID not valid. Length must be 32");
    }
    org.cloudiator.messages.Cloud.DeleteCloudRequest deleteCloudRequest = org.cloudiator.messages.Cloud.DeleteCloudRequest
        .newBuilder().setUserId(userService.getUserId()).setCloudId(id).build();
    System.out
        .println("----------- delete Cloud --------------- \n request: " + deleteCloudRequest);

    // to Kafka

    org.cloudiator.messages.Cloud.CloudDeletedResponse cloudDeletedResponse = cloudService
        .deleteCloud(deleteCloudRequest);
    System.out.println("----------------- response -----------\n" + cloudDeletedResponse);

    System.out.println("------ done ---------");
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<Cloud> findCloud(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id)
      throws Exception {

    if (id.equals("345")) {
      System.out.println(id + " not found ");
      throw new NotFoundException(404, "Could not find cloud " + id);
    }

    return new ResponseEntity<Cloud>(HttpStatus.OK);
  }

  public ResponseEntity<List<Cloud>> findClouds() throws Exception {
    System.out.println("---  QueryRequest  ---");
    //prepare
    org.cloudiator.messages.Cloud.CloudQueryRequest cloudQueryRequest = org.cloudiator.messages.Cloud.CloudQueryRequest
        .newBuilder().setUserId(userService.getUserId()).build();
    System.out.println(cloudQueryRequest);
    List<Cloud> cloudList = new ArrayList<Cloud>();
    System.out.println("-----------------------------------------------------------------");

    //kafka
    System.out.println("------------------------- to kafka -------------------------------------");

    org.cloudiator.messages.Cloud.CloudQueryResponse cloudQueryResponse = cloudService
        .getClouds(cloudQueryRequest);
    System.out.println("out: \n" + cloudQueryResponse);
    System.out.println(
        "---------------------------- waiting for response -----------------------------------------------");
    CloudToCloudConverter cloudToCloudConverter = new CloudToCloudConverter();
    cloudList = cloudQueryResponse.getCloudsList().stream().map(cloudToCloudConverter::applyBack)
        .collect(Collectors.toList());

    System.out.println(cloudList + " \n ------------   done  --------------- \n " + cloudList.size()
        + " items listed.");
    return new ResponseEntity<List<Cloud>>(cloudList, HttpStatus.OK);
  }

}
