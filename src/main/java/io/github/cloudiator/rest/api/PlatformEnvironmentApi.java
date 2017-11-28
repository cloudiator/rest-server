/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.NewPlatformEnvironment;
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
import java.io.IOException;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Api(value = "platformEnvironment", description = "the platformEnvironment API")
public interface PlatformEnvironmentApi {

    @ApiOperation(value = "", nickname = "addPlatformEnvironment", notes = "Creates a new PlatformEnvironment ", response = PlatformEnvironment.class, tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = PlatformEnvironment.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
        @ApiResponse(code = 404, message = "Item not found", response = Error.class),
        @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
        @ApiResponse(code = 504, message = "Server temporary not available", response = Error.class) })
    @RequestMapping(value = "/platformEnvironment",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<PlatformEnvironment> addPlatformEnvironment(@ApiParam(value = "PlatformEnvironment to be created " ,required=true )  @Valid @RequestBody NewPlatformEnvironment platformEnvironment, @RequestHeader(value = "Accept", required = false) String accept) throws Exception;


    @ApiOperation(value = "", nickname = "findPlatformEnvironment", notes = "Returns the PlatformEnvironment identified by the id parameter. ", response = PlatformEnvironment.class, tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PlatformEnvironment.class),
        @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
        @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
        @ApiResponse(code = 504, message = "Server temporary not available", response = Error.class) })
    @RequestMapping(value = "/platformEnvironment/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PlatformEnvironment> findPlatformEnvironment(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id, @RequestHeader(value = "Accept", required = false) String accept) throws Exception;


    @ApiOperation(value = "", nickname = "findPlatformEnvironments", notes = "Returns all platform environment  visible to the user ", response = PlatformEnvironment.class, responseContainer = "List", tags={ "platform", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = PlatformEnvironment.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
        @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
        @ApiResponse(code = 504, message = "Server temporary not available", response = Error.class) })
    @RequestMapping(value = "/platformEnvironment",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<PlatformEnvironment>> findPlatformEnvironments( @RequestHeader(value = "Accept", required = false) String accept) throws Exception;

}
