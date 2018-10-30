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
 * Issues a request to the matchmaking component 
 */
@ApiModel(description = "Issues a request to the matchmaking component ")
@Validated

public class NodeRequirements   {
  @JsonProperty("requirements")
  @Valid
  private List<Requirement> requirements = null;

  @JsonProperty("optimization")
  private Optimization optimization = null;

  public NodeRequirements requirements(List<Requirement> requirements) {
    this.requirements = requirements;
    return this;
  }

  public NodeRequirements addRequirementsItem(Requirement requirementsItem) {
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

  public NodeRequirements optimization(Optimization optimization) {
    this.optimization = optimization;
    return this;
  }

  /**
   * Get optimization
   * @return optimization
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Optimization getOptimization() {
    return optimization;
  }

  public void setOptimization(Optimization optimization) {
    this.optimization = optimization;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NodeRequirements nodeRequirements = (NodeRequirements) o;
    return Objects.equals(this.requirements, nodeRequirements.requirements) &&
        Objects.equals(this.optimization, nodeRequirements.optimization);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requirements, optimization);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NodeRequirements {\n");
    
    sb.append("    requirements: ").append(toIndentedString(requirements)).append("\n");
    sb.append("    optimization: ").append(toIndentedString(optimization)).append("\n");
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

