package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.CloudCredential;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Repesents a new PaaS provider to be created 
 */
@ApiModel(description = "Repesents a new PaaS provider to be created ")

public class NewPlatform   {
  @JsonProperty("name")
  private String name = null;

  /**
   * PaaS stack type
   */
  public enum TypeEnum {
    HEROKU("HEROKU"),
    
    OPENSHIFT("OPENSHIFT"),
    
    CLOUDFOUNDRY("CLOUDFOUNDRY");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("api")
  private Api api = null;

  @JsonProperty("credential")
  private CloudCredential credential = null;

  @JsonProperty("endpoint")
  private String endpoint = null;

  public NewPlatform name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Human-readable name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Human-readable name")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public NewPlatform type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * PaaS stack type
   * @return type
  **/
  @ApiModelProperty(value = "PaaS stack type")


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public NewPlatform api(Api api) {
    this.api = api;
    return this;
  }

   /**
   * Get api
   * @return api
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Api getApi() {
    return api;
  }

  public void setApi(Api api) {
    this.api = api;
  }

  public NewPlatform credential(CloudCredential credential) {
    this.credential = credential;
    return this;
  }

   /**
   * Get credential
   * @return credential
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public CloudCredential getCredential() {
    return credential;
  }

  public void setCredential(CloudCredential credential) {
    this.credential = credential;
  }

  public NewPlatform endpoint(String endpoint) {
    this.endpoint = endpoint;
    return this;
  }

   /**
   * URI where the api of this platform provider can be accessed.
   * @return endpoint
  **/
  @ApiModelProperty(example = "https://endpoint.example.com", value = "URI where the api of this platform provider can be accessed.")


  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewPlatform newPlatform = (NewPlatform) o;
    return Objects.equals(this.name, newPlatform.name) &&
        Objects.equals(this.type, newPlatform.type) &&
        Objects.equals(this.api, newPlatform.api) &&
        Objects.equals(this.credential, newPlatform.credential) &&
        Objects.equals(this.endpoint, newPlatform.endpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, api, credential, endpoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewPlatform {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    api: ").append(toIndentedString(api)).append("\n");
    sb.append("    credential: ").append(toIndentedString(credential)).append("\n");
    sb.append("    endpoint: ").append(toIndentedString(endpoint)).append("\n");
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

