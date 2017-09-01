package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.VirtualMachineRequest;

import javax.annotation.Nullable;

import org.cloudiator.messages.entities.IaasEntities;


/**
 * Created by daniel on 27.06.17.
 */
public class VirtualMachineRequestConverter implements
        TwoWayConverter<VirtualMachineRequest, IaasEntities.VirtualMachineRequest> {

    @Nullable
    @Override
    public IaasEntities.VirtualMachineRequest apply(
            @Nullable VirtualMachineRequest virtualMachineRequest) {

        IaasEntities.VirtualMachineRequest.Builder builder = IaasEntities.VirtualMachineRequest.newBuilder();

        if (virtualMachineRequest.getHardware() != null) {
            builder.setHardware(virtualMachineRequest.getHardware());
        } else {
            builder.clearHardware();
        }
        if (virtualMachineRequest.getLocation() != null) {
            builder.setLocation(virtualMachineRequest.getLocation());
        } else {
            builder.clearLocation();
        }
        if (virtualMachineRequest.getImage() != null) {
            builder.setImage(virtualMachineRequest.getImage());
        } else {
            builder.clearImage();
        }

        return builder.build();
    }

    @Override
    public VirtualMachineRequest applyBack(IaasEntities.VirtualMachineRequest virtualMachineRequest) {
        VirtualMachineRequest result = new VirtualMachineRequest();
        result.setHardware(virtualMachineRequest.getHardware());
        result.setImage(virtualMachineRequest.getLocation());
        result.setLocation(virtualMachineRequest.getLocation());
        return result;
    }
}
