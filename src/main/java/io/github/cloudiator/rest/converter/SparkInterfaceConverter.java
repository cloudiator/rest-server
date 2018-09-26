package io.github.cloudiator.rest.converter;

import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.SparkInterface;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messages.entities.TaskEntities.SparkInterface.Builder;

public class SparkInterfaceConverter implements
    TwoWayConverter<SparkInterface, TaskEntities.SparkInterface> {

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

    return ret;
  }

  @Override
  public TaskEntities.SparkInterface apply(SparkInterface sparkInterface) {

    Builder builder = TaskEntities.SparkInterface.newBuilder();
    builder.setFile(sparkInterface.getFile());
    if (sparkInterface.getClassName() != null) {
      builder.setClassName(sparkInterface.getClassName());
    }
    builder.addAllArguments(sparkInterface.getArguments())
        .putAllSparkArguments(sparkInterface.getSparkArguments())
        .putAllSparkConfiguration(sparkInterface.getSparkConfiguration());

    return builder.build();
  }
}
