package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Interval;
import io.github.cloudiator.rest.model.Interval.UnitEnum;
import org.cloudiator.messages.entities.MonitorEntities;
import org.cloudiator.messages.entities.MonitorEntities.Unit;

public class IntervalConverter implements TwoWayConverter<Interval, MonitorEntities.Interval> {

  private final UnitConverter unitConverter = new UnitConverter();


  @Override
  public Interval applyBack(MonitorEntities.Interval kafkaInterval) {
    Interval result = new Interval()
        .period(kafkaInterval.getPeriod())
        .unit(unitConverter.applyBack(kafkaInterval.getUnit()));

    return result;
  }

  @Override
  public MonitorEntities.Interval apply(Interval restInterval) {
    MonitorEntities.Interval.Builder result = MonitorEntities.Interval.newBuilder()
        .setPeriod(restInterval.getPeriod())
        .setUnit(unitConverter.apply(restInterval.getUnit()));

    return result.build();
  }


  private class UnitConverter implements TwoWayConverter<UnitEnum, MonitorEntities.Unit> {

    @Override
    public UnitEnum applyBack(Unit kafkaunit) {
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
    public Unit apply(UnitEnum restunitEnum) {
      switch (restunitEnum) {
        case DAYS:
          return Unit.DAYS;
        case HOURS:
          return Unit.HOURS;
        case MINUTES:
          return Unit.MINUTES;
        case SECONDS:
          return Unit.SECONDS;
        case MILLISECONDS:
          return Unit.MILLISECONDS;
        case MICROSECONDS:
          return Unit.MICROSECONDS;
        case NANOSECONDS:
          return Unit.NANOSECONDS;
        default:
          throw new AssertionError("unkown UnitType " + restunitEnum);
      }

    }
  }
}


