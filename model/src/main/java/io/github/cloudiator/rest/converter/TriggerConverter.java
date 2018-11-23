package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.HttpTrigger;
import io.github.cloudiator.rest.model.Trigger;
import org.cloudiator.messages.entities.TaskEntities;

public class TriggerConverter implements TwoWayConverter<Trigger, TaskEntities.Trigger> {

  private static HttpTriggerConverter httpTriggerConverter = new HttpTriggerConverter();

  private static class HttpTriggerConverter implements TwoWayConverter<HttpTrigger, TaskEntities.HttpTrigger> {

    @Override
    public HttpTrigger applyBack(TaskEntities.HttpTrigger httpTrigger) {
      return new HttpTrigger()
          .httpMethod(httpTrigger.getHttpMethod())
          .httpPath(httpTrigger.getHttpPath());
    }

    @Override
    public TaskEntities.HttpTrigger apply(HttpTrigger httpTrigger) {
      return TaskEntities.HttpTrigger.newBuilder()
          .setHttpMethod(httpTrigger.getHttpMethod())
          .setHttpPath(httpTrigger.getHttpPath())
          .build();
    }
  }


  @Override
  public Trigger applyBack(TaskEntities.Trigger trigger) {
    Trigger result;
    switch (trigger.getTriggerCase()) {
      case HTTPTRIGGER:
        result = httpTriggerConverter.applyBack(trigger.getHttpTrigger());
        result.setType(trigger.getHttpTrigger().getClass().getSimpleName());
        break;
      case TRIGGER_NOT_SET:
      default:
        throw new AssertionError(
            "Trigger not set or unknown: " + trigger.getTriggerCase());
    }

    return result;
  }

  @Override
  public TaskEntities.Trigger apply(Trigger trigger) {
    TaskEntities.Trigger.Builder result = TaskEntities.Trigger.newBuilder();

    if (trigger instanceof HttpTrigger) {
      result.setHttpTrigger(httpTriggerConverter.apply((HttpTrigger) trigger));
    } else {
      throw new AssertionError(
          "Trigger not known: " + trigger.getClass().getSimpleName());
    }

    return result.build();
  }
}
