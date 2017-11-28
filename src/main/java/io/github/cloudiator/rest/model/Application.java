package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Communication;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents an application. An application is a logical group of components.
 */
@ApiModel(description = "Represents an application. An application is a logical group of components. ")

public class Application {

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("components")
  private List<String> components = new ArrayList<String>();

  @JsonProperty("communications")
  private List<Communication> communications = new ArrayList<Communication>();

  public Application name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   *
   * @return name
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Application components(List<String> components) {
    this.components = components;
    return this;
  }

  public Application addComponentsItem(String componentsItem) {
    this.components.add(componentsItem);
    return this;
  }

  /**
   * An array of components (referenced by names) that form this application.
   *
   * @return components
   **/
  @ApiModelProperty(required = true, value = "An array of components (referenced by names) that form this application. ")
  @NotNull

  public List<String> getComponents() {
    return components;
  }

  public void setComponents(List<String> components) {
    this.components = components;
  }

  public Application communications(List<Communication> communications) {
    this.communications = communications;
    return this;
  }

  public Application addCommunicationsItem(Communication communicationsItem) {
    this.communications.add(communicationsItem);
    return this;
  }

  /**
   * Get communications
   *
   * @return communications
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Communication> getCommunications() {
    return communications;
  }

  public void setCommunications(List<Communication> communications) {
    this.communications = communications;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Application application = (Application) o;
    return Objects.equals(this.name, application.name) &&
        Objects.equals(this.components, application.components) &&
        Objects.equals(this.communications, application.communications);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, components, communications);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Application {\n");

    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    components: ").append(toIndentedString(components)).append("\n");
    sb.append("    communications: ").append(toIndentedString(communications)).append("\n");
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

