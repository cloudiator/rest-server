package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Distribution;
import io.github.cloudiator.rest.model.NormalDistribution;
import io.github.cloudiator.rest.model.SimulationInterface;
import org.cloudiator.messages.entities.TaskEntities;

public class SimulationInterfaceConverter implements
    TwoWayConverter<SimulationInterface, TaskEntities.SimulationInterface> {

  private static final DistributionConverter DISTRIBUTION_CONVERTER = new DistributionConverter();

  @Override
  public TaskEntities.SimulationInterface apply(SimulationInterface simulationInterface) {
    return TaskEntities.SimulationInterface.newBuilder()
        .setStartTime(DISTRIBUTION_CONVERTER.apply(simulationInterface.getStartTime())).build();
  }

  @Override
  public SimulationInterface applyBack(TaskEntities.SimulationInterface simulationInterface) {
    return new SimulationInterface()
        .startTime(DISTRIBUTION_CONVERTER.applyBack(simulationInterface.getStartTime()));
  }

  private static class DistributionConverter implements
      TwoWayConverter<Distribution, TaskEntities.Distribution> {

    @Override
    public TaskEntities.Distribution apply(Distribution distribution) {
      if (distribution instanceof NormalDistribution) {
        return TaskEntities.Distribution.newBuilder().setNormalDistribution(
            TaskEntities.NormalDistribution.newBuilder()
                .setMean(((NormalDistribution) distribution).getMean())
                .setStdDev(((NormalDistribution) distribution).getStdDev()).build()).build();
      } else {
        throw new AssertionError("Illegal distribution type" + distribution.getClass().getName());
      }
    }

    @Override
    public Distribution applyBack(TaskEntities.Distribution distribution) {
      switch (distribution.getDistributionCase()) {
        case NORMALDISTRIBUTION:
          return new NormalDistribution().mean(distribution.getNormalDistribution().getMean())
              .stdDev(distribution.getNormalDistribution().getStdDev());
        case DISTRIBUTION_NOT_SET:
        default:
          throw new AssertionError(
              "Illegal distribution case " + distribution.getDistributionCase());
      }
    }
  }

}
