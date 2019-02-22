package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.Api;
import io.github.cloudiator.rest.model.CloudConfiguration;
import io.github.cloudiator.rest.model.CloudCredential;
import io.github.cloudiator.rest.model.CloudType;
import io.github.cloudiator.rest.model.NewCloud;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Representation of a cloud used by Cloudiator 
 */
@ApiModel(description = "Representation of a cloud used by Cloudiator ")
@Validated

public class Cloud   {
  @JsonProperty("endpoint")
  private String endpoint = null;

  @JsonProperty("cloudType")
  private CloudType cloudType = null;

  @JsonProperty("api")
  private Api api = null;

  @JsonProperty("credential")
  private CloudCredential credential = null;

  @JsonProperty("cloudConfiguration")
  private CloudConfiguration cloudConfiguration = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("owner")
  private String owner = null;

  /**
   * State of the cloud
   */
  public enum StateEnum {
    OK("OK"),
    
    ERROR("ERROR");

    private String value;

    StateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StateEnum fromValue(String text) {
      for (StateEnum b : StateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("state")
  private StateEnum state = null;

  @JsonProperty("diagnostic")
  private String diagnostic = null;

  public Cloud endpoint(String endpoint) {
    this.endpoint = endpoint;
    return this;
  }

  /**
   * URI where the api of this cloud provider can be accessed.
   * @return endpoint
  **/
  @ApiModelProperty(example = "https://endpoint.example.com", value = "URI where the api of this cloud provider can be accessed.")


  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public Cloud cloudType(CloudType cloudType) {
    this.cloudType = cloudType;
    return this;
  }

  /**
   * Get cloudType
   * @return cloudType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public CloudType getCloudType() {
    return cloudType;
  }

  public void setCloudType(CloudType cloudType) {
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

  public Cloud owner(String owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Id of the user owning this cloud. 
   * @return owner
  **/
  @ApiModelProperty(value = "Id of the user owning this cloud. ")


  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public Cloud state(StateEnum state) {
    this.state = state;
    return this;
  }

  /**
   * State of the cloud
   * @return state
  **/
  @ApiModelProperty(value = "State of the cloud")


  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public Cloud diagnostic(String diagnostic) {
    this.diagnostic = diagnostic;
    return this;
  }

  /**
   * Diagnostic information for the cloud
   * @return diagnostic
  **/
  @ApiModelProperty(value = "Diagnostic information for the cloud")


  public String getDiagnostic() {
    return diagnostic;
  }

  public void setDiagnostic(String diagnostic) {
    this.diagnostic = diagnostic;
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
    return Objects.equals(this.endpoint, cloud.endpoint) &&
        Objects.equals(this.cloudType, cloud.cloudType) &&
        Objects.equals(this.api, cloud.api) &&
        Objects.equals(this.credential, cloud.credential) &&
        Objects.equals(this.cloudConfiguration, cloud.cloudConfiguration) &&
        Objects.equals(this.id, cloud.id) &&
        Objects.equals(this.owner, cloud.owner) &&
        Objects.equals(this.state, cloud.state) &&
        Objects.equals(this.diagnostic, cloud.diagnostic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(endpoint, cloudType, api, credential, cloudConfiguration, id, owner, state, diagnostic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cloud {\n");
    
    sb.append("    endpoint: ").append(toIndentedString(endpoint)).append("\n");
    sb.append("    cloudType: ").append(toIndentedString(cloudType)).append("\n");
    sb.append("    api: ").append(toIndentedString(api)).append("\n");
    sb.append("    credential: ").append(toIndentedString(credential)).append("\n");
    sb.append("    cloudConfiguration: ").append(toIndentedString(cloudConfiguration)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    diagnostic: ").append(toIndentedString(diagnostic)).append("\n");
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

