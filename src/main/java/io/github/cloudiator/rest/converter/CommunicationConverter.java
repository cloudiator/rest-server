package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Communication;
import org.cloudiator.messages.entities.JobEntities;

public class CommunicationConverter implements
    TwoWayConverter<Communication, JobEntities.Communication> {

  @Override
  public Communication applyBack(JobEntities.Communication communication) {
    Communication comm = new Communication();
    comm.setPortProvided(communication.getPortProvided());
    comm.setPortRequired(communication.getPortProvided());
    return comm;
  }

  @Override
  public JobEntities.Communication apply(Communication communication) {
    JobEntities.Communication.Builder result = JobEntities.Communication.newBuilder();
    result.setPortProvided(communication.getPortProvided())
        .setPortRequired(communication.getPortRequired());
    return result.build();
  }
}
