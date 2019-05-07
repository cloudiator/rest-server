package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.TaskInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subtype of TaskInterface to represent docker containers 
 */
@ApiModel(description = "Subtype of TaskInterface to represent docker containers ")
@Validated

public class DockerInterface extends TaskInterface  {
  @JsonProperty("dockerImage")
  private String dockerImage = null;

  @JsonProperty("environment")
  private java.util.Map environment = null;

  @JsonProperty("updateAction")
  private String updateAction = null;

  public DockerInterface dockerImage(String dockerImage) {
    this.dockerImage = dockerImage;
    return this;
  }

  /**
   * Name of the docker image (should include repository, credentials, tags) 
   * @return dockerImage
  **/
  @ApiModelProperty(value = "Name of the docker image (should include repository, credentials, tags) ")


  public String getDockerImage() {
    return dockerImage;
  }

  public void setDockerImage(String dockerImage) {
    this.dockerImage = dockerImage;
  }

  public DockerInterface environment(java.util.Map environment) {
    this.environment = environment;
    return this;
  }

  /**
   * A key-value map representing the environment of the docker container 
   * @return environment
  **/
  @ApiModelProperty(value = "A key-value map representing the environment of the docker container ")

  @Valid

  public java.util.Map getEnvironment() {
    return environment;
  }

  public void setEnvironment(java.util.Map environment) {
    this.environment = environment;
  }

  public DockerInterface updateAction(String updateAction) {
    this.updateAction = updateAction;
    return this;
  }

  /**
   * An (optional) update action for updating the communication. 
   * @return updateAction
  **/
  @ApiModelProperty(value = "An (optional) update action for updating the communication. ")


  public String getUpdateAction() {
    return updateAction;
  }

  public void setUpdateAction(String updateAction) {
    this.updateAction = updateAction;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DockerInterface dockerInterface = (DockerInterface) o;
    return Objects.equals(this.dockerImage, dockerInterface.dockerImage) &&
        Objects.equals(this.environment, dockerInterface.environment) &&
        Objects.equals(this.updateAction, dockerInterface.updateAction) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dockerImage, environment, updateAction, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DockerInterface {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    dockerImage: ").append(toIndentedString(dockerImage)).append("\n");
    sb.append("    environment: ").append(toIndentedString(environment)).append("\n");
    sb.append("    updateAction: ").append(toIndentedString(updateAction)).append("\n");
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

