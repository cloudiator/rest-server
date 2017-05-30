package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a runtime for a Platform component, e.g. Java, PHP, Tomcat
 */
@ApiModel(description = "Represents a runtime for a Platform component, e.g. Java, PHP, Tomcat")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-30T11:42:58.188+02:00")

public class PlatformRuntime   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("language")
  private String language = null;

  /**
   * specifies the runtime type
   */
  public enum RuntimeTypeEnum {
    RUNTIME("runtime"),
    
    SERVER("server");

    private String value;

    RuntimeTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RuntimeTypeEnum fromValue(String text) {
      for (RuntimeTypeEnum b : RuntimeTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("runtimeType")
  private RuntimeTypeEnum runtimeType = null;

  @JsonProperty("version")
  private Double version = null;

  public PlatformRuntime id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier for the hardware
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique identifier for the hardware")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PlatformRuntime language(String language) {
    this.language = language;
    return this;
  }

   /**
   * the specific runtime language
   * @return language
  **/
  @ApiModelProperty(required = true, value = "the specific runtime language")
  @NotNull


  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public PlatformRuntime runtimeType(RuntimeTypeEnum runtimeType) {
    this.runtimeType = runtimeType;
    return this;
  }

   /**
   * specifies the runtime type
   * @return runtimeType
  **/
  @ApiModelProperty(required = true, value = "specifies the runtime type")
  @NotNull


  public RuntimeTypeEnum getRuntimeType() {
    return runtimeType;
  }

  public void setRuntimeType(RuntimeTypeEnum runtimeType) {
    this.runtimeType = runtimeType;
  }

  public PlatformRuntime version(Double version) {
    this.version = version;
    return this;
  }

   /**
   * the version of the specified type
   * @return version
  **/
  @ApiModelProperty(required = true, value = "the version of the specified type")
  @NotNull


  public Double getVersion() {
    return version;
  }

  public void setVersion(Double version) {
    this.version = version;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlatformRuntime platformRuntime = (PlatformRuntime) o;
    return Objects.equals(this.id, platformRuntime.id) &&
        Objects.equals(this.language, platformRuntime.language) &&
        Objects.equals(this.runtimeType, platformRuntime.runtimeType) &&
        Objects.equals(this.version, platformRuntime.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, language, runtimeType, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlatformRuntime {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    runtimeType: ").append(toIndentedString(runtimeType)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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

