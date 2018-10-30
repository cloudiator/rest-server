package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * A key - value tuple 
 */
@ApiModel(description = "A key - value tuple ")
@Validated

public class Tuple   {
  @JsonProperty("key")
  private String key = null;

  @JsonProperty("value")
  private String value = null;

  public Tuple key(String key) {
    this.key = key;
    return this;
  }

  /**
   * Key of the tuple 
   * @return key
  **/
  @ApiModelProperty(required = true, value = "Key of the tuple ")
  @NotNull


  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Tuple value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Value of the tuple 
   * @return value
  **/
  @ApiModelProperty(required = true, value = "Value of the tuple ")
  @NotNull


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tuple tuple = (Tuple) o;
    return Objects.equals(this.key, tuple.key) &&
        Objects.equals(this.value, tuple.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tuple {\n");
    
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

