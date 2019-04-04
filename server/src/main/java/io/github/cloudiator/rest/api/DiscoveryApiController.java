package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.cloudiator.messages.Discovery.DiscoverStatusResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.CloudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class DiscoveryApiController implements DiscoveryApi {

  private static final Logger log = LoggerFactory.getLogger(DiscoveryApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;
  private final CloudService cloudService;

  @org.springframework.beans.factory.annotation.Autowired
  public DiscoveryApiController(ObjectMapper objectMapper, HttpServletRequest request,
      CloudService cloudService) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.cloudService = cloudService;
  }

  public ResponseEntity<Map<String, String>> discoveryStatus() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      try {
        final DiscoverStatusResponse discoverStatusResponse = cloudService.discoveryStatus();

        final Map<String, String> collect = discoverStatusResponse.getDiscoveryStatus()
            .getStatusMap().entrySet().stream()
            .collect(Collectors.toMap(Entry::getKey, e -> e.getValue().toString()));

        return new ResponseEntity<>(collect, HttpStatus.OK);


      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }

    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
