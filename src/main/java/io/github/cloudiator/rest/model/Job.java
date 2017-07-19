package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Communication;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents an job. An job is a logical group of tasks. 
 */
@ApiModel(description = "Represents an job. An job is a logical group of tasks. ")

public class Job   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("tasks")
  private List<String> tasks = new ArrayList<String>();

  @JsonProperty("communications")
  private List<Communication> communications = new ArrayList<Communication>();

  public Job name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Job tasks(List<String> tasks) {
    this.tasks = tasks;
    return this;
  }

  public Job addTasksItem(String tasksItem) {
    this.tasks.add(tasksItem);
    return this;
  }

   /**
   * An array of tasks (referenced by names) that form this application. 
   * @return tasks
  **/
  @ApiModelProperty(required = true, value = "An array of tasks (referenced by names) that form this application. ")
  @NotNull


  public List<String> getTasks() {
    return tasks;
  }

  public void setTasks(List<String> tasks) {
    this.tasks = tasks;
  }

  public Job communications(List<Communication> communications) {
    this.communications = communications;
    return this;
  }

  public Job addCommunicationsItem(Communication communicationsItem) {
    this.communications.add(communicationsItem);
    return this;
  }

   /**
   * Get communications
   * @return communications
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Communication> getCommunications() {
    return communications;
  }

  public void setCommunications(List<Communication> communications) {
    this.communications = communications;
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
        Objects.equals(this.communications, job.communications);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, tasks, communications);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Job {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    tasks: ").append(toIndentedString(tasks)).append("\n");
    sb.append("    communications: ").append(toIndentedString(communications)).append("\n");
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

