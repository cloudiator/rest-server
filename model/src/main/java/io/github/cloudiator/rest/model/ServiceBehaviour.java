package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Behaviour;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subtype of Behaviour Represents a service runtime behaviour 
 */
@ApiModel(description = "Subtype of Behaviour Represents a service runtime behaviour ")
@Validated

public class ServiceBehaviour extends Behaviour  {
  @JsonProperty("restart")
  private Boolean restart = null;

  public ServiceBehaviour restart(Boolean restart) {
    this.restart = restart;
    return this;
  }

  /**
   * True if the service should be restarted failure, false if not 
   * @return restart
  **/
  @ApiModelProperty(value = "True if the service should be restarted failure, false if not ")


  public Boolean isRestart() {
    return restart;
  }

  public void setRestart(Boolean restart) {
    this.restart = restart;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceBehaviour serviceBehaviour = (ServiceBehaviour) o;
    return Objects.equals(this.restart, serviceBehaviour.restart) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restart, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceBehaviour {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    restart: ").append(toIndentedString(restart)).append("\n");
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

