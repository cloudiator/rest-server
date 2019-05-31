package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Distribution;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NormalDistribution
 */
@Validated

public class NormalDistribution extends Distribution  {
  @JsonProperty("mean")
  private Double mean = null;

  @JsonProperty("stdDev")
  private Double stdDev = null;

  public NormalDistribution mean(Double mean) {
    this.mean = mean;
    return this;
  }

  /**
   * Get mean
   * @return mean
  **/
  @ApiModelProperty(value = "")


  public Double getMean() {
    return mean;
  }

  public void setMean(Double mean) {
    this.mean = mean;
  }

  public NormalDistribution stdDev(Double stdDev) {
    this.stdDev = stdDev;
    return this;
  }

  /**
   * Get stdDev
   * @return stdDev
  **/
  @ApiModelProperty(value = "")


  public Double getStdDev() {
    return stdDev;
  }

  public void setStdDev(Double stdDev) {
    this.stdDev = stdDev;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NormalDistribution normalDistribution = (NormalDistribution) o;
    return Objects.equals(this.mean, normalDistribution.mean) &&
        Objects.equals(this.stdDev, normalDistribution.stdDev) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mean, stdDev, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NormalDistribution {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    mean: ").append(toIndentedString(mean)).append("\n");
    sb.append("    stdDev: ").append(toIndentedString(stdDev)).append("\n");
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

