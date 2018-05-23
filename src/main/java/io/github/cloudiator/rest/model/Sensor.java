package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a sensor used for collecting monitoring data 
 */
@ApiModel(description = "Represents a sensor used for collecting monitoring data ")
@Validated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = PullSensor.class, name = "PullSensor"),
  @JsonSubTypes.Type(value = PushSensor.class, name = "PushSensor"),
})

public class Sensor   {
  @JsonProperty("type")
  private String type = null;

  @JsonProperty("metric")
  private String metric = null;

  public Sensor type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Discriminator used for sensor polymorphism
   * @return type
  **/
  @ApiModelProperty(value = "Discriminator used for sensor polymorphism")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Sensor metric(String metric) {
    this.metric = metric;
    return this;
  }

   /**
   * Name of the collected metric
   * @return metric
  **/
  @ApiModelProperty(value = "Name of the collected metric")


  public String getMetric() {
    return metric;
  }

  public void setMetric(String metric) {
    this.metric = metric;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sensor sensor = (Sensor) o;
    return Objects.equals(this.type, sensor.type) &&
        Objects.equals(this.metric, sensor.metric);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, metric);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sensor {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    metric: ").append(toIndentedString(metric)).append("\n");
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

