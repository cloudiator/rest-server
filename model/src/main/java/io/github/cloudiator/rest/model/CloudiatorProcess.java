package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.IpAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CloudiatorProcess
 */
@Validated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "processType", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = SingleProcess.class, name = "SingleProcess"),
  @JsonSubTypes.Type(value = ClusterProcess.class, name = "ClusterProcess"),
})

public class CloudiatorProcess   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("originId")
  private String originId = null;

  @JsonProperty("processType")
  private String processType = null;

  /**
   * Gets or Sets state
   */
  public enum StateEnum {
    PENDING("PENDING"),
    
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
    
    SPARK("SPARK"),
    
    FAAS("FAAS"),
    
    SIMULATION("SIMULATION"),
    
    UNKNOWN("UNKNOWN");

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

  @JsonProperty("taskInterface")
  private String taskInterface = null;

  @JsonProperty("diagnostic")
  private String diagnostic = null;

  @JsonProperty("reason")
  private String reason = null;

  @JsonProperty("owner")
  private String owner = null;

  @JsonProperty("ipAddresses")
  @Valid
  private List<IpAddress> ipAddresses = null;

  @JsonProperty("endpoint")
  private String endpoint = null;

  public CloudiatorProcess id(String id) {
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

  public CloudiatorProcess originId(String originId) {
    this.originId = originId;
    return this;
  }

  /**
   * Get originId
   * @return originId
  **/
  @ApiModelProperty(value = "")


  public String getOriginId() {
    return originId;
  }

  public void setOriginId(String originId) {
    this.originId = originId;
  }

  public CloudiatorProcess processType(String processType) {
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
  @ApiModelProperty(value = "")


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
  @ApiModelProperty(value = "The id of the schedule this process belongs to.")


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
  @ApiModelProperty(value = "The id of the task that is instantiated by this process.")


  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public CloudiatorProcess taskInterface(String taskInterface) {
    this.taskInterface = taskInterface;
    return this;
  }

  /**
   * The task interface used for running the process.
   * @return taskInterface
  **/
  @ApiModelProperty(value = "The task interface used for running the process.")


  public String getTaskInterface() {
    return taskInterface;
  }

  public void setTaskInterface(String taskInterface) {
    this.taskInterface = taskInterface;
  }

  public CloudiatorProcess diagnostic(String diagnostic) {
    this.diagnostic = diagnostic;
    return this;
  }

  /**
   * Diagnostic information about this process
   * @return diagnostic
  **/
  @ApiModelProperty(value = "Diagnostic information about this process")


  public String getDiagnostic() {
    return diagnostic;
  }

  public void setDiagnostic(String diagnostic) {
    this.diagnostic = diagnostic;
  }

  public CloudiatorProcess reason(String reason) {
    this.reason = reason;
    return this;
  }

  /**
   * Reason this process was created
   * @return reason
  **/
  @ApiModelProperty(value = "Reason this process was created")


  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public CloudiatorProcess owner(String owner) {
    this.owner = owner;
    return this;
  }

  /**
   * The user this process was created for
   * @return owner
  **/
  @ApiModelProperty(value = "The user this process was created for")


  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public CloudiatorProcess ipAddresses(List<IpAddress> ipAddresses) {
    this.ipAddresses = ipAddresses;
    return this;
  }

  public CloudiatorProcess addIpAddressesItem(IpAddress ipAddressesItem) {
    if (this.ipAddresses == null) {
      this.ipAddresses = new ArrayList<IpAddress>();
    }
    this.ipAddresses.add(ipAddressesItem);
    return this;
  }

  /**
   * The public/private ip addresses under which this process is reachable. 
   * @return ipAddresses
  **/
  @ApiModelProperty(value = "The public/private ip addresses under which this process is reachable. ")

  @Valid

  public List<IpAddress> getIpAddresses() {
    return ipAddresses;
  }

  public void setIpAddresses(List<IpAddress> ipAddresses) {
    this.ipAddresses = ipAddresses;
  }

  public CloudiatorProcess endpoint(String endpoint) {
    this.endpoint = endpoint;
    return this;
  }

  /**
   * The endpoint where this process is reachable. 
   * @return endpoint
  **/
  @ApiModelProperty(value = "The endpoint where this process is reachable. ")


  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
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
        Objects.equals(this.originId, cloudiatorProcess.originId) &&
        Objects.equals(this.processType, cloudiatorProcess.processType) &&
        Objects.equals(this.state, cloudiatorProcess.state) &&
        Objects.equals(this.type, cloudiatorProcess.type) &&
        Objects.equals(this.schedule, cloudiatorProcess.schedule) &&
        Objects.equals(this.task, cloudiatorProcess.task) &&
        Objects.equals(this.taskInterface, cloudiatorProcess.taskInterface) &&
        Objects.equals(this.diagnostic, cloudiatorProcess.diagnostic) &&
        Objects.equals(this.reason, cloudiatorProcess.reason) &&
        Objects.equals(this.owner, cloudiatorProcess.owner) &&
        Objects.equals(this.ipAddresses, cloudiatorProcess.ipAddresses) &&
        Objects.equals(this.endpoint, cloudiatorProcess.endpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, originId, processType, state, type, schedule, task, taskInterface, diagnostic, reason, owner, ipAddresses, endpoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CloudiatorProcess {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    originId: ").append(toIndentedString(originId)).append("\n");
    sb.append("    processType: ").append(toIndentedString(processType)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    schedule: ").append(toIndentedString(schedule)).append("\n");
    sb.append("    task: ").append(toIndentedString(task)).append("\n");
    sb.append("    taskInterface: ").append(toIndentedString(taskInterface)).append("\n");
    sb.append("    diagnostic: ").append(toIndentedString(diagnostic)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    ipAddresses: ").append(toIndentedString(ipAddresses)).append("\n");
    sb.append("    endpoint: ").append(toIndentedString(endpoint)).append("\n");
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

