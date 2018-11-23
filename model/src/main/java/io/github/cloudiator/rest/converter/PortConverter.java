package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Port;
import io.github.cloudiator.rest.model.PortProvided;
import io.github.cloudiator.rest.model.PortRequired;
import org.cloudiator.messages.entities.TaskEntities;

public class PortConverter implements TwoWayConverter<Port, TaskEntities.Port> {

  private final PortRequiredConverter requiredConverter = new PortRequiredConverter();
  private final PortProvidedConverter providedConverter = new PortProvidedConverter();

  @Override
  public Port applyBack(TaskEntities.Port port) {
    Port result;
    switch (port.getPortCase()) {

      case PORTPROVIDED:
        result = providedConverter.applyBack(port.getPortProvided());
        break;
      case PORTREQUIRED:
        result = requiredConverter.applyBack(port.getPortRequired());
        break;
      case PORT_NOT_SET:
      default:
        throw new AssertionError("PortCase is invalid: " + port.getPortCase());

    }
    return result;
  }

  @Override
  public TaskEntities.Port apply(Port port) {
    TaskEntities.Port.Builder result = TaskEntities.Port.newBuilder();

    if (port instanceof PortProvided) {
      result.setPortProvided(providedConverter.apply((PortProvided) port));
    } else if (port instanceof PortRequired) {
      result.setPortRequired(requiredConverter.apply((PortRequired) port));
    } else {
      throw new AssertionError("PortType not supported: " + port.getType());
    }
    /*
    String test = port.getType();
    switch (test) {
      case "PortProvided":
        result.setPortProvided(providedConverter.apply((PortProvided) port));
        break;
      case "PortRequired":
        result.setPortRequired(requiredConverter.apply((PortRequired) port));
        break;
      default:
        throw new AssertionError("PortType not supported: " + test);
    }
    */
    return result.build();
  }
}
