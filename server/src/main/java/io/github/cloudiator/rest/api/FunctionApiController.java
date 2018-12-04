package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Function;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class FunctionApiController implements FunctionApi {

    private static final Logger log = LoggerFactory.getLogger(FunctionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public FunctionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Function>> findFunctions() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Function>>(objectMapper.readValue("[ {  \"memory\" : 358,  \"locationId\" : \"locationId\",  \"cloudId\" : \"cloudId\",  \"stackId\" : \"stackId\",  \"id\" : \"id\"}, {  \"memory\" : 358,  \"locationId\" : \"locationId\",  \"cloudId\" : \"cloudId\",  \"stackId\" : \"stackId\",  \"id\" : \"id\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Function>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Function>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Function> getFunction(@ApiParam(value = "Unique identifier of the resource",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Function>(objectMapper.readValue("{  \"memory\" : 358,  \"locationId\" : \"locationId\",  \"cloudId\" : \"cloudId\",  \"stackId\" : \"stackId\",  \"id\" : \"id\"}", Function.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Function>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Function>(HttpStatus.NOT_IMPLEMENTED);
    }

}
