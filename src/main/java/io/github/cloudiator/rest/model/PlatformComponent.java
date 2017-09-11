package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Component;
import io.github.cloudiator.rest.model.Port;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a PaaS component 
 */
@ApiModel(description = "Represents a PaaS component ")

public class PlatformComponent extends Component  {
  @JsonProperty("sourceRepository")
  private String sourceRepository = null;

  public PlatformComponent sourceRepository(String sourceRepository) {
    this.sourceRepository = sourceRepository;
    return this;
  }

   /**
   * url to the source code repository (currently only git is supported) 
   * @return sourceRepository
  **/
  @ApiModelProperty(value = "url to the source code repository (currently only git is supported) ")


  public String getSourceRepository() {
    return sourceRepository;
  }

  public void setSourceRepository(String sourceRepository) {
    this.sourceRepository = sourceRepository;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlatformComponent platformComponent = (PlatformComponent) o;
    return Objects.equals(this.sourceRepository, platformComponent.sourceRepository) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceRepository, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlatformComponent {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    sourceRepository: ").append(toIndentedString(sourceRepository)).append("\n");
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

