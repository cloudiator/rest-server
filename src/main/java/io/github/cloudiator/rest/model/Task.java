package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.ExecutionEnvironment;
import io.github.cloudiator.rest.model.JobType;
import io.github.cloudiator.rest.model.Port;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.TaskInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a task of a job. 
 */
@ApiModel(description = "Represents a task of a job. ")

public class Task   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("ports")
  private List<Port> ports = null;

  @JsonProperty("interfaces")
  private List<TaskInterface> interfaces = null;

  @JsonProperty("executionEnvironment")
  private ExecutionEnvironment executionEnvironment = null;

  @JsonProperty("requirements")
  private List<Requirement> requirements = null;

  @JsonProperty("type")
  private JobType type = null;

  public Task name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Human-readable name. Uniquely identifies a task.
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Human-readable name. Uniquely identifies a task.")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Task ports(List<Port> ports) {
    this.ports = ports;
    return this;
  }

  public Task addPortsItem(Port portsItem) {
    if (this.ports == null) {
      this.ports = new ArrayList<Port>();
    }
    this.ports.add(portsItem);
    return this;
  }

   /**
   * Get ports
   * @return ports
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Port> getPorts() {
    return ports;
  }

  public void setPorts(List<Port> ports) {
    this.ports = ports;
  }

  public Task interfaces(List<TaskInterface> interfaces) {
    this.interfaces = interfaces;
    return this;
  }

  public Task addInterfacesItem(TaskInterface interfacesItem) {
    if (this.interfaces == null) {
      this.interfaces = new ArrayList<TaskInterface>();
    }
    this.interfaces.add(interfacesItem);
    return this;
  }

   /**
   * Interfaces of this task
   * @return interfaces
  **/
  @ApiModelProperty(value = "Interfaces of this task")

  @Valid

  public List<TaskInterface> getInterfaces() {
    return interfaces;
  }

  public void setInterfaces(List<TaskInterface> interfaces) {
    this.interfaces = interfaces;
  }

  public Task executionEnvironment(ExecutionEnvironment executionEnvironment) {
    this.executionEnvironment = executionEnvironment;
    return this;
  }

   /**
   * Get executionEnvironment
   * @return executionEnvironment
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public ExecutionEnvironment getExecutionEnvironment() {
    return executionEnvironment;
  }

  public void setExecutionEnvironment(ExecutionEnvironment executionEnvironment) {
    this.executionEnvironment = executionEnvironment;
  }

  public Task requirements(List<Requirement> requirements) {
    this.requirements = requirements;
    return this;
  }

  public Task addRequirementsItem(Requirement requirementsItem) {
    if (this.requirements == null) {
      this.requirements = new ArrayList<Requirement>();
    }
    this.requirements.add(requirementsItem);
    return this;
  }

   /**
   * Get requirements
   * @return requirements
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Requirement> getRequirements() {
    return requirements;
  }

  public void setRequirements(List<Requirement> requirements) {
    this.requirements = requirements;
  }

  public Task type(JobType type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public JobType getType() {
    return type;
  }

  public void setType(JobType type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Task task = (Task) o;
    return Objects.equals(this.name, task.name) &&
        Objects.equals(this.ports, task.ports) &&
        Objects.equals(this.interfaces, task.interfaces) &&
        Objects.equals(this.executionEnvironment, task.executionEnvironment) &&
        Objects.equals(this.requirements, task.requirements) &&
        Objects.equals(this.type, task.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, ports, interfaces, executionEnvironment, requirements, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Task {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    ports: ").append(toIndentedString(ports)).append("\n");
    sb.append("    interfaces: ").append(toIndentedString(interfaces)).append("\n");
    sb.append("    executionEnvironment: ").append(toIndentedString(executionEnvironment)).append("\n");
    sb.append("    requirements: ").append(toIndentedString(requirements)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

