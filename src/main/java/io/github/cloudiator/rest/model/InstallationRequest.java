package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Node;
import io.github.cloudiator.rest.model.Tool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents an Cloudiator tools installation request 
 */
@ApiModel(description = "Represents an Cloudiator tools installation request ")

public class InstallationRequest   {
  @JsonProperty("tools")
  private List<Tool> tools = new ArrayList<Tool>();

  @JsonProperty("node")
  private Node node = null;

  public InstallationRequest tools(List<Tool> tools) {
    this.tools = tools;
    return this;
  }

  public InstallationRequest addToolsItem(Tool toolsItem) {
    this.tools.add(toolsItem);
    return this;
  }

   /**
   * Unique identifier for this image
   * @return tools
  **/
  @ApiModelProperty(required = true, value = "Unique identifier for this image")
  @NotNull

  @Valid

  public List<Tool> getTools() {
    return tools;
  }

  public void setTools(List<Tool> tools) {
    this.tools = tools;
  }

  public InstallationRequest node(Node node) {
    this.node = node;
    return this;
  }

   /**
   * Get node
   * @return node
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InstallationRequest installationRequest = (InstallationRequest) o;
    return Objects.equals(this.tools, installationRequest.tools) &&
        Objects.equals(this.node, installationRequest.node);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tools, node);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InstallationRequest {\n");
    
    sb.append("    tools: ").append(toIndentedString(tools)).append("\n");
    sb.append("    node: ").append(toIndentedString(node)).append("\n");
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

