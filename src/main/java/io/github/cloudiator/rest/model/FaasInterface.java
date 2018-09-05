package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.TaskInterface;
import io.github.cloudiator.rest.model.Trigger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Part of a Task. Represents one function. 
 */
@ApiModel(description = "Part of a Task. Represents one function. ")
@Validated

public class FaasInterface extends TaskInterface  {
  @JsonProperty("funtionName")
  private String funtionName = null;

  @JsonProperty("sourceCodeUrl")
  private String sourceCodeUrl = null;

  @JsonProperty("handler")
  private String handler = null;

  @JsonProperty("runtime")
  private String runtime = null;

  @JsonProperty("triggers")
  @Valid
  private List<Trigger> triggers = null;

  @JsonProperty("timeout")
  private Integer timeout = 6;

  @JsonProperty("memory")
  private Integer memory = 1024;

  public FaasInterface funtionName(String funtionName) {
    this.funtionName = funtionName;
    return this;
  }

  /**
   * Unique name of the function. 
   * @return funtionName
  **/
  @ApiModelProperty(value = "Unique name of the function. ")


  public String getFuntionName() {
    return funtionName;
  }

  public void setFuntionName(String funtionName) {
    this.funtionName = funtionName;
  }

  public FaasInterface sourceCodeUrl(String sourceCodeUrl) {
    this.sourceCodeUrl = sourceCodeUrl;
    return this;
  }

  /**
   * URL path to ZIP artifact. 
   * @return sourceCodeUrl
  **/
  @ApiModelProperty(value = "URL path to ZIP artifact. ")


  public String getSourceCodeUrl() {
    return sourceCodeUrl;
  }

  public void setSourceCodeUrl(String sourceCodeUrl) {
    this.sourceCodeUrl = sourceCodeUrl;
  }

  public FaasInterface handler(String handler) {
    this.handler = handler;
    return this;
  }

  /**
   * function in the code to be invoked. 
   * @return handler
  **/
  @ApiModelProperty(value = "function in the code to be invoked. ")


  public String getHandler() {
    return handler;
  }

  public void setHandler(String handler) {
    this.handler = handler;
  }

  public FaasInterface runtime(String runtime) {
    this.runtime = runtime;
    return this;
  }

  /**
   * Code language. 
   * @return runtime
  **/
  @ApiModelProperty(value = "Code language. ")


  public String getRuntime() {
    return runtime;
  }

  public void setRuntime(String runtime) {
    this.runtime = runtime;
  }

  public FaasInterface triggers(List<Trigger> triggers) {
    this.triggers = triggers;
    return this;
  }

  public FaasInterface addTriggersItem(Trigger triggersItem) {
    if (this.triggers == null) {
      this.triggers = new ArrayList<Trigger>();
    }
    this.triggers.add(triggersItem);
    return this;
  }

  /**
   * Events on which function will be invoked. 
   * @return triggers
  **/
  @ApiModelProperty(value = "Events on which function will be invoked. ")

  @Valid

  public List<Trigger> getTriggers() {
    return triggers;
  }

  public void setTriggers(List<Trigger> triggers) {
    this.triggers = triggers;
  }

  public FaasInterface timeout(Integer timeout) {
    this.timeout = timeout;
    return this;
  }

  /**
   * Allowed time in seconds for funtion to finish its task. 
   * minimum: 1
   * maximum: 600
   * @return timeout
  **/
  @ApiModelProperty(value = "Allowed time in seconds for funtion to finish its task. ")

@Min(1) @Max(600) 
  public Integer getTimeout() {
    return timeout;
  }

  public void setTimeout(Integer timeout) {
    this.timeout = timeout;
  }

  public FaasInterface memory(Integer memory) {
    this.memory = memory;
    return this;
  }

  /**
   * Memory (in megabytes) available to function. CPU is allocated proportionally. 
   * minimum: 128
   * maximum: 3008
   * @return memory
  **/
  @ApiModelProperty(value = "Memory (in megabytes) available to function. CPU is allocated proportionally. ")

@Min(128) @Max(3008) 
  public Integer getMemory() {
    return memory;
  }

  public void setMemory(Integer memory) {
    this.memory = memory;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FaasInterface faasInterface = (FaasInterface) o;
    return Objects.equals(this.funtionName, faasInterface.funtionName) &&
        Objects.equals(this.sourceCodeUrl, faasInterface.sourceCodeUrl) &&
        Objects.equals(this.handler, faasInterface.handler) &&
        Objects.equals(this.runtime, faasInterface.runtime) &&
        Objects.equals(this.triggers, faasInterface.triggers) &&
        Objects.equals(this.timeout, faasInterface.timeout) &&
        Objects.equals(this.memory, faasInterface.memory) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(funtionName, sourceCodeUrl, handler, runtime, triggers, timeout, memory, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FaasInterface {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    funtionName: ").append(toIndentedString(funtionName)).append("\n");
    sb.append("    sourceCodeUrl: ").append(toIndentedString(sourceCodeUrl)).append("\n");
    sb.append("    handler: ").append(toIndentedString(handler)).append("\n");
    sb.append("    runtime: ").append(toIndentedString(runtime)).append("\n");
    sb.append("    triggers: ").append(toIndentedString(triggers)).append("\n");
    sb.append("    timeout: ").append(toIndentedString(timeout)).append("\n");
    sb.append("    memory: ").append(toIndentedString(memory)).append("\n");
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

