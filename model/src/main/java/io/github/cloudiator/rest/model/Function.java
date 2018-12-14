package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Runtime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Function
 */
@Validated

public class Function   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("cloudId")
  private String cloudId = null;

  @JsonProperty("locationId")
  private String locationId = null;

  @JsonProperty("memory")
  private Integer memory = null;

  @JsonProperty("runtime")
  private Runtime runtime = null;

  @JsonProperty("stackId")
  private String stackId = null;

  public Function id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Function cloudId(String cloudId) {
    this.cloudId = cloudId;
    return this;
  }

  /**
   * Get cloudId
   * @return cloudId
  **/
  @ApiModelProperty(value = "")


  public String getCloudId() {
    return cloudId;
  }

  public void setCloudId(String cloudId) {
    this.cloudId = cloudId;
  }

  public Function locationId(String locationId) {
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

  public Function memory(Integer memory) {
    this.memory = memory;
    return this;
  }

  /**
   * Get memory
   * @return memory
  **/
  @ApiModelProperty(value = "")


  public Integer getMemory() {
    return memory;
  }

  public void setMemory(Integer memory) {
    this.memory = memory;
  }

  public Function runtime(Runtime runtime) {
    this.runtime = runtime;
    return this;
  }

  /**
   * Get runtime
   * @return runtime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Runtime getRuntime() {
    return runtime;
  }

  public void setRuntime(Runtime runtime) {
    this.runtime = runtime;
  }

  public Function stackId(String stackId) {
    this.stackId = stackId;
    return this;
  }

  /**
   * Get stackId
   * @return stackId
  **/
  @ApiModelProperty(value = "")


  public String getStackId() {
    return stackId;
  }

  public void setStackId(String stackId) {
    this.stackId = stackId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Function function = (Function) o;
    return Objects.equals(this.id, function.id) &&
        Objects.equals(this.cloudId, function.cloudId) &&
        Objects.equals(this.locationId, function.locationId) &&
        Objects.equals(this.memory, function.memory) &&
        Objects.equals(this.runtime, function.runtime) &&
        Objects.equals(this.stackId, function.stackId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cloudId, locationId, memory, runtime, stackId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Function {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cloudId: ").append(toIndentedString(cloudId)).append("\n");
    sb.append("    locationId: ").append(toIndentedString(locationId)).append("\n");
    sb.append("    memory: ").append(toIndentedString(memory)).append("\n");
    sb.append("    runtime: ").append(toIndentedString(runtime)).append("\n");
    sb.append("    stackId: ").append(toIndentedString(stackId)).append("\n");
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

