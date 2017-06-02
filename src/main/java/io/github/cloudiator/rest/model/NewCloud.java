package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.CloudConfiguration;
import io.github.cloudiator.rest.model.CloudCredential;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a new cloud that is to be created 
 */
@ApiModel(description = "Represents a new cloud that is to be created ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-02T09:24:26.089+02:00")

public class NewCloud   {
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

  public NewCloud name(String name) {
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

  public NewCloud endpoint(String endpoint) {
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

  public NewCloud cloudType(CloudTypeEnum cloudType) {
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

  public NewCloud api(Api api) {
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

  public NewCloud credential(CloudCredential credential) {
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

  public NewCloud cloudConfiguration(CloudConfiguration cloudConfiguration) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewCloud newCloud = (NewCloud) o;
    return Objects.equals(this.name, newCloud.name) &&
        Objects.equals(this.endpoint, newCloud.endpoint) &&
        Objects.equals(this.cloudType, newCloud.cloudType) &&
        Objects.equals(this.api, newCloud.api) &&
        Objects.equals(this.credential, newCloud.credential) &&
        Objects.equals(this.cloudConfiguration, newCloud.cloudConfiguration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, endpoint, cloudType, api, credential, cloudConfiguration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewCloud {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    endpoint: ").append(toIndentedString(endpoint)).append("\n");
    sb.append("    cloudType: ").append(toIndentedString(cloudType)).append("\n");
    sb.append("    api: ").append(toIndentedString(api)).append("\n");
    sb.append("    credential: ").append(toIndentedString(credential)).append("\n");
    sb.append("    cloudConfiguration: ").append(toIndentedString(cloudConfiguration)).append("\n");
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

