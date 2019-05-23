package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Interval;
import io.github.cloudiator.rest.model.Interval.UnitEnum;
import org.cloudiator.messages.entities.CommonEntities;

public class IntervalConverter implements TwoWayConverter<Interval, CommonEntities.Interval> {

  private final UnitConverter unitConverter = new UnitConverter();


  @Override
  public Interval applyBack(CommonEntities.Interval kafkaInterval) {
    Interval result = new Interval()
        .period(kafkaInterval.getPeriod())
        .unit(unitConverter.applyBack(kafkaInterval.getUnit()));

    return result;
  }

  @Override
  public CommonEntities.Interval apply(Interval restInterval) {
    CommonEntities.Interval.Builder result = CommonEntities.Interval.newBuilder()
        .setPeriod(restInterval.getPeriod())
        .setUnit(unitConverter.apply(restInterval.getUnit()));

    return result.build();
  }


  private class UnitConverter implements TwoWayConverter<UnitEnum, CommonEntities.Unit> {

    @Override
    public UnitEnum applyBack(CommonEntities.Unit kafkaunit) {
      switch (kafkaunit) {
        case DAYS:
          return UnitEnum.DAYS;
        case HOURS:
          return UnitEnum.HOURS;
        case MINUTES:
          return UnitEnum.MINUTES;
        case SECONDS:
          return UnitEnum.SECONDS;
        case MILLISECONDS:
          return UnitEnum.MILLISECONDS;
        case MICROSECONDS:
          return UnitEnum.MICROSECONDS;
        case NANOSECONDS:
          return UnitEnum.NANOSECONDS;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("unkown UnitType " + kafkaunit);
      }
    }

    @Override
    public CommonEntities.Unit apply(UnitEnum restunitEnum) {
      switch (restunitEnum) {
        case DAYS:
          return CommonEntities.Unit.DAYS;
        case HOURS:
          return CommonEntities.Unit.HOURS;
        case MINUTES:
          return CommonEntities.Unit.MINUTES;
        case SECONDS:
          return CommonEntities.Unit.SECONDS;
        case MILLISECONDS:
          return CommonEntities.Unit.MILLISECONDS;
        case MICROSECONDS:
          return CommonEntities.Unit.MICROSECONDS;
        case NANOSECONDS:
          return CommonEntities.Unit.NANOSECONDS;
        default:
          throw new AssertionError("unkown UnitType " + restunitEnum);
      }

    }
  }
}


