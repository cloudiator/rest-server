package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.LRRStatus;
import io.github.cloudiator.rest.model.LRRType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a long-running request (LRR) within the system 
 */
@ApiModel(description = "Represents a long-running request (LRR) within the system ")
@Validated

public class LongRunningRequest   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("taskType")
  private LRRType taskType = null;

  @JsonProperty("taskStatus")
  private LRRStatus taskStatus = null;

  @JsonProperty("lrrData")
  private String lrrData = null;

  @JsonProperty("lrrDiagnostic")
  private String lrrDiagnostic = null;

  @JsonProperty("referenceId")
  private String referenceId = null;

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

  public LongRunningRequest lrrData(String lrrData) {
    this.lrrData = lrrData;
    return this;
  }

  /**
   * Get lrrData
   * @return lrrData
  **/
  @ApiModelProperty(value = "")


  public String getLrrData() {
    return lrrData;
  }

  public void setLrrData(String lrrData) {
    this.lrrData = lrrData;
  }

  public LongRunningRequest lrrDiagnostic(String lrrDiagnostic) {
    this.lrrDiagnostic = lrrDiagnostic;
    return this;
  }

  /**
   * Get lrrDiagnostic
   * @return lrrDiagnostic
  **/
  @ApiModelProperty(value = "")


  public String getLrrDiagnostic() {
    return lrrDiagnostic;
  }

  public void setLrrDiagnostic(String lrrDiagnostic) {
    this.lrrDiagnostic = lrrDiagnostic;
  }

  public LongRunningRequest referenceId(String referenceId) {
    this.referenceId = referenceId;
    return this;
  }

  /**
   * Get referenceId
   * @return referenceId
  **/
  @ApiModelProperty(value = "")


  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
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
        Objects.equals(this.taskStatus, longRunningRequest.taskStatus) &&
        Objects.equals(this.lrrData, longRunningRequest.lrrData) &&
        Objects.equals(this.lrrDiagnostic, longRunningRequest.lrrDiagnostic) &&
        Objects.equals(this.referenceId, longRunningRequest.referenceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, taskType, taskStatus, lrrData, lrrDiagnostic, referenceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LongRunningRequest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
    sb.append("    taskStatus: ").append(toIndentedString(taskStatus)).append("\n");
    sb.append("    lrrData: ").append(toIndentedString(lrrData)).append("\n");
    sb.append("    lrrDiagnostic: ").append(toIndentedString(lrrDiagnostic)).append("\n");
    sb.append("    referenceId: ").append(toIndentedString(referenceId)).append("\n");
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

