package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Schedule;
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
public class ScheduleApiController implements ScheduleApi {

    private static final Logger log = LoggerFactory.getLogger(ScheduleApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ScheduleApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Schedule> addSchedule(@ApiParam(value = "Schedule to be created " ,required=true )  @Valid @RequestBody Schedule schedule) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Schedule>(objectMapper.readValue("{  \"job\" : \"job\",  \"instantiation\" : \"AUTOMATIC\"}", Schedule.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Schedule>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Schedule>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Schedule>> getSchedules() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Schedule>>(objectMapper.readValue("[ {  \"job\" : \"job\",  \"instantiation\" : \"AUTOMATIC\"}, {  \"job\" : \"job\",  \"instantiation\" : \"AUTOMATIC\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Schedule>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Schedule>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
