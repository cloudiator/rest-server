package io.github.cloudiator.rest.converter;

import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.SparkInterface;
import io.github.cloudiator.rest.model.SparkInterface.DeployModeEnum;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messages.entities.TaskEntities.DeployMode;
import org.cloudiator.messages.entities.TaskEntities.SparkInterface.Builder;

public class SparkInterfaceConverter implements
    TwoWayConverter<SparkInterface, TaskEntities.SparkInterface> {

  private static final DeployModeConverter DEPLOY_MODE_CONVERTER = new DeployModeConverter();

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

    ret.setDeployMode(DEPLOY_MODE_CONVERTER.applyBack(sparkInterface.getDeployMode()));

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

    builder.setDeployMode(DEPLOY_MODE_CONVERTER.apply(sparkInterface.getDeployMode()));


    return builder.build();
  }


  private static class DeployModeConverter implements
      TwoWayConverter<DeployModeEnum, TaskEntities.DeployMode> {


    @Override
    public DeployModeEnum applyBack(DeployMode deployMode) {
      switch (deployMode) {

        case SCALE:
          return DeployModeEnum.SCALE;
        case SUBMIT:
          return DeployModeEnum.SUBMIT;
        case SCALE_SUBMIT:
          return DeployModeEnum.SCALE_SUBMIT;
        default:
          throw new AssertionError("Unrecognized deploy mode on message side: " + deployMode);
      }
    }

    @Override
    public DeployMode apply(DeployModeEnum deployModeEnum) {
      switch (deployModeEnum) {
        case SCALE:
          return DeployMode.SCALE;
        case SUBMIT:
          return DeployMode.SUBMIT;
        case SCALE_SUBMIT:
          return DeployMode.SCALE_SUBMIT;
        default:
          throw new AssertionError("Unrecognized deploy mode on REST side: " + deployModeEnum);
      }
    }
  }
}
