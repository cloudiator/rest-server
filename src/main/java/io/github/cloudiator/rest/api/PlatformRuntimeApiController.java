package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.PlatformRuntime;

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
public class PlatformRuntimeApiController implements PlatformRuntimeApi {



    public ResponseEntity<PlatformRuntime> addPlatformRuntime(@ApiParam(value = "PlatformRuntime to be created " ,required=true )  @Valid @RequestBody PlatformRuntime platformRuntime) {
        // do some magic!
        return new ResponseEntity<PlatformRuntime>(HttpStatus.OK);
    }

    public ResponseEntity<PlatformRuntime> findPlatformRuntime(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id) {
        // do some magic!
        return new ResponseEntity<PlatformRuntime>(HttpStatus.OK);
    }

    public ResponseEntity<List<PlatformRuntime>> findPlatformRuntimes() {
        // do some magic!
        return new ResponseEntity<List<PlatformRuntime>>(HttpStatus.OK);
    }

}
