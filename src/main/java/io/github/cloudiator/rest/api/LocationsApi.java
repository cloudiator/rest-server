package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.Location;

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

@Api(value = "locations", description = "the locations API")
public interface LocationsApi {

    @ApiOperation(value = "", notes = "Returns all locations visible to the user ", response = Location.class, responseContainer = "List", tags={ "cloud", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = Location.class),
        @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Location.class),
        @ApiResponse(code = 403, message = "Forbidden action", response = Location.class),
        @ApiResponse(code = 500, message = "An unexpected Error occured", response = Location.class),
        @ApiResponse(code = 504, message = "Server temporary not available", response = Location.class) })
    
    @RequestMapping(value = "/locations",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Location>> findLocations();

}
