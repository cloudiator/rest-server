package io.github.cloudiator.rest.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.UserServiceOld;
import io.github.cloudiator.rest.converter.CloudToCloudConverter;
import io.github.cloudiator.rest.converter.NewCloudConverter;
import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.NewCloud;
import io.swagger.annotations.ApiParam;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.cloudiator.messages.Cloud.CloudDeletedResponse;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messages.entities.User.AuthRequest;
import org.cloudiator.messages.entities.User.AuthResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.CloudService;
import org.cloudiator.messaging.services.CloudServiceImpl;
import org.cloudiator.messaging.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class CloudsApiController implements CloudsApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;
  private UserInfo userInfo;

  final NewCloudConverter newCloudConverter;
  final CloudToCloudConverter cloudToCloudConverter;

  @org.springframework.beans.factory.annotation.Autowired
  public CloudsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.newCloudConverter = new NewCloudConverter();
    this.cloudToCloudConverter = new CloudToCloudConverter();
  }


  @Autowired
  private CloudService cloudService;


  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<Cloud> addCloud(
      @ApiParam(value = "Cloud to add", required = true) @Valid @RequestBody NewCloud newCloud) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        userInfo = new UserInfo(request);
        //validate input
        System.out.println("------------------ addCloud --------------------");
        System.out.println("input: \n" + newCloud);
        //preparation
        NewCloud generated = new NewCloud();
        generated.setCloudType(newCloud.getCloudType());
        generated.setEndpoint(newCloud.getEndpoint());
        generated.setApi(newCloud.getApi());
        generated.setCredential(newCloud.getCredential());
        generated.setCloudConfiguration(newCloud.getCloudConfiguration());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName;
        String currentUserTenant;
        if ((authentication instanceof UsernamePasswordAuthenticationToken)) {
          throw new ApiException(403, "Authentication Error");
        }
        currentUserName = new String(authentication.getName());
        currentUserTenant = new String(authentication.getCredentials().toString());
        System.out.println("\n User " + currentUserName + "\n Tenant: " + currentUserTenant + "\n");

        IaasEntities.NewCloud iaasCloud = newCloudConverter.apply(generated);
        org.cloudiator.messages.Cloud.CreateCloudRequest.Builder builder = org.cloudiator.messages.Cloud.CreateCloudRequest
            .newBuilder();
        builder.setCloud(iaasCloud);
        builder.setUserId(userInfo.currentUserTenant());
        org.cloudiator.messages.Cloud.CloudCreatedResponse response = null;

        Cloud feedback = new Cloud();

        /*________________________ ↓for testing↓ _________________________________
        feedback.setId("32chars-long_testID_for_UnitTest");
        feedback.setCloudType(newCloud.getCloudType());
        feedback.setEndpoint(newCloud.getEndpoint());
        feedback.setApi(newCloud.getApi());
        feedback.setCredential(newCloud.getCredential());
        feedback.setCloudConfiguration(newCloud.getCloudConfiguration());
        *///___________________↑for testing↑ --- ↓to kafka↓ _______________________
        response = cloudService
            .createCloud(builder.build());
        System.out.println("response: \n" + response);

        feedback = cloudToCloudConverter.applyBack(response.getCloud());
        ///__________________________ ↑to kafka↑ __________________________________

        System.out.println("--------- done ------------");
        System.out.println(feedback + " \n");

        return new ResponseEntity<Cloud>(feedback, HttpStatus.CREATED);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      } catch (Exception ex) {
        log.error("Couldn't serialize response for content type application/json", ex);
        return new ResponseEntity<Cloud>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Cloud>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Void> deleteCloud(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        userInfo = new UserInfo(request);
        // inputvalidation+preparation
        if (id.length() != 32) {
          throw new ApiException(400, "ID not valid. Length must be 32");
        }
        org.cloudiator.messages.Cloud.DeleteCloudRequest deleteCloudRequest = org.cloudiator.messages.Cloud.DeleteCloudRequest
            .newBuilder().setUserId(userInfo.currentUserTenant()).setCloudId(id).build();
        org.cloudiator.messages.Cloud.CloudDeletedResponse cloudDeletedResponse = null;

        // to Kafka
        cloudDeletedResponse = cloudService.deleteCloud(deleteCloudRequest);

        System.out.println("----------------- response -----------\n" + cloudDeletedResponse);
        System.out.println("------ done ---------");

        return new ResponseEntity<>(HttpStatus.OK);
      } catch (ResponseException re) {
        throw new ApiException(re.code(), re.getMessage());
      }
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Cloud> findCloud(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      //ID Validation
      if (id.length() != 32) {
        throw new ApiException(400, "ID not valid. Length must be 32");
      }
      //Preparation
      org.cloudiator.messages.Cloud.UpdateCloudRequest.Builder updateCloudRequest = org.cloudiator.messages.Cloud.UpdateCloudRequest
          .newBuilder().setUserId(userInfo.currentUserTenant()); // implementation not finished

      //to Kafka

      return new ResponseEntity<Cloud>(HttpStatus.OK);

    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }


  public ResponseEntity<List<Cloud>> findClouds() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        System.out.println("------------------ find Clouds ----------------");
        userInfo = new UserInfo(request);

        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName;
        String currentUserTenant;
        if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
          throw new ApiException(403, "Authentication Error");
        }
        currentUserName = new String(authentication.getName());
        currentUserTenant = new String(authentication.getCredentials().toString());
        System.out.println("\n CurrentUser " + currentUserName + "\n CurrentTenant: " + currentUserTenant + "\n");
        */
        System.out.println(
            "\n UserInfo.name " + userInfo.currentUserame() + "\n UserInfo.Tenant: " + userInfo
                .currentUserTenant() + "\n");

        //prepare
        org.cloudiator.messages.Cloud.CloudQueryRequest cloudQueryRequest = org.cloudiator.messages.Cloud.CloudQueryRequest
            .newBuilder().setUserId(userInfo.currentUserTenant()).build();
        List<Cloud> cloudList = new ArrayList<Cloud>();
        CloudToCloudConverter cloudToCloudConverter = new CloudToCloudConverter();
        org.cloudiator.messages.Cloud.CloudQueryResponse cloudQueryResponse = null;

        //kafka
        System.out.println("----------- to kafka ------------");

        cloudQueryResponse = cloudService.getClouds(cloudQueryRequest);
        cloudList = cloudQueryResponse.getCloudsList().stream()
            .map(cloudToCloudConverter::applyBack)
            .collect(Collectors.toList());

        System.out.println(" ------------   done  --------------- \n " + cloudList.size()
            + " Cloud(s) listed.");

        return new ResponseEntity<List<Cloud>>(cloudList, HttpStatus.OK);
      } catch (ResponseException re) {
        throw new ApiException(re.code(), re.getMessage());
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
