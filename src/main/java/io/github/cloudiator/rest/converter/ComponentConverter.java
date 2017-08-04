package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Component;
import org.cloudiator.messages.entities.ComponentEntities;

public class ComponentConverter implements TwoWayConverter<Component, ComponentEntities.Component> {
    @Override
    public Component applyBack(ComponentEntities.Component component) {
        Component result = new Component();


        return null;
    }

    @Override
    public ComponentEntities.Component apply(Component component) {
        return null;
    }
}
