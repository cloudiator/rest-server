package io.github.cloudiator.rest.api;

import com.google.common.base.Strings;
import io.github.cloudiator.rest.converter.ByonConverter;
import io.github.cloudiator.rest.converter.NewNodeConverter;
import io.github.cloudiator.rest.model.ByonNode;
import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.NewNode;
import io.github.cloudiator.rest.model.Queue;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.queue.QueueService;
import io.github.cloudiator.rest.queue.QueueService.QueueItem;
import io.swagger.annotations.*;
import java.util.UUID;
import java.util.stream.Collectors;
import org.cloudiator.messages.Byon;
import org.cloudiator.messages.Byon.AddByonNodeRequest;
import org.cloudiator.messages.Byon.ByonNodeAddedResponse;
import org.cloudiator.messages.Byon.ByonNodeQueryRequest;
import org.cloudiator.messages.Byon.ByonNodeQueryResponse;
import org.cloudiator.messages.Byon.ByonNodeRemovedResponse;
import org.cloudiator.messages.Byon.RemoveByonNodeRequest;
import org.cloudiator.messages.entities.ByonEntities.QueryFilter;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.ByonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class ByonApiController implements ByonApi {

  //todo: Implement UserlessQueueService to get rid of this workaround
  private static final String tmpUserId = UUID.randomUUID().toString();
  private static final Logger log = LoggerFactory.getLogger(ByonApiController.class);
  private static final NewNodeConverter NEW_NODE_CONVERTER = new NewNodeConverter();
  private static final ByonConverter BYON_CONVERTER = new ByonConverter();
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public ByonApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Autowired
  private ByonService byonService;

  @Autowired
  private QueueService queueService;

  public ResponseEntity<Queue> addByon(@ApiParam(value = "Node to be registered" ,required=true )  @Valid @RequestBody NewNode newNode) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final QueueItem<ByonNodeAddedResponse> queueItem = queueService
          .queueCallback(tmpUserId,
              byonNodeAddedResponse -> "byon/" + byonNodeAddedResponse.getByonNode().getId());

      Byon.ByonNode byonNode = NEW_NODE_CONVERTER.apply(newNode);
      Byon.ByonData data = byonNode.getNodeData();
      AddByonNodeRequest byonRequest = AddByonNodeRequest.newBuilder()
          .setByonRequest(data).build();

      byonService.addByonNodeAsync(byonRequest, queueItem.getCallback());

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

      return new ResponseEntity<Queue>(queueItem.getQueue(), httpHeaders, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<Queue> deleteByon(@ApiParam(value = "Unique identifier of the resource",required=true) @PathVariable("id") String id) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      if (Strings.isNullOrEmpty(id)) {
        throw new ApiException(404, "Id is null or empty");
      }

      //If node is allocated -> cannot be removed
      if(checkAllocated(id)) {
         throw new ApiException(404, String.format("Byon with id: %s cannot be +"
             + "deleted as it is allocated at the moment.", id));
      }

      final QueueItem<ByonNodeRemovedResponse> queueItem = queueService
          .queueCallback(tmpUserId);

      RemoveByonNodeRequest byonRequest = RemoveByonNodeRequest.newBuilder().setId(id)
          .build();

      byonService.removeByonNodeAsync(byonRequest, queueItem.getCallback());

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

      return new ResponseEntity<Queue>(queueItem.getQueue(), httpHeaders, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<ByonNode>> findByons() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      ByonNodeQueryRequest queryRequest = ByonNodeQueryRequest.newBuilder()
          .setFilter(QueryFilter.ALL).build();
      List<ByonNode> byonNodes = queryByonNodes(queryRequest);
      return new ResponseEntity<>(byonNodes, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  private boolean checkAllocated(String id) {
    ByonNodeQueryRequest queryRequest = ByonNodeQueryRequest.newBuilder()
        .setFilter(QueryFilter.ALLOCATED).build();
    List<ByonNode> byonNodes = queryByonNodes(queryRequest);
    List<ByonNode> byonNodesFiltered = byonNodes.stream().filter(
        node -> node.getId() == id)
        .collect((Collectors.toList()));

    return (byonNodesFiltered.size() == 1);
  }

  private List<ByonNode> queryByonNodes(ByonNodeQueryRequest queryMessage) {
    ByonNodeQueryResponse response;
    try {
      response = byonService.findByonNodes(queryMessage);
    } catch (ResponseException e) {
      log.error("Response Error", e);
      throw new ApiException(e.code(), e.getMessage());
    }

    return response.getByonNodeList().stream()
        .map(BYON_CONVERTER::applyBack).collect(Collectors.toList());
  }
}