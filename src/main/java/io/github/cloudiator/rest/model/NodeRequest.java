package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.NodeRequirements;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NodeRequest
 */
@Validated

public class NodeRequest   {
  @JsonProperty("groupName")
  private String groupName = null;

  @JsonProperty("requirements")
  private NodeRequirements requirements = null;

  public NodeRequest groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  /**
   * Get groupName
   * @return groupName
  **/
  @ApiModelProperty(value = "")


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public NodeRequest requirements(NodeRequirements requirements) {
    this.requirements = requirements;
    return this;
  }

  /**
   * Get requirements
   * @return requirements
  **/
  @ApiModelProperty(value = "")

  @Valid

  public NodeRequirements getRequirements() {
    return requirements;
  }

  public void setRequirements(NodeRequirements requirements) {
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
    NodeRequest nodeRequest = (NodeRequest) o;
    return Objects.equals(this.groupName, nodeRequest.groupName) &&
        Objects.equals(this.requirements, nodeRequest.requirements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupName, requirements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NodeRequest {\n");
    
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
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

