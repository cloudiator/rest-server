package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Requirement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subtype of Requirement 
 */
@ApiModel(description = "Subtype of Requirement ")
@Validated

public class IdentifierRequirement extends Requirement  {
  @JsonProperty("hardwareId")
  private String hardwareId = null;

  @JsonProperty("locationId")
  private String locationId = null;

  @JsonProperty("imageId")
  private String imageId = null;

  public IdentifierRequirement hardwareId(String hardwareId) {
    this.hardwareId = hardwareId;
    return this;
  }

   /**
   * Get hardwareId
   * @return hardwareId
  **/
  @ApiModelProperty(value = "")


  public String getHardwareId() {
    return hardwareId;
  }

  public void setHardwareId(String hardwareId) {
    this.hardwareId = hardwareId;
  }

  public IdentifierRequirement locationId(String locationId) {
    this.locationId = locationId;
    return this;
  }

   /**
   * Get locationId
   * @return locationId
  **/
  @ApiModelProperty(value = "")


  public String getLocationId() {
    return locationId;
  }

  public void setLocationId(String locationId) {
    this.locationId = locationId;
  }

  public IdentifierRequirement imageId(String imageId) {
    this.imageId = imageId;
    return this;
  }

   /**
   * Get imageId
   * @return imageId
  **/
  @ApiModelProperty(value = "")


  public String getImageId() {
    return imageId;
  }

  public void setImageId(String imageId) {
    this.imageId = imageId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IdentifierRequirement identifierRequirement = (IdentifierRequirement) o;
    return Objects.equals(this.hardwareId, identifierRequirement.hardwareId) &&
        Objects.equals(this.locationId, identifierRequirement.locationId) &&
        Objects.equals(this.imageId, identifierRequirement.imageId) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hardwareId, locationId, imageId, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IdentifierRequirement {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    hardwareId: ").append(toIndentedString(hardwareId)).append("\n");
    sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
    sb.append("    imageId: ").append(toIndentedString(imageId)).append("\n");
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

