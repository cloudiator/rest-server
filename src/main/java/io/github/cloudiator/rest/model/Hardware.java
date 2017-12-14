package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Location;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a hardware offer by a cloud 
 */
@ApiModel(description = "Represents a hardware offer by a cloud ")
@Validated

public class Hardware   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("providerId")
  private String providerId = null;

  @JsonProperty("cores")
  private Integer cores = null;

  @JsonProperty("ram")
  private Long ram = null;

  @JsonProperty("disk")
  private Double disk = null;

  @JsonProperty("location")
  private Location location = null;

  public Hardware id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier for the hardware
   * @return id
  **/
  @ApiModelProperty(example = "1a79a4d60de6718e8e5b326e338ae533/RegionOne/1", required = true, value = "Unique identifier for the hardware")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Hardware name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Human-readable name for the hardware
   * @return name
  **/
  @ApiModelProperty(example = "m1.medium", required = true, value = "Human-readable name for the hardware")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Hardware providerId(String providerId) {
    this.providerId = providerId;
    return this;
  }

   /**
   * Original id issued by the provider
   * @return providerId
  **/
  @ApiModelProperty(example = "1", required = true, value = "Original id issued by the provider")
  @NotNull


  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public Hardware cores(Integer cores) {
    this.cores = cores;
    return this;
  }

   /**
   * Number of cores
   * @return cores
  **/
  @ApiModelProperty(example = "4", required = true, value = "Number of cores")
  @NotNull


  public Integer getCores() {
    return cores;
  }

  public void setCores(Integer cores) {
    this.cores = cores;
  }

  public Hardware ram(Long ram) {
    this.ram = ram;
    return this;
  }

   /**
   * Amount of RAM
   * @return ram
  **/
  @ApiModelProperty(example = "2048", required = true, value = "Amount of RAM")
  @NotNull


  public Long getRam() {
    return ram;
  }

  public void setRam(Long ram) {
    this.ram = ram;
  }

  public Hardware disk(Double disk) {
    this.disk = disk;
    return this;
  }

   /**
   * Amount of disk space
   * @return disk
  **/
  @ApiModelProperty(example = "100.0", value = "Amount of disk space")


  public Double getDisk() {
    return disk;
  }

  public void setDisk(Double disk) {
    this.disk = disk;
  }

  public Hardware location(Location location) {
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
    Hardware hardware = (Hardware) o;
    return Objects.equals(this.id, hardware.id) &&
        Objects.equals(this.name, hardware.name) &&
        Objects.equals(this.providerId, hardware.providerId) &&
        Objects.equals(this.cores, hardware.cores) &&
        Objects.equals(this.ram, hardware.ram) &&
        Objects.equals(this.disk, hardware.disk) &&
        Objects.equals(this.location, hardware.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, providerId, cores, ram, disk, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Hardware {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    providerId: ").append(toIndentedString(providerId)).append("\n");
    sb.append("    cores: ").append(toIndentedString(cores)).append("\n");
    sb.append("    ram: ").append(toIndentedString(ram)).append("\n");
    sb.append("    disk: ").append(toIndentedString(disk)).append("\n");
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

