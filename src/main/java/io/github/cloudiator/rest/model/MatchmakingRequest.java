package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.NodeRequirements;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Issues a request to the matchmaking component 
 */
@ApiModel(description = "Issues a request to the matchmaking component ")
@Validated

public class MatchmakingRequest   {
  @JsonProperty("requirements")
  private NodeRequirements requirements = null;

  public MatchmakingRequest requirements(NodeRequirements requirements) {
    this.requirements = requirements;
    return this;
  }

   /**
   * Get requirements
   * @return requirements
  **/
  @ApiModelProperty(value = "")

  @Valid

  public NodeRequirements getRequirements() {
    return requirements;
  }

  public void setRequirements(NodeRequirements requirements) {
    this.requirements = requirements;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatchmakingRequest matchmakingRequest = (MatchmakingRequest) o;
    return Objects.equals(this.requirements, matchmakingRequest.requirements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requirements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MatchmakingRequest {\n");
    
    sb.append("    requirements: ").append(toIndentedString(requirements)).append("\n");
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

