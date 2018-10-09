package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Interval;
import io.github.cloudiator.rest.model.Sensor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PullSensor
 */
@Validated

public class PullSensor extends Sensor  {
  @JsonProperty("className")
  private String className = null;

  @JsonProperty("configuration")
  private java.util.Map _configuration = null;

  @JsonProperty("interval")
  private Interval interval = null;

  public PullSensor className(String className) {
    this.className = className;
    return this;
  }

  /**
   * ClassName of the sensor
   * @return className
  **/
  @ApiModelProperty(value = "ClassName of the sensor")


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public PullSensor _configuration(java.util.Map _configuration) {
    this._configuration = _configuration;
    return this;
  }

  /**
   * Configuration of the sensor as key-value map
   * @return _configuration
  **/
  @ApiModelProperty(value = "Configuration of the sensor as key-value map")

  @Valid

  public java.util.Map getConfiguration() {
    return _configuration;
  }

  public void setConfiguration(java.util.Map _configuration) {
    this._configuration = _configuration;
  }

  public PullSensor interval(Interval interval) {
    this.interval = interval;
    return this;
  }

  /**
   * The interval at which the sensor is executed
   * @return interval
  **/
  @ApiModelProperty(value = "The interval at which the sensor is executed")

  @Valid

  public Interval getInterval() {
    return interval;
  }

  public void setInterval(Interval interval) {
    this.interval = interval;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PullSensor pullSensor = (PullSensor) o;
    return Objects.equals(this.className, pullSensor.className) &&
        Objects.equals(this._configuration, pullSensor._configuration) &&
        Objects.equals(this.interval, pullSensor.interval) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(className, _configuration, interval, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PullSensor {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    _configuration: ").append(toIndentedString(_configuration)).append("\n");
    sb.append("    interval: ").append(toIndentedString(interval)).append("\n");
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

