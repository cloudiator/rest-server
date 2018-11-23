package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a runtime for a Platform component, e.g. Java, PHP, Tomcat
 */
@ApiModel(description = "Represents a runtime for a Platform component, e.g. Java, PHP, Tomcat")
@Validated

public class NewPlatformRuntime   {
  @JsonProperty("name")
  private String name = null;

  /**
   * the specific runtime language
   */
  public enum LanguageEnum {
    PHP("PHP"),
    
    JAVA("JAVA"),
    
    RUBY("RUBY"),
    
    PYTHON("PYTHON");

    private String value;

    LanguageEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static LanguageEnum fromValue(String text) {
      for (LanguageEnum b : LanguageEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("language")
  private LanguageEnum language = null;

  @JsonProperty("languageVersion")
  private Double languageVersion = null;

  /**
   * specifies the runtime type
   */
  public enum RuntimeTypeEnum {
    STANDALONE("standalone"),
    
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

  public NewPlatformRuntime name(String name) {
    this.name = name;
    return this;
  }

  /**
   * human readable name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "human readable name")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public NewPlatformRuntime language(LanguageEnum language) {
    this.language = language;
    return this;
  }

  /**
   * the specific runtime language
   * @return language
  **/
  @ApiModelProperty(required = true, value = "the specific runtime language")
  @NotNull


  public LanguageEnum getLanguage() {
    return language;
  }

  public void setLanguage(LanguageEnum language) {
    this.language = language;
  }

  public NewPlatformRuntime languageVersion(Double languageVersion) {
    this.languageVersion = languageVersion;
    return this;
  }

  /**
   * language version number
   * @return languageVersion
  **/
  @ApiModelProperty(example = "11.0", value = "language version number")


  public Double getLanguageVersion() {
    return languageVersion;
  }

  public void setLanguageVersion(Double languageVersion) {
    this.languageVersion = languageVersion;
  }

  public NewPlatformRuntime runtimeType(RuntimeTypeEnum runtimeType) {
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

  public NewPlatformRuntime version(Double version) {
    this.version = version;
    return this;
  }

  /**
   * the version of the specified type
   * @return version
  **/
  @ApiModelProperty(example = "2.0", required = true, value = "the version of the specified type")
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
    NewPlatformRuntime newPlatformRuntime = (NewPlatformRuntime) o;
    return Objects.equals(this.name, newPlatformRuntime.name) &&
        Objects.equals(this.language, newPlatformRuntime.language) &&
        Objects.equals(this.languageVersion, newPlatformRuntime.languageVersion) &&
        Objects.equals(this.runtimeType, newPlatformRuntime.runtimeType) &&
        Objects.equals(this.version, newPlatformRuntime.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, language, languageVersion, runtimeType, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewPlatformRuntime {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    languageVersion: ").append(toIndentedString(languageVersion)).append("\n");
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

