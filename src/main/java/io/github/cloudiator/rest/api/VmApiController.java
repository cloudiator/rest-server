package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.model.Task;
import io.github.cloudiator.rest.model.VirtualMachineRequest;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import org.cloudiator.messaging.services.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class VmApiController implements VmApi {

  @Autowired
  private CloudService cloudService;

  @Autowired
  private UserService userService;

  public ResponseEntity<Task> addVM(
      @ApiParam(value = "VirtualMachine Request", required = true) @Valid @RequestBody VirtualMachineRequest virtualMachineRequest) {
    // do some magic!
    return new ResponseEntity<Task>(HttpStatus.OK);
  }

}
