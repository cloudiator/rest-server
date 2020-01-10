package io.github.cloudiator.rest.converter;

import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.Distribution;
import io.github.cloudiator.rest.model.FixedDistribution;
import io.github.cloudiator.rest.model.NormalDistribution;
import io.github.cloudiator.rest.model.SimulationInterface;
import io.github.cloudiator.rest.model.StartTime;
import org.cloudiator.messages.entities.TaskEntities;

public class SimulationInterfaceConverter implements
    TwoWayConverter<SimulationInterface, TaskEntities.SimulationInterface> {

  private static final StartTimeConverter START_TIME_CONVERTER = new StartTimeConverter();

  @Override
  public TaskEntities.SimulationInterface apply(SimulationInterface simulationInterface) {
    return TaskEntities.SimulationInterface.newBuilder()
        .setStartTime(START_TIME_CONVERTER.apply(simulationInterface.getStartTime())).build();
  }

  @Override
  public SimulationInterface applyBack(TaskEntities.SimulationInterface simulationInterface) {
    return new SimulationInterface()
        .startTime(START_TIME_CONVERTER.applyBack(simulationInterface.getStartTime()));
  }

  private static class StartTimeConverter implements
      TwoWayConverter<StartTime, TaskEntities.StartTime> {

    private static final TimeUnitConverter TIME_UNIT_CONVERTER = new TimeUnitConverter();
    private static final DistributionConverter DISTRIBUTION_CONVERTER = new DistributionConverter();

    @Override
    public StartTime applyBack(TaskEntities.StartTime startTime) {
      StartTime rest = new StartTime();
      rest.setDistribution(DISTRIBUTION_CONVERTER.applyBack(startTime.getDistribution()));
      rest.setUnit(TIME_UNIT_CONVERTER.applyBack(startTime.getTimeUnit()));
      return rest;
    }

    @Override
    public TaskEntities.StartTime apply(StartTime startTime) {
      return TaskEntities.StartTime.newBuilder()
          .setDistribution(DISTRIBUTION_CONVERTER.apply(startTime.getDistribution()))
          .setTimeUnit(TIME_UNIT_CONVERTER.apply(startTime.getUnit())).build();
    }
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
      } else if (distribution instanceof FixedDistribution) {
        return TaskEntities.Distribution.newBuilder().setFixedDistribution(
            TaskEntities.FixedDistribution.newBuilder()
                .setValue(((FixedDistribution) distribution).getValue())
                .build()).build();
      } else {
        throw new AssertionError("Illegal distribution type" + distribution.getClass().getName());
      }
    }

    @Override
    public Distribution applyBack(TaskEntities.Distribution distribution) {
      switch (distribution.getDistributionCase()) {
        case NORMALDISTRIBUTION:
          return new NormalDistribution().mean(distribution.getNormalDistribution().getMean())
              .stdDev(distribution.getNormalDistribution().getStdDev())
              .type(NormalDistribution.class.getSimpleName());
        case FIXEDDISTRIBUTION:
          return new FixedDistribution().value(distribution.getFixedDistribution().getValue())
              .type(FixedDistribution.class.getSimpleName());
        case DISTRIBUTION_NOT_SET:
        default:
          throw new AssertionError(
              "Illegal distribution case " + distribution.getDistributionCase());
      }
    }
  }

}
