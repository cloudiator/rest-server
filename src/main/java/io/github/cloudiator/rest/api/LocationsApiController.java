package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.LocationConverter;
import io.github.cloudiator.rest.model.Location;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Location.LocationQueryRequest;
import org.cloudiator.messages.Location.LocationQueryRequest.Builder;
import org.cloudiator.messages.Location.LocationQueryResponse;
import org.cloudiator.messages.entities.IaasEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class LocationsApiController implements LocationsApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;
  private final LocationConverter locationConverter = new LocationConverter();

  @org.springframework.beans.factory.annotation.Autowired
  public LocationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Autowired
  private LocationService locationService;


  @Override
  public ResponseEntity<Location> editLocation(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Location to update ", required = true) @Valid @RequestBody Location location) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Location>> findLocations(
      @ApiParam(value = "(Optional) Unique identifier to filter a specific cloud") @Valid @RequestParam(value = "cloudId", required = false) String cloudId) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      //Preparation
      System.out.println("--------------- find Location --------------------");

      List<Location> locationList = new ArrayList<>();
      final Builder builder = LocationQueryRequest.newBuilder()
          .setUserId(UserInfo.of(request).tenant());

      if (!Strings.isNullOrEmpty(cloudId)) {
        builder.setCloudId(cloudId);
      }

      LocationQueryResponse response = null;
      //Communication Kafka
      try {
        response = locationService.getLocations(builder.build());
      } catch (ResponseException re) {
        System.err.println("ResponseException: " + re.code() + ", " + re.getMessage());
        throw new ApiException(re.code(), re.getMessage());

      }

      for (IaasEntities.Location location : response.getLocationsList()) {
        locationList.add(locationConverter.applyBack(location));
      }
      System.out
          .println("----------- found " + locationList.size() + " location(s) ------------");

      return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Location> getLocation(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {

    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      try {
        final LocationQueryResponse locations = locationService
            .getLocations(
                LocationQueryRequest.newBuilder().setUserId(tenant).setLocationId(id).build());

        if (locations.getLocationsCount() > 1) {
          throw new ApiException(500, "Retrieved multiple locations for id " + id);
        }

        if (locations.getLocationsCount() == 0) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(locationConverter.applyBack(locations.getLocations(0)),
            HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }

    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
