package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ApplicationComponent
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-02T13:00:29.446+02:00")

public class ApplicationComponent   {
  @JsonProperty("test")
  private String test = null;

  public ApplicationComponent test(String test) {
    this.test = test;
    return this;
  }

   /**
   * Get test
   * @return test
  **/
  @ApiModelProperty(value = "")


  public String getTest() {
    return test;
  }

  public void setTest(String test) {
    this.test = test;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationComponent applicationComponent = (ApplicationComponent) o;
    return Objects.equals(this.test, applicationComponent.test);
  }

  @Override
  public int hashCode() {
    return Objects.hash(test);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApplicationComponent {\n");
    
    sb.append("    test: ").append(toIndentedString(test)).append("\n");
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

