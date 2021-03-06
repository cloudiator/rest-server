package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.MonitorConverter;
import io.github.cloudiator.rest.converter.MonitorTargetConverter;
import io.github.cloudiator.rest.model.Monitor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.model.MonitoringTarget;
import io.swagger.annotations.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.cloudiator.messages.Monitor.CreateMonitorRequest;
import org.cloudiator.messages.Monitor.CreateMonitorResponse;
import org.cloudiator.messages.Monitor.DeleteMonitorRequest;
import org.cloudiator.messages.Monitor.DeleteMonitorResponse;
import org.cloudiator.messages.Monitor.MonitorQueryRequest;
import org.cloudiator.messages.Monitor.MonitorQueryResponse;
import org.cloudiator.messages.Monitor.UpdateMonitorRequest;
import org.cloudiator.messages.Monitor.UpdateMonitorResponse;
import org.cloudiator.messages.Monitor.GetMonitorRequest;
import org.cloudiator.messages.Monitor.GetMonitorResponse;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messaging.ResponseException;
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

  private final MonitorTargetConverter targetConverter = MonitorTargetConverter.INSTANCE;

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
      final String userId = UserInfo.of(request).tenant();
      try {
        CreateMonitorRequest request = CreateMonitorRequest.newBuilder().setUserId(userId)
            .setNewmonitor(monitorConverter.apply(monitor)).build();
        CreateMonitorResponse response = monitorService.addMonitor(request);
        Monitor createdMonitor = monitorConverter.applyBack(response.getMonitor());

        return new ResponseEntity<Monitor>(createdMonitor, HttpStatus.OK);
      } catch (IllegalArgumentException ex) {
        log.error("Illegal Argument!", ex);
        throw new ApiException(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
      } catch (ResponseException e) {
        log.error("Error while creating Monitor: ", e);
        throw new ApiException(e.code(), e.getMessage());
      }
    }

    return new ResponseEntity<Monitor>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Void> deleteMonitor(
      @ApiParam(value = "Unique identifier of a monitor", required = true) @PathVariable("metric") String metric,
      @Valid @RequestBody MonitoringTarget target) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      final String userId = UserInfo.of(request).tenant();
      try {

        DeleteMonitorRequest request = DeleteMonitorRequest.newBuilder()
            .setUserId(userId)
            .setMetric(metric)
            .setTarget(targetConverter.apply(target))
            .build();
        DeleteMonitorResponse response = monitorService.deleteMonitor(request);

        if (response == null) {
          throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
              "Error while deleting Monitor");
        }

        return new ResponseEntity(HttpStatus.OK);
      } catch (ResponseException e) {
        log.error("Error while deleting Monitor: ", e);
        throw new ApiException(e.code(), e.getMessage());
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Monitor>> findMonitors() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      List<Monitor> result = new ArrayList<Monitor>();
      try {

        MonitorQueryRequest monitorQueryRequest = MonitorQueryRequest.newBuilder()
            .setUserId(UserInfo.of(request).tenant()).build();

        MonitorQueryResponse monitorQueryResponse = monitorService
            .findMonitors(monitorQueryRequest);

        result = monitorQueryResponse.getMonitorList().stream()
            .map(monitorConverter::applyBack).collect(
                Collectors.toList());
        return new ResponseEntity<List<Monitor>>(result, HttpStatus.OK);
      } catch (ResponseException re) {
        log.error("Error while searching(multi): ", re);
        throw new ApiException(re.code(), re.getMessage());
      }
    }
    return new ResponseEntity<List<Monitor>>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Monitor> getMonitor(
      @ApiParam(value = "Unique identifier of a monitor", required = true) @PathVariable("metric") String metric,
      @Valid @RequestBody MonitoringTarget target) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      final String userId = UserInfo.of(request).tenant();
      try {

        GetMonitorRequest request = GetMonitorRequest.newBuilder()
            .setUserId(userId)
            .setMetric(metric)
            .setTarget(targetConverter.apply(target))
            .build();
        GetMonitorResponse response = monitorService.getMonitor(request);

        if (response.getMonitor() == null) {
          System.out.println("ERROR - Monitor not found");
          throw new ApiException(HttpStatus.BAD_REQUEST.value(),
              "Monitor not found. Metric: " + response.getMonitor().getMetric());
        }

        Monitor result = monitorConverter.applyBack(response.getMonitor());
        return new ResponseEntity<Monitor>(result, HttpStatus.OK);
      } catch (ResponseException re) {
        log.error("Error while searching(single):", re);
        throw new ApiException(re.code(), re.getMessage());
      } catch (Exception e) {
        log.error("Error while searching(single): ", e);
        return new ResponseEntity<Monitor>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<Monitor>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Monitor> updateMonitor(String metric, Monitor monitor) {
    return null;
  }

}
