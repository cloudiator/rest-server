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
 * Represents a docker container 
 */
@ApiModel(description = "Represents a docker container ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T14:29:11.837+02:00")

public class DockerComponent extends Component  {
  @JsonProperty("dockerImage")
  private String dockerImage = null;

  public DockerComponent dockerImage(String dockerImage) {
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
    DockerComponent dockerComponent = (DockerComponent) o;
    return Objects.equals(this.dockerImage, dockerComponent.dockerImage) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dockerImage, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DockerComponent {\n");
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

