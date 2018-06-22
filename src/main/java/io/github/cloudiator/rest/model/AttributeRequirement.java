package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Requirement;
import io.github.cloudiator.rest.model.RequirementOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subtype of Requirement 
 */
@ApiModel(description = "Subtype of Requirement ")
@Validated

public class AttributeRequirement extends Requirement  {
  @JsonProperty("requirementClass")
  private String requirementClass = null;

  @JsonProperty("requirementAttribute")
  private String requirementAttribute = null;

  @JsonProperty("requirementOperator")
  private RequirementOperator requirementOperator = null;

  @JsonProperty("value")
  private String value = null;

  public AttributeRequirement requirementClass(String requirementClass) {
    this.requirementClass = requirementClass;
    return this;
  }

  /**
   * Get requirementClass
   * @return requirementClass
  **/
  @ApiModelProperty(value = "")


  public String getRequirementClass() {
    return requirementClass;
  }

  public void setRequirementClass(String requirementClass) {
    this.requirementClass = requirementClass;
  }

  public AttributeRequirement requirementAttribute(String requirementAttribute) {
    this.requirementAttribute = requirementAttribute;
    return this;
  }

  /**
   * Get requirementAttribute
   * @return requirementAttribute
  **/
  @ApiModelProperty(value = "")


  public String getRequirementAttribute() {
    return requirementAttribute;
  }

  public void setRequirementAttribute(String requirementAttribute) {
    this.requirementAttribute = requirementAttribute;
  }

  public AttributeRequirement requirementOperator(RequirementOperator requirementOperator) {
    this.requirementOperator = requirementOperator;
    return this;
  }

  /**
   * Get requirementOperator
   * @return requirementOperator
  **/
  @ApiModelProperty(value = "")

  @Valid

  public RequirementOperator getRequirementOperator() {
    return requirementOperator;
  }

  public void setRequirementOperator(RequirementOperator requirementOperator) {
    this.requirementOperator = requirementOperator;
  }

  public AttributeRequirement value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  **/
  @ApiModelProperty(value = "")


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttributeRequirement attributeRequirement = (AttributeRequirement) o;
    return Objects.equals(this.requirementClass, attributeRequirement.requirementClass) &&
        Objects.equals(this.requirementAttribute, attributeRequirement.requirementAttribute) &&
        Objects.equals(this.requirementOperator, attributeRequirement.requirementOperator) &&
        Objects.equals(this.value, attributeRequirement.value) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requirementClass, requirementAttribute, requirementOperator, value, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttributeRequirement {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    requirementClass: ").append(toIndentedString(requirementClass)).append("\n");
    sb.append("    requirementAttribute: ").append(toIndentedString(requirementAttribute)).append("\n");
    sb.append("    requirementOperator: ").append(toIndentedString(requirementOperator)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

