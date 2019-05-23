package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Node;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Scaling action object containing the required information to scale an Cloudiator cluster process 
 */
@ApiModel(description = "Scaling action object containing the required information to scale an Cloudiator cluster process ")
@Validated

public class Scale   {
  @JsonProperty("schedule")
  private String schedule = null;

  @JsonProperty("task")
  private String task = null;

  @JsonProperty("nodes")
  @Valid
  private List<Node> nodes = null;

  public Scale schedule(String schedule) {
    this.schedule = schedule;
    return this;
  }

  /**
   * The identifier of the schedule
   * @return schedule
  **/
  @ApiModelProperty(value = "The identifier of the schedule")


  public String getSchedule() {
    return schedule;
  }

  public void setSchedule(String schedule) {
    this.schedule = schedule;
  }

  public Scale task(String task) {
    this.task = task;
    return this;
  }

  /**
   * The identifier of the task
   * @return task
  **/
  @ApiModelProperty(value = "The identifier of the task")


  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public Scale nodes(List<Node> nodes) {
    this.nodes = nodes;
    return this;
  }

  public Scale addNodesItem(Node nodesItem) {
    if (this.nodes == null) {
      this.nodes = new ArrayList<Node>();
    }
    this.nodes.add(nodesItem);
    return this;
  }

  /**
   * An optional list of nodes
   * @return nodes
  **/
  @ApiModelProperty(value = "An optional list of nodes")

  @Valid

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
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
    Scale scale = (Scale) o;
    return Objects.equals(this.schedule, scale.schedule) &&
        Objects.equals(this.task, scale.task) &&
        Objects.equals(this.nodes, scale.nodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schedule, task, nodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Scale {\n");
    
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

