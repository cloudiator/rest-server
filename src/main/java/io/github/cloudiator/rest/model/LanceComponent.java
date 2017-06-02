package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Component;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a lance component. 
 */
@ApiModel(description = "Represents a lance component. ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-02T09:24:26.089+02:00")

public class LanceComponent extends Component  {
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

  public LanceComponent init(String init) {
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

  public LanceComponent preInstall(String preInstall) {
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

  public LanceComponent install(String install) {
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

  public LanceComponent postInstall(String postInstall) {
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

  public LanceComponent preStart(String preStart) {
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

  public LanceComponent start(String start) {
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

  public LanceComponent startDetection(String startDetection) {
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

  public LanceComponent stopDetection(String stopDetection) {
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

  public LanceComponent postStart(String postStart) {
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

  public LanceComponent preStop(String preStop) {
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

  public LanceComponent stop(String stop) {
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

  public LanceComponent postStop(String postStop) {
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

  public LanceComponent shutdown(String shutdown) {
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
    LanceComponent lanceComponent = (LanceComponent) o;
    return Objects.equals(this.init, lanceComponent.init) &&
        Objects.equals(this.preInstall, lanceComponent.preInstall) &&
        Objects.equals(this.install, lanceComponent.install) &&
        Objects.equals(this.postInstall, lanceComponent.postInstall) &&
        Objects.equals(this.preStart, lanceComponent.preStart) &&
        Objects.equals(this.start, lanceComponent.start) &&
        Objects.equals(this.startDetection, lanceComponent.startDetection) &&
        Objects.equals(this.stopDetection, lanceComponent.stopDetection) &&
        Objects.equals(this.postStart, lanceComponent.postStart) &&
        Objects.equals(this.preStop, lanceComponent.preStop) &&
        Objects.equals(this.stop, lanceComponent.stop) &&
        Objects.equals(this.postStop, lanceComponent.postStop) &&
        Objects.equals(this.shutdown, lanceComponent.shutdown) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(init, preInstall, install, postInstall, preStart, start, startDetection, stopDetection, postStart, preStop, stop, postStop, shutdown, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LanceComponent {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

