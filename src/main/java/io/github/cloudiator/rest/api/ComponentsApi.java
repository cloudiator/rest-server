package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Component;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T14:29:11.837+02:00")

@Api(value = "components", description = "the components API")
public interface ComponentsApi {

    @ApiOperation(value = "", notes = "Creates a new component ", response = Component.class, tags={ "application", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = Component.class) })
    
    @RequestMapping(value = "/components",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Component> addComponent(@ApiParam(value = "Component to be created " ,required=true )  @Valid @RequestBody Component component);


    @ApiOperation(value = "", notes = "Returns all components visible to the user ", response = Component.class, responseContainer = "List", tags={ "application", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = Component.class) })
    
    @RequestMapping(value = "/components",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Component>> findComponents();

}
