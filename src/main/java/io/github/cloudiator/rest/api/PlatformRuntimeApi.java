package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.PlatformRuntime;

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

@Api(value = "platformRuntime", description = "the platformRuntime API")
public interface PlatformRuntimeApi {

    @ApiOperation(value = "", notes = "Creates a new PlatformRuntime ", response = PlatformRuntime.class, tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = PlatformRuntime.class),
        @ApiResponse(code = 400, message = "Bad Request", response = PlatformRuntime.class),
        @ApiResponse(code = 401, message = "Authorization for this action is missing", response = PlatformRuntime.class),
        @ApiResponse(code = 403, message = "Forbidden action", response = PlatformRuntime.class),
        @ApiResponse(code = 404, message = "Item not found", response = PlatformRuntime.class),
        @ApiResponse(code = 500, message = "An unexpected Error occured", response = PlatformRuntime.class),
        @ApiResponse(code = 504, message = "Server temporary not available", response = PlatformRuntime.class) })
    
    @RequestMapping(value = "/platformRuntime",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<PlatformRuntime> addPlatformRuntime(@ApiParam(value = "PlatformRuntime to be created " ,required=true )  @Valid @RequestBody PlatformRuntime platformRuntime);


    @ApiOperation(value = "", notes = "Returns the PlatformRuntime identified by the id parameter. ", response = PlatformRuntime.class, tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PlatformRuntime.class),
        @ApiResponse(code = 401, message = "Authorization for this action is missing", response = PlatformRuntime.class),
        @ApiResponse(code = 403, message = "Forbidden action", response = PlatformRuntime.class),
        @ApiResponse(code = 500, message = "An unexpected Error occured", response = PlatformRuntime.class),
        @ApiResponse(code = 504, message = "Server temporary not available", response = PlatformRuntime.class) })
    
    @RequestMapping(value = "/platformRuntime/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PlatformRuntime> findPlatformRuntime(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id);


    @ApiOperation(value = "", notes = "Returns all platform runtime  visible to the user ", response = PlatformRuntime.class, responseContainer = "List", tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = PlatformRuntime.class),
        @ApiResponse(code = 401, message = "Authorization for this action is missing", response = PlatformRuntime.class),
        @ApiResponse(code = 403, message = "Forbidden action", response = PlatformRuntime.class),
        @ApiResponse(code = 500, message = "An unexpected Error occured", response = PlatformRuntime.class),
        @ApiResponse(code = 504, message = "Server temporary not available", response = PlatformRuntime.class) })
    
    @RequestMapping(value = "/platformRuntime",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<PlatformRuntime>> findPlatformRuntimes();

}
