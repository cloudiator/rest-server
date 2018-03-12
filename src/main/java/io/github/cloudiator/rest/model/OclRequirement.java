package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Requirement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subytpe of Requirement 
 */
@ApiModel(description = "Subytpe of Requirement ")
@Validated

public class OclRequirement extends Requirement  {
  @JsonProperty("constraint")
  private String constraint = null;

  public OclRequirement constraint(String constraint) {
    this.constraint = constraint;
    return this;
  }

   /**
   * Get constraint
   * @return constraint
  **/
  @ApiModelProperty(value = "")


  public String getConstraint() {
    return constraint;
  }

  public void setConstraint(String constraint) {
    this.constraint = constraint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OclRequirement oclRequirement = (OclRequirement) o;
    return Objects.equals(this.constraint, oclRequirement.constraint) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(constraint, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OclRequirement {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    constraint: ").append(toIndentedString(constraint)).append("\n");
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

