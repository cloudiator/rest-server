package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.DockerInterface;
import org.cloudiator.messages.entities.TaskEntities;

public class DockerInterfaceConverter implements
    TwoWayConverter<DockerInterface, TaskEntities.DockerInterface> {

  @Override
  public DockerInterface applyBack(TaskEntities.DockerInterface dockerInterface) {
    DockerInterface result = new DockerInterface()
        .dockerImage(dockerInterface.getDockerImage());
    return result;
  }

  @Override
  public TaskEntities.DockerInterface apply(DockerInterface dockerInterface) {
    TaskEntities.DockerInterface.Builder result = TaskEntities.DockerInterface.newBuilder()
        .setDockerImage(dockerInterface.getDockerImage());
    return result.build();
  }
}
