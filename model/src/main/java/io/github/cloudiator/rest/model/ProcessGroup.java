package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Groups multiple processes.
 */
@ApiModel(description = "Groups multiple processes.")
@Validated

public class ProcessGroup   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("processes")
  @Valid
  private List<Process> processes = new ArrayList<Process>();

  public ProcessGroup id(String id) {
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

  public ProcessGroup processes(List<Process> processes) {
    this.processes = processes;
    return this;
  }

  public ProcessGroup addProcessesItem(Process processesItem) {
    this.processes.add(processesItem);
    return this;
  }

  /**
   * Get processes
   * @return processes
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Process> getProcesses() {
    return processes;
  }

  public void setProcesses(List<Process> processes) {
    this.processes = processes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessGroup processGroup = (ProcessGroup) o;
    return Objects.equals(this.id, processGroup.id) &&
        Objects.equals(this.processes, processGroup.processes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, processes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcessGroup {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    processes: ").append(toIndentedString(processes)).append("\n");
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

