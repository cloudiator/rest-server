package io.github.cloudiator.rest.converter;

import static org.junit.Assert.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.model.DockerInterface;
import io.github.cloudiator.rest.model.ExecutionEnvironment;
import io.github.cloudiator.rest.model.IdentifierRequirement;
import io.github.cloudiator.rest.model.JobType;

import io.github.cloudiator.rest.model.LanceInterface;
import io.github.cloudiator.rest.model.OclRequirement;
import io.github.cloudiator.rest.model.PortProvided;
import io.github.cloudiator.rest.model.PortRequired;

import io.github.cloudiator.rest.model.Task;
import java.io.IOException;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.TaskEntities;

import org.cloudiator.messages.entities.TaskEntities.TaskType;

import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.Task;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.TaskEntities;


import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class TaskConverterTest {

  private final TaskConverter taskConverter = new TaskConverter();

  private final Task restTask;
  private final TaskEntities.Task iaasTask;
  //Ports
  //ProvidedPort
  private final PortProvided restProvidedPort;
  private final TaskEntities.Port iaasPortProvidedPort;
  //RequiredPort
  private final PortRequired restRequiredPort;
  private final TaskEntities.Port iaasPortRequiredPort;
  //Requirements
  //Ocl
  private final OclRequirement restOclRequirement;
  private final CommonEntities.Requirement iaasReqOclRequirement;
  //Identifier
  private final IdentifierRequirement restIdentifierRequirement;
  private final CommonEntities.Requirement iaasReqIdRequirement;
  //Interfaces
  //Docker
  private final DockerInterface restDockerInterface;
  private final TaskEntities.TaskInterface iaasTaskDockerInterface;
  //Lance
  private final LanceInterface restLanceInterface;
  private final TaskEntities.TaskInterface iaasTaskLanceInterface;

  public TaskConverterTest() {

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
    //Requirements
    this.restOclRequirement = new OclRequirement()
        .constraint("oclRequirement");

    this.restOclRequirement.setType(restOclRequirement.getClass().getSimpleName());

    this.iaasReqOclRequirement = CommonEntities.Requirement.newBuilder()
        .setOclRequirement(
            CommonEntities.OclRequirement.newBuilder()
                .setConstraint("oclRequirement").build())
        .build();
    this.restIdentifierRequirement = new IdentifierRequirement()
        .hardwareId("hardwareId")
        .imageId("imageId")
        .locationId("locationId");

    this.restIdentifierRequirement.setType(restIdentifierRequirement.getClass().getSimpleName());

    this.iaasReqIdRequirement = CommonEntities.Requirement.newBuilder()
        .setIdRequirement(
            CommonEntities.IdRequirement.newBuilder()
                .setHardwareId("hardwareId")
                .setImageId("imageId")
                .setLocationId("locationId").build())
        .build();
    //Task
    this.restTask = new Task()
        .name("TaskTest")

        .type(JobType.BATCH)
        .executionEnvironment(ExecutionEnvironment.LANCE)

        .addPortsItem(restProvidedPort)
        .addPortsItem(restRequiredPort)
        .addInterfacesItem(restDockerInterface)
        .addInterfacesItem(restLanceInterface)
        .addRequirementsItem(restOclRequirement)
        .addRequirementsItem(restIdentifierRequirement);
    this.iaasTask = TaskEntities.Task.newBuilder()
        .setName("TaskTest")
        .setTaskType(TaskType.BATCH)
        .setExecutionEnvironment(TaskEntities.ExecutionEnvironment.LANCE)
        .addPorts(iaasPortProvidedPort)
        .addPorts(iaasPortRequiredPort)
        .addInterfaces(iaasTaskDockerInterface)
        .addInterfaces(iaasTaskLanceInterface)
        .addRequirements(iaasReqOclRequirement)
        .addRequirements(iaasReqIdRequirement)
        .build();
  }


  @Test
  public void applyBack() throws Exception {
    // from iaas to rest

    Task result = taskConverter.applyBack(iaasTask);
    System.out.println(result);

    ObjectMapper mapper = new ObjectMapper();
    try {
      String jsoninString = mapper.writeValueAsString(result);
      System.out.println(jsoninString);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertThat(result, is(equalTo(restTask)));

  }

  @Test
  public void apply() throws Exception {
    //from rest to iaas

    TaskEntities.Task result2 = taskConverter.apply(restTask);
    System.out.println(result2);

    assertThat(result2, is(equalTo(iaasTask)));

    TaskEntities.Task result = taskConverter.apply(restTask);

    assertThat(result, is(equalTo(iaasTask)));

  }

}