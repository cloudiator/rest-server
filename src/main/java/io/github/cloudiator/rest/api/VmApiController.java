package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.LRRMapService;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.VirtualMachineRequestConverter;
import io.github.cloudiator.rest.model.LRRStatus;
import io.github.cloudiator.rest.model.LRRType;
import io.github.cloudiator.rest.model.LongRunningRequest;
import io.github.cloudiator.rest.model.VirtualMachineRequest;
import io.swagger.annotations.ApiParam;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.General;
import org.cloudiator.messages.Vm.CreateVirtualMachineRequestMessage;
import org.cloudiator.messages.Vm.VirtualMachineCreatedResponse;
import org.cloudiator.messaging.ResponseCallback;
import org.cloudiator.messaging.services.VirtualMachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class VmApiController implements VmApi {

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public VmApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Autowired
  private VirtualMachineService virtualMachineService;

  @Autowired
  private LRRMapService lrrMapService;


  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public ResponseEntity<LongRunningRequest> addVM(
      @ApiParam(value = "VirtualMachine Request", required = true) @Valid @RequestBody VirtualMachineRequest virtualMachineRequest) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final LongRunningRequest lrr = new LongRunningRequest();
      lrr.setId(UUID.randomUUID().toString());
      lrr.setTaskStatus(LRRStatus.RUNNING);
      lrr.setTaskType(LRRType.VIRTUALMACHINEREQUEST);
      lrr.setLrrData(virtualMachineRequest.toString());

      lrrMapService.addLRR(UserInfo.of(request).tenant(), lrr);

      VirtualMachineRequestConverter virtualMachineRequestConverter = new VirtualMachineRequestConverter();
      CreateVirtualMachineRequestMessage createVirtualMachineRequestMessage = CreateVirtualMachineRequestMessage
          .newBuilder()
          .setUserId(UserInfo.of(request).tenant())
          .setVirtualMachineRequest(virtualMachineRequestConverter.apply(virtualMachineRequest))
          .build();

      virtualMachineService
          .createVirtualMachineAsync(createVirtualMachineRequestMessage,
              new ResponseCallback<VirtualMachineCreatedResponse>() {
                @Override
                public void accept(@Nullable VirtualMachineCreatedResponse content,
                    @Nullable General.Error error) {
                  if (error == null) {
                    lrr.setTaskStatus(LRRStatus.COMPLETED);
                    lrr.setReferenceId(content.getVirtualMachine().getId());
                  } else {
                    lrr.setTaskStatus(LRRStatus.FAILED);
                    lrr.setLrrDiagnostic(error.getMessage());
                  }
                }
              });

      return new ResponseEntity<>(lrr, HttpStatus.ACCEPTED);

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
