package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.NewPlatform;
import io.github.cloudiator.rest.model.Platform;

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

@Api(value = "platform", description = "the platform API")
public interface PlatformApi {

  @ApiOperation(value = "", notes = "Creates a new platform.", response = Platform.class, tags = {
      "platform",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Platform.class),
      @ApiResponse(code = 400, message = "Bad Request", response = Platform.class),
      @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Platform.class),
      @ApiResponse(code = 403, message = "Forbidden action", response = Platform.class),
      @ApiResponse(code = 404, message = "Item not found", response = Platform.class),
      @ApiResponse(code = 500, message = "An unexpected Error occured", response = Platform.class),
      @ApiResponse(code = 504, message = "Server temporary not available", response = Platform.class)})

  @RequestMapping(value = "/platform",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<Platform> addPlatform(
      @ApiParam(value = "Platform to add", required = true) @Valid @RequestBody NewPlatform platform);


  @ApiOperation(value = "", notes = "Deletes the platform identified by the given id paramater. ", response = Void.class, tags = {
      "platform",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Void.class),
      @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
      @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
      @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
      @ApiResponse(code = 404, message = "Item not found", response = Error.class),
      @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
      @ApiResponse(code = 504, message = "Server temporary not available", response = Error.class)})

  @RequestMapping(value = "/platform/{id}",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.DELETE)
  ResponseEntity<Void> deletePlatform(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id);


  @ApiOperation(value = "", notes = "Returns the platform identified by the given id parameter ", response = Platform.class, tags = {
      "platform",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The platform identified by the id ", response = Platform.class),
      @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
      @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
      @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
      @ApiResponse(code = 504, message = "Server temporary not available", response = Error.class)})

  @RequestMapping(value = "/platform/{id}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<Platform> findPlatform(
      @ApiParam(value = "Unique identifier of the resource", required = true) @PathVariable("id") String id);


  @ApiOperation(value = "", notes = "Returns all platform from the system that the user has access to ", response = Platform.class, responseContainer = "List", tags = {
      "platform",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "All platforms ", response = Platform.class),
      @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
      @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
      @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
      @ApiResponse(code = 504, message = "Server temporary not available", response = Error.class)})

  @RequestMapping(value = "/platform",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<Platform>> findPlatforms();

}
