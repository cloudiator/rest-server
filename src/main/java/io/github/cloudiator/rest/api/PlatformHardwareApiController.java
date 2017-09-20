package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.NewPlatformHardware;
import io.github.cloudiator.rest.model.PlatformHardware;

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
public class PlatformHardwareApiController implements PlatformHardwareApi {



    public ResponseEntity<PlatformHardware> addPlatformHardware(@ApiParam(value = "PlatformHardware to be created " ,required=true )  @Valid @RequestBody NewPlatformHardware platformHardware) {
        // do some magic!
        return new ResponseEntity<PlatformHardware>(HttpStatus.OK);
    }

    public ResponseEntity<PlatformHardware> findPlatformHardware(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id) {
        // do some magic!
        return new ResponseEntity<PlatformHardware>(HttpStatus.OK);
    }

    public ResponseEntity<List<PlatformHardware>> findPlatformHardwares() {
        // do some magic!
        return new ResponseEntity<List<PlatformHardware>>(HttpStatus.OK);
    }

}
