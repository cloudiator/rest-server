package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.JobConverter;
import io.github.cloudiator.rest.model.Job;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import org.cloudiator.messaging.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JobsApiController implements JobsApi {

  private final ObjectMapper objectMapper;

  public JobsApiController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Autowired
  UserService userService;

  @Autowired
  JobService jobService;

  // jobService is missing

  private final JobConverter jobConverter = new JobConverter();


  @Override
  public ResponseEntity<Job> addJob(
      @ApiParam(value = "Job to be created. ", required = true) @Valid @RequestBody Job job, String accept) {
    // do some magic!

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<Job>(HttpStatus.OK);
    }
    return new ResponseEntity<Job>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Job>> findJobs(String accept) {
    // do some magic!

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<List<Job>>(HttpStatus.OK);
    }
    return new ResponseEntity<List<Job>>(HttpStatus.OK);
  }

}
