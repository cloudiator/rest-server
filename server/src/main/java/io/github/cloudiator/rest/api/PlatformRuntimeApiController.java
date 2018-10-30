package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.NewPlatformRuntime;
import io.github.cloudiator.rest.model.PlatformRuntime;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class PlatformRuntimeApiController implements PlatformRuntimeApi {

    private static final Logger log = LoggerFactory.getLogger(PlatformRuntimeApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PlatformRuntimeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<PlatformRuntime> addPlatformRuntime(@ApiParam(value = "PlatformRuntime to be created " ,required=true )  @Valid @RequestBody NewPlatformRuntime platformRuntime) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PlatformRuntime>(objectMapper.readValue("\"\"", PlatformRuntime.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PlatformRuntime>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlatformRuntime>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PlatformRuntime> findPlatformRuntime(@ApiParam(value = "Unique identifier of the resource",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PlatformRuntime>(objectMapper.readValue("\"\"", PlatformRuntime.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PlatformRuntime>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PlatformRuntime>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<PlatformRuntime>> findPlatformRuntimes() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<PlatformRuntime>>(objectMapper.readValue("[ \"\", \"\" ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<PlatformRuntime>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<PlatformRuntime>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
