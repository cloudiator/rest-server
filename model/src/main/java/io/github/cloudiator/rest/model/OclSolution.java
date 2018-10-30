package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Represents a solution to an ocl problem 
 */
@ApiModel(description = "Represents a solution to an ocl problem ")
@Validated

public class OclSolution   {
  @JsonProperty("nodes")
  @Valid
  private List<VirtualMachineRequest> nodes = null;

  public OclSolution nodes(List<VirtualMachineRequest> nodes) {
    this.nodes = nodes;
    return this;
  }

  public OclSolution addNodesItem(VirtualMachineRequest nodesItem) {
    if (this.nodes == null) {
      this.nodes = new ArrayList<VirtualMachineRequest>();
    }
    this.nodes.add(nodesItem);
    return this;
  }

   /**
   * Get nodes
   * @return nodes
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<VirtualMachineRequest> getNodes() {
    return nodes;
  }

  public void setNodes(List<VirtualMachineRequest> nodes) {
    this.nodes = nodes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OclSolution oclSolution = (OclSolution) o;
    return Objects.equals(this.nodes, oclSolution.nodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OclSolution {\n");
    
    sb.append("    nodes: ").append(toIndentedString(nodes)).append("\n");
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

