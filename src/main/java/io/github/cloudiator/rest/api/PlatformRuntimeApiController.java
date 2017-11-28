package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.NewPlatformRuntime;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Controller
public class PlatformRuntimeApiController implements PlatformRuntimeApi {
    private final ObjectMapper objectMapper;

    public PlatformRuntimeApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<PlatformRuntime> addPlatformRuntime(@ApiParam(value = "PlatformRuntime to be created " ,required=true )  @Valid @RequestBody NewPlatformRuntime platformRuntime,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<PlatformRuntime>(objectMapper.readValue("\"\"", PlatformRuntime.class), HttpStatus.OK);
        }

        return new ResponseEntity<PlatformRuntime>(HttpStatus.OK);
    }

    public ResponseEntity<PlatformRuntime> findPlatformRuntime(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<PlatformRuntime>(objectMapper.readValue("\"\"", PlatformRuntime.class), HttpStatus.OK);
        }

        return new ResponseEntity<PlatformRuntime>(HttpStatus.OK);
    }

    public ResponseEntity<List<PlatformRuntime>> findPlatformRuntimes(@RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<List<PlatformRuntime>>(objectMapper.readValue("[ \"\", \"\" ]", List.class), HttpStatus.OK);
        }

        return new ResponseEntity<List<PlatformRuntime>>(HttpStatus.OK);
    }

}
