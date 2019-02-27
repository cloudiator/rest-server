package io.github.cloudiator.rest.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.NodeCandidateConverter;
import io.github.cloudiator.rest.converter.NodeConverter;
import io.github.cloudiator.rest.converter.NodeRequirementsConverter;
import io.github.cloudiator.rest.model.Node;
import io.github.cloudiator.rest.model.NodeRequest;
import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.queue.QueueService;
import io.github.cloudiator.rest.queue.QueueService.QueueItem;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Node.NodeDeleteMessage;
import org.cloudiator.messages.Node.NodeDeleteResponseMessage;
import org.cloudiator.messages.Node.NodeQueryMessage;
import org.cloudiator.messages.Node.NodeQueryResponse;
import org.cloudiator.messages.Node.NodeRequestMessage;
import org.cloudiator.messages.Node.NodeRequestMessage.Builder;
import org.cloudiator.messages.Node.NodeRequestResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
  private QueueService queueService;

  private NodeRequirementsConverter nodeRequirementsConverter = new NodeRequirementsConverter();
  private NodeCandidateConverter nodeCandidateConverter = new NodeCandidateConverter();
  private NodeConverter nodeConverter = new NodeConverter();

  @Override
  public ResponseEntity<Queue> addNode(
      @ApiParam(value = "Node Request", required = true) @Valid @RequestBody NodeRequest nodeRequest) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();
      final QueueItem<NodeRequestResponse> queueItem = queueService
          .queueCallback(tenant,
              nodeRequestResponse -> "node/" + nodeRequestResponse.getNode().getId());

      Builder builder = NodeRequestMessage.newBuilder()
          .setUserId(tenant);

      if (nodeRequest.getNodeCandidate() == null) {
        throw new ApiException(400, "Node candidate is mandatory.");
      }
      builder.setNodeCandidate(nodeCandidateConverter.apply(nodeRequest.getNodeCandidate()));

      if (nodeRequest.getGroupName() != null) {
        builder.setGroupName(nodeRequest.getGroupName());
      }

      final NodeRequestMessage nodeRequestMessage = builder.build();

      nodeService.createNodesAsync(nodeRequestMessage, queueItem.getCallback());

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

      return new ResponseEntity<Queue>(queueItem.getQueue(), httpHeaders, HttpStatus.OK);


    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Queue> deleteNode(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") final String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      if (Strings.isNullOrEmpty(id)) {
        throw new ApiException(400, "Id is null or empty");
      }

      final String tenant = UserInfo.of(request).tenant();
      final QueueItem<NodeDeleteResponseMessage> queueItem = queueService
          .queueCallback(tenant);

      NodeDeleteMessage nodeDeleteMessage = NodeDeleteMessage.newBuilder().setNodeId(id)
          .setUserId(tenant).build();

      nodeService.deleteNodeAsync(nodeDeleteMessage, queueItem.getCallback());

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

      return new ResponseEntity<Queue>(queueItem.getQueue(), httpHeaders, HttpStatus.OK);


    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<List<Node>> findNodes() {
    String accept = request.getHeader("Accept");

    final String tenant = UserInfo.of(request).tenant();

    if (accept != null && accept.contains("application/json")) {
      try {
        final NodeQueryResponse nodeQueryResponse = nodeService
            .queryNodes(NodeQueryMessage.newBuilder().setUserId(tenant).build());

        final List<Node> nodes = nodeQueryResponse.getNodesList().stream()
            .map(nodeConverter::applyBack).collect(
                Collectors.toList());

        return new ResponseEntity<>(nodes, HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<Node> getNode(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") final String id) {

    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      try {
        final NodeQueryResponse nodeQueryResponse = nodeService
            .queryNodes(
                NodeQueryMessage.newBuilder().setUserId(tenant).setNodeId(id).build());

        if (nodeQueryResponse.getNodesCount() == 0) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (nodeQueryResponse.getNodesCount() > 1) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(nodeConverter.applyBack(nodeQueryResponse.getNodes(0)),
            HttpStatus.OK);

      } catch (ResponseException e) {
        throw new ApiException(e.code(), e.getMessage());
      }

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }


}
