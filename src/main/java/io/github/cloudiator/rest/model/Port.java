package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a communication port of a task 
 */
@ApiModel(description = "Represents a communication port of a task ")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = PortProvided.class, name = "PortProvided"),
  @JsonSubTypes.Type(value = PortRequired.class, name = "PortRequired"),
})

public class Port   {
  @JsonProperty("type")
  private String type = null;

  @JsonProperty("name")
  private String name = null;

  public Port type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Discriminator for polymorphism. 
   * @return type
  **/
  @ApiModelProperty(value = "Discriminator for polymorphism. ")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Port name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Uniquely identifies a port. Defines the name of the environment variables holding IP addresses of remote tasks. 
   * @return name
  **/
  @ApiModelProperty(example = "LOADBALANCERREQWIKI", required = true, value = "Uniquely identifies a port. Defines the name of the environment variables holding IP addresses of remote tasks. ")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Port port = (Port) o;
    return Objects.equals(this.type, port.type) &&
        Objects.equals(this.name, port.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Port {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

