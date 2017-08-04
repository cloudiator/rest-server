package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Communication;
import org.cloudiator.messages.entities.ApplicationEntities;

public class CommunicationConverter implements TwoWayConverter<Communication, ApplicationEntities.Communication> {
    @Override
    public Communication applyBack(ApplicationEntities.Communication communication) {
        return null;
    }

    @Override
    public ApplicationEntities.Communication apply(Communication communication) {
        return null;
    }
}
