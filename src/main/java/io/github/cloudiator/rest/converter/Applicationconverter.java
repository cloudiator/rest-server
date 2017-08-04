package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Application;
import org.cloudiator.messages.entities.ApplicationEntities;

public class Applicationconverter implements TwoWayConverter<Application, ApplicationEntities.Application> {

    @Override
    public Application applyBack(ApplicationEntities.Application application) {
        return null;
    }

    @Override
    public ApplicationEntities.Application apply(Application application) {
        return null;
    }
}
