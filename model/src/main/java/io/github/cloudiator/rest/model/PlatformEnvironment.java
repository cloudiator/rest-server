package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.NewPlatformEnvironment;
import io.github.cloudiator.rest.model.Platform;
import io.github.cloudiator.rest.model.PlatformHardware;
import io.github.cloudiator.rest.model.PlatformRuntime;
import io.github.cloudiator.rest.model.PlatformService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Repesents a PaaS environemnt to run an component 
 */
@ApiModel(description = "Repesents a PaaS environemnt to run an component ")
@Validated

public class PlatformEnvironment   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("platform")
  private Platform platform = null;

  @JsonProperty("platformHardware")
  private PlatformHardware platformHardware = null;

  @JsonProperty("platformRuntime")
  private PlatformRuntime platformRuntime = null;

  @JsonProperty("platformService")
  @Valid
  private List<PlatformService> platformService = null;

  @JsonProperty("id")
  private String id = null;

  public PlatformEnvironment name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Human-readable name")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PlatformEnvironment platform(Platform platform) {
    this.platform = platform;
    return this;
  }

  /**
   * Get platform
   * @return platform
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Platform getPlatform() {
    return platform;
  }

  public void setPlatform(Platform platform) {
    this.platform = platform;
  }

  public PlatformEnvironment platformHardware(PlatformHardware platformHardware) {
    this.platformHardware = platformHardware;
    return this;
  }

  /**
   * Get platformHardware
   * @return platformHardware
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public PlatformHardware getPlatformHardware() {
    return platformHardware;
  }

  public void setPlatformHardware(PlatformHardware platformHardware) {
    this.platformHardware = platformHardware;
  }

  public PlatformEnvironment platformRuntime(PlatformRuntime platformRuntime) {
    this.platformRuntime = platformRuntime;
    return this;
  }

  /**
   * Get platformRuntime
   * @return platformRuntime
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public PlatformRuntime getPlatformRuntime() {
    return platformRuntime;
  }

  public void setPlatformRuntime(PlatformRuntime platformRuntime) {
    this.platformRuntime = platformRuntime;
  }

  public PlatformEnvironment platformService(List<PlatformService> platformService) {
    this.platformService = platformService;
    return this;
  }

  public PlatformEnvironment addPlatformServiceItem(PlatformService platformServiceItem) {
    if (this.platformService == null) {
      this.platformService = new ArrayList<PlatformService>();
    }
    this.platformService.add(platformServiceItem);
    return this;
  }

  /**
   * Get platformService
   * @return platformService
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<PlatformService> getPlatformService() {
    return platformService;
  }

  public void setPlatformService(List<PlatformService> platformService) {
    this.platformService = platformService;
  }

  public PlatformEnvironment id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier")


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
    PlatformEnvironment platformEnvironment = (PlatformEnvironment) o;
    return Objects.equals(this.name, platformEnvironment.name) &&
        Objects.equals(this.platform, platformEnvironment.platform) &&
        Objects.equals(this.platformHardware, platformEnvironment.platformHardware) &&
        Objects.equals(this.platformRuntime, platformEnvironment.platformRuntime) &&
        Objects.equals(this.platformService, platformEnvironment.platformService) &&
        Objects.equals(this.id, platformEnvironment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, platform, platformHardware, platformRuntime, platformService, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlatformEnvironment {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
    sb.append("    platformHardware: ").append(toIndentedString(platformHardware)).append("\n");
    sb.append("    platformRuntime: ").append(toIndentedString(platformRuntime)).append("\n");
    sb.append("    platformService: ").append(toIndentedString(platformService)).append("\n");
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

