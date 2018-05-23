package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.GeoLocation;
import io.github.cloudiator.rest.model.OperatingSystem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * attributes defining a node
 */
@ApiModel(description = "attributes defining a node")
@Validated

public class NodeProperties   {
  @JsonProperty("numberOfCores")
  private Integer numberOfCores = null;

  @JsonProperty("memory")
  private Long memory = null;

  @JsonProperty("disk")
  private Float disk = null;

  @JsonProperty("operatingSystem")
  private OperatingSystem operatingSystem = null;

  @JsonProperty("geoLocation")
  private GeoLocation geoLocation = null;

  public NodeProperties numberOfCores(Integer numberOfCores) {
    this.numberOfCores = numberOfCores;
    return this;
  }

   /**
   * Get numberOfCores
   * @return numberOfCores
  **/
  @ApiModelProperty(value = "")


  public Integer getNumberOfCores() {
    return numberOfCores;
  }

  public void setNumberOfCores(Integer numberOfCores) {
    this.numberOfCores = numberOfCores;
  }

  public NodeProperties memory(Long memory) {
    this.memory = memory;
    return this;
  }

   /**
   * Get memory
   * @return memory
  **/
  @ApiModelProperty(value = "")


  public Long getMemory() {
    return memory;
  }

  public void setMemory(Long memory) {
    this.memory = memory;
  }

  public NodeProperties disk(Float disk) {
    this.disk = disk;
    return this;
  }

   /**
   * Get disk
   * @return disk
  **/
  @ApiModelProperty(value = "")


  public Float getDisk() {
    return disk;
  }

  public void setDisk(Float disk) {
    this.disk = disk;
  }

  public NodeProperties operatingSystem(OperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
    return this;
  }

   /**
   * Get operatingSystem
   * @return operatingSystem
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OperatingSystem getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(OperatingSystem operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  public NodeProperties geoLocation(GeoLocation geoLocation) {
    this.geoLocation = geoLocation;
    return this;
  }

   /**
   * Get geoLocation
   * @return geoLocation
  **/
  @ApiModelProperty(value = "")

  @Valid

  public GeoLocation getGeoLocation() {
    return geoLocation;
  }

  public void setGeoLocation(GeoLocation geoLocation) {
    this.geoLocation = geoLocation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NodeProperties nodeProperties = (NodeProperties) o;
    return Objects.equals(this.numberOfCores, nodeProperties.numberOfCores) &&
        Objects.equals(this.memory, nodeProperties.memory) &&
        Objects.equals(this.disk, nodeProperties.disk) &&
        Objects.equals(this.operatingSystem, nodeProperties.operatingSystem) &&
        Objects.equals(this.geoLocation, nodeProperties.geoLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numberOfCores, memory, disk, operatingSystem, geoLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NodeProperties {\n");
    
    sb.append("    numberOfCores: ").append(toIndentedString(numberOfCores)).append("\n");
    sb.append("    memory: ").append(toIndentedString(memory)).append("\n");
    sb.append("    disk: ").append(toIndentedString(disk)).append("\n");
    sb.append("    operatingSystem: ").append(toIndentedString(operatingSystem)).append("\n");
    sb.append("    geoLocation: ").append(toIndentedString(geoLocation)).append("\n");
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

