package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * Tagging for monitoring
 */
@ApiModel(description = "Tagging for monitoring")
@Validated

public class MonitoringTag   {
  @JsonProperty("key")
  private String key = null;

  @JsonProperty("value")
  private String value = null;

  public MonitoringTag key(String key) {
    this.key = key;
    return this;
  }

  /**
   * Key of the tag
   * @return key
  **/
  @ApiModelProperty(value = "Key of the tag")


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public MonitoringTag value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Value of the tag
   * @return value
  **/
  @ApiModelProperty(value = "Value of the tag")


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MonitoringTag monitoringTag = (MonitoringTag) o;
    return Objects.equals(this.key, monitoringTag.key) &&
        Objects.equals(this.value, monitoringTag.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MonitoringTag {\n");
    
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

