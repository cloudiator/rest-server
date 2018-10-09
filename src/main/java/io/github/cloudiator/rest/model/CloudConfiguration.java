package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Repesents the configuration of a cloud. 
 */
@ApiModel(description = "Repesents the configuration of a cloud. ")
@Validated

public class CloudConfiguration   {
  @JsonProperty("nodeGroup")
  private String nodeGroup = null;

  @JsonProperty("properties")
  private java.util.Map properties = null;

  public CloudConfiguration nodeGroup(String nodeGroup) {
    this.nodeGroup = nodeGroup;
    return this;
  }

  /**
   * A prefix all Cloudiator managed entities will belong to.
   * @return nodeGroup
  **/
  @ApiModelProperty(example = "cloudiator", value = "A prefix all Cloudiator managed entities will belong to.")


  public String getNodeGroup() {
    return nodeGroup;
  }

  public void setNodeGroup(String nodeGroup) {
    this.nodeGroup = nodeGroup;
  }

  public CloudConfiguration properties(java.util.Map properties) {
    this.properties = properties;
    return this;
  }

  /**
   * Configuration as key-value map.
   * @return properties
  **/
  @ApiModelProperty(value = "Configuration as key-value map.")

  @Valid

  public java.util.Map getProperties() {
    return properties;
  }

  public void setProperties(java.util.Map properties) {
    this.properties = properties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CloudConfiguration cloudConfiguration = (CloudConfiguration) o;
    return Objects.equals(this.nodeGroup, cloudConfiguration.nodeGroup) &&
        Objects.equals(this.properties, cloudConfiguration.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeGroup, properties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CloudConfiguration {\n");
    
    sb.append("    nodeGroup: ").append(toIndentedString(nodeGroup)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
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

