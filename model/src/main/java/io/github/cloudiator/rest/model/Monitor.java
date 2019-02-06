package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.DataSink;
import io.github.cloudiator.rest.model.MonitoringTarget;
import io.github.cloudiator.rest.model.Sensor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Monitor
 */
@Validated

public class Monitor   {
  @JsonProperty("metric")
  private String metric = null;

  @JsonProperty("targets")
  @Valid
  private List<MonitoringTarget> targets = null;

  @JsonProperty("sensor")
  private Sensor sensor = null;

  @JsonProperty("sinks")
  @Valid
  private List<DataSink> sinks = null;

  @JsonProperty("tags")
  private java.util.Map tags = null;

  public Monitor metric(String metric) {
    this.metric = metric;
    return this;
  }

  /**
   * Name of the collected metric
   * @return metric
  **/
  @ApiModelProperty(required = true, value = "Name of the collected metric")
  @NotNull


  public String getMetric() {
    return metric;
  }

  public void setMetric(String metric) {
    this.metric = metric;
  }

  public Monitor targets(List<MonitoringTarget> targets) {
    this.targets = targets;
    return this;
  }

  public Monitor addTargetsItem(MonitoringTarget targetsItem) {
    if (this.targets == null) {
      this.targets = new ArrayList<MonitoringTarget>();
    }
    this.targets.add(targetsItem);
    return this;
  }

  /**
   * Get targets
   * @return targets
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<MonitoringTarget> getTargets() {
    return targets;
  }

  public void setTargets(List<MonitoringTarget> targets) {
    this.targets = targets;
  }

  public Monitor sensor(Sensor sensor) {
    this.sensor = sensor;
    return this;
  }

  /**
   * Get sensor
   * @return sensor
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Sensor getSensor() {
    return sensor;
  }

  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }

  public Monitor sinks(List<DataSink> sinks) {
    this.sinks = sinks;
    return this;
  }

  public Monitor addSinksItem(DataSink sinksItem) {
    if (this.sinks == null) {
      this.sinks = new ArrayList<DataSink>();
    }
    this.sinks.add(sinksItem);
    return this;
  }

  /**
   * Get sinks
   * @return sinks
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<DataSink> getSinks() {
    return sinks;
  }

  public void setSinks(List<DataSink> sinks) {
    this.sinks = sinks;
  }

  public Monitor tags(java.util.Map tags) {
    this.tags = tags;
    return this;
  }

  /**
   * MonitoringTags as key-value map
   * @return tags
  **/
  @ApiModelProperty(value = "MonitoringTags as key-value map")

  @Valid

  public java.util.Map getTags() {
    return tags;
  }

  public void setTags(java.util.Map tags) {
    this.tags = tags;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Monitor monitor = (Monitor) o;
    return Objects.equals(this.metric, monitor.metric) &&
        Objects.equals(this.targets, monitor.targets) &&
        Objects.equals(this.sensor, monitor.sensor) &&
        Objects.equals(this.sinks, monitor.sinks) &&
        Objects.equals(this.tags, monitor.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metric, targets, sensor, sinks, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Monitor {\n");
    
    sb.append("    metric: ").append(toIndentedString(metric)).append("\n");
    sb.append("    targets: ").append(toIndentedString(targets)).append("\n");
    sb.append("    sensor: ").append(toIndentedString(sensor)).append("\n");
    sb.append("    sinks: ").append(toIndentedString(sinks)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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

