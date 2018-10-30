package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * PushSensor
 */
@Validated

public class PushSensor extends Sensor  {
  @JsonProperty("port")
  private Integer port = null;

  public PushSensor port(Integer port) {
    this.port = port;
    return this;
  }

  /**
   * (optional) port recommendation for starting the push sensor. API does not guarantee that this port will be used. 
   * @return port
  **/
  @ApiModelProperty(value = "(optional) port recommendation for starting the push sensor. API does not guarantee that this port will be used. ")


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
    PushSensor pushSensor = (PushSensor) o;
    return Objects.equals(this.port, pushSensor.port) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(port, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PushSensor {\n");
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

