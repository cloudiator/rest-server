package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.StreamUtil;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.JobConverter;
import io.github.cloudiator.rest.converter.JobNewConverter;
import io.github.cloudiator.rest.model.Job;
import io.github.cloudiator.rest.model.JobNew;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Job.CreateJobRequest;
import org.cloudiator.messages.Job.JobCreatedResponse;
import org.cloudiator.messages.Job.JobGraphRequest;
import org.cloudiator.messages.Job.JobGraphResponse;
import org.cloudiator.messages.Job.JobQueryRequest;
import org.cloudiator.messages.Job.JobQueryResponse;
import org.cloudiator.messages.entities.JobEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JobsApiController implements JobsApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public JobsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Autowired
  JobService jobService;

  // jobService is missing

  private final JobConverter jobConverter = new JobConverter();
  private final JobNewConverter jobNewConverter = new JobNewConverter();


  @Override
  public ResponseEntity<Job> addJob(
      @ApiParam(value = "Job to be created. ", required = true) @Valid @RequestBody JobNew jobNew) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {

        final CreateJobRequest createJobRequest = CreateJobRequest.newBuilder()
            .setJob(jobNewConverter.apply(jobNew)).setUserId(UserInfo.of(request).tenant()).build();

        final JobCreatedResponse jobCreatedResponse = jobService.createJob(createJobRequest);
        return new ResponseEntity<>(jobConverter.applyBack(jobCreatedResponse.getJob()),
            HttpStatus.OK);

      } catch (ResponseException e) {
        return new ResponseEntity<>(HttpStatus.valueOf(e.code()));
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Job> findJob(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      if (Strings.isNullOrEmpty(id)) {
        throw new ApiException(400, "id not provided.");
      }

      final String tenant = UserInfo.of(request).tenant();

      try {
        JobQueryResponse jobQueryResponse = jobService
            .getJobs(JobQueryRequest.newBuilder().setUserId(tenant).setJobId(id).build());

        final Optional<JobEntities.Job> optionalJob = jobQueryResponse.getJobsList().stream()
            .collect(StreamUtil.getOnly());

        if (!optionalJob.isPresent()) {
          throw new ApiException(404, String.format("Job with id %s could not be found.", id));
        }

        return new ResponseEntity<>(
            jobConverter.applyBack(optionalJob.get()), HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }


  public ResponseEntity<List<Job>> findJobs() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      try {
        JobQueryResponse jobQueryResponse = jobService
            .getJobs(JobQueryRequest.newBuilder().setUserId(tenant).build());

        return new ResponseEntity<>(
            jobQueryResponse.getJobsList().stream().map(jobConverter::applyBack)
                .collect(Collectors.toList()), HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }

  @Override
  public ResponseEntity<Object> jobGraph(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");

    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      if (Strings.isNullOrEmpty(id)) {
        throw new ApiException(400, "id is null or empty");
      }

      try {

        JobGraphResponse jobGraphResponse = jobService
            .graph(JobGraphRequest.newBuilder().setUserId(tenant).setJobId(id).build());

        return new ResponseEntity<>(jobGraphResponse.getJson(), HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
