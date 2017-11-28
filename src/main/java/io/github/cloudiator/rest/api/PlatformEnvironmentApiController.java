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
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Controller
public class PlatformEnvironmentApiController implements PlatformEnvironmentApi {
    private final ObjectMapper objectMapper;

    public PlatformEnvironmentApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<PlatformEnvironment> addPlatformEnvironment(@ApiParam(value = "PlatformEnvironment to be created " ,required=true )  @Valid @RequestBody NewPlatformEnvironment platformEnvironment,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<PlatformEnvironment>(objectMapper.readValue("\"\"", PlatformEnvironment.class), HttpStatus.OK);
        }

        return new ResponseEntity<PlatformEnvironment>(HttpStatus.OK);
    }

    public ResponseEntity<PlatformEnvironment> findPlatformEnvironment(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<PlatformEnvironment>(objectMapper.readValue("\"\"", PlatformEnvironment.class), HttpStatus.OK);
        }

        return new ResponseEntity<PlatformEnvironment>(HttpStatus.OK);
    }

    public ResponseEntity<List<PlatformEnvironment>> findPlatformEnvironments(@RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<List<PlatformEnvironment>>(objectMapper.readValue("[ \"\", \"\" ]", List.class), HttpStatus.OK);
        }

        return new ResponseEntity<List<PlatformEnvironment>>(HttpStatus.OK);
    }

}
