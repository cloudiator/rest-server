package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.Image;

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

@Api(value = "images", description = "the images API")
public interface ImagesApi {

    @ApiOperation(value = "", notes = "Updates a specific image ", response = Image.class, tags={ "cloud", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = Image.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
        @ApiResponse(code = 404, message = "Item not found", response = Error.class),
        @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
        @ApiResponse(code = 504, message = "Service temporary unavailable", response = Error.class)})
    
    @RequestMapping(value = "/images/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Image> editImage(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id,@ApiParam(value = "Image to update " ,required=true )  @Valid @RequestBody Image image);


    @ApiOperation(value = "", notes = "Returns all images visable to the user ", response = Image.class, responseContainer = "List", tags={ "cloud", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "All images ", response = Image.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
        @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
        @ApiResponse(code = 504, message = "Service temporary unavailable", response = Error.class)})
    
    @RequestMapping(value = "/images",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Image>> findImages();

}
