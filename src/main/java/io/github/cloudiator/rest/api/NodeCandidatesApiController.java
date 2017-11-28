package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.NodeCandidate;

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
public class NodeCandidatesApiController implements NodeCandidatesApi {
    private final ObjectMapper objectMapper;

    public NodeCandidatesApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<List<NodeCandidate>> findNodeCandidates(@RequestHeader(value = "Accept", required = false) String accept) throws Exception {
        // do some magic!

        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<List<NodeCandidate>>(objectMapper.readValue("[ {  \"cloud\" : \"\",  \"image\" : {    \"providerId\" : \"1\",    \"name\" : \"Ubuntu 16.04 LTS AMD 64\",    \"location\" : {      \"parent\" : null,      \"isAssignable\" : true,      \"geoLocation\" : {        \"country\" : \"country\",        \"city\" : \"city\"      },      \"providerId\" : \"RegionOne\",      \"locationScope\" : \"ZONE\",      \"name\" : \"RegionOne\",      \"id\" : \"1a79a4d60de6718e8e5b326e338ae533/RegionOne\"    },    \"id\" : \"1a79a4d60de6718e8e5b326e338ae533/RegionOne/1\",    \"operatingSystem\" : {      \"operatingSystemFamily\" : { },      \"operatingSystemVersion\" : \"16.04 LTS\",      \"operatingSystemType\" : { },      \"operatingSystemArchitecture\" : { }    }  },  \"location\" : {    \"parent\" : null,    \"isAssignable\" : true,    \"geoLocation\" : {      \"country\" : \"country\",      \"city\" : \"city\"    },    \"providerId\" : \"RegionOne\",    \"locationScope\" : \"ZONE\",    \"name\" : \"RegionOne\",    \"id\" : \"1a79a4d60de6718e8e5b326e338ae533/RegionOne\"  },  \"hardware\" : {    \"disk\" : 100.0,    \"cores\" : 4,    \"providerId\" : \"1\",    \"name\" : \"m1.medium\",    \"location\" : {      \"parent\" : null,      \"isAssignable\" : true,      \"geoLocation\" : {        \"country\" : \"country\",        \"city\" : \"city\"      },      \"providerId\" : \"RegionOne\",      \"locationScope\" : \"ZONE\",      \"name\" : \"RegionOne\",      \"id\" : \"1a79a4d60de6718e8e5b326e338ae533/RegionOne\"    },    \"id\" : \"1a79a4d60de6718e8e5b326e338ae533/RegionOne/1\",    \"ram\" : 2048  }}, {  \"cloud\" : \"\",  \"image\" : {    \"providerId\" : \"1\",    \"name\" : \"Ubuntu 16.04 LTS AMD 64\",    \"location\" : {      \"parent\" : null,      \"isAssignable\" : true,      \"geoLocation\" : {        \"country\" : \"country\",        \"city\" : \"city\"      },      \"providerId\" : \"RegionOne\",      \"locationScope\" : \"ZONE\",      \"name\" : \"RegionOne\",      \"id\" : \"1a79a4d60de6718e8e5b326e338ae533/RegionOne\"    },    \"id\" : \"1a79a4d60de6718e8e5b326e338ae533/RegionOne/1\",    \"operatingSystem\" : {      \"operatingSystemFamily\" : { },      \"operatingSystemVersion\" : \"16.04 LTS\",      \"operatingSystemType\" : { },      \"operatingSystemArchitecture\" : { }    }  },  \"location\" : {    \"parent\" : null,    \"isAssignable\" : true,    \"geoLocation\" : {      \"country\" : \"country\",      \"city\" : \"city\"    },    \"providerId\" : \"RegionOne\",    \"locationScope\" : \"ZONE\",    \"name\" : \"RegionOne\",    \"id\" : \"1a79a4d60de6718e8e5b326e338ae533/RegionOne\"  },  \"hardware\" : {    \"disk\" : 100.0,    \"cores\" : 4,    \"providerId\" : \"1\",    \"name\" : \"m1.medium\",    \"location\" : {      \"parent\" : null,      \"isAssignable\" : true,      \"geoLocation\" : {        \"country\" : \"country\",        \"city\" : \"city\"      },      \"providerId\" : \"RegionOne\",      \"locationScope\" : \"ZONE\",      \"name\" : \"RegionOne\",      \"id\" : \"1a79a4d60de6718e8e5b326e338ae533/RegionOne\"    },    \"id\" : \"1a79a4d60de6718e8e5b326e338ae533/RegionOne/1\",    \"ram\" : 2048  }} ]", List.class), HttpStatus.OK);
        }

        return new ResponseEntity<List<NodeCandidate>>(HttpStatus.OK);
    }

}
