package io.github.cloudiator.rest.api;

import com.google.common.base.Strings;
import io.github.cloudiator.rest.UserInfo;
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

      final String tenant = UserInfo.of(request).tenant();
      final QueueItem<ByonNodeAddedResponse> queueItem = queueService
          .queueCallback(tenant,
              byonNodeAddedResponse -> "byon/" + byonNodeAddedResponse.getByonNode().getId());

      Byon.ByonNode byonNode = NEW_NODE_CONVERTER.apply(newNode);
      Byon.ByonData data = byonNode.getNodeData();
      AddByonNodeRequest byonRequest = AddByonNodeRequest.newBuilder()
          .setByonRequest(data).setUserId(tenant).build();

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

      final String tenant = UserInfo.of(request).tenant();
      final QueueItem<ByonNodeRemovedResponse> queueItem = queueService
          .queueCallback(tenant);

      RemoveByonNodeRequest byonRequest = RemoveByonNodeRequest.newBuilder().setId(id)
          .setUserId(tenant).build();

      byonService.removeByonNodeAsync(byonRequest, queueItem.getCallback());

      final HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(HttpHeaders.LOCATION, queueItem.getQueueLocation());

      return new ResponseEntity<Queue>(queueItem.getQueue(), httpHeaders, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  public ResponseEntity<List<ByonNode>> findByons() {

    String accept = request.getHeader("Accept");

    final String tenant = UserInfo.of(request).tenant();

    if (accept != null && accept.contains("application/json")) {

      try {
        final ByonNodeQueryRequest queryRequest = ByonNodeQueryRequest.newBuilder()
            .setUserId(tenant)
            .setFilter(QueryFilter.ALL).build();
        ByonNodeQueryResponse response = byonService.findByonNodes(queryRequest);
        List<ByonNode> byonNodes = response.getByonNodeList().stream()
            .map(BYON_CONVERTER::applyBack).collect(Collectors.toList());
        return new ResponseEntity<>(byonNodes, HttpStatus.OK);
      } catch (ResponseException e) {
        log.error("Response Error", e);
        throw new ApiException(e.code(), e.getMessage());
      }
    }

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}