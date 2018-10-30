package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Node;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ByonApiController implements ByonApi {

    private static final Logger log = LoggerFactory.getLogger(ByonApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ByonApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Node> addBYON(@ApiParam(value = "Node to be registered" ,required=true )  @Valid @RequestBody Node node) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Node>(objectMapper.readValue("{  \"name\" : \"name\",  \"loginCredential\" : {    \"privateKey\" : \"privateKey\",    \"password\" : \"password\",    \"username\" : \"username\"  },  \"ipAddresses\" : [ {    \"IpAddressType\" : { },    \"IpVersion\" : { },    \"value\" : \"value\"  }, {    \"IpAddressType\" : { },    \"IpVersion\" : { },    \"value\" : \"value\"  } ],  \"nodeType\" : \"UNKNOWN_TYPE\",  \"nodeId\" : \"nodeId\",  \"nodeProperties\" : {    \"disk\" : 1.4658129,    \"memory\" : 6,    \"geoLocation\" : {      \"city\" : \"Ulm\",      \"country\" : \"DE\",      \"latitude\" : 48.4010822,      \"longitude\" : 9.9876076    },    \"numberOfCores\" : 0,    \"operatingSystem\" : {      \"operatingSystemFamily\" : { },      \"operatingSystemVersion\" : \"16.04 LTS\",      \"operatingSystemType\" : { },      \"operatingSystemArchitecture\" : { }    }  }}", Node.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Node>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Node>(HttpStatus.NOT_IMPLEMENTED);
    }

}
