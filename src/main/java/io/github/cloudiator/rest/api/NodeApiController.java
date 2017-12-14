package io.github.cloudiator.rest.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.model.LongRunningRequest;


import io.github.cloudiator.rest.converter.NodeRequirementsConverter;
import io.github.cloudiator.rest.model.NodeRequirements;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.cloudiator.messages.Node.NodeRequestMessage;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.NodeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class NodeApiController implements NodeApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public NodeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Autowired
  private NodeService nodeService;

  @Autowired
  private UserService userService;

  private NodeRequirementsConverter nodeRequirementsConverter = new NodeRequirementsConverter();

  public ResponseEntity<LongRunningRequest> addNode(
      @ApiParam(value = "Node Request", required = true) @Valid @RequestBody NodeRequirements nodeRequirements) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {


        nodeService
            .createNodesAsync(NodeRequestMessage.newBuilder().setUserId(userService.getUserId())
                    .setNodeRequest(nodeRequirementsConverter.apply(nodeRequirements)).build(),
                (content, error) -> {
                  System.out.println("Error " + error);
                  System.out.println("Content " + content);
                });

        // do some magic!

        return new ResponseEntity<LongRunningRequest>(HttpStatus.OK);

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }


}
