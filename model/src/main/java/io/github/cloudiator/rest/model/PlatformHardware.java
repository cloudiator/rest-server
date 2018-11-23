package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.NewPlatformHardware;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Repesents a PaaS environemnt to run an component 
 */
@ApiModel(description = "Repesents a PaaS environemnt to run an component ")
@Validated

public class PlatformHardware   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("cores")
  private Integer cores = null;

  @JsonProperty("ram")
  private Long ram = null;

  @JsonProperty("disk")
  private Float disk = null;

  @JsonProperty("id")
  private String id = null;

  public PlatformHardware name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable name for the hardware
   * @return name
  **/
  @ApiModelProperty(example = "example.p1", required = true, value = "Human-readable name for the hardware")
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
  @ApiModelProperty(example = "16", required = true, value = "Number of cores")
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
  @ApiModelProperty(example = "2048", required = true, value = "Amount of RAM")
  @NotNull


  public Long getRam() {
    return ram;
  }

  public void setRam(Long ram) {
    this.ram = ram;
  }

  public PlatformHardware disk(Float disk) {
    this.disk = disk;
    return this;
  }

  /**
   * Amount of disk space
   * @return disk
  **/
  @ApiModelProperty(example = "100.0", required = true, value = "Amount of disk space")
  @NotNull


  public Float getDisk() {
    return disk;
  }

  public void setDisk(Float disk) {
    this.disk = disk;
  }

  public PlatformHardware id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier for the hardware
   * @return id
  **/
  @ApiModelProperty(example = "1a79a4d60de6718e8e5b326e33812345/example.p1", value = "Unique identifier for the hardware")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
    return Objects.equals(this.name, platformHardware.name) &&
        Objects.equals(this.cores, platformHardware.cores) &&
        Objects.equals(this.ram, platformHardware.ram) &&
        Objects.equals(this.disk, platformHardware.disk) &&
        Objects.equals(this.id, platformHardware.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cores, ram, disk, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlatformHardware {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    cores: ").append(toIndentedString(cores)).append("\n");
    sb.append("    ram: ").append(toIndentedString(ram)).append("\n");
    sb.append("    disk: ").append(toIndentedString(disk)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

