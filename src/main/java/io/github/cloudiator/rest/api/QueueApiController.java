package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.queue.QueueService;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QueueApiController implements QueueApi {

  private static final Logger log = LoggerFactory.getLogger(QueueApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private QueueService queueService;

  @org.springframework.beans.factory.annotation.Autowired
  public QueueApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Queue> findQueuedTask(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      final Queue queue = queueService.get(tenant, id);
      if (queue == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.LOCATION, queue.getLocation());

      return new ResponseEntity<>(queue, httpHeaders, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<Queue>> getQueuedTasks() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();
      return new ResponseEntity<>(Lists.newArrayList(queueService.getAll(tenant)),
          HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
