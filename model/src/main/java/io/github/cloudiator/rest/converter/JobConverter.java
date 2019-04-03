package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
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
  private final OptimizationConverter optimizationConverter = OptimizationConverter.INSTANCE;

  @Override
  public Job applyBack(JobEntities.Job job) {
    Job result = new Job()
        .name(job.getName()).id(job.getId()).owner(job.getUserId());
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

    if (job.hasOptimization()) {
      result.setOptimization(optimizationConverter.applyBack(job.getOptimization()));
    }

    return result;
  }

  @Override
  public JobEntities.Job apply(Job job) {
    JobEntities.Job.Builder result = JobEntities.Job.newBuilder()
        .setName(job.getName()).setId(job.getId()).setUserId(job.getOwner());
    if (job.getTasks() != null) {
      for (Task task : job.getTasks()) {
        result.addTasks(taskConverter.apply(task));
      }
    }
    if (job.getCommunications() != null) {
      for (Communication commu : job.getCommunications()) {
        result.addCommunications(communicationConverter.apply(commu));
      }
    }
    if (job.getRequirements() != null) {
      for (Requirement req : job.getRequirements()) {
        result.addRequirements(requirementConverter.apply(req));
      }
    }

    if (job.getOptimization() != null) {
      result.setOptimization(optimizationConverter.apply(job.getOptimization()));
    }

    return result.build();
  }
}
