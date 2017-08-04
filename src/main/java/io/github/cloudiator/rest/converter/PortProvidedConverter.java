package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.PortProvided;
import org.cloudiator.messages.entities.ComponentEntities;

public class PortProvidedConverter implements TwoWayConverter<PortProvided, ComponentEntities.PortProvided>{
    @Override
    public PortProvided applyBack(ComponentEntities.PortProvided portProvided) {
        PortProvided result = new PortProvided();
        result.setName(portProvided.getName());
        result.setPort(portProvided.getPort());
        result.setType(result.getClass().getSimpleName());

        return result;
    }

    @Override
    public ComponentEntities.PortProvided apply(PortProvided portProvided) {

        ComponentEntities.PortProvided.Builder result = ComponentEntities.PortProvided.newBuilder();
        result.setName(portProvided.getName()).setPort(portProvided.getPort());

        return result.build();
    }
}
