package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Credentials for remote access to the virtual machine. Typically, one of password or privateKey is set. 
 */
@ApiModel(description = "Credentials for remote access to the virtual machine. Typically, one of password or privateKey is set. ")
@Validated

public class LoginCredential   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("privateKey")
  private String privateKey = null;

  public LoginCredential username(String username) {
    this.username = username;
    return this;
  }

   /**
   * The username for login
   * @return username
  **/
  @ApiModelProperty(value = "The username for login")


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public LoginCredential password(String password) {
    this.password = password;
    return this;
  }

   /**
   * The password for login
   * @return password
  **/
  @ApiModelProperty(value = "The password for login")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LoginCredential privateKey(String privateKey) {
    this.privateKey = privateKey;
    return this;
  }

   /**
   * The private key for login
   * @return privateKey
  **/
  @ApiModelProperty(value = "The private key for login")


  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginCredential loginCredential = (LoginCredential) o;
    return Objects.equals(this.username, loginCredential.username) &&
        Objects.equals(this.password, loginCredential.password) &&
        Objects.equals(this.privateKey, loginCredential.privateKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, privateKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginCredential {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    privateKey: ").append(toIndentedString(privateKey)).append("\n");
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

