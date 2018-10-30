package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * NodeGroup
 */
@Validated

public class NodeGroup   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("nodes")
  @Valid
  private List<Node> nodes = null;

  public NodeGroup id(String id) {
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

  public NodeGroup nodes(List<Node> nodes) {
    this.nodes = nodes;
    return this;
  }

  public NodeGroup addNodesItem(Node nodesItem) {
    if (this.nodes == null) {
      this.nodes = new ArrayList<Node>();
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

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
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
    NodeGroup nodeGroup = (NodeGroup) o;
    return Objects.equals(this.id, nodeGroup.id) &&
        Objects.equals(this.nodes, nodeGroup.nodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NodeGroup {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

