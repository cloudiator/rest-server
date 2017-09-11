package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.PortRequired;
import org.cloudiator.messages.entities.TaskEntities;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class PortRequiredConverterTest {

    private final PortRequiredConverter portRequiredConverter = new PortRequiredConverter();
    private final PortRequired restPortRequired;
    private final TaskEntities.PortRequired iaasPortRequired;

    public PortRequiredConverterTest() {
        this.restPortRequired = new PortRequired()
                .isMandatory(true).updateAction("TestUpdateAction");
        this.restPortRequired.setName("TestName");
        this.restPortRequired.setType(this.restPortRequired.getClass().getSimpleName());
        this.iaasPortRequired = TaskEntities.PortRequired.newBuilder()
                .setIsMandatory(true).setUpdateAction("TestUpdateAction")
                .setName("TestName").build();
    }

    @Test
    public void applyBack() throws Exception {
        //from iaas to rest
        PortRequired actual = portRequiredConverter.applyBack(iaasPortRequired);
        assertThat(actual, is(equalTo(restPortRequired)));
    }

    @Test
    public void apply() throws Exception {
        //from rest to iaas
        TaskEntities.PortRequired actual = portRequiredConverter.apply(restPortRequired);
        assertThat(actual, is(equalTo(iaasPortRequired)));
    }

}