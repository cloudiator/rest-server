package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.LRRMapService;
import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.VirtualMachineRequestConverter;
import io.github.cloudiator.rest.model.LongRunningRequest;
import io.github.cloudiator.rest.model.VirtualMachineRequest;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

import org.cloudiator.messages.Vm.CreateVirtualMachineRequestMessage;
import org.cloudiator.messages.Vm.VirtualMachineCreatedResponse;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.VirtualMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.security.SecureRandom;

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
        LongRunningRequest result = null;
        System.out.println(virtualMachineRequest.toString());
        result = lrrMapService.addLRR(userService.getUserId(), virtualMachineRequest.toString());
        System.out.println("ID: " + result.getId());
        System.out.println("item(s): " + lrrMapService.getAllLRR(userService.getUserId()).size());
        /*
        VirtualMachineRequestConverter virtualMachineRequestConverter = new VirtualMachineRequestConverter();

        CreateVirtualMachineRequestMessage request = CreateVirtualMachineRequestMessage.newBuilder()
                .setUserId(userService.getUserId())
                .setVirtualMachineRequest(virtualMachineRequestConverter.apply(virtualMachineRequest))
                .build();

        VirtualMachineCreatedResponse virtualMachineCreatedResponse = null;

        try

        {
            virtualMachineCreatedResponse = virtualMachineService.createVirtualMachine(request);
        } catch (
                ResponseException e)

        {
            e.printStackTrace();
        }

        System.out.println("HAHA !! \n" + virtualMachineCreatedResponse);
        */
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
