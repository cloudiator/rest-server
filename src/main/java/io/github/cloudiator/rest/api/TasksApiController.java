package io.github.cloudiator.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cloudiator.rest.UserInfo;
import io.github.cloudiator.rest.UserServiceOld;
import io.github.cloudiator.rest.converter.TaskConverter;
import io.github.cloudiator.rest.model.Port;
import io.github.cloudiator.rest.model.PortProvided;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.Task;

import io.github.cloudiator.rest.model.TaskInterface;
import io.swagger.annotations.*;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.cloudiator.messages.Task.CreateTaskRequest;
import org.cloudiator.messages.Task.TaskCreatedResponse;
import org.cloudiator.messages.Task.TaskQueryRequest;
import org.cloudiator.messages.Task.TaskQueryResponse;
import org.cloudiator.messages.entities.TaskEntities;
import org.cloudiator.messaging.ResponseException;
import org.cloudiator.messaging.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private final TaskConverter taskConverter;

  private static final Logger log = LoggerFactory.getLogger(PlatformApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;
  private UserInfo userInfo;

  @org.springframework.beans.factory.annotation.Autowired
  public TasksApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.taskConverter = new TaskConverter();
  }

  @Autowired
  private UserServiceOld userService;

  @Autowired
  private TaskService taskService;


  public ResponseEntity<Task> addTask(
      @ApiParam(value = "Task to add", required = true) @Valid @RequestBody Task task) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      userInfo = new UserInfo(request);
      try {
        //Validate und Verification
        Task newOne = new Task()
            .name(task.getName())
            .taskType(task.getTaskType())
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
                  CreateTaskRequest.newBuilder()
                      //.setUserId(userService.getUserId())
                      .setUserId(userInfo.currentUserTenant())
                      .setTask(taskConverter.apply(newOne)).build());

        } catch (ResponseException ex) {
          throw new ApiException(ex.code(), ex.getMessage());
        }

        newOne = taskConverter.applyBack(response.getTask());

        return new ResponseEntity<Task>(newOne, HttpStatus.OK);
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }


  public ResponseEntity<List<Task>> findTasks() {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      userInfo = new UserInfo(request);
      try {

        List<Task> taskList = new ArrayList<>();
        //final TaskConverter taskConverter = new TaskConverter();
        TaskQueryResponse response = null;

        response = taskService
            .getTasks(TaskQueryRequest.newBuilder()
                //.setUserId(userService.getUserId())
                .setUserId(userInfo.currentUserTenant())
                .build());

        for (TaskEntities.Task task : response.getTaskList()) {
          taskList.add(taskConverter.applyBack(task));
        }

        return new ResponseEntity<List<Task>>(taskList, HttpStatus.OK);
      } catch (ResponseException ex) {
        throw new ApiException(ex.code(), ex.getMessage());
      } catch (Exception e) {
        log.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
