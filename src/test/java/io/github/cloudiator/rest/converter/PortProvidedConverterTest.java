package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.PortProvided;
import org.cloudiator.messages.entities.TaskEntities;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class PortProvidedConverterTest {

    private final PortProvidedConverter portProvidedConverter = new PortProvidedConverter();
    private final PortProvided restPortProvided;
    private final TaskEntities.PortProvided iaasPortProvided;

    public PortProvidedConverterTest() {
        this.restPortProvided = new PortProvided().port(12345);
        this.restPortProvided.setName("TestName");
        this.restPortProvided.setType(this.restPortProvided.getClass().getSimpleName());
        this.iaasPortProvided = TaskEntities.PortProvided.newBuilder().setPort(12345).setName("TestName").build();
    }

    @Test
    public void applyBack() throws Exception {
        //from iaas to rest
        PortProvided actual = portProvidedConverter.applyBack(iaasPortProvided);
        assertThat(actual, is(equalTo(restPortProvided)));
    }

    @Test
    public void apply() throws Exception {
        //from rest to iaas
        TaskEntities.PortProvided actual = portProvidedConverter.apply(restPortProvided);
        assertThat(actual, is(equalTo(iaasPortProvided)));
    }

}