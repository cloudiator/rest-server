package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.CloudiatorProcess;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ClusterProcess
 */
@Validated

public class ClusterProcess extends CloudiatorProcess  {
  @JsonProperty("nodeGroup")
  private String nodeGroup = null;

  public ClusterProcess nodeGroup(String nodeGroup) {
    this.nodeGroup = nodeGroup;
    return this;
  }

  /**
   * The id of the nodeGroup this process is hosted on.
   * @return nodeGroup
  **/
  @ApiModelProperty(required = true, value = "The id of the nodeGroup this process is hosted on.")
  @NotNull


  public String getNodeGroup() {
    return nodeGroup;
  }

  public void setNodeGroup(String nodeGroup) {
    this.nodeGroup = nodeGroup;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClusterProcess clusterProcess = (ClusterProcess) o;
    return Objects.equals(this.nodeGroup, clusterProcess.nodeGroup) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeGroup, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClusterProcess {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    nodeGroup: ").append(toIndentedString(nodeGroup)).append("\n");
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

