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
 * CloudiatorProcess
 */
@Validated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "processType", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = ClusterProcess.class, name = "ClusterProcess"),
  @JsonSubTypes.Type(value = SingleProcess.class, name = "SingleProcess"),
})

public class CloudiatorProcess   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("processType")
  private String processType = null;

  /**
   * Gets or Sets state
   */
  public enum StateEnum {
    CREATED("CREATED"),
    
    FAILED("FAILED"),
    
    RUNNING("RUNNING"),
    
    ERROR("ERROR"),
    
    DELETED("DELETED"),
    
    FINISHED("FINISHED");

    private String value;

    StateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StateEnum fromValue(String text) {
      for (StateEnum b : StateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("state")
  private StateEnum state = null;

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

  public CloudiatorProcess id(String id) {
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

  public CloudiatorProcess processType(String processType) {
    this.processType = processType;
    return this;
  }

  /**
   * Get processType
   * @return processType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getProcessType() {
    return processType;
  }

  public void setProcessType(String processType) {
    this.processType = processType;
  }

  public CloudiatorProcess state(StateEnum state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  **/
  @ApiModelProperty(value = "")


  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public CloudiatorProcess type(TypeEnum type) {
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

  public CloudiatorProcess schedule(String schedule) {
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

  public CloudiatorProcess task(String task) {
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
    CloudiatorProcess cloudiatorProcess = (CloudiatorProcess) o;
    return Objects.equals(this.id, cloudiatorProcess.id) &&
        Objects.equals(this.processType, cloudiatorProcess.processType) &&
        Objects.equals(this.state, cloudiatorProcess.state) &&
        Objects.equals(this.type, cloudiatorProcess.type) &&
        Objects.equals(this.schedule, cloudiatorProcess.schedule) &&
        Objects.equals(this.task, cloudiatorProcess.task);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, processType, state, type, schedule, task);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CloudiatorProcess {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    processType: ").append(toIndentedString(processType)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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

