package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.ScheduleNew;
import org.cloudiator.messages.entities.ProcessEntities;
import org.cloudiator.messages.entities.ProcessEntities.Instantiation;

public class ScheduleNewConverter implements
    OneWayConverter<ScheduleNew, ProcessEntities.ScheduleNew> {

  @Override
  public ProcessEntities.ScheduleNew apply(ScheduleNew scheduleNew) {

    return ProcessEntities.ScheduleNew.newBuilder().setJob(scheduleNew.getJob())
        .setInstantiation(Instantiation.valueOf(scheduleNew.getInstantiation().name())).build();
  }
}
