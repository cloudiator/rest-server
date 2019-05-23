package io.github.cloudiator.rest.converter;

import org.cloudiator.messages.entities.TaskEntities.HdfsInterface.Builder;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.HdfsInterface;
import org.cloudiator.messages.entities.TaskEntities;

/**
 * Created by Daniel Seybold on 06.05.2019.
 */
public class HdfsInterfaceConverter implements
    TwoWayConverter<HdfsInterface, TaskEntities.HdfsInterface> {

  private static final ProcessMappingConverter PROCESS_MAPPING_CONVERTER = new ProcessMappingConverter();

  @Override
  public HdfsInterface applyBack(TaskEntities.HdfsInterface hdfsInterface) {


    HdfsInterface result = new HdfsInterface();
    result.setProcessMapping(PROCESS_MAPPING_CONVERTER.applyBack(hdfsInterface.getProcessMapping()));
    return result;
  }

  @Override
  public TaskEntities.HdfsInterface apply(HdfsInterface hdfsInterface) {

    Builder builder = TaskEntities.HdfsInterface.newBuilder();
    builder.setProcessMapping(PROCESS_MAPPING_CONVERTER.apply(hdfsInterface.getProcessMapping()));

    return builder.build();
  }
}
