package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A process represents a task running on a node
 */
@ApiModel(description = "A process represents a task running on a node")
@Validated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "processType", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = ClusterProcessNew.class, name = "ClusterProcessNew"),
  @JsonSubTypes.Type(value = SingleProcessNew.class, name = "SingleProcessNew"),
})

public class CloudiatorProcessNew   {
  @JsonProperty("processType")
  private String processType = null;

  @JsonProperty("schedule")
  private String schedule = null;

  @JsonProperty("task")
  private String task = null;

  @JsonProperty("taskInterface")
  private String taskInterface = null;

  public CloudiatorProcessNew processType(String processType) {
    this.processType = processType;
    return this;
  }

  /**
   * Get processType
   * @return processType
  **/
  @ApiModelProperty(value = "")


  public String getProcessType() {
    return processType;
  }

  public void setProcessType(String processType) {
    this.processType = processType;
  }

  public CloudiatorProcessNew schedule(String schedule) {
    this.schedule = schedule;
    return this;
  }

  /**
   * The id of the schedule this process belongs to.
   * @return schedule
  **/
  @ApiModelProperty(required = true, value = "The id of the schedule this process belongs to.")
  @NotNull


  public String getSchedule() {
    return schedule;
  }

  public void setSchedule(String schedule) {
    this.schedule = schedule;
  }

  public CloudiatorProcessNew task(String task) {
    this.task = task;
    return this;
  }

  /**
   * The id of the task that is instantiated by this process.
   * @return task
  **/
  @ApiModelProperty(required = true, value = "The id of the task that is instantiated by this process.")
  @NotNull


  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public CloudiatorProcessNew taskInterface(String taskInterface) {
    this.taskInterface = taskInterface;
    return this;
  }

  /**
   * The task interface used for running the process.
   * @return taskInterface
  **/
  @ApiModelProperty(required = true, value = "The task interface used for running the process.")
  @NotNull


  public String getTaskInterface() {
    return taskInterface;
  }

  public void setTaskInterface(String taskInterface) {
    this.taskInterface = taskInterface;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CloudiatorProcessNew cloudiatorProcessNew = (CloudiatorProcessNew) o;
    return Objects.equals(this.processType, cloudiatorProcessNew.processType) &&
        Objects.equals(this.schedule, cloudiatorProcessNew.schedule) &&
        Objects.equals(this.task, cloudiatorProcessNew.task) &&
        Objects.equals(this.taskInterface, cloudiatorProcessNew.taskInterface);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processType, schedule, task, taskInterface);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CloudiatorProcessNew {\n");
    
    sb.append("    processType: ").append(toIndentedString(processType)).append("\n");
    sb.append("    schedule: ").append(toIndentedString(schedule)).append("\n");
    sb.append("    task: ").append(toIndentedString(task)).append("\n");
    sb.append("    taskInterface: ").append(toIndentedString(taskInterface)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

