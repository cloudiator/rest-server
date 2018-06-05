package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.DataSink;
import io.github.cloudiator.rest.model.MonitoringTag;
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
 * MonitorNew
 */
@Validated

public class MonitorNew   {
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
  @Valid
  private List<MonitoringTag> tags = null;

  public MonitorNew metric(String metric) {
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

  public MonitorNew targets(List<MonitoringTarget> targets) {
    this.targets = targets;
    return this;
  }

  public MonitorNew addTargetsItem(MonitoringTarget targetsItem) {
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

  public MonitorNew sensor(Sensor sensor) {
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

  public MonitorNew sinks(List<DataSink> sinks) {
    this.sinks = sinks;
    return this;
  }

  public MonitorNew addSinksItem(DataSink sinksItem) {
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

  public MonitorNew tags(List<MonitoringTag> tags) {
    this.tags = tags;
    return this;
  }

  public MonitorNew addTagsItem(MonitoringTag tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<MonitoringTag>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<MonitoringTag> getTags() {
    return tags;
  }

  public void setTags(List<MonitoringTag> tags) {
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
    MonitorNew monitorNew = (MonitorNew) o;
    return Objects.equals(this.metric, monitorNew.metric) &&
        Objects.equals(this.targets, monitorNew.targets) &&
        Objects.equals(this.sensor, monitorNew.sensor) &&
        Objects.equals(this.sinks, monitorNew.sinks) &&
        Objects.equals(this.tags, monitorNew.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metric, targets, sensor, sinks, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MonitorNew {\n");
    
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

