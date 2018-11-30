package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.ProcessGroup;
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
public class ProcessGroupApiController implements ProcessGroupApi {

    private static final Logger log = LoggerFactory.getLogger(ProcessGroupApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ProcessGroupApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<ProcessGroup>> findProcessGroups() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<ProcessGroup>>(objectMapper.readValue("[ {  \"processes\" : [ {    \"schedule\" : \"schedule\",    \"task\" : \"task\",    \"id\" : \"id\",    \"type\" : \"LANCE\"  }, {    \"schedule\" : \"schedule\",    \"task\" : \"task\",    \"id\" : \"id\",    \"type\" : \"LANCE\"  } ],  \"id\" : \"id\"}, {  \"processes\" : [ {    \"schedule\" : \"schedule\",    \"task\" : \"task\",    \"id\" : \"id\",    \"type\" : \"LANCE\"  }, {    \"schedule\" : \"schedule\",    \"task\" : \"task\",    \"id\" : \"id\",    \"type\" : \"LANCE\"  } ],  \"id\" : \"id\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<ProcessGroup>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<ProcessGroup>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ProcessGroup> getProcessGroup(@ApiParam(value = "Unique identifier of the resource",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ProcessGroup>(objectMapper.readValue("{  \"processes\" : [ {    \"schedule\" : \"schedule\",    \"task\" : \"task\",    \"id\" : \"id\",    \"type\" : \"LANCE\"  }, {    \"schedule\" : \"schedule\",    \"task\" : \"task\",    \"id\" : \"id\",    \"type\" : \"LANCE\"  } ],  \"id\" : \"id\"}", ProcessGroup.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ProcessGroup>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ProcessGroup>(HttpStatus.NOT_IMPLEMENTED);
    }

}
