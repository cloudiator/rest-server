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
 * Subtype of port. Represents a communication port that the task requires from other (downstream) tasks. 
 */
@ApiModel(description = "Subtype of port. Represents a communication port that the task requires from other (downstream) tasks. ")
@Validated

public class PortRequired extends Port  {
  @JsonProperty("updateAction")
  private String updateAction = null;

  @JsonProperty("isMandatory")
  private Boolean isMandatory = null;

  public PortRequired updateAction(String updateAction) {
    this.updateAction = updateAction;
    return this;
  }

   /**
   * A script that is executed if a new instance of a downstream task is available. 
   * @return updateAction
  **/
  @ApiModelProperty(value = "A script that is executed if a new instance of a downstream task is available. ")


  public String getUpdateAction() {
    return updateAction;
  }

  public void setUpdateAction(String updateAction) {
    this.updateAction = updateAction;
  }

  public PortRequired isMandatory(Boolean isMandatory) {
    this.isMandatory = isMandatory;
    return this;
  }

   /**
   * States if an instance of a downstream tasks needs to be already started (true), or if the task can start without a downstream task (false). 
   * @return isMandatory
  **/
  @ApiModelProperty(value = "States if an instance of a downstream tasks needs to be already started (true), or if the task can start without a downstream task (false). ")


  public Boolean isIsMandatory() {
    return isMandatory;
  }

  public void setIsMandatory(Boolean isMandatory) {
    this.isMandatory = isMandatory;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PortRequired portRequired = (PortRequired) o;
    return Objects.equals(this.updateAction, portRequired.updateAction) &&
        Objects.equals(this.isMandatory, portRequired.isMandatory) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(updateAction, isMandatory, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PortRequired {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    updateAction: ").append(toIndentedString(updateAction)).append("\n");
    sb.append("    isMandatory: ").append(toIndentedString(isMandatory)).append("\n");
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

