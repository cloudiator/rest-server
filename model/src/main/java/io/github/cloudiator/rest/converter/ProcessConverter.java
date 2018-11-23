package io.github.cloudiator.rest.converter;


import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Process;
import io.github.cloudiator.rest.model.Process.TypeEnum;
import org.cloudiator.messages.entities.ProcessEntities;
import org.cloudiator.messages.entities.ProcessEntities.ProcessType;

public class ProcessConverter implements TwoWayConverter<Process, ProcessEntities.Process> {

  public final static ProcessConverter INSTANCE = new ProcessConverter();
  NodeGroupConverter nodeGroupConverter = new NodeGroupConverter();

  private ProcessConverter() {
  }

  @Override
  public Process applyBack(ProcessEntities.Process process) {

    Process result = new Process();
    result.setId(process.getId());
    result.setNodeGroup(process.getNodeGroup());
    result.setSchedule(process.getSchedule());
    result.setTask(process.getTask());
    result.setType(ProcessTypeConverter.INSTANCE.applyBack(process.getType()));

    return result;
  }

  @Override
  public ProcessEntities.Process apply(Process process) {

    return  ProcessEntities.Process.newBuilder()
        .setId(process.getId())
        .setSchedule(process.getSchedule())
        .setTask(process.getTask())
        .setNodeGroup(process.getNodeGroup())
        .setType(ProcessTypeConverter.INSTANCE.apply(process.getType()))
     .build();


  }

  private static class ProcessTypeConverter implements
      TwoWayConverter<Process.TypeEnum, ProcessEntities.ProcessType> {

    private static final ProcessTypeConverter INSTANCE = new ProcessTypeConverter();

    @Override
    public TypeEnum applyBack(ProcessType processType) {
      switch (processType) {
        case LANCE:
          return TypeEnum.LANCE;
        case SPARK:
          return TypeEnum.SPARK;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unknown processType: " + processType);
      }
    }

    @Override
    public ProcessType apply(TypeEnum typeEnum) {
      switch (typeEnum) {
        case SPARK:
          return ProcessType.SPARK;
        case LANCE:
          return ProcessType.LANCE;
        default:
          throw new AssertionError("Unknown typeEnum: " + typeEnum);
      }
    }
  }
}
