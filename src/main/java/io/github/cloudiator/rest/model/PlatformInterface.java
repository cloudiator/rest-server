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
 * Subtype of TaskInterface Represents a PaaS interface 
 */
@ApiModel(description = "Subtype of TaskInterface Represents a PaaS interface ")
@Validated

public class PlatformInterface extends TaskInterface  {
  @JsonProperty("sourceRepository")
  private String sourceRepository = null;

  public PlatformInterface sourceRepository(String sourceRepository) {
    this.sourceRepository = sourceRepository;
    return this;
  }

   /**
   * URL to the source code repository (currently only git is supported) 
   * @return sourceRepository
  **/
  @ApiModelProperty(value = "URL to the source code repository (currently only git is supported) ")


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
    PlatformInterface platformInterface = (PlatformInterface) o;
    return Objects.equals(this.sourceRepository, platformInterface.sourceRepository) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceRepository, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlatformInterface {\n");
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

