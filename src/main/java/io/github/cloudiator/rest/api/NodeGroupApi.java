/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Error;
import io.github.cloudiator.rest.model.NodeGroup;
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

@Api(value = "nodeGroup", description = "the nodeGroup API")
public interface NodeGroupApi {

    @ApiOperation(value = "", nickname = "findNodeGroups", notes = "Returns all node groups for the current user", response = NodeGroup.class, responseContainer = "List", authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags={ "node", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = NodeGroup.class, responseContainer = "List") })
    @RequestMapping(value = "/nodeGroup",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<NodeGroup>> findNodeGroups();


    @ApiOperation(value = "", nickname = "getNodeGroup", notes = "Retrieves a node group, which groups multiple nodes that were create during one request", response = NodeGroup.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")
    }, tags={ "node", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK ", response = NodeGroup.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Authorization for this action is missing", response = Error.class),
        @ApiResponse(code = 403, message = "Forbidden action", response = Error.class),
        @ApiResponse(code = 404, message = "Item not found", response = Error.class),
        @ApiResponse(code = 500, message = "An unexpected Error occured", response = Error.class),
        @ApiResponse(code = 504, message = "Server temporary not available", response = Error.class) })
    @RequestMapping(value = "/nodeGroup/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<NodeGroup> getNodeGroup(@ApiParam(value = "Unique identifier of the resource",required=true) @PathVariable("id") String id);

}