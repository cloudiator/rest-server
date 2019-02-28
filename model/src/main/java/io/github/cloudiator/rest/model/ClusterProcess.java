package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.CloudiatorProcess;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ClusterProcess
 */
@Validated

public class ClusterProcess extends CloudiatorProcess  {
  @JsonProperty("nodes")
  @Valid
  private List<String> nodes = new ArrayList<String>();

  public ClusterProcess nodes(List<String> nodes) {
    this.nodes = nodes;
    return this;
  }

  public ClusterProcess addNodesItem(String nodesItem) {
    this.nodes.add(nodesItem);
    return this;
  }

  /**
   * A list of node identifiers this process is hosted on.
   * @return nodes
  **/
  @ApiModelProperty(required = true, value = "A list of node identifiers this process is hosted on.")
  @NotNull


  public List<String> getNodes() {
    return nodes;
  }

  public void setNodes(List<String> nodes) {
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
    ClusterProcess clusterProcess = (ClusterProcess) o;
    return Objects.equals(this.nodes, clusterProcess.nodes) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodes, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClusterProcess {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

