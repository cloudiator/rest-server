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
 * polymorphic superclass, only subtypes are allowed. Represents a communication port of a task 
 */
@ApiModel(description = "polymorphic superclass, only subtypes are allowed. Represents a communication port of a task ")
@Validated
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = PortRequired.class, name = "PortRequired"),
  @JsonSubTypes.Type(value = PortProvided.class, name = "PortProvided"),
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
   * Discriminator for polymorphism. Only subtypes are allowed. 
   * @return type
  **/
  @ApiModelProperty(required = true, value = "Discriminator for polymorphism. Only subtypes are allowed. ")
  @NotNull


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
  @ApiModelProperty(required = true, value = "Uniquely identifies a port. Defines the name of the environment variables holding IP addresses of remote tasks. ")
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

