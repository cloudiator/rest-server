package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.converter.MonitorConverter;
import io.github.cloudiator.rest.model.Monitor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.cloudiator.messages.Monitor.MonitorQueryRequest;
import org.cloudiator.messages.Monitor.MonitorQueryResponse;
import org.cloudiator.messaging.services.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Controller
public class MonitorsApiController implements MonitorsApi {

  private static final Logger log = LoggerFactory.getLogger(MonitorsApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  final MonitorConverter monitorConverter;

  @org.springframework.beans.factory.annotation.Autowired
  public MonitorsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.monitorConverter = new MonitorConverter();
  }


  @Autowired
  private MonitorService monitorService;

  public ResponseEntity<Monitor> addMonitor(
      @ApiParam(value = "Monitor to be created ", required = true) @Valid @RequestBody Monitor monitor) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        if (monitor == null) {
          System.out.println("NISCHTS DRIN");
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Monitor>(monitor, HttpStatus.NOT_IMPLEMENTED);


        // return new ResponseEntity<Monitor>(objectMapper.readValue("{  \"metric\" : \"metric\",  \"sinks\" : [ {    \"configuration\" : { },    \"type\" : \"KAIROS_DB\"  }, {    \"configuration\" : { },    \"type\" : \"KAIROS_DB\"  } ],  \"sensor\" : {    \"type\" : \"type\"  },  \"targets\" : [ {    \"identifier\" : \"identifier\",    \"type\" : \"JOB\"  }, {    \"identifier\" : \"identifier\",    \"type\" : \"JOB\"  } ],  \"tags\" : [ {    \"value\" : \"value\",    \"key\" : \"key\"  }, {    \"value\" : \"value\",    \"key\" : \"key\"  } ]}", Monitor.class), HttpStatus.NOT_IMPLEMENTED);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<Monitor>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<Monitor>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Void> deleteMonitor(
      @ApiParam(value = "Unique identifier of a monitor", required = true) @PathVariable("metric") String metric) {
    String accept = request.getHeader("Accept");
    System.out.println("nothing to delete!!");
    return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Monitor>> findMonitors() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        MonitorQueryRequest monitorQueryRequest = MonitorQueryRequest.newBuilder().build();
        List<Monitor> monitorList = new ArrayList<>();
        MonitorQueryResponse monitorQueryResponse = monitorService
            .findMonitors(monitorQueryRequest);
        monitorList = monitorQueryResponse.getMonitorList().stream()
            .map(monitorConverter::applyBack).collect(
                Collectors.toList());
        return new ResponseEntity<List<Monitor>>(monitorList, HttpStatus.OK);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<Monitor>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<List<Monitor>>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Monitor>> getMonitor(
      @ApiParam(value = "Unique identifier of a monitor", required = true) @PathVariable("metric") String metric) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        System.out.println("Jetzt gehts los");
        return new ResponseEntity<List<Monitor>>(objectMapper.readValue(
            "[ {  \"metric\" : \"metric\",  \"sinks\" : [ {    \"configuration\" : { },    \"type\" : \"KAIROS_DB\"  }, {    \"configuration\" : { },    \"type\" : \"KAIROS_DB\"  } ],  \"sensor\" : {    \"type\" : \"type\"  },  \"targets\" : [ {    \"identifier\" : \"identifier\",    \"type\" : \"JOB\"  }, {    \"identifier\" : \"identifier\",    \"type\" : \"JOB\"  } ],  \"tags\" : [ {    \"value\" : \"value\",    \"key\" : \"key\"  }, {    \"value\" : \"value\",    \"key\" : \"key\"  } ]}, {  \"metric\" : \"metric\",  \"sinks\" : [ {    \"configuration\" : { },    \"type\" : \"KAIROS_DB\"  }, {    \"configuration\" : { },    \"type\" : \"KAIROS_DB\"  } ],  \"sensor\" : {    \"type\" : \"type\"  },  \"targets\" : [ {    \"identifier\" : \"identifier\",    \"type\" : \"JOB\"  }, {    \"identifier\" : \"identifier\",    \"type\" : \"JOB\"  } ],  \"tags\" : [ {    \"value\" : \"value\",    \"key\" : \"key\"  }, {    \"value\" : \"value\",    \"key\" : \"key\"  } ]} ]",
            List.class), HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<Monitor>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<List<Monitor>>(HttpStatus.NOT_IMPLEMENTED);
  }

}
