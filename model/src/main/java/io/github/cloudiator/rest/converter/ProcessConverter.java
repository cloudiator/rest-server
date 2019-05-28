package io.github.cloudiator.rest.converter;


import com.google.common.base.Strings;
import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.CloudiatorProcess;
import io.github.cloudiator.rest.model.CloudiatorProcess.StateEnum;
import io.github.cloudiator.rest.model.ClusterProcess;
import io.github.cloudiator.rest.model.SingleProcess;
import java.util.stream.Collectors;
import org.cloudiator.messages.entities.ProcessEntities;
import org.cloudiator.messages.entities.ProcessEntities.NodeCluster;
import org.cloudiator.messages.entities.ProcessEntities.Process.Builder;
import org.cloudiator.messages.entities.ProcessEntities.ProcessState;
import org.cloudiator.messages.entities.ProcessEntities.ProcessType;

public class ProcessConverter implements
    TwoWayConverter<CloudiatorProcess, ProcessEntities.Process> {

  public final static ProcessConverter INSTANCE = new ProcessConverter();
  public final static ProcessStateConverter PROCESS_STATE_CONVERTER = new ProcessStateConverter();
  private static final String SINGLE_PROCESS_TYPE = "singleProcess";
  private static final String CLUSTER_PROCESS_TYPE = "clusterProcess";
  private static final IpAddressConverter IP_ADDRESS_CONVERTER = new IpAddressConverter();

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
        singleProcess.setOwner(process.getUserId());
        singleProcess.setState(PROCESS_STATE_CONVERTER.applyBack(process.getState()));
        singleProcess.setTaskInterface(process.getTaskInterface());
        singleProcess.setIpAddresses(
            process.getIpAddressesList().stream().map(IP_ADDRESS_CONVERTER::applyBack).collect(
                Collectors.toList()));

        if (!Strings.isNullOrEmpty(process.getOriginId())) {
          singleProcess.setOriginId(process.getOriginId());
        }

        if (!Strings.isNullOrEmpty(process.getReason())) {
          singleProcess.setReason(process.getReason());
        }

        if (!Strings.isNullOrEmpty(process.getDiagnostic())) {
          singleProcess.setDiagnostic(process.getDiagnostic());
        }

        if (!Strings.isNullOrEmpty(process.getEndpoint())) {
          singleProcess.setEndpoint(process.getEndpoint());
        }

        return singleProcess;
      case CLUSTER:
        ClusterProcess clusterProcess = new ClusterProcess();
        clusterProcess.setId(process.getId());
        clusterProcess.setProcessType(ClusterProcess.class.getSimpleName());
        clusterProcess.setNodes(process.getCluster().getNodesList());
        clusterProcess.setSchedule(process.getSchedule());
        clusterProcess.setTask(process.getTask());
        clusterProcess.setType(ProcessTypeConverter.INSTANCE.applyBack(process.getType()));
        clusterProcess.setOwner(process.getUserId());
        clusterProcess.setState(PROCESS_STATE_CONVERTER.applyBack(process.getState()));
        clusterProcess.setTaskInterface(process.getTaskInterface());
        clusterProcess.setIpAddresses(
            process.getIpAddressesList().stream().map(IP_ADDRESS_CONVERTER::applyBack).collect(
                Collectors.toList()));

        if (!Strings.isNullOrEmpty(process.getOriginId())) {
          clusterProcess.setOriginId(process.getOriginId());
        }

        if (!Strings.isNullOrEmpty(process.getReason())) {
          clusterProcess.setReason(process.getReason());
        }

        if (!Strings.isNullOrEmpty(process.getDiagnostic())) {
          clusterProcess.setDiagnostic(process.getDiagnostic());
        }

        if (!Strings.isNullOrEmpty(process.getEndpoint())) {
          clusterProcess.setEndpoint(process.getEndpoint());
        }

        return clusterProcess;
      case RUNSON_NOT_SET:
        throw new AssertionError(
            "RUNS_ON not set for process message with id: " + process.getId());
    }

    return null;
  }

  @Override
  public ProcessEntities.Process apply(CloudiatorProcess process) {

    Builder processBuilder = ProcessEntities.Process.newBuilder()
        .setId(process.getId())
        .setSchedule(process.getSchedule())
        .setTask(process.getTask())
        .setUserId(process.getOwner())
        .setType(ProcessTypeConverter.INSTANCE.apply(process.getType()))
        .setState(PROCESS_STATE_CONVERTER.apply(process.getState()))
        .setTaskInterface(process.getTaskInterface())
        .addAllIpAddresses(process.getIpAddresses().stream().map(IP_ADDRESS_CONVERTER)
            .collect(Collectors.toSet()));

    if (!Strings.isNullOrEmpty(process.getOriginId())) {
      processBuilder.setOriginId(process.getOriginId());
    }

    if (!Strings.isNullOrEmpty(process.getDiagnostic())) {
      processBuilder.setDiagnostic(process.getDiagnostic());
    }

    if (!Strings.isNullOrEmpty(process.getReason())) {
      processBuilder.setReason(process.getReason());
    }

    if (!Strings.isNullOrEmpty(process.getEndpoint())) {
      processBuilder.setEndpoint(process.getEndpoint());
    }

    if (process.getProcessType().equals(SingleProcess.class.getSimpleName())) {

      SingleProcess singleProcess = (SingleProcess) process;
      processBuilder.setNode(singleProcess.getNode());

    } else if (process.getProcessType().equals(ClusterProcess.class.getSimpleName())) {
      ClusterProcess clusterProcess = (ClusterProcess) process;
      processBuilder
          .setCluster(
              NodeCluster.newBuilder().addAllNodes(clusterProcess.getNodes()).build());
    } else {
      throw new AssertionError(
          "Unsupported CloudiatorProcess type: " + process.getProcessType().getClass()
              .getSimpleName());
    }

    return processBuilder.build();

  }

  private static class ProcessStateConverter implements
      TwoWayConverter<CloudiatorProcess.StateEnum, ProcessEntities.ProcessState> {

    @Override
    public StateEnum applyBack(ProcessState processState) {
      switch (processState) {
        case PROCESS_STATE_FINISHED:
          return StateEnum.FINISHED;
        case PROCESS_STATE_RUNNING:
          return StateEnum.RUNNING;
        case PROCESS_STATE_DELETED:
          return StateEnum.DELETED;
        case PROCESS_STATE_PENDING:
          return StateEnum.PENDING;
        case PROCESS_STATE_ERROR:
          return StateEnum.ERROR;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unknown process state " + processState);
      }
    }

    @Override
    public ProcessState apply(StateEnum stateEnum) {
      switch (stateEnum) {
        case PENDING:
          return ProcessState.PROCESS_STATE_PENDING;
        case ERROR:
          return ProcessState.PROCESS_STATE_ERROR;
        case DELETED:
          return ProcessState.PROCESS_STATE_DELETED;
        case RUNNING:
          return ProcessState.PROCESS_STATE_RUNNING;
        case FINISHED:
          return ProcessState.PROCESS_STATE_FINISHED;
        default:
          throw new AssertionError("Unknown process state " + stateEnum);
      }
    }
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
        case FAAS:
          return CloudiatorProcess.TypeEnum.FAAS;
        case SIMULATION:
          return CloudiatorProcess.TypeEnum.SIMULATION;
        case UNKNOWN:
          return CloudiatorProcess.TypeEnum.UNKNOWN;
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
        case FAAS:
          return ProcessType.FAAS;
        case SIMULATION:
          return ProcessType.SIMULATION;
        case UNKNOWN:
          return ProcessType.UNKNOWN;
        default:
          throw new AssertionError("Unknown typeEnum: " + typeEnum);
      }
    }
  }
}
