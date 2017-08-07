package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Application;
import io.github.cloudiator.rest.model.Communication;
import org.cloudiator.messages.entities.ApplicationEntities;

import java.util.ArrayList;
import java.util.List;

public class Applicationconverter implements TwoWayConverter<Application, ApplicationEntities.Application> {

    private final CommunicationConverter communicationConverter = new CommunicationConverter();

    @Override
    public Application applyBack(ApplicationEntities.Application application) {
        Application result = new Application();
        result.setName(application.getName());
        if (!application.getCommunicationsList().isEmpty()) {
            for (ApplicationEntities.Communication com : application.getCommunicationsList()) {
                result.addCommunicationsItem(communicationConverter.applyBack(com));
            }
        }
        if ((application.getComponentsList() != null) && (!application.getComponentsList().isEmpty())) {
            for (String item : application.getComponentsList()) {
                result.addComponentsItem(item);
            }
        }
        return result;
    }

    @Override
    public ApplicationEntities.Application apply(Application application) {
        ApplicationEntities.Application.Builder result = ApplicationEntities.Application.newBuilder();
        if ((application.getCommunications() != null) && (!application.getCommunications().isEmpty())) {
            for (Communication comm : application.getCommunications()) {
                result.addCommunications(communicationConverter.apply(comm));
            }
        } else {
            result.clearCommunications();
        }
        if ((application.getComponents() != null) && (!application.getComponents().isEmpty())) {
            for (String item : application.getComponents()) {
                result.addComponents(item);
            }
        } else {
            result.clearComponents();
        }
        return result.build();
    }
}
