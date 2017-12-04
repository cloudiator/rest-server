package io.github.cloudiator.rest.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.model.LongRunningRequest;


import io.github.cloudiator.rest.converter.NodeRequirementsConverter;
import io.github.cloudiator.rest.model.NodeRequirements;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

import org.cloudiator.messages.Node.NodeRequestMessage;
import org.cloudiator.messaging.services.NodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class NodeApiController implements NodeApi {

  private final ObjectMapper objectMapper;

  public NodeApiController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Autowired
  private NodeService nodeService;

  @Autowired
  private UserService userService;

  private NodeRequirementsConverter nodeRequirementsConverter = new NodeRequirementsConverter();

  public ResponseEntity<LongRunningRequest> addNode(
      @ApiParam(value = "Node Request", required = true) @Valid @RequestBody NodeRequirements nodeRequirements,
      String accept) {

    nodeService
        .createNodesAsync(NodeRequestMessage.newBuilder().setUserId(userService.getUserId())
                .setNodeRequest(nodeRequirementsConverter.apply(nodeRequirements)).build(),
            (content, error) -> {
              System.out.println("Error " + error);
              System.out.println("Content " + content);
            });

    // do some magic!

    if (accept != null && accept.contains("application/json")) {
      return new ResponseEntity<LongRunningRequest>(HttpStatus.OK);
    }
    return new ResponseEntity<LongRunningRequest>(HttpStatus.OK);
  }


}
