package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.JobConverter;
import io.github.cloudiator.rest.model.Job;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Job.YAMLRequest;
import org.cloudiator.messages.Job.YAMLResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class YamlApiController implements YamlApi {

  private static final Logger log = LoggerFactory.getLogger(YamlApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  private final JobService jobService;

  private static final JobConverter JOB_CONVERTER = new JobConverter();

  @org.springframework.beans.factory.annotation.Autowired
  public YamlApiController(ObjectMapper objectMapper, HttpServletRequest request,
      JobService jobService) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.jobService = jobService;
  }

  public ResponseEntity<Job> parseYAML(
      @ApiParam(value = "YAML payload", required = true) @Valid @RequestBody String yaml) {
    String accept = request.getHeader("Accept");

    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      final YAMLRequest yamlRequest = YAMLRequest.newBuilder().setUserId(tenant).setYaml(yaml)
          .build();

      try {
        final YAMLResponse response = jobService.yaml(yamlRequest);
        return new ResponseEntity<>(JOB_CONVERTER.applyBack(response.getJob()), HttpStatus.OK);
      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
