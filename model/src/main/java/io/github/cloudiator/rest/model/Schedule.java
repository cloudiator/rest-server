package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.CloudiatorProcess;
import io.github.cloudiator.rest.model.ScheduleNew;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Schedule
 */
@Validated

public class Schedule   {
  @JsonProperty("job")
  private String job = null;

  /**
   * If the instantiation should be handled AUTOMATIC or MANUAL
   */
  public enum InstantiationEnum {
    AUTOMATIC("AUTOMATIC"),
    
    MANUAL("MANUAL");

    private String value;

    InstantiationEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static InstantiationEnum fromValue(String text) {
      for (InstantiationEnum b : InstantiationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("instantiation")
  private InstantiationEnum instantiation = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("owner")
  private String owner = null;

  @JsonProperty("processes")
  @Valid
  private List<CloudiatorProcess> processes = null;

  /**
   * Gets or Sets state
   */
  public enum StateEnum {
    PENDING("PENDING"),
    
    RUNNING("RUNNING"),
    
    ERROR("ERROR"),
    
    RESTORING("RESTORING"),
    
    DELETED("DELETED"),
    
    MANUAL("MANUAL");

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

  public Schedule job(String job) {
    this.job = job;
    return this;
  }

  /**
   * The identifier of the job
   * @return job
  **/
  @ApiModelProperty(value = "The identifier of the job")


  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public Schedule instantiation(InstantiationEnum instantiation) {
    this.instantiation = instantiation;
    return this;
  }

  /**
   * If the instantiation should be handled AUTOMATIC or MANUAL
   * @return instantiation
  **/
  @ApiModelProperty(value = "If the instantiation should be handled AUTOMATIC or MANUAL")


  public InstantiationEnum getInstantiation() {
    return instantiation;
  }

  public void setInstantiation(InstantiationEnum instantiation) {
    this.instantiation = instantiation;
  }

  public Schedule id(String id) {
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

  public Schedule owner(String owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
  **/
  @ApiModelProperty(value = "")


  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public Schedule processes(List<CloudiatorProcess> processes) {
    this.processes = processes;
    return this;
  }

  public Schedule addProcessesItem(CloudiatorProcess processesItem) {
    if (this.processes == null) {
      this.processes = new ArrayList<CloudiatorProcess>();
    }
    this.processes.add(processesItem);
    return this;
  }

  /**
   * Get processes
   * @return processes
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CloudiatorProcess> getProcesses() {
    return processes;
  }

  public void setProcesses(List<CloudiatorProcess> processes) {
    this.processes = processes;
  }

  public Schedule state(StateEnum state) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Schedule schedule = (Schedule) o;
    return Objects.equals(this.job, schedule.job) &&
        Objects.equals(this.instantiation, schedule.instantiation) &&
        Objects.equals(this.id, schedule.id) &&
        Objects.equals(this.owner, schedule.owner) &&
        Objects.equals(this.processes, schedule.processes) &&
        Objects.equals(this.state, schedule.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(job, instantiation, id, owner, processes, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Schedule {\n");
    
    sb.append("    job: ").append(toIndentedString(job)).append("\n");
    sb.append("    instantiation: ").append(toIndentedString(instantiation)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    processes: ").append(toIndentedString(processes)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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

