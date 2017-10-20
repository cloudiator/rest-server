package io.github.cloudiator.rest.api;

import io.github.cloudiator.rest.UserService;
import io.github.cloudiator.rest.converter.TaskConverter;
import io.github.cloudiator.rest.model.Port;
import io.github.cloudiator.rest.model.PortProvided;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.Task;

import io.github.cloudiator.rest.model.TaskInterface;
import io.swagger.annotations.*;

import java.util.ArrayList;
import org.cloudiator.messages.Task.CreateTaskRequest;
import org.cloudiator.messages.Task.TaskCreatedResponse;
import org.cloudiator.messages.Task.TaskQueryRequest;
import org.cloudiator.messages.Task.TaskQueryResponse;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:00:45.563+02:00")

@Controller
public class TasksApiController implements TasksApi {

  @Autowired
  private UserService userService;

  @Autowired
  private TaskService taskService;

  private final TaskConverter taskConverter = new TaskConverter();

  @Override
  public ResponseEntity<Task> addTask(
      @ApiParam(value = "Task to add", required = true) @Valid @RequestBody Task task) {
    //Validate und Verification
    Task newOne = new Task()
        .name(task.getName())
        .type(task.getType())
        .executionEnvironment(task.getExecutionEnvironment());
    for (Port port : task.getPorts()) {
      newOne.addPortsItem(port);
    }
    for (TaskInterface taskinterface : task.getInterfaces()) {
      newOne.addInterfacesItem(taskinterface);
    }
    for (Requirement requirement : task.getRequirements()) {
      newOne.addRequirementsItem(requirement);
    }

    TaskCreatedResponse response = null;
    try {
      response = taskService
          .createTask(
              CreateTaskRequest.newBuilder().setUserId(userService.getUserId())
                  .setTask(taskConverter.apply(newOne)).build());

    } catch (ResponseException ex) {
      throw new ApiException(ex.code(), ex.getMessage());
    }

    newOne = taskConverter.applyBack(response.getTask());

    return new ResponseEntity<Task>(newOne, HttpStatus.OK);
  }

  public ResponseEntity<List<Task>> findTasks() {

    List<Task> taskList = new ArrayList<>();
    //final TaskConverter taskConverter = new TaskConverter();
    TaskQueryResponse response = null;

    try {
      response = taskService
          .getTasks(TaskQueryRequest.newBuilder().setUserId(userService.getUserId()).build());

      for (TaskEntities.Task task : response.getTaskList()) {
        taskList.add(taskConverter.applyBack(task));
      }

    } catch (ResponseException ex) {
      throw new ApiException(ex.code(), ex.getMessage());
    }

    return new ResponseEntity<List<Task>>(taskList, HttpStatus.OK);
  }
}
