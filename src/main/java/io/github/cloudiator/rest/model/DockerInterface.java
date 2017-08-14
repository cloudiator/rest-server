package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.TaskInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DockerInterface
 */

public class DockerInterface extends TaskInterface  {
  @JsonProperty("dockerImage")
  private String dockerImage = null;

  public DockerInterface dockerImage(String dockerImage) {
    this.dockerImage = dockerImage;
    return this;
  }

   /**
   * Name of the docker image 
   * @return dockerImage
  **/
  @ApiModelProperty(value = "Name of the docker image ")


  public String getDockerImage() {
    return dockerImage;
  }

  public void setDockerImage(String dockerImage) {
    this.dockerImage = dockerImage;
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
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dockerImage, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DockerInterface {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    dockerImage: ").append(toIndentedString(dockerImage)).append("\n");
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

