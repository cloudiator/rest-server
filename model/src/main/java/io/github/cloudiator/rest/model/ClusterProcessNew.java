package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.CloudiatorProcessNew;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ClusterProcessNew
 */
@Validated

public class ClusterProcessNew   {
  @JsonProperty("schedule")
  private String schedule = null;

  @JsonProperty("task")
  private String task = null;

  @JsonProperty("lifecycleInterface")
  private String lifecycleInterface = null;

  @JsonProperty("nodes")
  @Valid
  private List<String> nodes = new ArrayList<String>();

  public ClusterProcessNew schedule(String schedule) {
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

  public ClusterProcessNew task(String task) {
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

  public ClusterProcessNew lifecycleInterface(String lifecycleInterface) {
    this.lifecycleInterface = lifecycleInterface;
    return this;
  }

  /**
   * The lifecycle interface used for running the process.
   * @return lifecycleInterface
  **/
  @ApiModelProperty(required = true, value = "The lifecycle interface used for running the process.")
  @NotNull


  public String getLifecycleInterface() {
    return lifecycleInterface;
  }

  public void setLifecycleInterface(String lifecycleInterface) {
    this.lifecycleInterface = lifecycleInterface;
  }

  public ClusterProcessNew nodes(List<String> nodes) {
    this.nodes = nodes;
    return this;
  }

  public ClusterProcessNew addNodesItem(String nodesItem) {
    this.nodes.add(nodesItem);
    return this;
  }

  /**
   * A list of node identifiers defining the cluster the process is hosted on.
   * @return nodes
  **/
  @ApiModelProperty(required = true, value = "A list of node identifiers defining the cluster the process is hosted on.")
  @NotNull


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
    ClusterProcessNew clusterProcessNew = (ClusterProcessNew) o;
    return Objects.equals(this.schedule, clusterProcessNew.schedule) &&
        Objects.equals(this.task, clusterProcessNew.task) &&
        Objects.equals(this.lifecycleInterface, clusterProcessNew.lifecycleInterface) &&
        Objects.equals(this.nodes, clusterProcessNew.nodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schedule, task, lifecycleInterface, nodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClusterProcessNew {\n");
    
    sb.append("    schedule: ").append(toIndentedString(schedule)).append("\n");
    sb.append("    task: ").append(toIndentedString(task)).append("\n");
    sb.append("    lifecycleInterface: ").append(toIndentedString(lifecycleInterface)).append("\n");
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

