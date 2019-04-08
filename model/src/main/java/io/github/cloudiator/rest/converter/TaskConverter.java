package io.github.cloudiator.rest.converter;


import de.uniulm.omi.cloudiator.util.TwoWayConverter;
import io.github.cloudiator.rest.model.PeriodicBehaviour;
import io.github.cloudiator.rest.model.PeriodicBehaviour.CollisionHandlingEnum;
import io.github.cloudiator.rest.model.Port;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.ServiceBehaviour;
import io.github.cloudiator.rest.model.Task;
import io.github.cloudiator.rest.model.TaskInterface;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messages.entities.TaskEntities.CollisionHandling;


public class TaskConverter implements TwoWayConverter<Task, TaskEntities.Task> {

  private static final PortConverter PORT_CONVERTER = new PortConverter();
  private static final RequirementConverter REQUIREMENT_CONVERTER = new RequirementConverter();
  private static final TaskInterfaceConverter INTERFACE_CONVERTER = new TaskInterfaceConverter();
  private static final OptimizationConverter OPTIMIZATION_CONVERTER = OptimizationConverter.INSTANCE;
  private static final ServiceBehaviourConverter SERVICE_BEHAVIOUR_CONVERTER = new ServiceBehaviourConverter();
  private static final PeriodicBehaviourConverter PERIODIC_BEHAVIOUR_CONVERTER = new PeriodicBehaviourConverter();

  @Override
  public Task applyBack(TaskEntities.Task task) {
    Task result = new Task()
        .name(task.getName());

    for (TaskEntities.Port port : task.getPortsList()) {
      result.addPortsItem(PORT_CONVERTER.applyBack(port));
    }

    for (CommonEntities.Requirement req : task.getRequirementsList()) {
      result.addRequirementsItem(REQUIREMENT_CONVERTER.applyBack(req));
    }

    if (task.hasOptimization()) {
      result.setOptimization(OPTIMIZATION_CONVERTER.applyBack(task.getOptimization()));
    }

    for (TaskEntities.TaskInterface tInterface : task.getInterfacesList()) {
      result.addInterfacesItem(INTERFACE_CONVERTER.applyBack(tInterface));
    }

    switch (task.getBehaviourCase()) {
      case SERVICE:
        result.setBehaviour(SERVICE_BEHAVIOUR_CONVERTER.applyBack(task.getService()));
      case PERIODIC:
        result.setBehaviour(PERIODIC_BEHAVIOUR_CONVERTER.applyBack(task.getPeriodic()));
      case BEHAVIOUR_NOT_SET:
        throw new IllegalStateException("Behaviour case not set.");
    }

    return result;
  }

  @Override
  public TaskEntities.Task apply(Task task) {
    TaskEntities.Task.Builder result = TaskEntities.Task.newBuilder()
        .setName(task.getName());

    if (task.getPorts() != null) {
      for (Port port : task.getPorts()) {
        result.addPorts(PORT_CONVERTER.apply(port));
      }
    }

    if (task.getRequirements() != null) {
      for (Requirement req : task.getRequirements()) {
        result.addRequirements(REQUIREMENT_CONVERTER.apply(req));
      }
    }

    if (task.getOptimization() != null) {
      result.setOptimization(OPTIMIZATION_CONVERTER.apply(task.getOptimization()));
    }

    for (TaskInterface tInterface : task.getInterfaces()) {
      result.addInterfaces(INTERFACE_CONVERTER.apply(tInterface));
    }

    if (task.getBehaviour() instanceof PeriodicBehaviour) {
      result
          .setPeriodic(PERIODIC_BEHAVIOUR_CONVERTER.apply((PeriodicBehaviour) task.getBehaviour()));
    } else if (task.getBehaviour() instanceof ServiceBehaviour) {
      result.setService(SERVICE_BEHAVIOUR_CONVERTER.apply((ServiceBehaviour) task.getBehaviour()));
    } else {
      throw new AssertionError(
          "Illegal behaviour type " + task.getBehaviour().getClass().getName());
    }

    return result.build();
  }

  private static class ServiceBehaviourConverter implements
      TwoWayConverter<ServiceBehaviour, TaskEntities.ServiceBehaviour> {

    @Override
    public ServiceBehaviour applyBack(TaskEntities.ServiceBehaviour serviceBehaviour) {
      return new ServiceBehaviour().restart(serviceBehaviour.getRestart());
    }

    @Override
    public TaskEntities.ServiceBehaviour apply(ServiceBehaviour serviceBehaviour) {
      return TaskEntities.ServiceBehaviour.newBuilder().setRestart(serviceBehaviour.isRestart())
          .build();
    }
  }

  private static class PeriodicBehaviourConverter implements
      TwoWayConverter<PeriodicBehaviour, TaskEntities.PeriodicBehaviour> {

    private static final IntervalConverter INTERVAL_CONVERTER = new IntervalConverter();
    private static final CollisionHandlingConverter COLLISION_HANDLING_CONVERTER = new CollisionHandlingConverter();

    @Override
    public PeriodicBehaviour applyBack(TaskEntities.PeriodicBehaviour periodicBehaviour) {

      return new PeriodicBehaviour().collisionHandling(
          COLLISION_HANDLING_CONVERTER.applyBack(periodicBehaviour.getCollisionHandling()))
          .interval(INTERVAL_CONVERTER.applyBack(periodicBehaviour.getInterval()));
    }

    @Override
    public TaskEntities.PeriodicBehaviour apply(PeriodicBehaviour periodicBehaviour) {
      return TaskEntities.PeriodicBehaviour.newBuilder().setCollisionHandling(
          COLLISION_HANDLING_CONVERTER.apply(periodicBehaviour.getCollisionHandling()))
          .setInterval(INTERVAL_CONVERTER.apply(periodicBehaviour.getInterval())).build();
    }
  }

  private static class CollisionHandlingConverter implements
      TwoWayConverter<CollisionHandlingEnum, TaskEntities.CollisionHandling> {

    @Override
    public CollisionHandlingEnum applyBack(CollisionHandling collisionHandling) {
      switch (collisionHandling) {
        case SKIP:
          return CollisionHandlingEnum.SKIP;
        case CANCEL:
          return CollisionHandlingEnum.CANCEL;
        case PARALLEL:
          return CollisionHandlingEnum.PARALLEL;
        case UNRECOGNIZED:
        default:
          throw new AssertionError("Unknown collision handling " + collisionHandling);
      }
    }

    @Override
    public CollisionHandling apply(CollisionHandlingEnum collisionHandlingEnum) {
      switch (collisionHandlingEnum) {
        case PARALLEL:
          return CollisionHandling.PARALLEL;
        case CANCEL:
          return CollisionHandling.CANCEL;
        case SKIP:
          return CollisionHandling.SKIP;
        default:
          throw new AssertionError("Unknown collision handling " + collisionHandlingEnum);
      }
    }
  }
}
