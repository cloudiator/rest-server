/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Monitor;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Api(value = "monitors", description = "the monitors API")
public interface MonitorsApi {

    @ApiOperation(value = "", nickname = "addMonitor", notes = "Creates a monitor ", response = Monitor.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags={ "monitoring", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = Monitor.class) })
    @RequestMapping(value = "/monitors",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Monitor> addMonitor(@ApiParam(value = "Monitor to be created " ,required=true )  @Valid @RequestBody Monitor monitor);


    @ApiOperation(value = "", nickname = "deleteMonitor", notes = "Deletes the monitor identified by the given metric name. ", authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags={ "monitoring", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK") })
    @RequestMapping(value = "/monitors/{metric}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteMonitor(@ApiParam(value = "Unique identifier of a monitor",required=true) @PathVariable("metric") String metric);


    @ApiOperation(value = "", nickname = "findMonitors", notes = "Returns all monitors visible to the user ", response = Monitor.class, responseContainer = "List", authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags={ "monitoring", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = Monitor.class, responseContainer = "List") })
    @RequestMapping(value = "/monitors",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Monitor>> findMonitors();


    @ApiOperation(value = "", nickname = "getMonitor", notes = "Retrieves the monitor with the given metric name ", response = Monitor.class, responseContainer = "List", authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags={ "monitoring", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Monitor.class, responseContainer = "List") })
    @RequestMapping(value = "/monitors/{metric}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Monitor>> getMonitor(@ApiParam(value = "Unique identifier of a monitor",required=true) @PathVariable("metric") String metric);


    @ApiOperation(value = "", nickname = "updateMonitor", notes = "Updating a monitor ", response = Monitor.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags={ "monitoring", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = Monitor.class) })
    @RequestMapping(value = "/monitors/{metric}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Monitor> updateMonitor(@ApiParam(value = "Unique identifier of a monitor",required=true) @PathVariable("metric") String metric,@ApiParam(value = "Monitor to be updated " ,required=true )  @Valid @RequestBody Monitor monitor);

}
