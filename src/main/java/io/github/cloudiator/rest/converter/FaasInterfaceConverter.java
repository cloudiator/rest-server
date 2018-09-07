package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.FaasInterface;
import org.cloudiator.messages.entities.TaskEntities;

import java.util.stream.Collectors;

public class FaasInterfaceConverter implements
    TwoWayConverter<FaasInterface, TaskEntities.FaasInterface> {

  private static TriggerConverter triggerConverter = new TriggerConverter();

  @Override
  public FaasInterface applyBack(TaskEntities.FaasInterface taskInterface) {
    return new FaasInterface()
        .functionName(taskInterface.getFunctionName())
        .sourceCodeUrl(taskInterface.getSourceCodeUrl())
        .handler(taskInterface.getHandler())
        .runtime(taskInterface.getRuntime())
        .timeout(taskInterface.getTimeout())
        .memory(taskInterface.getMemory())
        .triggers(taskInterface
            .getTriggersList()
            .stream()
            .map(triggerConverter::applyBack)
            .collect(Collectors.toList()));
  }

  @Override
  public TaskEntities.FaasInterface apply(FaasInterface faasInterface) {
    return TaskEntities.FaasInterface.newBuilder()
        .setFunctionName(faasInterface.getFunctionName())
        .setSourceCodeUrl(faasInterface.getSourceCodeUrl())
        .setHandler(faasInterface.getHandler())
        .setRuntime(faasInterface.getRuntime())
        .setTimeout(faasInterface.getTimeout())
        .setMemory(faasInterface.getMemory())
        .addAllTriggers(faasInterface
            .getTriggers()
            .stream()
            .map(triggerConverter)
            .collect(Collectors.toList()))
        .build();
  }
}
