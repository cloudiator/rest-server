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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-02T09:24:26.089+02:00")

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

  @JsonProperty("cloudConfiguration")
  private CloudConfiguration cloudConfiguration = null;

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
  @ApiModelProperty(example = "Openstack", required = true, value = "Human readable name for the cloud. Needs to be unique.")
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
  @ApiModelProperty(example = "https://endpoint.example.com", required = true, value = "URI where the api of this cloud provider can be accessed.")
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
  @ApiModelProperty(example = "PRIVATE", required = true, value = "Type of the cloud")
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

  public Cloud cloudConfiguration(CloudConfiguration cloudConfiguration) {
    this.cloudConfiguration = cloudConfiguration;
    return this;
  }

   /**
   * Get cloudConfiguration
   * @return cloudConfiguration
  **/
  @ApiModelProperty(value = "")

  @Valid

  public CloudConfiguration getCloudConfiguration() {
    return cloudConfiguration;
  }

  public void setCloudConfiguration(CloudConfiguration cloudConfiguration) {
    this.cloudConfiguration = cloudConfiguration;
  }

  public Cloud id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier for the cloud
   * @return id
  **/
  @ApiModelProperty(example = "1a79a4d60de6718e8e5b326e338ae533", value = "Unique identifier for the cloud")


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
        Objects.equals(this.cloudConfiguration, cloud.cloudConfiguration) &&
        Objects.equals(this.id, cloud.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, endpoint, cloudType, api, credential, cloudConfiguration, id);
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
    sb.append("    cloudConfiguration: ").append(toIndentedString(cloudConfiguration)).append("\n");
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

