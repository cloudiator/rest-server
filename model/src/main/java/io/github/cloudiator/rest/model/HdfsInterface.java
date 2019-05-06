package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.TaskInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subtype of TaskInterface. Describes how to deploy a Task to Hdfs. 
 */
@ApiModel(description = "Subtype of TaskInterface. Describes how to deploy a Task to Hdfs. ")
@Validated

public class HdfsInterface extends TaskInterface  {
  /**
   * Gets or Sets processMapping
   */
  public enum ProcessMappingEnum {
    SINGLE("SINGLE"),
    
    CLUSTER("CLUSTER");

    private String value;

    ProcessMappingEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ProcessMappingEnum fromValue(String text) {
      for (ProcessMappingEnum b : ProcessMappingEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("processMapping")
  private ProcessMappingEnum processMapping = null;

  public HdfsInterface processMapping(ProcessMappingEnum processMapping) {
    this.processMapping = processMapping;
    return this;
  }

  /**
   * Get processMapping
   * @return processMapping
  **/
  @ApiModelProperty(value = "")


  public ProcessMappingEnum getProcessMapping() {
    return processMapping;
  }

  public void setProcessMapping(ProcessMappingEnum processMapping) {
    this.processMapping = processMapping;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HdfsInterface hdfsInterface = (HdfsInterface) o;
    return Objects.equals(this.processMapping, hdfsInterface.processMapping) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processMapping, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HdfsInterface {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    processMapping: ").append(toIndentedString(processMapping)).append("\n");
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

