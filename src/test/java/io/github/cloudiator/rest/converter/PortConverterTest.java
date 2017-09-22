package io.github.cloudiator.rest.converter;

import static org.junit.Assert.*;

import io.github.cloudiator.rest.model.Port;
import io.github.cloudiator.rest.model.PortProvided;
import io.github.cloudiator.rest.model.PortRequired;
import org.cloudiator.messages.entities.TaskEntities;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class PortConverterTest {

  private final PortConverter portConverter = new PortConverter();

  //ProvidedPort
  private final PortProvided restProvidedPort;
  private final TaskEntities.PortProvided iaasProvidedPort;
  private final TaskEntities.Port iaasProPort;
  //RequiredPort
  private final PortRequired restRequiredPort;
  private final TaskEntities.PortRequired iaasRequiredPort;
  private final TaskEntities.Port iaasRePort;

  public PortConverterTest() {
    this.restProvidedPort = new PortProvided()
        .port(12345);
    this.restProvidedPort.setType(restProvidedPort.getClass().getSimpleName());
    this.restProvidedPort.setName("PortProvidedTest");
    this.iaasProvidedPort = TaskEntities.PortProvided.newBuilder()
        .setPort(12345).setName("PortProvidedTest").build();
    this.iaasProPort = TaskEntities.Port.newBuilder().setPortProvided(iaasProvidedPort).build();
    this.restRequiredPort = new PortRequired()
        .isMandatory(true).updateAction("UpdateActionTest");
    this.restRequiredPort.setType(restRequiredPort.getClass().getSimpleName());
    this.restRequiredPort.setName("PortRequiredTest");
    this.iaasRequiredPort = TaskEntities.PortRequired.newBuilder()
        .setIsMandatory(true).setUpdateAction("UpdateActionTest")
        .setName("PortRequiredTest").build();
    this.iaasRePort = TaskEntities.Port.newBuilder().setPortRequired(iaasRequiredPort).build();
  }

  @Test
  public void applyBack() throws Exception {
    //form iaas to rest correct
    //Provided Port
    Port providedresult = portConverter.applyBack(iaasProPort);
    System.out.println("ConverterAusgabe Apply_Back := " + providedresult + "\n");
    System.out.println("RestProPort:= " + restProvidedPort + "\n");
    System.out.println("IaasProPort(Port):= " + iaasProPort + "\n");
    System.out.println("IaasProvidedPort:= " + iaasProvidedPort + "\n");

    assertThat(providedresult, is(equalTo(restProvidedPort)));

  }

  @Test
  public void apply() throws Exception {
    //from rest to iaas correct
    //Provided Port
    TaskEntities.Port result = portConverter.apply(restProvidedPort);

    System.out.println("Converterausgabe Apply := " + result);
    assertThat(result, is(equalTo(iaasProPort)));

  }

}