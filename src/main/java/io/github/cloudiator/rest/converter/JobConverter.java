package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Communication;
import io.github.cloudiator.rest.model.Job;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.Task;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.JobEntities;
import org.cloudiator.messages.entities.TaskEntities;

public class JobConverter implements TwoWayConverter<Job, JobEntities.Job> {

  private final CommunicationConverter communicationConverter = new CommunicationConverter();
  private final TaskConverter taskConverter = new TaskConverter();
  private final RequirementConverter requirementConverter = new RequirementConverter();

  @Override
  public Job applyBack(JobEntities.Job job) {
    Job result = new Job()
        .name(job.getName());
    if (!job.getTasksList().isEmpty()) {
      for (TaskEntities.Task task : job.getTasksList()) {
        result.addTasksItem(taskConverter.applyBack(task));
      }
    }
    if (!job.getCommunicationsList().isEmpty()) {
      for (JobEntities.Communication comm : job.getCommunicationsList()) {
        result.addCommunicationsItem(communicationConverter.applyBack(comm));
      }
    }
    if (!job.getRequirementsList().isEmpty()) {
      for (CommonEntities.Requirement req : job.getRequirementsList()) {
        result.addRequirementsItem(requirementConverter.applyBack(req));
      }
    }

    return result;
  }

  @Override
  public JobEntities.Job apply(Job job) {
    JobEntities.Job.Builder result = JobEntities.Job.newBuilder()
        .setName(job.getName());
    if (!job.getTasks().isEmpty()) {
      for (Task task : job.getTasks()) {
        result.addTasks(taskConverter.apply(task));
      }
    }
    if (!job.getCommunications().isEmpty()) {
      for (Communication commu : job.getCommunications()) {
        result.addCommunications(communicationConverter.apply(commu));
      }
    }
    if (!job.getRequirements().isEmpty()) {
      for (Requirement req : job.getRequirements()) {
        result.addRequirements(requirementConverter.apply(req));
      }
    }

    return result.build();
  }
}
