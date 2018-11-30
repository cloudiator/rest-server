package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Process
 */
@Validated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "processType", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = ClusterProcess.class, name = "ClusterProcess"),
  @JsonSubTypes.Type(value = SingleProcess.class, name = "SingleProcess"),
})

public class Process   {
  @JsonProperty("id")
  private String id = null;

  /**
   * Gets or Sets processType
   */
  public enum ProcessTypeEnum {
    SINGLE("SINGLE"),
    
    CLUSTER("CLUSTER");

    private String value;

    ProcessTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ProcessTypeEnum fromValue(String text) {
      for (ProcessTypeEnum b : ProcessTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("processType")
  private ProcessTypeEnum processType = null;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    LANCE("LANCE"),
    
    SPARK("SPARK");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("schedule")
  private String schedule = null;

  @JsonProperty("task")
  private String task = null;

  public Process id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Process processType(ProcessTypeEnum processType) {
    this.processType = processType;
    return this;
  }

  /**
   * Get processType
   * @return processType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public ProcessTypeEnum getProcessType() {
    return processType;
  }

  public void setProcessType(ProcessTypeEnum processType) {
    this.processType = processType;
  }

  public Process type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Process process = (Process) o;
    return Objects.equals(this.id, process.id) &&
        Objects.equals(this.processType, process.processType) &&
        Objects.equals(this.type, process.type) &&
        Objects.equals(this.schedule, process.schedule) &&
        Objects.equals(this.task, process.task);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, processType, type, schedule, task);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Process {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    processType: ").append(toIndentedString(processType)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    schedule: ").append(toIndentedString(schedule)).append("\n");
    sb.append("    task: ").append(toIndentedString(task)).append("\n");
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

