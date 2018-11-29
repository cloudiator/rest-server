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
 * Node candidate environment
 */
@ApiModel(description = "Node candidate environment")
@Validated

public class Environment   {
  @JsonProperty("runtime")
  private Runtime runtime = null;

  public Environment runtime(Runtime runtime) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Environment environment = (Environment) o;
    return Objects.equals(this.runtime, environment.runtime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(runtime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Environment {\n");
    
    sb.append("    runtime: ").append(toIndentedString(runtime)).append("\n");
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

