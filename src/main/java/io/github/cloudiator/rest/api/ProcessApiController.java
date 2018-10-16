package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.ProcessNewConverter;
import io.github.cloudiator.rest.model.Process;
import io.github.cloudiator.rest.model.ProcessNew;
import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.queue.QueueService;
import io.github.cloudiator.rest.queue.QueueService.QueueItem;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Process.CreateProcessRequest;
import org.cloudiator.messages.Process.ProcessCreatedResponse;
import org.cloudiator.messaging.services.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProcessApiController implements ProcessApi {

  private static final Logger log = LoggerFactory.getLogger(ProcessApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  private static final ProcessNewConverter PROCESS_NEW_CONVERTER = ProcessNewConverter.INSTANCE;

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

      final CreateProcessRequest createProcessRequest = CreateProcessRequest.newBuilder()
          .setUserId(tenant).setProcess(PROCESS_NEW_CONVERTER.apply(process)).build();

      final QueueItem<ProcessCreatedResponse> queueItem = queueService
          .queueCallback(tenant,
              processCreatedResponse -> "process/" + processCreatedResponse.getProcess()
                  .getId());

      processService.createProcessAsync(createProcessRequest, queueItem.getCallback());

      return new ResponseEntity<>(queueItem.getQueue(), HttpStatus.ACCEPTED);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Process>> getProcesses() {
    return new ResponseEntity<List<Process>>(HttpStatus.NOT_IMPLEMENTED);
    /** String accept = request.getHeader("Accept");
     if (accept != null && accept.contains("application/json")) {
     try {
     return new ResponseEntity<List<Process>>(objectMapper.readValue("[ \"\", \"\" ]", List.class), HttpStatus.NOT_IMPLEMENTED);
     } catch (IOException e) {
     log.error("Couldn't serialize response for content type application/json", e);
     return new ResponseEntity<List<Process>>(HttpStatus.INTERNAL_SERVER_ERROR);
     }
     }

     return new ResponseEntity<List<Process>>(HttpStatus.NOT_IMPLEMENTED); **/
  }

}
