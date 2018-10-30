package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.Optimization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An OCL attribute expression that should be optimized
 */
@ApiModel(description = "An OCL attribute expression that should be optimized")
@Validated

public class OCLOptimization extends Optimization  {
  @JsonProperty("expression")
  private String expression = null;

  public OCLOptimization expression(String expression) {
    this.expression = expression;
    return this;
  }

  /**
   * The expression that is to be opimized. 
   * @return expression
  **/
  @ApiModelProperty(value = "The expression that is to be opimized. ")


  public String getExpression() {
    return expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OCLOptimization ocLOptimization = (OCLOptimization) o;
    return Objects.equals(this.expression, ocLOptimization.expression) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expression, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OCLOptimization {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    expression: ").append(toIndentedString(expression)).append("\n");
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

