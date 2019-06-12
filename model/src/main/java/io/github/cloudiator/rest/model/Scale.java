package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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

  /**
   * Gets or Sets scaleDirection
   */
  public enum ScaleDirectionEnum {
    IN("SCALE_IN"),
    
    OUT("SCALE_OUT");

    private String value;

    ScaleDirectionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ScaleDirectionEnum fromValue(String text) {
      for (ScaleDirectionEnum b : ScaleDirectionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("scaleDirection")
  private ScaleDirectionEnum scaleDirection = null;

  @JsonProperty("nodes")
  @Valid
  private List<String> nodes = null;

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

  public Scale scaleDirection(ScaleDirectionEnum scaleDirection) {
    this.scaleDirection = scaleDirection;
    return this;
  }

  /**
   * Get scaleDirection
   * @return scaleDirection
  **/
  @ApiModelProperty(value = "")


  public ScaleDirectionEnum getScaleDirection() {
    return scaleDirection;
  }

  public void setScaleDirection(ScaleDirectionEnum scaleDirection) {
    this.scaleDirection = scaleDirection;
  }

  public Scale nodes(List<String> nodes) {
    this.nodes = nodes;
    return this;
  }

  public Scale addNodesItem(String nodesItem) {
    if (this.nodes == null) {
      this.nodes = new ArrayList<String>();
    }
    this.nodes.add(nodesItem);
    return this;
  }

  /**
   * An optional list of nodes for the scaling process
   * @return nodes
  **/
  @ApiModelProperty(value = "An optional list of nodes for the scaling process")


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
    Scale scale = (Scale) o;
    return Objects.equals(this.schedule, scale.schedule) &&
        Objects.equals(this.task, scale.task) &&
        Objects.equals(this.scaleDirection, scale.scaleDirection) &&
        Objects.equals(this.nodes, scale.nodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schedule, task, scaleDirection, nodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Scale {\n");
    
    sb.append("    schedule: ").append(toIndentedString(schedule)).append("\n");
    sb.append("    task: ").append(toIndentedString(task)).append("\n");
    sb.append("    scaleDirection: ").append(toIndentedString(scaleDirection)).append("\n");
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

