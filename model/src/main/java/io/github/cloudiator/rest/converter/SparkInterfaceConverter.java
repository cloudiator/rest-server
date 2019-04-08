package io.github.cloudiator.rest.converter;

import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.SparkInterface;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messages.entities.TaskEntities.SparkInterface.Builder;

public class SparkInterfaceConverter implements
    TwoWayConverter<SparkInterface, TaskEntities.SparkInterface> {

  private static final ProcessMappingConverter PROCESS_MAPPING_CONVERTER = new ProcessMappingConverter();

  @Override
  public SparkInterface applyBack(TaskEntities.SparkInterface sparkInterface) {
    SparkInterface ret = new SparkInterface();
    ret.setFile(sparkInterface.getFile());
    if (!Strings.isNullOrEmpty(sparkInterface.getClassName())) {
      ret.setClassName(sparkInterface.getClassName());
    }
    ret.setArguments(sparkInterface.getArgumentsList());
    ret.setSparkArguments(sparkInterface.getSparkArgumentsMap());
    ret.setSparkConfiguration(sparkInterface.getSparkConfigurationMap());
    ret.setProcessMapping(PROCESS_MAPPING_CONVERTER.applyBack(sparkInterface.getProcessMapping()));

    return ret;
  }

  @Override
  public TaskEntities.SparkInterface apply(SparkInterface sparkInterface) {

    Builder builder = TaskEntities.SparkInterface.newBuilder();
    builder.setFile(sparkInterface.getFile());
    if (sparkInterface.getClassName() != null) {
      builder.setClassName(sparkInterface.getClassName());
    }

    if (sparkInterface.getArguments() != null) {
      builder.addAllArguments(sparkInterface.getArguments());
    }

    if (sparkInterface.getSparkArguments() != null) {
      builder.putAllSparkArguments(sparkInterface.getSparkArguments());
    }

    if (sparkInterface.getSparkConfiguration() != null) {
      builder.putAllSparkConfiguration(sparkInterface.getSparkConfiguration());
    }

    builder.setProcessMapping(PROCESS_MAPPING_CONVERTER.apply(sparkInterface.getProcessMapping()));

    return builder.build();
  }
}
