package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.ProcessConverter;
import io.github.cloudiator.rest.converter.ProcessNewConverter;
import io.github.cloudiator.rest.model.Process;
import io.github.cloudiator.rest.model.ProcessNew;
import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.queue.QueueService;
import io.github.cloudiator.rest.queue.QueueService.QueueItem;
import io.github.cloudiator.util.Base64IdEncoder;
import io.github.cloudiator.util.IdEncoder;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Process.CreateProcessRequest;
import org.cloudiator.messages.Process.DeleteProcessRequest;
import org.cloudiator.messages.Process.ProcessCreatedResponse;
import org.cloudiator.messages.Process.ProcessDeletedResponse;
import org.cloudiator.messages.Process.ProcessQueryRequest;
import org.cloudiator.messages.Process.ProcessQueryRequest.Builder;
import org.cloudiator.messages.Process.ProcessQueryResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProcessApiController implements ProcessApi {

  private static final Logger log = LoggerFactory.getLogger(ProcessApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  private static final ProcessNewConverter PROCESS_NEW_CONVERTER = ProcessNewConverter.INSTANCE;
  private static final ProcessConverter PROCESS_CONVERTER = ProcessConverter.INSTANCE;
  private final IdEncoder idEncoder = Base64IdEncoder.create();

  private final ProcessService processService;

  private final QueueService queueService;

  @org.springframework.beans.factory.annotation.Autowired
  public ProcessApiController(ObjectMapper objectMapper, HttpServletRequest request,
      ProcessService processService, QueueService queueService) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.processService = processService;
    this.queueService = queueService;
  }

  public ResponseEntity<Queue> createProcess(
      @ApiParam(value = "Process to be created ", required = true) @Valid @RequestBody ProcessNew process) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      process.setNodeGroup(idEncoder.decode(process.getNodeGroup()));

      final CreateProcessRequest createProcessRequest = CreateProcessRequest.newBuilder()
          .setUserId(tenant).setProcess(PROCESS_NEW_CONVERTER.apply(process)).build();

      final QueueItem<ProcessCreatedResponse> queueItem = queueService
          .queueCallback(tenant,
              processCreatedResponse -> "process/" + processCreatedResponse.getProcessGroup()
                  .getId());

      processService.createProcessAsync(createProcessRequest, queueItem.getCallback());

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

      return new ResponseEntity<>(queueItem.getQueue(), httpHeaders, HttpStatus.ACCEPTED);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Queue> deleteProcess(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {

    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      if (Strings.isNullOrEmpty(id)) {
        throw new ApiException(400, "id is null or empty");
      }

      final String tenant = UserInfo.of(request).tenant();

      final DeleteProcessRequest deleteProcessRequest = DeleteProcessRequest.newBuilder()
          .setUserId(tenant).setProcessId(id).build();

      final QueueItem<ProcessDeletedResponse> queueItem = queueService.queueCallback(tenant);

      processService.deleteProcessAsync(deleteProcessRequest, queueItem.getCallback());

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

      return new ResponseEntity<>(queueItem.getQueue(), httpHeaders, HttpStatus.ACCEPTED);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Process> findProcess(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      if (Strings.isNullOrEmpty(id)) {
        throw new ApiException(400, "ProcessId is null or empty");
      }

      final ProcessQueryRequest processQueryRequest = ProcessQueryRequest.newBuilder()
          .setProcessId(id)
          .setUserId(tenant).build();

      try {
        final ProcessQueryResponse processQueryResponse = processService
            .queryProcesses(processQueryRequest);

        if (processQueryResponse.getProcessesCount() == 0) {
          throw new ApiException(404, "Process not found");
        }

        if (processQueryResponse.getProcessesCount() > 1) {
          throw new ApiException(500, "Multiple process found for id");
        }




        final Process process = PROCESS_CONVERTER.applyBack(processQueryResponse.getProcesses(0));

        //TODO: needs to be refactored for Single and Cluster Process
        //process.setNodeGroup(idEncoder.encode(process.getNodeGroup()));

        return new ResponseEntity<>(process, HttpStatus.OK);


      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Process>> getProcesses(
      @ApiParam(value = "Id of the schedule. ") @Valid @RequestParam(value = "scheduleId", required = false) String scheduleId) {

    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        final String tenant = UserInfo.of(request).tenant();

        final Builder builder = ProcessQueryRequest.newBuilder().setUserId(tenant);
        if (!Strings.isNullOrEmpty(scheduleId)) {
          builder.setScheduleId(scheduleId);
        }

        final ProcessQueryResponse processQueryResponse = processService
            .queryProcesses(builder.build());

        //TODO: needs to be refactored for Single and Cluster Process
        /*
        return new ResponseEntity<>(
            processQueryResponse.getProcessesList().stream().map(PROCESS_CONVERTER::applyBack).map(
                (Function<Process, Process>) input -> input.nodeGroup(idEncoder.encode(input.getNodeGroup())))
                .collect(
                    Collectors.toList()), HttpStatus.OK);
                    */

        return new ResponseEntity<>(
            processQueryResponse.getProcessesList().stream().map(PROCESS_CONVERTER::applyBack)
                .collect(
                    Collectors.toList()), HttpStatus.OK);


      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
