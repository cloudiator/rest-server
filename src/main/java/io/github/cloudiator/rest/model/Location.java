package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.Location;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Repesents a (virtual) location offers by a cloud 
 */
@ApiModel(description = "Repesents a (virtual) location offers by a cloud ")

public class Location   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("providerId")
  private String providerId = null;

  /**
   * Scope of the location
   */
  public enum LocationScopeEnum {
    PROVIDER("PROVIDER"),
    
    REGION("REGION"),
    
    ZONE("ZONE"),
    
    HOST("HOST");

    private String value;

    LocationScopeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static LocationScopeEnum fromValue(String text) {
      for (LocationScopeEnum b : LocationScopeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("locationScope")
  private LocationScopeEnum locationScope = null;

  @JsonProperty("isAssignable")
  private Boolean isAssignable = null;

  @JsonProperty("parent")
  private Location parent = null;

  public Location id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier
   * @return id
  **/
  @ApiModelProperty(example = "1a79a4d60de6718e8e5b326e338ae533/RegionOne", required = true, value = "Unique identifier")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Location name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Human-readable name
   * @return name
  **/
  @ApiModelProperty(example = "RegionOne", required = true, value = "Human-readable name")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Location providerId(String providerId) {
    this.providerId = providerId;
    return this;
  }

   /**
   * Original id issued by the provider
   * @return providerId
  **/
  @ApiModelProperty(example = "RegionOne", required = true, value = "Original id issued by the provider")
  @NotNull


  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public Location locationScope(LocationScopeEnum locationScope) {
    this.locationScope = locationScope;
    return this;
  }

   /**
   * Scope of the location
   * @return locationScope
  **/
  @ApiModelProperty(example = "ZONE", required = true, value = "Scope of the location")
  @NotNull


  public LocationScopeEnum getLocationScope() {
    return locationScope;
  }

  public void setLocationScope(LocationScopeEnum locationScope) {
    this.locationScope = locationScope;
  }

  public Location isAssignable(Boolean isAssignable) {
    this.isAssignable = isAssignable;
    return this;
  }

   /**
   * True of the location can be used to start virtual machines, false if not
   * @return isAssignable
  **/
  @ApiModelProperty(example = "true", required = true, value = "True of the location can be used to start virtual machines, false if not")
  @NotNull


  public Boolean getIsAssignable() {
    return isAssignable;
  }

  public void setIsAssignable(Boolean isAssignable) {
    this.isAssignable = isAssignable;
  }

  public Location parent(Location parent) {
    this.parent = parent;
    return this;
  }

   /**
   * Get parent
   * @return parent
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Location getParent() {
    return parent;
  }

  public void setParent(Location parent) {
    this.parent = parent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location = (Location) o;
    return Objects.equals(this.id, location.id) &&
        Objects.equals(this.name, location.name) &&
        Objects.equals(this.providerId, location.providerId) &&
        Objects.equals(this.locationScope, location.locationScope) &&
        Objects.equals(this.isAssignable, location.isAssignable) &&
        Objects.equals(this.parent, location.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, providerId, locationScope, isAssignable, parent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Location {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    providerId: ").append(toIndentedString(providerId)).append("\n");
    sb.append("    locationScope: ").append(toIndentedString(locationScope)).append("\n");
    sb.append("    isAssignable: ").append(toIndentedString(isAssignable)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
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

