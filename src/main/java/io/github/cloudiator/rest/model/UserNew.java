package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Tenant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a user that should be create in the system 
 */
@ApiModel(description = "Represents a user that should be create in the system ")
@Validated

public class UserNew   {
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("passwordRepeat")
  private String passwordRepeat = null;

  @JsonProperty("tenant")
  private Tenant tenant = null;

  public UserNew email(String email) {
    this.email = email;
    return this;
  }

  /**
   * EMail address of the user
   * @return email
  **/
  @ApiModelProperty(required = true, value = "EMail address of the user")
  @NotNull


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserNew password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Password of the user
   * @return password
  **/
  @ApiModelProperty(required = true, value = "Password of the user")
  @NotNull


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserNew passwordRepeat(String passwordRepeat) {
    this.passwordRepeat = passwordRepeat;
    return this;
  }

  /**
   * Repetition of the user's password
   * @return passwordRepeat
  **/
  @ApiModelProperty(required = true, value = "Repetition of the user's password")
  @NotNull


  public String getPasswordRepeat() {
    return passwordRepeat;
  }

  public void setPasswordRepeat(String passwordRepeat) {
    this.passwordRepeat = passwordRepeat;
  }

  public UserNew tenant(Tenant tenant) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserNew userNew = (UserNew) o;
    return Objects.equals(this.email, userNew.email) &&
        Objects.equals(this.password, userNew.password) &&
        Objects.equals(this.passwordRepeat, userNew.passwordRepeat) &&
        Objects.equals(this.tenant, userNew.tenant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, passwordRepeat, tenant);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserNew {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    passwordRepeat: ").append(toIndentedString(passwordRepeat)).append("\n");
    sb.append("    tenant: ").append(toIndentedString(tenant)).append("\n");
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

