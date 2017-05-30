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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-30T11:38:27.568+02:00")

@Api(value = "tasks", description = "the tasks API")
public interface TasksApi {

    @ApiOperation(value = "", notes = "Returns all running tasks visible to the user ", response = Task.class, responseContainer = "List", tags={ "task", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Task.class) })
    
    @RequestMapping(value = "/tasks",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Task>> findTasks();

}
