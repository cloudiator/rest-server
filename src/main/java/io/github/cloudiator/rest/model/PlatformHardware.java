package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a hardware configuration for a Platform (not all attributes might be supported for all PaaS providers) 
 */
@ApiModel(description = "Represents a hardware configuration for a Platform (not all attributes might be supported for all PaaS providers) ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T12:03:11.942+02:00")

public class PlatformHardware   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("cores")
  private Integer cores = null;

  @JsonProperty("ram")
  private Long ram = null;

  @JsonProperty("disk")
  private Double disk = null;

  public PlatformHardware id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier for the hardware
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique identifier for the hardware")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PlatformHardware name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Human-readable name for the hardware
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Human-readable name for the hardware")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PlatformHardware cores(Integer cores) {
    this.cores = cores;
    return this;
  }

   /**
   * Number of cores
   * @return cores
  **/
  @ApiModelProperty(required = true, value = "Number of cores")
  @NotNull


  public Integer getCores() {
    return cores;
  }

  public void setCores(Integer cores) {
    this.cores = cores;
  }

  public PlatformHardware ram(Long ram) {
    this.ram = ram;
    return this;
  }

   /**
   * Amount of RAM
   * @return ram
  **/
  @ApiModelProperty(required = true, value = "Amount of RAM")
  @NotNull


  public Long getRam() {
    return ram;
  }

  public void setRam(Long ram) {
    this.ram = ram;
  }

  public PlatformHardware disk(Double disk) {
    this.disk = disk;
    return this;
  }

   /**
   * Amount of disk space
   * @return disk
  **/
  @ApiModelProperty(required = true, value = "Amount of disk space")
  @NotNull


  public Double getDisk() {
    return disk;
  }

  public void setDisk(Double disk) {
    this.disk = disk;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlatformHardware platformHardware = (PlatformHardware) o;
    return Objects.equals(this.id, platformHardware.id) &&
        Objects.equals(this.name, platformHardware.name) &&
        Objects.equals(this.cores, platformHardware.cores) &&
        Objects.equals(this.ram, platformHardware.ram) &&
        Objects.equals(this.disk, platformHardware.disk);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, cores, ram, disk);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlatformHardware {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    cores: ").append(toIndentedString(cores)).append("\n");
    sb.append("    ram: ").append(toIndentedString(ram)).append("\n");
    sb.append("    disk: ").append(toIndentedString(disk)).append("\n");
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

