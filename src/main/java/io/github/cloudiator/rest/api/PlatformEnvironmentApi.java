package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.PlatformEnvironment;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-02T09:24:26.089+02:00")

@Api(value = "platformEnvironment", description = "the platformEnvironment API")
public interface PlatformEnvironmentApi {

    @ApiOperation(value = "", notes = "Creates a new PlatformEnvironment ", response = PlatformEnvironment.class, tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = PlatformEnvironment.class) })
    
    @RequestMapping(value = "/platformEnvironment",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<PlatformEnvironment> addPlatformEnvironment(@ApiParam(value = "PlatformEnvironment to be created " ,required=true )  @Valid @RequestBody PlatformEnvironment platformEnvironment);


    @ApiOperation(value = "", notes = "Returns the PlatformEnvironment identified by the id parameter. ", response = PlatformEnvironment.class, tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PlatformEnvironment.class) })
    
    @RequestMapping(value = "/platformEnvironment/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<PlatformEnvironment> findPlatformEnvironment(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id);


    @ApiOperation(value = "", notes = "Returns all platform environment  visible to the user ", response = PlatformEnvironment.class, responseContainer = "List", tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = PlatformEnvironment.class) })
    
    @RequestMapping(value = "/platformEnvironment",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<PlatformEnvironment>> findPlatformEnvironments();

}
