package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.LRRMapService;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.VirtualMachineRequestConverter;
import io.github.cloudiator.rest.model.*;
import io.swagger.annotations.ApiParam;

import javax.annotation.Nullable;
import javax.validation.Valid;

import org.cloudiator.messages.General;
import org.cloudiator.messages.Vm;
import org.cloudiator.messages.Vm.CreateVirtualMachineRequestMessage;
import org.cloudiator.messages.Vm.VirtualMachineCreatedResponse;
import org.cloudiator.messaging.ResponseCallback;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.VirtualMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class VmApiController implements VmApi {

    @Autowired
    private VirtualMachineService virtualMachineService;

    @Autowired
    private UserService userService;

    @Autowired
    private LRRMapService lrrMapService;


    public ResponseEntity<LongRunningRequest> addVM(
            @ApiParam(value = "VirtualMachine Request", required = true) @Valid @RequestBody VirtualMachineRequest virtualMachineRequest) {


        final LongRunningRequest lrr = new LongRunningRequest();
        lrr.setId(UUID.randomUUID().toString());
        lrr.setTaskStatus(LRRStatus.RUNNING);
        lrr.setTaskType(LRRType.VIRTUALMACHINEREQUEST);
        lrr.setLrrData(virtualMachineRequest.toString());

        lrrMapService.addLRR(userService.getUserId(),lrr);

        VirtualMachineRequestConverter virtualMachineRequestConverter = new VirtualMachineRequestConverter();
        CreateVirtualMachineRequestMessage request = CreateVirtualMachineRequestMessage.newBuilder()
                .setUserId(userService.getUserId())
                .setVirtualMachineRequest(virtualMachineRequestConverter.apply(virtualMachineRequest))
                .build();

        virtualMachineService.createVirtualMachineAsync(request, new ResponseCallback<VirtualMachineCreatedResponse>() {
            @Override
            public void accept(@Nullable VirtualMachineCreatedResponse content, @Nullable General.Error error) {
                if(error == null) {
                    lrr.setTaskStatus(LRRStatus.COMPLETED);
                    lrr.setReferenceId(content.getVirtualMachine().getId());
                } else {
                    lrr.setTaskStatus(LRRStatus.FAILED);
                    lrr.setLrrDiagnostic(error.getMessage());
                }
            }
        });

        return new ResponseEntity<>(lrr, HttpStatus.OK);
    }

}
