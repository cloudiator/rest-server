package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Task;

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

@Api(value = "task", description = "the task API")
public interface TaskApi {

    @ApiOperation(value = "", notes = "Returns the task identified by the id parameter. ", response = Task.class, tags={ "task", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Task.class) })
    
    @RequestMapping(value = "/task/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Task> findTask(@ApiParam(value = "Unique identifier of the resource",required=true ) @PathVariable("id") String id);

}
