package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.NewPlatformEnvironment;
import io.github.cloudiator.rest.model.PlatformEnvironment;

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
public class PlatformEnvironmentApiController implements PlatformEnvironmentApi {



    public ResponseEntity<PlatformEnvironment> addPlatformEnvironment(@ApiParam(value = "PlatformEnvironment to be created " ,required=true )  @Valid @RequestBody NewPlatformEnvironment platformEnvironment) {
        // do some magic!
        return new ResponseEntity<PlatformEnvironment>(HttpStatus.OK);
    }

    public ResponseEntity<PlatformEnvironment> findPlatformEnvironment(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id) {
        // do some magic!
        return new ResponseEntity<PlatformEnvironment>(HttpStatus.OK);
    }

    public ResponseEntity<List<PlatformEnvironment>> findPlatformEnvironments() {
        // do some magic!
        return new ResponseEntity<List<PlatformEnvironment>>(HttpStatus.OK);
    }

}
