package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.LRRStatus;
import io.github.cloudiator.rest.model.LRRType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a long-running request (LRR) within the system 
 */
@ApiModel(description = "Represents a long-running request (LRR) within the system ")

public class LongRunningRequest   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("taskType")
  private LRRType taskType = null;

  @JsonProperty("taskStatus")
  private LRRStatus taskStatus = null;

  public LongRunningRequest id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier for the LRR
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier for the LRR")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LongRunningRequest taskType(LRRType taskType) {
    this.taskType = taskType;
    return this;
  }

   /**
   * Get taskType
   * @return taskType
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LRRType getTaskType() {
    return taskType;
  }

  public void setTaskType(LRRType taskType) {
    this.taskType = taskType;
  }

  public LongRunningRequest taskStatus(LRRStatus taskStatus) {
    this.taskStatus = taskStatus;
    return this;
  }

   /**
   * Get taskStatus
   * @return taskStatus
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LRRStatus getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(LRRStatus taskStatus) {
    this.taskStatus = taskStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LongRunningRequest longRunningRequest = (LongRunningRequest) o;
    return Objects.equals(this.id, longRunningRequest.id) &&
        Objects.equals(this.taskType, longRunningRequest.taskType) &&
        Objects.equals(this.taskStatus, longRunningRequest.taskStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, taskType, taskStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LongRunningRequest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
    sb.append("    taskStatus: ").append(toIndentedString(taskStatus)).append("\n");
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

