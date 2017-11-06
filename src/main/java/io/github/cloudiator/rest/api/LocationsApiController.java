package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.LocationConverter;
import io.github.cloudiator.rest.model.Location;

import io.swagger.annotations.*;

import java.util.ArrayList;
import org.cloudiator.messages.Location.LocationQueryRequest;
import org.cloudiator.messages.Location.LocationQueryResponse;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class LocationsApiController implements LocationsApi {

  private final ObjectMapper objectMapper;

  public LocationsApiController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Autowired
  private UserService userService;

  @Autowired
  private LocationService locationService;


  @Override
  public ResponseEntity<List<Location>> findLocations(String accept) {
    //Preparation
    System.out.println("--------------- find Location --------------------");
    LocationConverter locationConverter = new LocationConverter();
    List<Location> locationList = new ArrayList<>();
    LocationQueryRequest request = LocationQueryRequest.newBuilder()
        .setUserId(userService.getUserId()).build();
    LocationQueryResponse response = null;
    //Communication Kafka
    try {
      response = locationService.getLocations(request);
    } catch (ResponseException re) {
      System.err.println("ResponseException: "+re.code()+", "+re.getMessage());
      throw new ApiException(re.code(), re.getMessage());

    }

    for (IaasEntities.Location location : response.getLocationsList()) {
      locationList.add(locationConverter.applyBack(location));
    }
    System.out.println("----------- found " + locationList.size() + " location(s) ------------");

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
    }
    return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
  }

}
