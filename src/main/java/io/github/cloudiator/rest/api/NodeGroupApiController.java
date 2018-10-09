package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.NodeConverter;
import io.github.cloudiator.rest.converter.NodeGroupConverter;
import io.github.cloudiator.rest.model.NodeGroup;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.cloudiator.messages.Node.NodeGroupQueryMessage;
import org.cloudiator.messages.Node.NodeGroupQueryResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NodeGroupApiController implements NodeGroupApi {

  private static final Logger log = LoggerFactory.getLogger(NodeGroupApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private NodeService nodeService;

  private final NodeConverter nodeConverter = new NodeConverter();
  private final NodeGroupConverter nodeGroupConverter = new NodeGroupConverter();

  @org.springframework.beans.factory.annotation.Autowired
  public NodeGroupApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Override
  public ResponseEntity<List<NodeGroup>> findNodeGroups() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      try {
        final NodeGroupQueryResponse nodeQueryResponse = nodeService
            .queryNodeGroups(NodeGroupQueryMessage.newBuilder().setUserId(tenant).build());

        return new ResponseEntity<>(
            nodeQueryResponse.getNodeGroupsList().stream().map(nodeGroupConverter::applyBack)
                .collect(
                    Collectors.toList()), HttpStatus.OK);


      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<NodeGroup> getNodeGroup(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      try {
        final NodeGroupQueryResponse nodeGroupQueryResponse = nodeService.queryNodeGroups(
            NodeGroupQueryMessage.newBuilder().setUserId(tenant).setNodeGroupId(id).build());

        if (nodeGroupQueryResponse.getNodeGroupsCount() == 0) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (nodeGroupQueryResponse.getNodeGroupsCount() > 1) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(
            nodeGroupConverter.applyBack(nodeGroupQueryResponse.getNodeGroups(0)), HttpStatus.OK);


      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }


    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
