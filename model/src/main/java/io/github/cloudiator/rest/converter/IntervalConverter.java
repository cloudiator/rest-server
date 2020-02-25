package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Interval;
import org.cloudiator.messages.entities.CommonEntities;

public class IntervalConverter implements TwoWayConverter<Interval, CommonEntities.Interval> {

  private final TimeUnitConverter unitConverter = new TimeUnitConverter();


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


}


