package io.github.cloudiator.rest.api;

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

  @Autowired
  private UserService userService;

  @Autowired
  private LocationService locationService;


  public ResponseEntity<List<Location>> findLocations() throws ResponseException {
    //Preparation
    System.out.println("--------------- find Location --------------------");
    LocationConverter locationConverter = new LocationConverter();
    List<Location> locationList = new ArrayList<>();
    LocationQueryRequest request = LocationQueryRequest.newBuilder()
        .setUserId(userService.getUserId()).build();
    //Communication Kafka
    LocationQueryResponse response = locationService.getLocations(request);

    for (IaasEntities.Location location : response.getLocationsList()) {
      locationList.add(locationConverter.applyBack(location));
    }
    System.out.println("----------- found " + locationList.size() + " location(s) ------------");
    return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
  }

}
