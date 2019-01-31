package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.ProcessGroup;
import org.cloudiator.messages.entities.ProcessEntities;
import org.cloudiator.messages.entities.ProcessEntities.ProcessGroup.Builder;

/**
 * Created by Daniel Seybold on 22.11.2018.
 */
public class ProcessGroupConverter implements
    TwoWayConverter<ProcessGroup, ProcessEntities.ProcessGroup> {

  private final ProcessConverter processConverter = ProcessConverter.INSTANCE;

  @Override
  public ProcessGroup applyBack(ProcessEntities.ProcessGroup processGroup) {

    ProcessGroup rest = new ProcessGroup();
    rest.setId(processGroup.getId());
    rest.setOwner(processGroup.getUserId());
    rest.schedule(processGroup.getScheduleId());

    processGroup.getProcessList().stream().map(processConverter::applyBack)
        .forEach(rest::addProcessesItem);

    return rest;
  }

  @Override
  public ProcessEntities.ProcessGroup apply(ProcessGroup processGroup) {

    final Builder builder = ProcessEntities.ProcessGroup.newBuilder();
    builder.setId(processGroup.getId());
    builder.setUserId(processGroup.getOwner());
    builder.setScheduleId(processGroup.getSchedule());

    processGroup.getProcesses().stream().map(processConverter).forEach(builder::addProcess);

    return builder.build();
  }
}
