package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Property;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Repesents the configuration of a cloud. 
 */
@ApiModel(description = "Repesents the configuration of a cloud. ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-02T13:00:29.446+02:00")

public class CloudConfiguration   {
  @JsonProperty("nodeGroup")
  private String nodeGroup = null;

  @JsonProperty("properties")
  private List<Property> properties = null;

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

  public CloudConfiguration properties(List<Property> properties) {
    this.properties = properties;
    return this;
  }

  public CloudConfiguration addPropertiesItem(Property propertiesItem) {
    if (this.properties == null) {
      this.properties = new ArrayList<Property>();
    }
    this.properties.add(propertiesItem);
    return this;
  }

   /**
   * Array of configuration properties.
   * @return properties
  **/
  @ApiModelProperty(value = "Array of configuration properties.")

  @Valid

  public List<Property> getProperties() {
    return properties;
  }

  public void setProperties(List<Property> properties) {
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

