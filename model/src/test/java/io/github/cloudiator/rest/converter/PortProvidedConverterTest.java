package io.github.cloudiator.rest.converter;

import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.PortProvided;
import org.cloudiator.messages.entities.TaskEntities;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class PortProvidedConverterTest {

  private final PortProvidedConverter portProvidedConverter = new PortProvidedConverter();
  private final PortProvided restPortProvided;
  private final TaskEntities.PortProvided iaasPortProvided;

  public PortProvidedConverterTest() {
    this.restPortProvided = new PortProvided().port(12345);
    this.restPortProvided.setName("TestName");
    this.restPortProvided.setType(this.restPortProvided.getClass().getSimpleName());
    this.iaasPortProvided = TaskEntities.PortProvided.newBuilder().setPort(12345)
        .setName("TestName").build();
  }

  @Test
  public void applyBack() throws Exception {
    //from iaas to rest
    PortProvided actual = portProvidedConverter.applyBack(iaasPortProvided);
    assertThat(actual, Matchers.is(Matchers.equalTo(restPortProvided)));
  }

  @Test
  public void apply() throws Exception {
    //from rest to iaas
    TaskEntities.PortProvided actual = portProvidedConverter.apply(restPortProvided);
    Assert.assertThat(actual, Matchers.is(Matchers.equalTo(iaasPortProvided)));
  }

}
