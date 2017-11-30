package io.github.cloudiator.rest.converter;

import static org.junit.Assert.*;

import io.github.cloudiator.rest.model.Communication;
import io.github.cloudiator.rest.model.DockerInterface;
import io.github.cloudiator.rest.model.ExecutionEnvironment;
import io.github.cloudiator.rest.model.IdentifierRequirement;
import io.github.cloudiator.rest.model.Job;
import io.github.cloudiator.rest.model.TaskType;
import io.github.cloudiator.rest.model.LanceInterface;
import io.github.cloudiator.rest.model.OclRequirement;
import io.github.cloudiator.rest.model.PortProvided;
import io.github.cloudiator.rest.model.PortRequired;
import io.github.cloudiator.rest.model.Task;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.JobEntities;
import org.cloudiator.messages.entities.TaskEntities;


import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class JobConverterTest {

  private final JobConverter jobConverter = new JobConverter();

  private final Job restJob;
  private final JobEntities.Job iaasJob;
  //Requirements
  private final OclRequirement restOclRequirement;
  private final CommonEntities.Requirement iaasOclRequirement;
  private final IdentifierRequirement restIdRequirement;
  private final CommonEntities.Requirement iaasIdRequirement;
  //Task
  private final Task restTask;
  private final TaskEntities.Task iaasTask;
  //Ports
  //ProvidedPort
  private final PortProvided restProvidedPort;
  private final TaskEntities.Port iaasPortProvidedPort;
  //RequiredPort
  private final PortRequired restRequiredPort;
  private final TaskEntities.Port iaasPortRequiredPort;
  //Interfaces
  //Docker
  private final DockerInterface restDockerInterface;
  private final TaskEntities.TaskInterface iaasTaskDockerInterface;
  //Lance
  private final LanceInterface restLanceInterface;
  private final TaskEntities.TaskInterface iaasTaskLanceInterface;

  public JobConverterTest() {
    //Requirements
    this.restOclRequirement = new OclRequirement()
        .constraint("OclRequirement");
    this.restOclRequirement.setType(restOclRequirement.getClass().getSimpleName());
    this.iaasOclRequirement = CommonEntities.Requirement.newBuilder()
        .setOclRequirement(CommonEntities.OclRequirement.newBuilder()
            .setConstraint("OclRequirement").build()).build();
    this.restIdRequirement = new IdentifierRequirement()
        .hardwareId("HardwareId")
        .imageId("ImageId")
        .locationId("LocationId");
    this.restIdRequirement.setType(restIdRequirement.getClass().getSimpleName());
    this.iaasIdRequirement = CommonEntities.Requirement.newBuilder()
        .setIdRequirement(CommonEntities.IdRequirement.newBuilder()
            .setHardwareId("HardwareId")
            .setImageId("ImageId")
            .setLocationId("LocationId").build()).build();
    //Ports
    this.restProvidedPort = new PortProvided()
        .port(12345);
    this.restProvidedPort.setType(restProvidedPort.getClass().getSimpleName());
    this.restProvidedPort.setName("PortProvidedTest");
    this.iaasPortProvidedPort = TaskEntities.Port.newBuilder().setPortProvided(
        TaskEntities.PortProvided.newBuilder()
            .setPort(12345).setName("PortProvidedTest").build()
    ).build();
    this.restRequiredPort = new PortRequired()
        .isMandatory(true).updateAction("UpdateActionTest");
    this.restRequiredPort.setType(restRequiredPort.getClass().getSimpleName());
    this.restRequiredPort.setName("PortRequiredTest");
    this.iaasPortRequiredPort = TaskEntities.Port.newBuilder()
        .setPortRequired(
            TaskEntities.PortRequired.newBuilder()
                .setIsMandatory(true).setUpdateAction("UpdateActionTest")
                .setName("PortRequiredTest").build()
        ).build();
    //Interfaces
    this.restDockerInterface = new DockerInterface()
        .dockerImage("DockerImage");
    this.iaasTaskDockerInterface = TaskEntities.TaskInterface.newBuilder()
        .setDockerInterface(
            TaskEntities.DockerInterface.newBuilder()
                .setDockerImage("DockerImage").build()
        ).build();
    this.restLanceInterface = new LanceInterface()
        .init("init").preInstall("preInstall").install("install").postInstall("postInstall")
        .startDetection("startDetection").preStart("preStart").start("start").postStart("postStart")
        .stopDetection("stopDetection").preStop("preStop").stop("stop").postStop("postStop")
        .shutdown("shutdown");
    this.iaasTaskLanceInterface = TaskEntities.TaskInterface.newBuilder()
        .setLanceInterface(
            TaskEntities.LanceInterface.newBuilder()
                .setInit("init").setPreInstall("preInstall").setInstall("install")
                .setPostInstall("postInstall")
                .setStartDetection("startDetection").setPreStart("preStart").setStart("start")
                .setPostStart("postStart")
                .setStopDetection("stopDetection").setPreStop("preStop").setStop("stop")
                .setPostStop("postStop")
                .setShutdown("shutdown").build()
        ).build();
    //Task
    this.restTask = new Task()
        .name("TestTask")
        .type(TaskType.BATCH)
        .executionEnvironment(ExecutionEnvironment.SPARK)
        .addPortsItem(restProvidedPort)
        .addPortsItem(restRequiredPort)
        .addRequirementsItem(restIdRequirement)
        .addRequirementsItem(restOclRequirement)
        .addInterfacesItem(restDockerInterface)
        .addInterfacesItem(restLanceInterface);
    this.iaasTask = TaskEntities.Task.newBuilder()
        .setName("TestTask")
        .setTaskType(TaskEntities.TaskType.BATCH)
        .setExecutionEnvironment(TaskEntities.ExecutionEnvironment.SPARK)
        .addPorts(iaasPortProvidedPort)
        .addPorts(iaasPortRequiredPort)
        .addRequirements(iaasIdRequirement)
        .addRequirements(iaasOclRequirement)
        .addInterfaces(iaasTaskDockerInterface)
        .addInterfaces(iaasTaskLanceInterface).build();
    //Jobs
    this.restJob = new Job()
        .name("TestJob")
        .addCommunicationsItem(new Communication()
            .portProvided("ProvidedPort").portRequired("RequiredPort"))
        .addRequirementsItem(restOclRequirement)
        .addRequirementsItem(restIdRequirement)
        .addTasksItem(restTask);
    this.iaasJob = JobEntities.Job.newBuilder()
        .setName("TestJob")
        .addCommunications(JobEntities.Communication.newBuilder()
            .setPortProvided("ProvidedPort").setPortRequired("RequiredPort"))
        .addRequirements(iaasOclRequirement)
        .addRequirements(iaasIdRequirement)
        .addTasks(iaasTask).build();

  }

  @Test
  public void applyBack() throws Exception {
    //from iaas to rest
    Job result = jobConverter.applyBack(iaasJob);

    assertThat(result, is(equalTo(restJob)));
  }

  @Test
  public void apply() throws Exception {
    //from rest to iaas
    JobEntities.Job result = jobConverter.apply(restJob);

    assertThat(result, is(equalTo(iaasJob)));
  }

}