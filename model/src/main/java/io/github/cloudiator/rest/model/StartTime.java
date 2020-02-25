package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Distribution;
import io.github.cloudiator.rest.model.TimeUnit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * StartTime
 */
@Validated

public class StartTime   {
  @JsonProperty("distribution")
  private Distribution distribution = null;

  @JsonProperty("unit")
  private TimeUnit unit = null;

  public StartTime distribution(Distribution distribution) {
    this.distribution = distribution;
    return this;
  }

  /**
   * Get distribution
   * @return distribution
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Distribution getDistribution() {
    return distribution;
  }

  public void setDistribution(Distribution distribution) {
    this.distribution = distribution;
  }

  public StartTime unit(TimeUnit unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Get unit
   * @return unit
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TimeUnit getUnit() {
    return unit;
  }

  public void setUnit(TimeUnit unit) {
    this.unit = unit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartTime startTime = (StartTime) o;
    return Objects.equals(this.distribution, startTime.distribution) &&
        Objects.equals(this.unit, startTime.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(distribution, unit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StartTime {\n");
    
    sb.append("    distribution: ").append(toIndentedString(distribution)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
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

