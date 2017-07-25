package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.LRRMapService;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.LongRunningRequest;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;

@Controller
public class LongRunningRequestsApiController implements LongRunningRequestsApi {

    @Autowired
    private LRRMapService lrrMapService;

    @Autowired
    private UserService userService;


    public ResponseEntity<List<LongRunningRequest>> findAllLongRunningRequest() {
        List<LongRunningRequest> result = lrrMapService.getAllLRR(userService.getUserId());
        return new ResponseEntity<List<LongRunningRequest>>(result, HttpStatus.OK);
    }

    public ResponseEntity<LongRunningRequest> findLongRunningRequest(@ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id) {

        LongRunningRequest result = null;
        if (lrrMapService.getLRR(userService.getUserId(), id) != null) {
            result = lrrMapService.getLRR(userService.getUserId(), id);
        } else {
            throw new ApiException(404, "Not Found");
        }

        return new ResponseEntity<LongRunningRequest>(result, HttpStatus.OK);
    }

}
