package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents an image offer by a cloud 
 */
@ApiModel(description = "Represents an image offer by a cloud ")
@Validated

public class Image   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("providerId")
  private String providerId = null;

  @JsonProperty("operatingSystem")
  private OperatingSystem operatingSystem = null;

  @JsonProperty("location")
  private Location location = null;

  public Image id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier for this image
   * @return id
  **/
  @ApiModelProperty(example = "1a79a4d60de6718e8e5b326e338ae533/RegionOne/1", required = true, value = "Unique identifier for this image")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Image name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable name
   * @return name
  **/
  @ApiModelProperty(example = "Ubuntu 16.04 LTS AMD 64", required = true, value = "Human-readable name")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Image providerId(String providerId) {
    this.providerId = providerId;
    return this;
  }

  /**
   * Original id issued by provider
   * @return providerId
  **/
  @ApiModelProperty(example = "1", required = true, value = "Original id issued by provider")
  @NotNull


  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public Image operatingSystem(OperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
    return this;
  }

  /**
   * Get operatingSystem
   * @return operatingSystem
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public OperatingSystem getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(OperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  public Image location(Location location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image image = (Image) o;
    return Objects.equals(this.id, image.id) &&
        Objects.equals(this.name, image.name) &&
        Objects.equals(this.providerId, image.providerId) &&
        Objects.equals(this.operatingSystem, image.operatingSystem) &&
        Objects.equals(this.location, image.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, providerId, operatingSystem, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Image {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    providerId: ").append(toIndentedString(providerId)).append("\n");
    sb.append("    operatingSystem: ").append(toIndentedString(operatingSystem)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
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

