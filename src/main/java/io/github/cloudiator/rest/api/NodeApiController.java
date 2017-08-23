package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.LongRunningRequest;
import io.github.cloudiator.rest.model.NodeRequest;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;

@Controller
public class NodeApiController implements NodeApi {



    public ResponseEntity<LongRunningRequest> addNode(@ApiParam(value = "Node Request" ,required=true )  @Valid @RequestBody NodeRequest nodeRequest) {
        // do some magic!
        return new ResponseEntity<LongRunningRequest>(HttpStatus.OK);
    }

}
