package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.Communication;
import io.github.cloudiator.rest.model.JobNew;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.Task;
import org.cloudiator.messages.entities.JobEntities;

public class JobNewConverter implements OneWayConverter<JobNew, JobEntities.JobNew> {

  private final CommunicationConverter communicationConverter = new CommunicationConverter();
  private final TaskConverter taskConverter = new TaskConverter();
  private final RequirementConverter requirementConverter = new RequirementConverter();

  @Override
  public JobEntities.JobNew apply(JobNew jobNew) {
    JobEntities.JobNew.Builder result = JobEntities.JobNew.newBuilder()
        .setName(jobNew.getName());
    if (jobNew.getTasks() != null) {
      for (Task task : jobNew.getTasks()) {
        result.addTasks(taskConverter.apply(task));
      }
    }
    if (jobNew.getCommunications() != null) {
      for (Communication communication : jobNew.getCommunications()) {
        result.addCommunications(communicationConverter.apply(communication));
      }
    }
    if (jobNew.getRequirements() != null) {
      for (Requirement req : jobNew.getRequirements()) {
        result.addRequirements(requirementConverter.apply(req));
      }
    }

    return result.build();
  }
}
