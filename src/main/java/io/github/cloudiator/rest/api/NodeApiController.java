package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.LRRMapService;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.LongRunningRequest;
import io.github.cloudiator.rest.model.NodeRequest;

import io.swagger.annotations.*;

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

@Controller
public class NodeApiController implements NodeApi {

    @Autowired
    private LRRMapService lrrMapService;

    @Autowired
    private UserService userService;



    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<LongRunningRequest> addNode(@ApiParam(value = "Node Request" ,required=true )  @Valid @RequestBody NodeRequest nodeRequest) {

        final LongRunningRequest newlrr = new LongRunningRequest();
                newlrr.setId(UUID.randomUUID().toString());


        return new ResponseEntity<LongRunningRequest>(newlrr, HttpStatus.OK);
    }

}
