package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.NodeCandidate;
import io.github.cloudiator.rest.model.NodeRequirements;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A request to start a node. May either contain requirements or a candidate. If requirements are passed, the matchmaking logic is used to derive the most suitable node candidate otherwise the given node candidate is used. If a node candidate is given, this one will be always prefered above the requirements. 
 */
@ApiModel(description = "A request to start a node. May either contain requirements or a candidate. If requirements are passed, the matchmaking logic is used to derive the most suitable node candidate otherwise the given node candidate is used. If a node candidate is given, this one will be always prefered above the requirements. ")
@Validated

public class NodeRequest   {
  @JsonProperty("groupName")
  private String groupName = null;

  @JsonProperty("requirements")
  private NodeRequirements requirements = null;

  @JsonProperty("nodeCandidate")
  private NodeCandidate nodeCandidate = null;

  public NodeRequest groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  /**
   * An (optional) name for all nodes starting in this group 
   * @return groupName
  **/
  @ApiModelProperty(value = "An (optional) name for all nodes starting in this group ")


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

  public NodeRequest nodeCandidate(NodeCandidate nodeCandidate) {
    this.nodeCandidate = nodeCandidate;
    return this;
  }

  /**
   * Get nodeCandidate
   * @return nodeCandidate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public NodeCandidate getNodeCandidate() {
    return nodeCandidate;
  }

  public void setNodeCandidate(NodeCandidate nodeCandidate) {
    this.nodeCandidate = nodeCandidate;
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
        Objects.equals(this.requirements, nodeRequest.requirements) &&
        Objects.equals(this.nodeCandidate, nodeRequest.nodeCandidate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupName, requirements, nodeCandidate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NodeRequest {\n");
    
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
    sb.append("    requirements: ").append(toIndentedString(requirements)).append("\n");
    sb.append("    nodeCandidate: ").append(toIndentedString(nodeCandidate)).append("\n");
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

