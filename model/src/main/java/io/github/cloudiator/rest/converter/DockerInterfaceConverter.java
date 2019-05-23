package io.github.cloudiator.rest.converter;

import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.DockerInterface;
import org.cloudiator.messages.entities.TaskEntities;

public class DockerInterfaceConverter implements
    TwoWayConverter<DockerInterface, TaskEntities.DockerInterface> {

  @Override
  public DockerInterface applyBack(TaskEntities.DockerInterface dockerInterface) {
    DockerInterface result = new DockerInterface()
        .dockerImage(dockerInterface.getDockerImage())
        .environment(dockerInterface.getEnvironmentMap());

    if (!Strings.isNullOrEmpty(dockerInterface.getPortUpdateAction())) {
      result.setUpdateAction(dockerInterface.getPortUpdateAction());
    }

    return result;
  }

  @Override
  public TaskEntities.DockerInterface apply(DockerInterface dockerInterface) {
    TaskEntities.DockerInterface.Builder result = TaskEntities.DockerInterface.newBuilder()
        .setDockerImage(dockerInterface.getDockerImage())
        .putAllEnvironment(dockerInterface.getEnvironment());

    if (!Strings.isNullOrEmpty(dockerInterface.getUpdateAction())) {
      result.setPortUpdateAction(dockerInterface.getUpdateAction());
    }

    return result.build();
  }
}
