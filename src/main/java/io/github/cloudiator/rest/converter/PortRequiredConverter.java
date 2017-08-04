package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.PortRequired;
import org.cloudiator.messages.entities.ComponentEntities;

public class PortRequiredConverter implements TwoWayConverter<PortRequired, ComponentEntities.PortRequired> {
    @Override
    public PortRequired applyBack(ComponentEntities.PortRequired portRequired) {
        PortRequired result = new PortRequired();
        result.setName(portRequired.getName());
        result.setIsMandatory(portRequired.getIsMandatory());
        result.setUpdateAction(portRequired.getUpdateAction());
        result.setType(result.getClass().getSimpleName());
        return result;
    }

    @Override
    public ComponentEntities.PortRequired apply(PortRequired portRequired) {
        ComponentEntities.PortRequired.Builder result = ComponentEntities.PortRequired.newBuilder();
        result.setName(portRequired.getName()).setIsMandatory(portRequired.getIsMandatory()).setUpdateAction(portRequired.getUpdateAction());
        return result.build();
    }
}
