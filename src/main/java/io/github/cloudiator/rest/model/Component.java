package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.cloudiator.rest.model.Port;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a component of an application.
 */
@ApiModel(description = "Represents a component of an application. ")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DockerComponent.class, name = "DockerComponent"),
    @JsonSubTypes.Type(value = LanceComponent.class, name = "LanceComponent"),
    @JsonSubTypes.Type(value = PlatformComponent.class, name = "PlatformComponent"),
})

public class Component {

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("ports")
  private List<Port> ports = null;

  public Component type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Discriminator for polymorphism.
   *
   * @return type
   **/
  @ApiModelProperty(required = true, value = "Discriminator for polymorphism. ")
  @NotNull

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Component name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable name. Uniquely identifies a component.
   *
   * @return name
   **/
  @ApiModelProperty(required = true, value = "Human-readable name. Uniquely identifies a component.")
  @NotNull

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Component ports(List<Port> ports) {
    this.ports = ports;
    return this;
  }

  public Component addPortsItem(Port portsItem) {
    if (this.ports == null) {
      this.ports = new ArrayList<Port>();
    }
    this.ports.add(portsItem);
    return this;
  }

  /**
   * Get ports
   *
   * @return ports
   **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Port> getPorts() {
    return ports;
  }

  public void setPorts(List<Port> ports) {
    this.ports = ports;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Component component = (Component) o;
    return Objects.equals(this.type, component.type) &&
        Objects.equals(this.name, component.name) &&
        Objects.equals(this.ports, component.ports);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, name, ports);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Component {\n");

    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    ports: ").append(toIndentedString(ports)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first
   * line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

