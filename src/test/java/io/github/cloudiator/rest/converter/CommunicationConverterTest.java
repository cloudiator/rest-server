package io.github.cloudiator.rest.converter;

import static org.junit.Assert.*;

import io.github.cloudiator.rest.model.Communication;
import org.cloudiator.messages.entities.JobEntities;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class CommunicationConverterTest {

  private final CommunicationConverter communicationConverter = new CommunicationConverter();
  private final Communication restComm;
  private final JobEntities.Communication iaasComm;

  public CommunicationConverterTest() {
    this.restComm = new Communication()
        .portProvided("providedPort")
        .portRequired("requiredPort");
    this.iaasComm = JobEntities.Communication.newBuilder()
        .setPortProvided("providedPort")
        .setPortRequired("requiredPort").build();
  }

  @Test
  public void applyBack() throws Exception {
    //from iaas to rest
    Communication result = communicationConverter.applyBack(iaasComm);

    assertThat(result, is(equalTo(restComm)));
  }

  @Test
  public void apply() throws Exception {
    // from rest to iaas

    JobEntities.Communication result = communicationConverter.apply(restComm);

    assertThat(result, is(equalTo(iaasComm)));
  }

}