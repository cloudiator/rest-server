package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents an API used by a cloud 
 */
@ApiModel(description = "Represents an API used by a cloud ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-29T14:29:11.837+02:00")

public class Api   {
  @JsonProperty("providerName")
  private String providerName = null;

  public Api providerName(String providerName) {
    this.providerName = providerName;
    return this;
  }

   /**
   * Name of the API provider, maps to a driver
   * @return providerName
  **/
  @ApiModelProperty(required = true, value = "Name of the API provider, maps to a driver")
  @NotNull


  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Api api = (Api) o;
    return Objects.equals(this.providerName, api.providerName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(providerName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Api {\n");
    
    sb.append("    providerName: ").append(toIndentedString(providerName)).append("\n");
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

