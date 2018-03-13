package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.UserServiceOld;
import io.github.cloudiator.rest.converter.JobConverter;
import io.github.cloudiator.rest.model.Job;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Job.CreateJobRequest;
import org.cloudiator.messages.Job.JobCreatedResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JobsApiController implements JobsApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;
  private UserInfo userInfo;

  @org.springframework.beans.factory.annotation.Autowired
  public JobsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Autowired
  UserServiceOld userService;

  @Autowired
  JobService jobService;

  // jobService is missing

  private final JobConverter jobConverter = new JobConverter();


  @Override
  public ResponseEntity<Job> addJob(
      @ApiParam(value = "Job to be created. ", required = true) @Valid @RequestBody Job job) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        final CreateJobRequest createJobRequest = CreateJobRequest.newBuilder()
            .setJob(jobConverter.apply(job)).setUserId(userService.getUserId()).build();

        final JobCreatedResponse jobCreatedResponse = jobService.createJob(createJobRequest);
        return new ResponseEntity<>(jobConverter.applyBack(jobCreatedResponse.getJob()),
            HttpStatus.OK);

      } catch (ResponseException e) {
        return new ResponseEntity<>(HttpStatus.valueOf(e.code()));
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }


  public ResponseEntity<List<Job>> findJobs() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<List<Job>>(HttpStatus.NOT_IMPLEMENTED);

      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<List<Job>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }

}
