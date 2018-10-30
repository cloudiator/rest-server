package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Superclass for polymorphism, only subtypes are allowed
 */
@ApiModel(description = "Superclass for polymorphism, only subtypes are allowed")
@Validated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = AttributeOptimization.class, name = "AttributeOptimization"),
  @JsonSubTypes.Type(value = OCLOptimization.class, name = "OCLOptimization"),
})

public class Optimization   {
  @JsonProperty("type")
  private String type = null;

  /**
   * Gets or Sets objective
   */
  public enum ObjectiveEnum {
    MAXIMIZE("MAXIMIZE"),
    
    MINIMIZE("MINIMIZE");

    private String value;

    ObjectiveEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ObjectiveEnum fromValue(String text) {
      for (ObjectiveEnum b : ObjectiveEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("objective")
  private ObjectiveEnum objective = null;

  public Optimization type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Optimization objective(ObjectiveEnum objective) {
    this.objective = objective;
    return this;
  }

  /**
   * Get objective
   * @return objective
  **/
  @ApiModelProperty(value = "")


  public ObjectiveEnum getObjective() {
    return objective;
  }

  public void setObjective(ObjectiveEnum objective) {
    this.objective = objective;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Optimization optimization = (Optimization) o;
    return Objects.equals(this.type, optimization.type) &&
        Objects.equals(this.objective, optimization.objective);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, objective);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Optimization {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    objective: ").append(toIndentedString(objective)).append("\n");
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

