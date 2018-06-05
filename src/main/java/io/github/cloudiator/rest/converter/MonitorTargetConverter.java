package io.github.cloudiator.rest.converter;


import io.github.cloudiator.rest.model.MonitoringTarget;
import io.github.cloudiator.rest.model.MonitoringTarget.TypeEnum;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.MonitorEntities.TargetType;

/**
 * Created by Daniel Seybold on 13.03.2018.
 */
public class MonitorTargetConverter implements
    TwoWayConverter<MonitoringTarget, MonitorEntities.MonitoringTarget> {

  private final MonitorTargetTypeConverter typeConverter = new MonitorTargetTypeConverter();


  @Override
  public MonitoringTarget applyBack(MonitorEntities.MonitoringTarget kafkaMonitorTarget) {
    MonitoringTarget restTarget = new MonitoringTarget()
        .identifier(kafkaMonitorTarget.getIdentifier())
        .type(typeConverter.applyBack(kafkaMonitorTarget.getType()));
    return restTarget;
  }

  @Override
  public MonitorEntities.MonitoringTarget apply(MonitoringTarget restMonitorTarget) {
    MonitorEntities.MonitoringTarget.Builder builder = MonitorEntities.MonitoringTarget.newBuilder()
        .setIdentifier(restMonitorTarget.getIdentifier())
        .setType(typeConverter.apply(restMonitorTarget.getType()));

    return builder.build();
  }

  private class MonitorTargetTypeConverter implements
      TwoWayConverter<TypeEnum, MonitorEntities.TargetType> {

    @Override
    public TypeEnum applyBack(TargetType kafkaTargetType) {

      switch (kafkaTargetType) {
        case JOB:
          return TypeEnum.JOB;
        case NODE:
          return TypeEnum.NODE;
        case TASK:
          return TypeEnum.TASK;
        case CLOUD:
          return TypeEnum.CLOUD;
        case PROCESS:
          return TypeEnum.PROCESS;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unkown TargetType: " + kafkaTargetType);
      }
    }

    @Override
    public TargetType apply(TypeEnum restTypeEnum) {

      switch (restTypeEnum) {
        case JOB:
          return TargetType.JOB;
        case NODE:
          return TargetType.NODE;
        case TASK:
          return TargetType.TASK;
        case CLOUD:
          return TargetType.CLOUD;
        case PROCESS:
          return TargetType.PROCESS;
        default:
          throw new AssertionError("Unkown TypeEnum: " + restTypeEnum);
      }
    }
  }

}