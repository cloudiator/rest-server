package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Communication;
import org.cloudiator.messages.entities.ApplicationEntities;

public class CommunicationConverter implements TwoWayConverter<Communication, ApplicationEntities.Communication> {
    @Override
    public Communication applyBack(ApplicationEntities.Communication communication) {
        Communication comm = new Communication();
        comm.setPortProvided(communication.getPortProvided());
        comm.setPortRequired(communication.getPortProvided());
        return comm;
    }

    @Override
    public ApplicationEntities.Communication apply(Communication communication) {
        ApplicationEntities.Communication.Builder result = ApplicationEntities.Communication.newBuilder();
        result.setPortProvided(communication.getPortProvided()).setPortRequired(communication.getPortRequired());
        return result.build();
    }
}
