package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Task;
import io.github.cloudiator.rest.model.VirtualMachineRequest;

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

@Api(value = "vm", description = "the vm API")
public interface VmApi {

    @ApiOperation(value = "", notes = "Creates a new virtual machine request", response = Task.class, tags={ "cloud", })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "ACCEPTED", response = Task.class) })
    
    @RequestMapping(value = "/vm",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Task> addVM(@ApiParam(value = "VirtualMachine Request" ,required=true )  @Valid @RequestBody VirtualMachineRequest virtualMachineRequest);

}
