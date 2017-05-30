package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.CloudConfiguration;
import io.github.cloudiator.rest.model.CloudCredential;
import io.github.cloudiator.rest.model.NewCloud;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Representation of a cloud used by Cloudiator 
 */
@ApiModel(description = "Representation of a cloud used by Cloudiator ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-30T11:42:58.188+02:00")

public class Cloud   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("endpoint")
  private String endpoint = null;

  /**
   * Type of the cloud
   */
  public enum CloudTypeEnum {
    PRIVATE("PRIVATE"),
    
    PUBLIC("PUBLIC");

    private String value;

    CloudTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CloudTypeEnum fromValue(String text) {
      for (CloudTypeEnum b : CloudTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("cloudType")
  private CloudTypeEnum cloudType = null;

  @JsonProperty("api")
  private Api api = null;

  @JsonProperty("credential")
  private CloudCredential credential = null;

  @JsonProperty("configuration")
  private CloudConfiguration _configuration = null;

  @JsonProperty("id")
  private String id = null;

  public Cloud name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Human readable name for the cloud. Needs to be unique.
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Human readable name for the cloud. Needs to be unique.")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Cloud endpoint(String endpoint) {
    this.endpoint = endpoint;
    return this;
  }

   /**
   * URI where the api of this cloud provider can be accessed.
   * @return endpoint
  **/
  @ApiModelProperty(required = true, value = "URI where the api of this cloud provider can be accessed.")
  @NotNull


  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public Cloud cloudType(CloudTypeEnum cloudType) {
    this.cloudType = cloudType;
    return this;
  }

   /**
   * Type of the cloud
   * @return cloudType
  **/
  @ApiModelProperty(required = true, value = "Type of the cloud")
  @NotNull


  public CloudTypeEnum getCloudType() {
    return cloudType;
  }

  public void setCloudType(CloudTypeEnum cloudType) {
    this.cloudType = cloudType;
  }

  public Cloud api(Api api) {
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

  public Cloud credential(CloudCredential credential) {
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

  public Cloud _configuration(CloudConfiguration _configuration) {
    this._configuration = _configuration;
    return this;
  }

   /**
   * Get _configuration
   * @return _configuration
  **/
  @ApiModelProperty(value = "")

  @Valid

  public CloudConfiguration getConfiguration() {
    return _configuration;
  }

  public void setConfiguration(CloudConfiguration _configuration) {
    this._configuration = _configuration;
  }

  public Cloud id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier for the cloud
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier for the cloud")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cloud cloud = (Cloud) o;
    return Objects.equals(this.name, cloud.name) &&
        Objects.equals(this.endpoint, cloud.endpoint) &&
        Objects.equals(this.cloudType, cloud.cloudType) &&
        Objects.equals(this.api, cloud.api) &&
        Objects.equals(this.credential, cloud.credential) &&
        Objects.equals(this._configuration, cloud._configuration) &&
        Objects.equals(this.id, cloud.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, endpoint, cloudType, api, credential, _configuration, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cloud {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    endpoint: ").append(toIndentedString(endpoint)).append("\n");
    sb.append("    cloudType: ").append(toIndentedString(cloudType)).append("\n");
    sb.append("    api: ").append(toIndentedString(api)).append("\n");
    sb.append("    credential: ").append(toIndentedString(credential)).append("\n");
    sb.append("    _configuration: ").append(toIndentedString(_configuration)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

