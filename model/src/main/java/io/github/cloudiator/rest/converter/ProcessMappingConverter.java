package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import org.cloudiator.messages.entities.TaskEntities.ProcessMapping;

public class ProcessMappingConverter implements
    TwoWayConverter<io.github.cloudiator.rest.model.ProcessMapping, ProcessMapping> {

  @Override
  public io.github.cloudiator.rest.model.ProcessMapping applyBack(ProcessMapping processMapping) {

    switch (processMapping) {
      case SINGLE:
        return io.github.cloudiator.rest.model.ProcessMapping.SINGLE;
      case CLUSTER:
        return io.github.cloudiator.rest.model.ProcessMapping.CLUSTER;
      case UNRECOGNIZED:
      default:
        throw new AssertionError("Unknown process mapping " + processMapping);

    }
  }

  @Override
  public ProcessMapping apply(io.github.cloudiator.rest.model.ProcessMapping processMappingEnum) {
    switch (processMappingEnum) {
      case CLUSTER:
        return ProcessMapping.CLUSTER;
      case SINGLE:
        return ProcessMapping.SINGLE;
      default:
        throw new AssertionError("Unknown process mapping " + processMappingEnum);
    }
  }
}
