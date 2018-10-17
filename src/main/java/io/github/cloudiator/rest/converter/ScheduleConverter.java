package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.Schedule;
import io.github.cloudiator.rest.model.Schedule.InstantiationEnum;
import org.cloudiator.messages.entities.ProcessEntities;
import org.cloudiator.messages.entities.ProcessEntities.Process;

public class ScheduleConverter implements OneWayConverter<ProcessEntities.Schedule, Schedule> {

  private static final ProcessConverter PROCESS_CONVERTER = ProcessConverter.INSTANCE;

  @Override
  public Schedule apply(ProcessEntities.Schedule schedule) {

    Schedule result = new Schedule();
    result.setId(schedule.getId());
    result.setInstantiation(InstantiationEnum.fromValue(schedule.getInstantiation().name()));
    result.setJob(schedule.getJob());

    for (Process process : schedule.getProcessesList()) {
      result.addProcessesItem(PROCESS_CONVERTER.applyBack(process));
    }

    return result;
  }
}
