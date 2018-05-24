package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.converter.VirtualMachineRequestConverter;
import io.github.cloudiator.rest.model.Queue;
import io.github.cloudiator.rest.model.VirtualMachineRequest;
import io.github.cloudiator.rest.queue.QueueService;
import io.github.cloudiator.rest.queue.QueueService.QueueItem;
import io.swagger.annotations.ApiParam;
import java.util.function.Function;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.cloudiator.messages.Vm.CreateVirtualMachineRequestMessage;
import org.cloudiator.messages.Vm.VirtualMachineCreatedResponse;
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
  private QueueService queueService;


  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public ResponseEntity<Queue> addVM(
      @ApiParam(value = "VirtualMachine Request", required = true) @Valid @RequestBody VirtualMachineRequest virtualMachineRequest) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      final String tenant = UserInfo.of(request).tenant();

      final QueueItem<VirtualMachineCreatedResponse> queueItem = queueService
          .queueCallback(tenant, new Function<VirtualMachineCreatedResponse, String>() {
            @Override
            public String apply(VirtualMachineCreatedResponse virtualMachineCreatedResponse) {
              return "vm/" + virtualMachineCreatedResponse.getVirtualMachine().getId();
            }
          });

      VirtualMachineRequestConverter virtualMachineRequestConverter = new VirtualMachineRequestConverter();
      CreateVirtualMachineRequestMessage createVirtualMachineRequestMessage = CreateVirtualMachineRequestMessage
          .newBuilder()
          .setUserId(UserInfo.of(request).tenant())
          .setVirtualMachineRequest(virtualMachineRequestConverter.apply(virtualMachineRequest))
          .build();

      virtualMachineService
          .createVirtualMachineAsync(createVirtualMachineRequestMessage, queueItem.getCallback());

      return new ResponseEntity<>(queueItem.getQueue(), HttpStatus.ACCEPTED);

    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
