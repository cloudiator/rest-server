package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents the operating system of an image 
 */
@ApiModel(description = "Represents the operating system of an image ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-30T11:42:58.188+02:00")

public class OperatingSystem   {
  @JsonProperty("operatingSystemFamily")
  private String operatingSystemFamily = null;

  @JsonProperty("operatingSystemArchitecture")
  private String operatingSystemArchitecture = null;

  @JsonProperty("operatingSystemVersion")
  private String operatingSystemVersion = null;

  public OperatingSystem operatingSystemFamily(String operatingSystemFamily) {
    this.operatingSystemFamily = operatingSystemFamily;
    return this;
  }

   /**
   * Family of the OS
   * @return operatingSystemFamily
  **/
  @ApiModelProperty(value = "Family of the OS")


  public String getOperatingSystemFamily() {
    return operatingSystemFamily;
  }

  public void setOperatingSystemFamily(String operatingSystemFamily) {
    this.operatingSystemFamily = operatingSystemFamily;
  }

  public OperatingSystem operatingSystemArchitecture(String operatingSystemArchitecture) {
    this.operatingSystemArchitecture = operatingSystemArchitecture;
    return this;
  }

   /**
   * Architecture of the OS
   * @return operatingSystemArchitecture
  **/
  @ApiModelProperty(value = "Architecture of the OS")


  public String getOperatingSystemArchitecture() {
    return operatingSystemArchitecture;
  }

  public void setOperatingSystemArchitecture(String operatingSystemArchitecture) {
    this.operatingSystemArchitecture = operatingSystemArchitecture;
  }

  public OperatingSystem operatingSystemVersion(String operatingSystemVersion) {
    this.operatingSystemVersion = operatingSystemVersion;
    return this;
  }

   /**
   * Version of the OS
   * @return operatingSystemVersion
  **/
  @ApiModelProperty(value = "Version of the OS")


  public String getOperatingSystemVersion() {
    return operatingSystemVersion;
  }

  public void setOperatingSystemVersion(String operatingSystemVersion) {
    this.operatingSystemVersion = operatingSystemVersion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperatingSystem operatingSystem = (OperatingSystem) o;
    return Objects.equals(this.operatingSystemFamily, operatingSystem.operatingSystemFamily) &&
        Objects.equals(this.operatingSystemArchitecture, operatingSystem.operatingSystemArchitecture) &&
        Objects.equals(this.operatingSystemVersion, operatingSystem.operatingSystemVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operatingSystemFamily, operatingSystemArchitecture, operatingSystemVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperatingSystem {\n");
    
    sb.append("    operatingSystemFamily: ").append(toIndentedString(operatingSystemFamily)).append("\n");
    sb.append("    operatingSystemArchitecture: ").append(toIndentedString(operatingSystemArchitecture)).append("\n");
    sb.append("    operatingSystemVersion: ").append(toIndentedString(operatingSystemVersion)).append("\n");
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

