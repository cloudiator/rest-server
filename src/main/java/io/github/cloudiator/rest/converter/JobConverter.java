package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.Communication;
import io.github.cloudiator.rest.model.Job;
import org.cloudiator.messages.entities.ApplicationEntities;

public class JobConverter implements TwoWayConverter<Job, ApplicationEntities.Job> {

    private final CommunicationConverter communicationConverter = new CommunicationConverter();

    @Override
    public Job applyBack(ApplicationEntities.Job job) {
        Job result = new Job();
        result.setName(job.getName());
        if ((job.getCommunicationsList() != null) && (!job.getCommunicationsList().isEmpty())) {
            for (ApplicationEntities.Communication comm : job.getCommunicationsList()) {
                result.addCommunicationsItem(communicationConverter.applyBack(comm));
            }
        }
        if ((job.getTasksList() != null) && (!job.getTasksList().isEmpty())) {
            for (String item : job.getTasksList()) {
                result.addTasksItem(item);
            }
        }
        return result;
    }

    @Override
    public ApplicationEntities.Job apply(Job job) {
        ApplicationEntities.Job.Builder result = ApplicationEntities.Job.newBuilder();
        result.setName(job.getName());
        if ((job.getCommunications() != null) && (!job.getCommunications().isEmpty())) {
            for (Communication comm : job.getCommunications()) {
                result.addCommunications(communicationConverter.apply(comm));
            }
        } else {
            result.clearCommunications();
        }
        if ((job.getTasks() != null) && (!job.getTasks().isEmpty())) {
            result.addAllTasks(job.getTasks());
        } else {
            result.clearTasks();
        }
        return result.build();
    }
}
