package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
  @JsonProperty("target")
  private MonitoringTarget target = null;

  @JsonProperty("sensor")
  private Sensor sensor = null;

  @JsonProperty("tags")
  @Valid
  private List<MonitoringTag> tags = null;

  public MonitorNew target(MonitoringTarget target) {
    this.target = target;
    return this;
  }

   /**
   * Get target
   * @return target
  **/
  @ApiModelProperty(value = "")

  @Valid

  public MonitoringTarget getTarget() {
    return target;
  }

  public void setTarget(MonitoringTarget target) {
    this.target = target;
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
    return Objects.equals(this.target, monitorNew.target) &&
        Objects.equals(this.sensor, monitorNew.sensor) &&
        Objects.equals(this.tags, monitorNew.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(target, sensor, tags);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MonitorNew {\n");
    
    sb.append("    target: ").append(toIndentedString(target)).append("\n");
    sb.append("    sensor: ").append(toIndentedString(sensor)).append("\n");
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

