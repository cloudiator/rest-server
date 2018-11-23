package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Trigger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subtype of Trigger. Represents a HTTP request which will trigger a function. 
 */
@ApiModel(description = "Subtype of Trigger. Represents a HTTP request which will trigger a function. ")
@Validated

public class HttpTrigger extends Trigger  {
  @JsonProperty("httpPath")
  private String httpPath = null;

  @JsonProperty("httpMethod")
  private String httpMethod = null;

  public HttpTrigger httpPath(String httpPath) {
    this.httpPath = httpPath;
    return this;
  }

  /**
   * Get httpPath
   * @return httpPath
  **/
  @ApiModelProperty(value = "")


  public String getHttpPath() {
    return httpPath;
  }

  public void setHttpPath(String httpPath) {
    this.httpPath = httpPath;
  }

  public HttpTrigger httpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
    return this;
  }

  /**
   * Get httpMethod
   * @return httpMethod
  **/
  @ApiModelProperty(value = "")


  public String getHttpMethod() {
    return httpMethod;
  }

  public void setHttpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HttpTrigger httpTrigger = (HttpTrigger) o;
    return Objects.equals(this.httpPath, httpTrigger.httpPath) &&
        Objects.equals(this.httpMethod, httpTrigger.httpMethod) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(httpPath, httpMethod, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HttpTrigger {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    httpPath: ").append(toIndentedString(httpPath)).append("\n");
    sb.append("    httpMethod: ").append(toIndentedString(httpMethod)).append("\n");
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

