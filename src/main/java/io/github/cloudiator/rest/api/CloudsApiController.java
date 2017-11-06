package io.github.cloudiator.rest.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.UserServiceImpl;
import io.github.cloudiator.rest.converter.CloudToCloudConverter;
import io.github.cloudiator.rest.converter.NewCloudConverter;
import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.NewCloud;
import io.swagger.annotations.ApiParam;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.cloudiator.messages.Cloud.CloudDeletedResponse;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class CloudsApiController implements CloudsApi {

  private final ObjectMapper objectMapper;

  public CloudsApiController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper; //new ObjectMapper();
  }

  @Autowired
  private UserService userService;

  @Autowired
  private CloudService cloudService;


  @Override
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<Cloud> addCloud(
      @ApiParam(value = "Cloud to add", required = true) @Valid @RequestBody NewCloud cloud,
      @RequestHeader(value = "Accept", required = false) String accept)
      throws Exception {

    //validate input
    System.out.println("------------------ addCloud --------------------");
    System.out.println("input: \n" + cloud);
    //preparation
    NewCloud generated = new NewCloud();
    generated.setCloudType(cloud.getCloudType());
    generated.setEndpoint(cloud.getEndpoint());
    generated.setApi(cloud.getApi());
    generated.setCredential(cloud.getCredential());
    generated.setCloudConfiguration(cloud.getCloudConfiguration());

    final NewCloudConverter newCloudConverter = new NewCloudConverter();
    final CloudToCloudConverter cloudToCloudConverter = new CloudToCloudConverter();
    IaasEntities.NewCloud newCloud = newCloudConverter.apply(generated);
    org.cloudiator.messages.Cloud.CreateCloudRequest.Builder builder = org.cloudiator.messages.Cloud.CreateCloudRequest
        .newBuilder();
    builder.setCloud(newCloud);
    builder.setUserId(userService.getUserId());
    org.cloudiator.messages.Cloud.CloudCreatedResponse response = null;

    Cloud feedback = new Cloud();
    //
    //
    feedback.setId("32chars-long_testID_for_UnitTest");
    feedback.setCloudType(cloud.getCloudType());
    feedback.setEndpoint(cloud.getEndpoint());
    feedback.setApi(cloud.getApi());
    feedback.setCredential(cloud.getCredential());
    feedback.setCloudConfiguration(cloud.getCloudConfiguration());
    //

    //to kafka
    System.out.println("--------- to kafka -------------");

    /*
    try {
      response = cloudService
          .createCloud(builder.build());
      System.out.println("response: \n" + response);
    } catch (ResponseException re) {
      throw new ApiException(re.code(), re.getMessage());
    }

    */
    IaasEntities.Cloud er = cloudToCloudConverter.apply(feedback);
    feedback = cloudToCloudConverter.applyBack(er);

    System.out.println("--------- done ------------");
    System.out.println(feedback + " \n");

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<Cloud>(feedback, HttpStatus.CREATED);
    }

    return new ResponseEntity<Cloud>(feedback, HttpStatus.OK);
  }


  @Override
  public ResponseEntity<Void> deleteCloud(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id,
      String accept) {
    // inputvalidation+preparation
    if (id.length() != 32) {
      throw new ApiException(400, "ID not valid. Length must be 32");
    }
    org.cloudiator.messages.Cloud.DeleteCloudRequest deleteCloudRequest = org.cloudiator.messages.Cloud.DeleteCloudRequest
        .newBuilder().setUserId(userService.getUserId()).setCloudId(id).build();
    org.cloudiator.messages.Cloud.CloudDeletedResponse cloudDeletedResponse = null;
    System.out.println("----------- delete Cloud ----------- \n request: " + deleteCloudRequest);

    // to Kafka

    try {
      cloudDeletedResponse = cloudService.deleteCloud(deleteCloudRequest);
    } catch (ResponseException re) {
      throw new ApiException(re.code(), re.getMessage());
    }
    System.out.println("----------------- response -----------\n" + cloudDeletedResponse);

    System.out.println("------ done ---------");

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    }
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Cloud> findCloud(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id,
      String accept) {

    //ID Validation
    if (id.length() != 32) {
      throw new ApiException(400, "ID not valid. Length must be 32");
    }
    //Preparation
    org.cloudiator.messages.Cloud.UpdateCloudRequest.Builder updateCloudRequest = org.cloudiator.messages.Cloud.UpdateCloudRequest
        .newBuilder().setUserId(userService.getUserId()); // implementation not finished

    //to Kafka

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<Cloud>(HttpStatus.OK);
    }

    return new ResponseEntity<Cloud>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Cloud>> findClouds(String accept) {
    System.out.println("------------------ find Clouds ----------------");
    //prepare
    org.cloudiator.messages.Cloud.CloudQueryRequest cloudQueryRequest = org.cloudiator.messages.Cloud.CloudQueryRequest
        .newBuilder().setUserId(userService.getUserId()).build();
    List<Cloud> cloudList = new ArrayList<Cloud>();
    CloudToCloudConverter cloudToCloudConverter = new CloudToCloudConverter();
    org.cloudiator.messages.Cloud.CloudQueryResponse cloudQueryResponse = null;

    //kafka
    System.out.println("----------- to kafka ------------");
    try {
      cloudQueryResponse = cloudService.getClouds(cloudQueryRequest);
      cloudList = cloudQueryResponse.getCloudsList().stream().map(cloudToCloudConverter::applyBack)
          .collect(Collectors.toList());
    } catch (ResponseException re) {
      throw new ApiException(re.code(), re.getMessage());
    }

    System.out.println(" ------------   done  --------------- \n " + cloudList.size()
        + " Cloud(s) listed.");

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<List<Cloud>>(cloudList, HttpStatus.OK);
    }

    return new ResponseEntity<List<Cloud>>(cloudList, HttpStatus.OK);
  }

}
