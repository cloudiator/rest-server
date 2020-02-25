package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.TimeUnit;
import org.cloudiator.messages.entities.CommonEntities.Unit;

class TimeUnitConverter implements TwoWayConverter<TimeUnit, Unit> {

  @Override
  public TimeUnit applyBack(Unit kafkaunit) {
    switch (kafkaunit) {
      case DAYS:
        return TimeUnit.DAYS;
      case HOURS:
        return TimeUnit.HOURS;
      case MINUTES:
        return TimeUnit.MINUTES;
      case SECONDS:
        return TimeUnit.SECONDS;
      case MILLISECONDS:
        return TimeUnit.MILLISECONDS;
      case MICROSECONDS:
        return TimeUnit.MICROSECONDS;
      case NANOSECONDS:
        return TimeUnit.NANOSECONDS;
      case UNRECOGNIZED:
      default:
        throw new AssertionError("unkown UnitType " + kafkaunit);
    }
  }

  @Override
  public Unit apply(TimeUnit restunitEnum) {
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
