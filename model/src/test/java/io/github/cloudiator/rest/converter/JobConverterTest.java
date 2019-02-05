package io.github.cloudiator.rest.converter;

import static org.junit.Assert.assertThat;

import io.github.cloudiator.rest.model.Communication;
import io.github.cloudiator.rest.model.DockerInterface;
import io.github.cloudiator.rest.model.IdentifierRequirement;
import io.github.cloudiator.rest.model.Job;
import io.github.cloudiator.rest.model.LanceInterface;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.github.cloudiator.rest.model.*;
import io.github.cloudiator.rest.model.LanceInterface.ContainerTypeEnum;
import io.github.cloudiator.rest.model.OclRequirement;
import io.github.cloudiator.rest.model.PortProvided;
import io.github.cloudiator.rest.model.PortRequired;
import io.github.cloudiator.rest.model.Task;
import io.github.cloudiator.rest.model.TaskType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.cloudiator.messages.entities.CommonEntities;
import org.cloudiator.messages.entities.JobEntities;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messages.entities.TaskEntities.ContainerType;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
  //Faas
  private final FaasInterface restFaasInterface;
  private final TaskEntities.TaskInterface iaasTaskFaasInterface;

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
    Map<String,String> testEnv = new HashMap<>();
    testEnv.put("john","doe");
    this.restDockerInterface = new DockerInterface()
        .dockerImage("DockerImage").environment(testEnv);
    this.restDockerInterface.setType(restDockerInterface.getClass().getSimpleName());
    this.iaasTaskDockerInterface = TaskEntities.TaskInterface.newBuilder()
        .setDockerInterface(
            TaskEntities.DockerInterface.newBuilder()
                .setDockerImage("DockerImage").putAllEnvironment(testEnv).build()
        ).build();
    this.restLanceInterface = new LanceInterface()
        .containerType(ContainerTypeEnum.BOTH)
        .init("init").preInstall("preInstall").install("install").postInstall("postInstall")
        .startDetection("startDetection").preStart("preStart").start("start").postStart("postStart")
        .stopDetection("stopDetection").preStop("preStop").stop("stop").postStop("postStop")
        .shutdown("shutdown");
    this.restLanceInterface.setType(restLanceInterface.getClass().getSimpleName());
    this.iaasTaskLanceInterface = TaskEntities.TaskInterface.newBuilder()
        .setLanceInterface(
            TaskEntities.LanceInterface.newBuilder()
                .setContainerType(ContainerType.BOTH)
                .setInit("init").setPreInstall("preInstall").setInstall("install")
                .setPostInstall("postInstall")
                .setStartDetection("startDetection").setPreStart("preStart").setStart("start")
                .setPostStart("postStart")
                .setStopDetection("stopDetection").setPreStop("preStop").setStop("stop")
                .setPostStop("postStop")
                .setShutdown("shutdown").build()
        ).build();


    this.restFaasInterface = new FaasInterface()
        .functionName("functionName")
        .sourceCodeUrl("http://code.url")
        .handler("file.handler")
        .timeout(123)
        .triggers(ImmutableList.of(new HttpTrigger()
            .httpMethod("GET")
            .httpPath("http/path")))
        .functionEnvironment(ImmutableMap.of("key", "value"));
    this.restFaasInterface.setType(restFaasInterface.getClass().getSimpleName());
    Trigger trigger = this.restFaasInterface.getTriggers().get(0);
    trigger.setType(trigger.getClass().getSimpleName());
    this.iaasTaskFaasInterface = TaskEntities.TaskInterface.newBuilder()
        .setFaasInterface(
            TaskEntities.FaasInterface.newBuilder()
                .setFunctionName("functionName")
                .setSourceCodeUrl("http://code.url")
                .setHandler("file.handler")
                .setTimeout(123)
                .addTriggers(TaskEntities.Trigger.newBuilder().setHttpTrigger(
                    TaskEntities.HttpTrigger.newBuilder()
                        .setHttpMethod("GET")
                        .setHttpPath("http/path")))
                .putFunctionEnvironment("key", "value")
        ).build();
    //Task
    this.restTask = new Task()
        .name("TestTask")
        .taskType(TaskType.BATCH)
        .addPortsItem(restProvidedPort)
        .addPortsItem(restRequiredPort)
        .addRequirementsItem(restIdRequirement)
        .addRequirementsItem(restOclRequirement)
        .addInterfacesItem(restDockerInterface)
        .addInterfacesItem(restLanceInterface)
        .addInterfacesItem(restFaasInterface);
    this.iaasTask = TaskEntities.Task.newBuilder()
        .setName("TestTask")
        .setTaskType(TaskEntities.TaskType.BATCH)
        .addPorts(iaasPortProvidedPort)
        .addPorts(iaasPortRequiredPort)
        .addRequirements(iaasIdRequirement)
        .addRequirements(iaasOclRequirement)
        .addInterfaces(iaasTaskDockerInterface)
        .addInterfaces(iaasTaskLanceInterface)
        .addInterfaces(iaasTaskFaasInterface)
        .build();
    //Jobs
    final UUID uuid = UUID.randomUUID();
    this.restJob = new Job()
        .id(uuid.toString())
        .owner("admin")
        .name("TestJob")
        .addCommunicationsItem(new Communication()
            .portProvided("ProvidedPort").portRequired("RequiredPort"))
        .addRequirementsItem(restOclRequirement)
        .addRequirementsItem(restIdRequirement)
        .addTasksItem(restTask);
    this.iaasJob = JobEntities.Job.newBuilder()
        .setId(uuid.toString())
        .setUserId("admin")
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

    assertThat(result, Matchers.is(Matchers.equalTo(restJob)));
  }

  @Test
  public void apply() throws Exception {
    //from rest to iaas
    JobEntities.Job result = jobConverter.apply(restJob);

    assertThat(result, Matchers.is(Matchers.equalTo(iaasJob)));
  }

}
