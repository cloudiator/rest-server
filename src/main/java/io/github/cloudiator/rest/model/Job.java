package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Communication;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.Task;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents an job. An job is a logical group of tasks. 
 */
@ApiModel(description = "Represents an job. An job is a logical group of tasks. ")
@Validated

public class Job   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("tasks")
  @Valid
  private List<Task> tasks = null;

  @JsonProperty("communications")
  @Valid
  private List<Communication> communications = null;

  @JsonProperty("requirements")
  @Valid
  private List<Requirement> requirements = null;

  public Job name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "MediaWiki Application", required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Job tasks(List<Task> tasks) {
    this.tasks = tasks;
    return this;
  }

  public Job addTasksItem(Task tasksItem) {
    if (this.tasks == null) {
      this.tasks = new ArrayList<Task>();
    }
    this.tasks.add(tasksItem);
    return this;
  }

  /**
   * An array of tasks that form this application. 
   * @return tasks
  **/
  @ApiModelProperty(value = "An array of tasks that form this application. ")

  @Valid

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public Job communications(List<Communication> communications) {
    this.communications = communications;
    return this;
  }

  public Job addCommunicationsItem(Communication communicationsItem) {
    if (this.communications == null) {
      this.communications = new ArrayList<Communication>();
    }
    this.communications.add(communicationsItem);
    return this;
  }

  /**
   * Get communications
   * @return communications
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Communication> getCommunications() {
    return communications;
  }

  public void setCommunications(List<Communication> communications) {
    this.communications = communications;
  }

  public Job requirements(List<Requirement> requirements) {
    this.requirements = requirements;
    return this;
  }

  public Job addRequirementsItem(Requirement requirementsItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Job job = (Job) o;
    return Objects.equals(this.name, job.name) &&
        Objects.equals(this.tasks, job.tasks) &&
        Objects.equals(this.communications, job.communications) &&
        Objects.equals(this.requirements, job.requirements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, tasks, communications, requirements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Job {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    tasks: ").append(toIndentedString(tasks)).append("\n");
    sb.append("    communications: ").append(toIndentedString(communications)).append("\n");
    sb.append("    requirements: ").append(toIndentedString(requirements)).append("\n");
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

