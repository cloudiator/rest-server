package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A process represents a task running on a node
 */
@ApiModel(description = "A process represents a task running on a node")
@Validated

public class CloudiatorProcessNew   {
  @JsonProperty("schedule")
  private String schedule = null;

  @JsonProperty("task")
  private String task = null;

  @JsonProperty("nodes")
  @Valid
  private List<String> nodes = null;

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

  public CloudiatorProcessNew nodes(List<String> nodes) {
    this.nodes = nodes;
    return this;
  }

  public CloudiatorProcessNew addNodesItem(String nodesItem) {
    if (this.nodes == null) {
      this.nodes = new ArrayList<String>();
    }
    this.nodes.add(nodesItem);
    return this;
  }

  /**
   * A list of node identifiers this process is hosted on.
   * @return nodes
  **/
  @ApiModelProperty(value = "A list of node identifiers this process is hosted on.")


  public List<String> getNodes() {
    return nodes;
  }

  public void setNodes(List<String> nodes) {
    this.nodes = nodes;
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
    return Objects.equals(this.schedule, cloudiatorProcessNew.schedule) &&
        Objects.equals(this.task, cloudiatorProcessNew.task) &&
        Objects.equals(this.nodes, cloudiatorProcessNew.nodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schedule, task, nodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CloudiatorProcessNew {\n");
    
    sb.append("    schedule: ").append(toIndentedString(schedule)).append("\n");
    sb.append("    task: ").append(toIndentedString(task)).append("\n");
    sb.append("    nodes: ").append(toIndentedString(nodes)).append("\n");
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

