package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Part of a job Represents a communication requirement between two tasks by mapping a required port of a task to a provided port of another task. 
 */
@ApiModel(description = "Part of a job Represents a communication requirement between two tasks by mapping a required port of a task to a provided port of another task. ")
@Validated

public class Communication   {
  @JsonProperty("portRequired")
  private String portRequired = null;

  @JsonProperty("portProvided")
  private String portProvided = null;

  public Communication portRequired(String portRequired) {
    this.portRequired = portRequired;
    return this;
  }

   /**
   * The name of the required port this communication refers to. 
   * @return portRequired
  **/
  @ApiModelProperty(example = "Re10375", value = "The name of the required port this communication refers to. ")


  public String getPortRequired() {
    return portRequired;
  }

  public void setPortRequired(String portRequired) {
    this.portRequired = portRequired;
  }

  public Communication portProvided(String portProvided) {
    this.portProvided = portProvided;
    return this;
  }

   /**
   * The name of the provided port this communication refers to. 
   * @return portProvided
  **/
  @ApiModelProperty(example = "Pro11745", value = "The name of the provided port this communication refers to. ")


  public String getPortProvided() {
    return portProvided;
  }

  public void setPortProvided(String portProvided) {
    this.portProvided = portProvided;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Communication communication = (Communication) o;
    return Objects.equals(this.portRequired, communication.portRequired) &&
        Objects.equals(this.portProvided, communication.portProvided);
  }

  @Override
  public int hashCode() {
    return Objects.hash(portRequired, portProvided);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Communication {\n");
    
    sb.append("    portRequired: ").append(toIndentedString(portRequired)).append("\n");
    sb.append("    portProvided: ").append(toIndentedString(portProvided)).append("\n");
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

