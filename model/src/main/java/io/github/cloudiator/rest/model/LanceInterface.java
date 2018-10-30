package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * Part of a task. Subtype of TaskInterface. 
 */
@ApiModel(description = "Part of a task. Subtype of TaskInterface. ")
@Validated

public class LanceInterface extends TaskInterface  {
  /**
   * The container type that lance should use. Can be DOCKER to force a docker deployment, NATIVE to force a plain container deployment or BOTH to let the system derive the container type. 
   */
  public enum ContainerTypeEnum {
    NATIVE("NATIVE"),
    
    DOCKER("DOCKER"),
    
    BOTH("BOTH");

    private String value;

    ContainerTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ContainerTypeEnum fromValue(String text) {
      for (ContainerTypeEnum b : ContainerTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("containerType")
  private ContainerTypeEnum containerType = null;

  @JsonProperty("init")
  private String init = null;

  @JsonProperty("preInstall")
  private String preInstall = null;

  @JsonProperty("install")
  private String install = null;

  @JsonProperty("postInstall")
  private String postInstall = null;

  @JsonProperty("preStart")
  private String preStart = null;

  @JsonProperty("start")
  private String start = null;

  @JsonProperty("startDetection")
  private String startDetection = null;

  @JsonProperty("stopDetection")
  private String stopDetection = null;

  @JsonProperty("postStart")
  private String postStart = null;

  @JsonProperty("preStop")
  private String preStop = null;

  @JsonProperty("stop")
  private String stop = null;

  @JsonProperty("postStop")
  private String postStop = null;

  @JsonProperty("shutdown")
  private String shutdown = null;

  public LanceInterface containerType(ContainerTypeEnum containerType) {
    this.containerType = containerType;
    return this;
  }

  /**
   * The container type that lance should use. Can be DOCKER to force a docker deployment, NATIVE to force a plain container deployment or BOTH to let the system derive the container type. 
   * @return containerType
  **/
  @ApiModelProperty(value = "The container type that lance should use. Can be DOCKER to force a docker deployment, NATIVE to force a plain container deployment or BOTH to let the system derive the container type. ")


  public ContainerTypeEnum getContainerType() {
    return containerType;
  }

  public void setContainerType(ContainerTypeEnum containerType) {
    this.containerType = containerType;
  }

  public LanceInterface init(String init) {
    this.init = init;
    return this;
  }

  /**
   * Initialization action. 
   * @return init
  **/
  @ApiModelProperty(value = "Initialization action. ")


  public String getInit() {
    return init;
  }

  public void setInit(String init) {
    this.init = init;
  }

  public LanceInterface preInstall(String preInstall) {
    this.preInstall = preInstall;
    return this;
  }

  /**
   * Executed before installation action. Can be e.g. used for downloading binaries. 
   * @return preInstall
  **/
  @ApiModelProperty(value = "Executed before installation action. Can be e.g. used for downloading binaries. ")


  public String getPreInstall() {
    return preInstall;
  }

  public void setPreInstall(String preInstall) {
    this.preInstall = preInstall;
  }

  public LanceInterface install(String install) {
    this.install = install;
    return this;
  }

  /**
   * Used for installing the application. 
   * @return install
  **/
  @ApiModelProperty(value = "Used for installing the application. ")


  public String getInstall() {
    return install;
  }

  public void setInstall(String install) {
    this.install = install;
  }

  public LanceInterface postInstall(String postInstall) {
    this.postInstall = postInstall;
    return this;
  }

  /**
   * Used for configuration of the application component. First action where Lance environment variables are set. 
   * @return postInstall
  **/
  @ApiModelProperty(value = "Used for configuration of the application component. First action where Lance environment variables are set. ")


  public String getPostInstall() {
    return postInstall;
  }

  public void setPostInstall(String postInstall) {
    this.postInstall = postInstall;
  }

  public LanceInterface preStart(String preStart) {
    this.preStart = preStart;
    return this;
  }

  /**
   * Called before starting the application. Can be e.g. used for configuration an environment. 
   * @return preStart
  **/
  @ApiModelProperty(value = "Called before starting the application. Can be e.g. used for configuration an environment. ")


  public String getPreStart() {
    return preStart;
  }

  public void setPreStart(String preStart) {
    this.preStart = preStart;
  }

  public LanceInterface start(String start) {
    this.start = start;
    return this;
  }

  /**
   * Starts the component. Needs to return for PlainContainer and not return for Docker. 
   * @return start
  **/
  @ApiModelProperty(value = "Starts the component. Needs to return for PlainContainer and not return for Docker. ")


  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public LanceInterface startDetection(String startDetection) {
    this.startDetection = startDetection;
    return this;
  }

  /**
   * Detects the start of the application. Required if the application does not start instantianous. 
   * @return startDetection
  **/
  @ApiModelProperty(value = "Detects the start of the application. Required if the application does not start instantianous. ")


  public String getStartDetection() {
    return startDetection;
  }

  public void setStartDetection(String startDetection) {
    this.startDetection = startDetection;
  }

  public LanceInterface stopDetection(String stopDetection) {
    this.stopDetection = stopDetection;
    return this;
  }

  /**
   * Checks if the application has stopped. Is periodically checked to detect a crash of the application. 
   * @return stopDetection
  **/
  @ApiModelProperty(value = "Checks if the application has stopped. Is periodically checked to detect a crash of the application. ")


  public String getStopDetection() {
    return stopDetection;
  }

  public void setStopDetection(String stopDetection) {
    this.stopDetection = stopDetection;
  }

  public LanceInterface postStart(String postStart) {
    this.postStart = postStart;
    return this;
  }

  /**
   * Executed after the application has successfully started. 
   * @return postStart
  **/
  @ApiModelProperty(value = "Executed after the application has successfully started. ")


  public String getPostStart() {
    return postStart;
  }

  public void setPostStart(String postStart) {
    this.postStart = postStart;
  }

  public LanceInterface preStop(String preStop) {
    this.preStop = preStop;
    return this;
  }

  /**
   * Called before the application is stopped. 
   * @return preStop
  **/
  @ApiModelProperty(value = "Called before the application is stopped. ")


  public String getPreStop() {
    return preStop;
  }

  public void setPreStop(String preStop) {
    this.preStop = preStop;
  }

  public LanceInterface stop(String stop) {
    this.stop = stop;
    return this;
  }

  /**
   * Stops the application. 
   * @return stop
  **/
  @ApiModelProperty(value = "Stops the application. ")


  public String getStop() {
    return stop;
  }

  public void setStop(String stop) {
    this.stop = stop;
  }

  public LanceInterface postStop(String postStop) {
    this.postStop = postStop;
    return this;
  }

  /**
   * Executed after the application is successfully stopped. 
   * @return postStop
  **/
  @ApiModelProperty(value = "Executed after the application is successfully stopped. ")


  public String getPostStop() {
    return postStop;
  }

  public void setPostStop(String postStop) {
    this.postStop = postStop;
  }

  public LanceInterface shutdown(String shutdown) {
    this.shutdown = shutdown;
    return this;
  }

  /**
   * Executed before the container is shutdown. Can be used to backup state. 
   * @return shutdown
  **/
  @ApiModelProperty(value = "Executed before the container is shutdown. Can be used to backup state. ")


  public String getShutdown() {
    return shutdown;
  }

  public void setShutdown(String shutdown) {
    this.shutdown = shutdown;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LanceInterface lanceInterface = (LanceInterface) o;
    return Objects.equals(this.containerType, lanceInterface.containerType) &&
        Objects.equals(this.init, lanceInterface.init) &&
        Objects.equals(this.preInstall, lanceInterface.preInstall) &&
        Objects.equals(this.install, lanceInterface.install) &&
        Objects.equals(this.postInstall, lanceInterface.postInstall) &&
        Objects.equals(this.preStart, lanceInterface.preStart) &&
        Objects.equals(this.start, lanceInterface.start) &&
        Objects.equals(this.startDetection, lanceInterface.startDetection) &&
        Objects.equals(this.stopDetection, lanceInterface.stopDetection) &&
        Objects.equals(this.postStart, lanceInterface.postStart) &&
        Objects.equals(this.preStop, lanceInterface.preStop) &&
        Objects.equals(this.stop, lanceInterface.stop) &&
        Objects.equals(this.postStop, lanceInterface.postStop) &&
        Objects.equals(this.shutdown, lanceInterface.shutdown) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(containerType, init, preInstall, install, postInstall, preStart, start, startDetection, stopDetection, postStart, preStop, stop, postStop, shutdown, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LanceInterface {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    containerType: ").append(toIndentedString(containerType)).append("\n");
    sb.append("    init: ").append(toIndentedString(init)).append("\n");
    sb.append("    preInstall: ").append(toIndentedString(preInstall)).append("\n");
    sb.append("    install: ").append(toIndentedString(install)).append("\n");
    sb.append("    postInstall: ").append(toIndentedString(postInstall)).append("\n");
    sb.append("    preStart: ").append(toIndentedString(preStart)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    startDetection: ").append(toIndentedString(startDetection)).append("\n");
    sb.append("    stopDetection: ").append(toIndentedString(stopDetection)).append("\n");
    sb.append("    postStart: ").append(toIndentedString(postStart)).append("\n");
    sb.append("    preStop: ").append(toIndentedString(preStop)).append("\n");
    sb.append("    stop: ").append(toIndentedString(stop)).append("\n");
    sb.append("    postStop: ").append(toIndentedString(postStop)).append("\n");
    sb.append("    shutdown: ").append(toIndentedString(shutdown)).append("\n");
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

