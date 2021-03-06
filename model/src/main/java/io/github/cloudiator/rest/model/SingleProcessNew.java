package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.CloudiatorProcessNew;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SingleProcessNew
 */
@Validated

public class SingleProcessNew extends CloudiatorProcessNew  {
  @JsonProperty("node")
  private String node = null;

  public SingleProcessNew node(String node) {
    this.node = node;
    return this;
  }

  /**
   * The id of the node this process is hosted on.
   * @return node
  **/
  @ApiModelProperty(required = true, value = "The id of the node this process is hosted on.")
  @NotNull


  public String getNode() {
    return node;
  }

  public void setNode(String node) {
    this.node = node;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SingleProcessNew singleProcessNew = (SingleProcessNew) o;
    return Objects.equals(this.node, singleProcessNew.node) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(node, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SingleProcessNew {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    node: ").append(toIndentedString(node)).append("\n");
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

