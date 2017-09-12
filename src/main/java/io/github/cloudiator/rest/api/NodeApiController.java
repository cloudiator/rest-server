package io.github.cloudiator.rest.api;


import io.github.cloudiator.rest.LRRMapService;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.LongRunningRequest;
import io.github.cloudiator.rest.model.NodeRequest;

import io.swagger.annotations.*;


import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.NodeRequestConverter;
import io.github.cloudiator.rest.model.LongRunningRequest;
import io.github.cloudiator.rest.model.NodeRequest;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

import org.cloudiator.messages.Node.NodeRequestMessage;
import org.cloudiator.messaging.services.NodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.*;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class NodeApiController implements NodeApi {


    @Autowired
    private NodeService nodeService;

    @Autowired
    private UserService userService;

    private NodeRequestConverter nodeRequestConverter = new NodeRequestConverter();

    public ResponseEntity<LongRunningRequest> addNode(
            @ApiParam(value = "Node Request", required = true) @Valid @RequestBody NodeRequest nodeRequest) {

        nodeService
                .createNodesAsync(NodeRequestMessage.newBuilder().setUserId(userService.getUserId())
                                .setNodeRequest(nodeRequestConverter.apply(nodeRequest)).build(),
                        (content, error) -> {
                            System.out.println("Error " + error);
                            System.out.println("Content " + content);
                        });

        // do some magic!
        return new ResponseEntity<LongRunningRequest>(HttpStatus.OK);
    }


}
