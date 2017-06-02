package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.PlatformHardware;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-02T13:00:29.446+02:00")

@Api(value = "platformHardware", description = "the platformHardware API")
public interface PlatformHardwareApi {

    @ApiOperation(value = "", notes = "Creates a new PlatformHardware ", response = PlatformHardware.class, tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = PlatformHardware.class) })
    
    @RequestMapping(value = "/platformHardware",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<PlatformHardware> addPlatformHardware(@ApiParam(value = "PlatformHardware to be created " ,required=true )  @Valid @RequestBody PlatformHardware platformHardware);


    @ApiOperation(value = "", notes = "Returns the PlatformHardware identified by the id parameter. ", response = PlatformHardware.class, tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PlatformHardware.class) })
    
    @RequestMapping(value = "/platformHardware/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<PlatformHardware> findPlatformHardware(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id);


    @ApiOperation(value = "", notes = "Returns all platform hardware visible to the user ", response = PlatformHardware.class, responseContainer = "List", tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = PlatformHardware.class) })
    
    @RequestMapping(value = "/platformHardware",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<PlatformHardware>> findPlatformHardwares();

}
