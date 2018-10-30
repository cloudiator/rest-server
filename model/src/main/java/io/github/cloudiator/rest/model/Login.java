package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Login information provided by the user to be authorized 
 */
@ApiModel(description = "Login information provided by the user to be authorized ")
@Validated

public class Login   {
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("tenant")
  private Tenant tenant = null;

  @JsonProperty("password")
  private String password = null;

  public Login email(String email) {
    this.email = email;
    return this;
  }

  /**
   * EMail address of the user
   * @return email
  **/
  @ApiModelProperty(example = "john.doe@example.com", required = true, value = "EMail address of the user")
  @NotNull


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Login tenant(Tenant tenant) {
    this.tenant = tenant;
    return this;
  }

  /**
   * Get tenant
   * @return tenant
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Tenant getTenant() {
    return tenant;
  }

  public void setTenant(Tenant tenant) {
    this.tenant = tenant;
  }

  public Login password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Password of the user
   * @return password
  **/
  @ApiModelProperty(example = "SecretPassword", required = true, value = "Password of the user")
  @NotNull


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Login login = (Login) o;
    return Objects.equals(this.email, login.email) &&
        Objects.equals(this.tenant, login.tenant) &&
        Objects.equals(this.password, login.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, tenant, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Login {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    tenant: ").append(toIndentedString(tenant)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

