package io.github.cloudiator.rest.converter;

import io.github.cloudiator.rest.model.AttributeOptimization;
import io.github.cloudiator.rest.model.AttributeOptimization.AggregationEnum;
import io.github.cloudiator.rest.model.OCLOptimization;
import io.github.cloudiator.rest.model.Optimization;
import io.github.cloudiator.rest.model.Optimization.ObjectiveEnum;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.CommonEntities.Aggregation;
import org.cloudiator.messages.entities.CommonEntities.Objective;
import org.cloudiator.messages.entities.CommonEntities.Optimization.Builder;

public class OptimizationConverter implements
    TwoWayConverter<Optimization, CommonEntities.Optimization> {

  public static final OptimizationConverter INSTANCE = new OptimizationConverter();

  private static final OCLOptimizationConverter OCL_OPTIMIZATION_CONVERTER = new OCLOptimizationConverter();
  private static final AttributeOptimizationConverter ATTRIBUTE_OPTIMIZATION_CONVERTER = new AttributeOptimizationConverter();
  private static final ObjectiveConverter OBJECTIVE_CONVERTER = new ObjectiveConverter();

  private OptimizationConverter() {

  }

  @Override
  public Optimization applyBack(CommonEntities.Optimization optimization) {

    switch (optimization.getOptimizationCase()) {
      case OCLOPTIMIZATION:
        return OCL_OPTIMIZATION_CONVERTER
            .applyBack(optimization.getOclOptimization(), optimization.getObjective());
      case ATTRIBUTEOPTIMIZATION:
        return ATTRIBUTE_OPTIMIZATION_CONVERTER
            .applyBack(optimization.getAttributeOptimization(), optimization.getObjective());
      case OPTIMIZATION_NOT_SET:
        throw new AssertionError("Optimization type not set.");
      default:
        throw new AssertionError("Unknown optimization type " + optimization.getOptimizationCase());
    }
  }

  @Override
  public CommonEntities.Optimization apply(Optimization optimization) {

    final Builder builder = CommonEntities.Optimization.newBuilder();
    builder.setObjective(OBJECTIVE_CONVERTER.applyBack(optimization.getObjective()));
    if (optimization instanceof AttributeOptimization) {
      builder.setAttributeOptimization(
          ATTRIBUTE_OPTIMIZATION_CONVERTER.apply((AttributeOptimization) optimization)).build();
    } else if (optimization instanceof OCLOptimization) {
      builder
          .setOclOptimization(OCL_OPTIMIZATION_CONVERTER.apply(
              (OCLOptimization) optimization)).build();
    } else {
      throw new AssertionError("Unknown optimization type " + optimization.getClass().getName());
    }
    return builder.build();
  }

  private static class OCLOptimizationConverter {


    public OCLOptimization applyBack(CommonEntities.OCLOptimization oclOptimization,
        CommonEntities.Objective objective) {

      final OCLOptimization result = new OCLOptimization();
      result.setExpression(oclOptimization.getExpression());
      result.setObjective(OBJECTIVE_CONVERTER.apply(objective));
      return result;
    }

    public CommonEntities.OCLOptimization apply(OCLOptimization oclOptimization) {
      return CommonEntities.OCLOptimization.newBuilder()
          .setExpression(oclOptimization.getExpression())
          .build();
    }
  }

  private static class AttributeOptimizationConverter {

    private static final AggregationConverter AGGREGATION_CONVERTER = new AggregationConverter();

    public AttributeOptimization applyBack(
        CommonEntities.AttributeOptimization attributeOptimization,
        CommonEntities.Objective objective) {

      final AttributeOptimization result = new AttributeOptimization();
      result.setAggregation(AGGREGATION_CONVERTER.apply(attributeOptimization.getAggregation()));
      result.setObjectiveAttribute(attributeOptimization.getObjectiveAttribute());
      result.setObjectiveClass(attributeOptimization.getObjectiveClass());
      result.setObjective(OBJECTIVE_CONVERTER.apply(objective));
      return result;
    }

    public CommonEntities.AttributeOptimization apply(AttributeOptimization attributeOptimization) {

      return CommonEntities.AttributeOptimization.newBuilder()
          .setAggregation(AGGREGATION_CONVERTER.applyBack(attributeOptimization.getAggregation()))
          .setObjectiveAttribute(attributeOptimization.getObjectiveAttribute())
          .setObjectiveClass(attributeOptimization.getObjectiveClass())
          .build();
    }
  }

  private static class AggregationConverter implements
      TwoWayConverter<CommonEntities.Aggregation, AggregationEnum> {

    @Override
    public Aggregation applyBack(AggregationEnum aggregationEnum) {
      switch (aggregationEnum) {
        case AVG:
          return Aggregation.AVG;
        case SUM:
          return Aggregation.SUM;
        default:
          throw new AssertionError("Unknown aggregation type " + aggregationEnum);
      }
    }

    @Override
    public AggregationEnum apply(Aggregation aggregation) {
      switch (aggregation) {
        case SUM:
          return AggregationEnum.SUM;
        case AVG:
          return AggregationEnum.AVG;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unknown aggregation type " + aggregation);
      }
    }
  }

  private static class ObjectiveConverter implements
      TwoWayConverter<CommonEntities.Objective, ObjectiveEnum> {

    @Override
    public Objective applyBack(ObjectiveEnum objectiveEnum) {
      switch (objectiveEnum) {
        case MAXIMIZE:
          return Objective.MAXIMIZE;
        case MINIMIZE:
          return Objective.MINIMIZE;
        default:
          throw new AssertionError("Unknown objective type " + objectiveEnum);
      }
    }

    @Override
    public ObjectiveEnum apply(Objective objective) {
      switch (objective) {
        case MINIMIZE:
          return ObjectiveEnum.MINIMIZE;
        case MAXIMIZE:
          return ObjectiveEnum.MAXIMIZE;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unknown objective type " + objective);
      }
    }
  }

}
