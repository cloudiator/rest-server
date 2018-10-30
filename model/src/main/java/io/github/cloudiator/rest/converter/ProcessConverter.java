package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Process;
import org.cloudiator.messages.entities.ProcessEntities;

public class ProcessConverter implements TwoWayConverter<Process, ProcessEntities.Process> {

  public final static ProcessConverter INSTANCE = new ProcessConverter();

  private ProcessConverter() {
  }

  @Override
  public Process applyBack(ProcessEntities.Process process) {

    Process result = new Process();
    result.setId(process.getId());
    result.setNode(process.getNode());
    result.setSchedule(process.getSchedule());
    result.setTask(process.getTask());

    return result;
  }

  @Override
  public ProcessEntities.Process apply(Process process) {

    return ProcessEntities.Process.newBuilder().setId(process.getId()).setNode(process.getNode())
        .setSchedule(process.getSchedule()).setTask(process.getTask()).build();
  }
}
