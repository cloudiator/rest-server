package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Communication;
import io.github.cloudiator.rest.model.Job;
import org.cloudiator.messages.entities.JobEntities;

public class JobConverter implements TwoWayConverter<Job, JobEntities.Job> {

  private final CommunicationConverter communicationConverter = new CommunicationConverter();

  @Override
  public Job applyBack(JobEntities.Job job) {
    return null;
  }

  @Override
  public JobEntities.Job apply(Job job) {
    return null;
  }
}
