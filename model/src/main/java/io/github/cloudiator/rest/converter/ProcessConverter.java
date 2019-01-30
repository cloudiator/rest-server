package io.github.cloudiator.rest.converter;


import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.CloudiatorProcess;
import io.github.cloudiator.rest.model.ClusterProcess;
import io.github.cloudiator.rest.model.SingleProcess;
import org.cloudiator.messages.entities.ProcessEntities;
import org.cloudiator.messages.entities.ProcessEntities.Process.Builder;
import org.cloudiator.messages.entities.ProcessEntities.ProcessType;

public class ProcessConverter implements
    TwoWayConverter<CloudiatorProcess, ProcessEntities.Process> {

  public final static ProcessConverter INSTANCE = new ProcessConverter();
  private static final String SINGLE_PROCESS_TYPE = "singleProcess";
  private static final String CLUSTER_PROCESS_TYPE = "clusterProcess";

  private ProcessConverter() {
  }

  @Override
  public CloudiatorProcess applyBack(ProcessEntities.Process process) {

    switch (process.getRunsOnCase()) {
      case NODE:
        SingleProcess singleProcess = new SingleProcess();
        singleProcess.setId(process.getId());
        singleProcess.setProcessType(SingleProcess.class.getSimpleName());
        singleProcess.setNode(process.getNode());
        singleProcess.setSchedule(process.getSchedule());
        singleProcess.setTask(process.getTask());
        singleProcess.setType(ProcessTypeConverter.INSTANCE.applyBack(process.getType()));
        return singleProcess;
      case NODEGROUP:
        ClusterProcess clusterProcess = new ClusterProcess();
        clusterProcess.setId(process.getId());
        clusterProcess.setProcessType(ClusterProcess.class.getSimpleName());
        clusterProcess.setNodeGroup(process.getNodeGroup());
        clusterProcess.setSchedule(process.getSchedule());
        clusterProcess.setTask(process.getTask());
        clusterProcess.setType(ProcessTypeConverter.INSTANCE.applyBack(process.getType()));
        return clusterProcess;
      case RUNSON_NOT_SET:
        throw new IllegalStateException(
            "RUNSON not set for process message with id: " + process.getId());
    }

    return null;
  }

  @Override
  public ProcessEntities.Process apply(CloudiatorProcess process) {

    Builder processBuilder = ProcessEntities.Process.newBuilder()
        .setId(process.getId())
        .setSchedule(process.getSchedule())
        .setTask(process.getTask())
        .setType(ProcessTypeConverter.INSTANCE.apply(process.getType()));

    if (process.getProcessType().equals(SingleProcess.class.getSimpleName())) {

      SingleProcess singleProcess = (SingleProcess) process;
      processBuilder.setNode(singleProcess.getNode());

    } else if (process.getProcessType().equals(ClusterProcess.class.getSimpleName())) {
      ClusterProcess clusterProcess = (ClusterProcess) process;
      processBuilder.setNodeGroup(clusterProcess.getNodeGroup());
    } else {
      throw new IllegalStateException(
          "Unsupported CloudiatorProcess type: " + process.getProcessType().getClass()
              .getSimpleName());
    }

    return processBuilder.build();

  }


  private static class ProcessTypeConverter implements
      TwoWayConverter<CloudiatorProcess.TypeEnum, ProcessEntities.ProcessType> {

    private static final ProcessTypeConverter INSTANCE = new ProcessTypeConverter();

    @Override
    public CloudiatorProcess.TypeEnum applyBack(ProcessType processType) {
      switch (processType) {
        case LANCE:
          return CloudiatorProcess.TypeEnum.LANCE;
        case SPARK:
          return CloudiatorProcess.TypeEnum.SPARK;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unknown processType: " + processType);
      }
    }

    @Override
    public ProcessType apply(CloudiatorProcess.TypeEnum typeEnum) {
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
