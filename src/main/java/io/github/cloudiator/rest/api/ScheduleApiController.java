package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.ScheduleConverter;
import io.github.cloudiator.rest.converter.ScheduleNewConverter;
import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.model.Schedule;
import io.github.cloudiator.rest.model.ScheduleNew;
import io.github.cloudiator.rest.queue.QueueService;
import io.github.cloudiator.rest.queue.QueueService.QueueItem;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Process.CreateScheduleRequest;
import org.cloudiator.messages.Process.ScheduleCreatedResponse;
import org.cloudiator.messaging.services.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

      return new ResponseEntity<>(queueItem.getQueue(), HttpStatus.ACCEPTED);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Schedule>> getSchedules() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<Schedule>>(
            objectMapper.readValue("[ \"\", \"\" ]", List.class), HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<Schedule>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<List<Schedule>>(HttpStatus.NOT_IMPLEMENTED);
  }

}
