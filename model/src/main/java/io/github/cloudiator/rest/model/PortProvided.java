package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Port;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subtype of port. Represents a communication port that the tasks provides for other tasks or the end user. 
 */
@ApiModel(description = "Subtype of port. Represents a communication port that the tasks provides for other tasks or the end user. ")
@Validated

public class PortProvided extends Port  {
  @JsonProperty("port")
  private Integer port = null;

  public PortProvided port(Integer port) {
    this.port = port;
    return this;
  }

  /**
   * Get port
   * @return port
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PortProvided portProvided = (PortProvided) o;
    return Objects.equals(this.port, portProvided.port) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(port, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PortProvided {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
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

