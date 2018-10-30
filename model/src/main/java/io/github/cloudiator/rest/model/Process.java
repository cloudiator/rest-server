package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Process
 */
@Validated

public class Process   {
  @JsonProperty("schedule")
  private String schedule = null;

  @JsonProperty("task")
  private String task = null;

  @JsonProperty("node")
  private String node = null;

  @JsonProperty("id")
  private String id = null;

  public Process schedule(String schedule) {
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

  public Process task(String task) {
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

  public Process node(String node) {
    this.node = node;
    return this;
  }

  /**
   * Tne id of the node this process is hosted on.
   * @return node
  **/
  @ApiModelProperty(required = true, value = "Tne id of the node this process is hosted on.")
  @NotNull


  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public Process id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Process process = (Process) o;
    return Objects.equals(this.schedule, process.schedule) &&
        Objects.equals(this.task, process.task) &&
        Objects.equals(this.node, process.node) &&
        Objects.equals(this.id, process.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schedule, task, node, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Process {\n");
    
    sb.append("    schedule: ").append(toIndentedString(schedule)).append("\n");
    sb.append("    task: ").append(toIndentedString(task)).append("\n");
    sb.append("    node: ").append(toIndentedString(node)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

