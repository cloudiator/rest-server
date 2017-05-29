package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Cloud;
import io.github.cloudiator.rest.model.NewCloud;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:03:11.942+02:00")

@Api(value = "clouds", description = "the clouds API")
public interface CloudsApi {

    @ApiOperation(value = "", notes = "Creates a new cloud.", response = Cloud.class, tags={ "cloud", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Cloud.class) })
    
    @RequestMapping(value = "/clouds",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Cloud> addCloud(@ApiParam(value = "Cloud to add" ,required=true )  @Valid @RequestBody NewCloud cloud);


    @ApiOperation(value = "", notes = "Deletes the cloud identified by the given id paramater. ", response = Void.class, tags={ "cloud", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Void.class) })
    
    @RequestMapping(value = "/clouds/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCloud(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id);


    @ApiOperation(value = "", notes = "Returns the cloud identified by the given id parameter ", response = Cloud.class, tags={ "cloud", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The cloud identified by the id ", response = Cloud.class) })
    
    @RequestMapping(value = "/clouds/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Cloud> findCloud(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id);


    @ApiOperation(value = "", notes = "Returns all clouds from the system that the user has access to ", response = Cloud.class, responseContainer = "List", tags={ "cloud", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "All clouds ", response = Cloud.class) })
    
    @RequestMapping(value = "/clouds",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Cloud>> findClouds();

}
