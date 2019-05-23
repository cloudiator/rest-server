package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.NodeConverter;
import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.model.Scale;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Process.ScaleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ScaleApiController implements ScaleApi {

    private static final Logger log = LoggerFactory.getLogger(ScaleApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private static final NodeConverter NODE_CONVERTER = new NodeConverter();

    @org.springframework.beans.factory.annotation.Autowired
    public ScaleApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Queue> triggerScale(@ApiParam(value = "Scaling action to be executed " ,required=true )  @Valid @RequestBody Scale scale) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {

                final String tenant = UserInfo.of(request).tenant();

                final ScaleRequest scaleRequest = ScaleRequest.newBuilder()
                    .setUserId(tenant)
                    .setScheduleId(scale.getSchedule())
                    .setTaskId(scale.getTask())
                    .setNodes(NODE_CONVERTER.apply())


                return new ResponseEntity<Queue>(objectMapper.readValue("{  \"start\" : \"2000-01-23T04:56:07.000+00:00\",  \"diagnosis\" : \"diagnosis\",  \"end\" : \"2000-01-23T04:56:07.000+00:00\",  \"location\" : \"location\",  \"id\" : \"id\",  \"status\" : { }}", Queue.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Queue>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Queue>(HttpStatus.NOT_IMPLEMENTED);
    }

}
