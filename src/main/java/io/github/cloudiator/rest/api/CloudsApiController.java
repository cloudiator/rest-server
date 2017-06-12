package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserServiceImpl;
import io.github.cloudiator.rest.converter.NewCloudConverter;
import io.github.cloudiator.rest.converter.UserService;
import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.NewCloud;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;

import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messaging.kafka.Kafka;
import org.cloudiator.messaging.services.CloudService;
import org.cloudiator.messaging.services.CloudServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class CloudsApiController implements CloudsApi {

  private CloudService cloudService = new CloudServiceImpl(Kafka.messageInterface());
  private UserService userService = new UserServiceImpl();

  public ResponseEntity<Cloud> addCloud(
          @ApiParam(value = "Cloud to add", required = true) @Valid @RequestBody NewCloud cloud) throws Exception {

    //Get NewCloud and validate input
    //? validate duplication and/or availability on kafka
    System.out.println(cloud);

    Cloud generated = new Cloud();
    generated.setName(cloud.getName());
    generated.setCloudType(cloud.getCloudType());
    generated.setEndpoint(cloud.getEndpoint());
    generated.setApi(cloud.getApi());
    generated.setCredential(cloud.getCredential());
    generated.setCloudConfiguration(cloud.getCloudConfiguration());
/*
        //convert NewCloud to kafka and send
        NewCloudConverter newCloudConverter = new NewCloudConverter();
        IaasEntities.NewCloud newCloud = newCloudConverter.apply(cloud);
        org.cloudiator.messages.Cloud.CreateCloudRequest.Builder builder = org.cloudiator.messages.Cloud.CreateCloudRequest.newBuilder();
        builder.setCloud(newCloud);
        builder.setUserId(userService.getUserId());
        cloudService.createCloud(builder.build());
*/
    System.out.println("ready");
    return new ResponseEntity<Cloud>(generated, HttpStatus.OK);
  }

  public ResponseEntity<Void> deleteCloud(
          @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    // do some magic!
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<Cloud> findCloud(
          @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) throws NotFoundException {

    if (id.equals("345")) {
      System.out.println(id + " not found ");
      throw new NotFoundException(404, "Could not find cloud " + id);
    }

    return new ResponseEntity<Cloud>(HttpStatus.OK);
  }

  public ResponseEntity<List<Cloud>> findClouds() {
    // do some magic!
    return new ResponseEntity<List<Cloud>>(HttpStatus.OK);
  }

}
