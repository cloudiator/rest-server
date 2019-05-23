package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.OneWayConverter;
import io.github.cloudiator.rest.model.CloudiatorProcessNew;
import io.github.cloudiator.rest.model.ClusterProcessNew;
import io.github.cloudiator.rest.model.SingleProcessNew;
import org.cloudiator.messages.entities.ProcessEntities;
import org.cloudiator.messages.entities.ProcessEntities.NodeCluster;
import org.cloudiator.messages.entities.ProcessEntities.ProcessNew;
import org.cloudiator.messages.entities.ProcessEntities.ProcessNew.Builder;

public class ProcessNewConverter implements
    OneWayConverter<CloudiatorProcessNew, ProcessEntities.ProcessNew> {

  public static final ProcessNewConverter INSTANCE = new ProcessNewConverter();

  private ProcessNewConverter() {
  }

  @Override
  public ProcessEntities.ProcessNew apply(CloudiatorProcessNew processNew) {

    final Builder builder = ProcessNew.newBuilder()
        .setSchedule(processNew.getSchedule())
        .setTask(processNew.getTask())
        .setTaskInterface(processNew.getTaskInterface());

    if (processNew instanceof SingleProcessNew) {
      builder.setNode(((SingleProcessNew) processNew).getNode());
    } else if (processNew instanceof ClusterProcessNew) {
      builder.setCluster(
          NodeCluster.newBuilder().addAllNodes(((ClusterProcessNew) processNew).getNodes())
              .build());
    } else {
      throw new AssertionError("Unknown type of process " + processNew.getClass().getName());
    }

    return builder.build();
  }
}
