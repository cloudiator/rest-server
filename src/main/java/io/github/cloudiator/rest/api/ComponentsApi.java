package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Component;
import io.github.cloudiator.rest.model.Error;

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

@Api(value = "components", description = "the components API")
public interface ComponentsApi {

  @ApiOperation(value = "", notes = "Creates a new component ", response = Component.class, tags = {
      "application",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK ", response = Component.class),
      @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
      @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
      @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
      @ApiResponse(code = 404, message = "Item not found", response = Error.class),
      @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
      @ApiResponse(code = 504, message = "Service temporary unavailable", response = Error.class)})

  @RequestMapping(value = "/components",
      produces = {"application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<Component> addComponent(
      @ApiParam(value = "Component to be created ", required = true) @Valid @RequestBody Component component);


  @ApiOperation(value = "", notes = "Returns all components visible to the user ", response = Component.class, responseContainer = "List", tags = {
      "application",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK ", response = Component.class),
      @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
      @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
      @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
      @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
      @ApiResponse(code = 504, message = "Service temporary unavailable", response = Error.class)})

  @RequestMapping(value = "/components",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<Component>> findComponents();

}
