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
 * Refers to the attribute that should be optimized
 */
@ApiModel(description = "Refers to the attribute that should be optimized")
@Validated

public class AttributeOptimization extends Optimization  {
  @JsonProperty("objectiveClass")
  private String objectiveClass = null;

  @JsonProperty("objectiveAttribute")
  private String objectiveAttribute = null;

  /**
   * Gets or Sets aggregation
   */
  public enum AggregationEnum {
    SUM("SUM"),
    
    AVG("AVG");

    private String value;

    AggregationEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AggregationEnum fromValue(String text) {
      for (AggregationEnum b : AggregationEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("aggregation")
  private AggregationEnum aggregation = null;

  public AttributeOptimization objectiveClass(String objectiveClass) {
    this.objectiveClass = objectiveClass;
    return this;
  }

  /**
   * Get objectiveClass
   * @return objectiveClass
  **/
  @ApiModelProperty(value = "")


  public String getObjectiveClass() {
    return objectiveClass;
  }

  public void setObjectiveClass(String objectiveClass) {
    this.objectiveClass = objectiveClass;
  }

  public AttributeOptimization objectiveAttribute(String objectiveAttribute) {
    this.objectiveAttribute = objectiveAttribute;
    return this;
  }

  /**
   * Get objectiveAttribute
   * @return objectiveAttribute
  **/
  @ApiModelProperty(value = "")


  public String getObjectiveAttribute() {
    return objectiveAttribute;
  }

  public void setObjectiveAttribute(String objectiveAttribute) {
    this.objectiveAttribute = objectiveAttribute;
  }

  public AttributeOptimization aggregation(AggregationEnum aggregation) {
    this.aggregation = aggregation;
    return this;
  }

  /**
   * Get aggregation
   * @return aggregation
  **/
  @ApiModelProperty(value = "")


  public AggregationEnum getAggregation() {
    return aggregation;
  }

  public void setAggregation(AggregationEnum aggregation) {
    this.aggregation = aggregation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttributeOptimization attributeOptimization = (AttributeOptimization) o;
    return Objects.equals(this.objectiveClass, attributeOptimization.objectiveClass) &&
        Objects.equals(this.objectiveAttribute, attributeOptimization.objectiveAttribute) &&
        Objects.equals(this.aggregation, attributeOptimization.aggregation) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectiveClass, objectiveAttribute, aggregation, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttributeOptimization {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    objectiveClass: ").append(toIndentedString(objectiveClass)).append("\n");
    sb.append("    objectiveAttribute: ").append(toIndentedString(objectiveAttribute)).append("\n");
    sb.append("    aggregation: ").append(toIndentedString(aggregation)).append("\n");
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

