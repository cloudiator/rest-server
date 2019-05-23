package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.Schedule;
import io.github.cloudiator.rest.model.Schedule.InstantiationEnum;
import io.github.cloudiator.rest.model.Schedule.StateEnum;
import org.cloudiator.messages.entities.ProcessEntities;
import org.cloudiator.messages.entities.ProcessEntities.Process;
import org.cloudiator.messages.entities.ProcessEntities.ScheduleState;

public class ScheduleConverter implements OneWayConverter<ProcessEntities.Schedule, Schedule> {

  private static final ProcessConverter PROCESS_CONVERTER = ProcessConverter.INSTANCE;
  private static final ScheduleStateConverter SCHEDULE_STATE_CONVERTER = new ScheduleStateConverter();

  @Override
  public Schedule apply(ProcessEntities.Schedule schedule) {

    Schedule result = new Schedule();
    result.setId(schedule.getId());
    result.setOwner(schedule.getUserId());
    result.setInstantiation(InstantiationEnum.fromValue(schedule.getInstantiation().name()));
    result.setJob(schedule.getJob());
    result.setState(SCHEDULE_STATE_CONVERTER.apply(schedule.getState()));

    for (Process process : schedule.getProcessesList()) {
      result.addProcessesItem(PROCESS_CONVERTER.applyBack(process));
    }

    return result;
  }

  private static class ScheduleStateConverter implements
      OneWayConverter<ProcessEntities.ScheduleState, Schedule.StateEnum> {

    @Override
    public StateEnum apply(ScheduleState scheduleState) {

      switch (scheduleState) {
        case SCHEDULE_STATE_RUNNING:
          return StateEnum.RUNNING;
        case SCHEDULE_STATE_PENDING:
          return StateEnum.PENDING;
        case SCHEDULE_STATE_DELETED:
          return StateEnum.DELETED;
        case SCHEDULE_STATE_ERROR:
          return StateEnum.ERROR;
        case SCHEDULE_STATE_MANUAL:
          return StateEnum.MANUAL;
        case SCHEDULE_STATE_RESTORING:
          return StateEnum.RESTORING;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unknown schedule state " + scheduleState);
      }
    }
  }

}
