package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.Schedule;
import io.github.cloudiator.rest.model.Schedule.InstantiationEnum;
import org.cloudiator.messages.entities.ProcessEntities;

public class ScheduleConverter implements OneWayConverter<ProcessEntities.Schedule, Schedule> {

  @Override
  public Schedule apply(ProcessEntities.Schedule schedule) {

    Schedule result = new Schedule();
    result.setId(schedule.getId());
    result.setInstantiation(InstantiationEnum.fromValue(schedule.getInstantiaton().name()));
    result.setJob(schedule.getJob());

    return result;
  }
}
