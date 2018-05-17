package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.OclRequirement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a request to create a new node fullfilling the given requirements 
 */
@ApiModel(description = "Represents a request to create a new node fullfilling the given requirements ")
@Validated

public class OclProblem   {
  @JsonProperty("requirements")
  @Valid
  private List<OclRequirement> requirements = null;

  public OclProblem requirements(List<OclRequirement> requirements) {
    this.requirements = requirements;
    return this;
  }

  public OclProblem addRequirementsItem(OclRequirement requirementsItem) {
    if (this.requirements == null) {
      this.requirements = new ArrayList<OclRequirement>();
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

  public List<OclRequirement> getRequirements() {
    return requirements;
  }

  public void setRequirements(List<OclRequirement> requirements) {
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
    OclProblem oclProblem = (OclProblem) o;
    return Objects.equals(this.requirements, oclProblem.requirements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requirements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OclProblem {\n");
    
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

