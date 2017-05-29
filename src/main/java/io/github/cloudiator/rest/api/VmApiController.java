package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.model.Task;
import io.github.cloudiator.rest.model.VirtualMachineRequest;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class VmApiController implements VmApi {



    public ResponseEntity<Task> addVM(@ApiParam(value = "VirtualMachine Request" ,required=true )  @Valid @RequestBody VirtualMachineRequest virtualMachineRequest) {
        // do some magic!
        return new ResponseEntity<Task>(HttpStatus.OK);
    }

}
