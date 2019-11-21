package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.FaasInterface;
import java.util.stream.Collectors;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messages.entities.TaskEntities.FaasInterface.Builder;

public class FaasInterfaceConverter implements
    TwoWayConverter<FaasInterface, TaskEntities.FaasInterface> {

  private static TriggerConverter triggerConverter = new TriggerConverter();

  @Override
  public FaasInterface applyBack(TaskEntities.FaasInterface taskInterface) {
    return new FaasInterface()
        .functionName(taskInterface.getFunctionName())
        .sourceCodeUrl(taskInterface.getSourceCodeUrl())
        .handler(taskInterface.getHandler())
        .timeout(taskInterface.getTimeout())
        .triggers(taskInterface
            .getTriggersList()
            .stream()
            .map(triggerConverter::applyBack)
            .collect(Collectors.toList()))
        .functionEnvironment(taskInterface.getFunctionEnvironmentMap());
  }

  @Override
  public TaskEntities.FaasInterface apply(FaasInterface faasInterface) {
    final Builder builder = TaskEntities.FaasInterface.newBuilder()
        .setFunctionName(faasInterface.getFunctionName())
        .setSourceCodeUrl(faasInterface.getSourceCodeUrl())
        .setHandler(faasInterface.getHandler())
        .setTimeout(faasInterface.getTimeout())
        .addAllTriggers(faasInterface
            .getTriggers()
            .stream()
            .map(triggerConverter)
            .collect(Collectors.toList()));

    if (faasInterface.getFunctionEnvironment() != null) {
      builder.putAllFunctionEnvironment(faasInterface.getFunctionEnvironment());
    }

    return builder.build();
  }
}
