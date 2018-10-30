package io.github.cloudiator.rest.converter;

import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.PortRequired;
import org.cloudiator.messages.entities.TaskEntities;

public class PortRequiredConverter implements
    TwoWayConverter<PortRequired, TaskEntities.PortRequired> {

  @Override
  public PortRequired applyBack(TaskEntities.PortRequired portRequired) {
    PortRequired result = new PortRequired();
    result.setName(portRequired.getName());
    result.setIsMandatory(portRequired.getIsMandatory());

    if (!Strings.isNullOrEmpty(portRequired.getUpdateAction())) {
      result.setUpdateAction(portRequired.getUpdateAction());
    }

    result.setType(result.getClass().getSimpleName());
    return result;
  }

  @Override
  public TaskEntities.PortRequired apply(PortRequired portRequired) {
    TaskEntities.PortRequired.Builder result = TaskEntities.PortRequired.newBuilder();
    result.setName(portRequired.getName()).setIsMandatory(portRequired.isIsMandatory());

    if(portRequired.getUpdateAction() != null) {
      result.setUpdateAction(portRequired.getUpdateAction());
    }

    return result.build();
  }
}
