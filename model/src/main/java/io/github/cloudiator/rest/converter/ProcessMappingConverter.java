package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.SparkInterface.ProcessMappingEnum;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messages.entities.TaskEntities.ProcessMapping;

public class ProcessMappingConverter implements
    TwoWayConverter<ProcessMappingEnum, TaskEntities.ProcessMapping> {

  @Override
  public ProcessMappingEnum applyBack(ProcessMapping processMapping) {

    switch (processMapping) {
      case SINGLE:
        return ProcessMappingEnum.SINGLE;
      case CLUSTER:
        return ProcessMappingEnum.CLUSTER;
      case UNRECOGNIZED:
      default:
        throw new AssertionError("Unknown process mapping " + processMapping);

    }
  }

  @Override
  public ProcessMapping apply(ProcessMappingEnum processMappingEnum) {
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
