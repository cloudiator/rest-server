package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.ScheduleConverter;
import io.github.cloudiator.rest.converter.ScheduleNewConverter;
import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.model.Schedule;
import io.github.cloudiator.rest.model.ScheduleNew;
import io.github.cloudiator.rest.queue.QueueService;
import io.github.cloudiator.rest.queue.QueueService.QueueItem;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Process.CreateScheduleRequest;
import org.cloudiator.messages.Process.DeleteScheduleRequest;
import org.cloudiator.messages.Process.ScheduleCreatedResponse;
import org.cloudiator.messages.Process.ScheduleDeleteResponse;
import org.cloudiator.messages.Process.ScheduleGraphRequest;
import org.cloudiator.messages.Process.ScheduleGraphResponse;
import org.cloudiator.messages.Process.ScheduleQueryRequest;
import org.cloudiator.messages.Process.ScheduleQueryResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ScheduleApiController implements ScheduleApi {

  private static final Logger log = LoggerFactory.getLogger(ScheduleApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private QueueService queueService;

  @Autowired
  private ProcessService processService;

  private final ScheduleConverter scheduleConverter = new ScheduleConverter();
  private final ScheduleNewConverter scheduleNewConverter = new ScheduleNewConverter();

  @org.springframework.beans.factory.annotation.Autowired
  public ScheduleApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Override
  public ResponseEntity<Queue> addSchedule(
      @ApiParam(value = "Schedule to be created ", required = true) @Valid @RequestBody ScheduleNew schedule) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      final CreateScheduleRequest createScheduleRequest = CreateScheduleRequest.newBuilder()
          .setUserId(tenant).setSchedule(scheduleNewConverter.apply(schedule)).build();

      final QueueItem<ScheduleCreatedResponse> queueItem = queueService
          .queueCallback(tenant,
              scheduleCreatedResponse -> "schedule/" + scheduleCreatedResponse.getSchedule()
                  .getId());

      //try {

      processService.createScheduleAsync(createScheduleRequest, queueItem.getCallback());

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

      return new ResponseEntity<>(queueItem.getQueue(), httpHeaders, HttpStatus.ACCEPTED);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Queue> deleteSchedule(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {

    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      if (Strings.isNullOrEmpty(id)) {
        throw new ApiException(400, "id is null or empty");
      }

      final String tenant = UserInfo.of(request).tenant();

      DeleteScheduleRequest deleteScheduleRequest = DeleteScheduleRequest.newBuilder()
          .setUserId(tenant).setScheduleId(id).build();

      final QueueItem<ScheduleDeleteResponse> queueItem = queueService.queueCallback(tenant);

      processService.deleteScheduleAsync(deleteScheduleRequest, queueItem.getCallback());

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

      return new ResponseEntity<>(queueItem.getQueue(), httpHeaders, HttpStatus.ACCEPTED);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Schedule> findSchedule(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      if (Strings.isNullOrEmpty(id)) {
        throw new ApiException(400, "id is null or empty.");
      }

      final String tenant = UserInfo.of(request).tenant();

      try {
        final ScheduleQueryResponse scheduleQueryResponse = processService
            .querySchedules(
                ScheduleQueryRequest.newBuilder().setUserId(tenant).setScheduleId(id).build());

        if (scheduleQueryResponse.getSchedulesCount() == 0) {
          throw new ApiException(404, "Could not find schedule with id " + id);
        }

        if (scheduleQueryResponse.getSchedulesCount() >= 1) {
          throw new ApiException(500, "Retrieved more than one schedule.");
        }

        return new ResponseEntity<>(
            scheduleConverter.apply(scheduleQueryResponse.getSchedulesList().get(0)),
            HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<List<Schedule>> getSchedules() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      try {
        final ScheduleQueryResponse scheduleQueryResponse = processService
            .querySchedules(ScheduleQueryRequest.newBuilder().setUserId(tenant).build());

        return new ResponseEntity<>(
            scheduleQueryResponse.getSchedulesList().stream().map(scheduleConverter).collect(
                Collectors.toList()), HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Object> scheduleGraph(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");

    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      if (Strings.isNullOrEmpty(id)) {
        throw new ApiException(400, "id is null or empty");
      }

      try {

        final ScheduleGraphResponse graph = processService
            .graph(ScheduleGraphRequest.newBuilder().setUserId(tenant).setScheduleId(id).build());

        return new ResponseEntity<>(graph.getJson(), HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
