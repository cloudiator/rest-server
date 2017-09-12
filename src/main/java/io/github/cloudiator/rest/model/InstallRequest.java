package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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

public class InstallRequest   {
  @JsonProperty("tools")
  private List<Tool> tools = new ArrayList<Tool>();

  @JsonProperty("node")
  private Object node = null;

  public InstallRequest tools(List<Tool> tools) {
    this.tools = tools;
    return this;
  }

  public InstallRequest addToolsItem(Tool toolsItem) {
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

  public InstallRequest node(Object node) {
    this.node = node;
    return this;
  }

   /**
   * Human-readable name
   * @return node
  **/
  @ApiModelProperty(example = "\"Ubuntu 16.04 LTS AMD 64\"", required = true, value = "Human-readable name")
  @NotNull


  public Object getNode() {
    return node;
  }

  public void setNode(Object node) {
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
    InstallRequest installRequest = (InstallRequest) o;
    return Objects.equals(this.tools, installRequest.tools) &&
        Objects.equals(this.node, installRequest.node);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tools, node);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InstallRequest {\n");
    
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

