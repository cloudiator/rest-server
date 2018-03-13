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
 * Represents an API Token, that is used to represent a successful authentication of a user 
 */
@ApiModel(description = "Represents an API Token, that is used to represent a successful authentication of a user ")
@Validated

public class Token   {
  @JsonProperty("token")
  private String token = null;

  @JsonProperty("owner")
  private String owner = null;

  @JsonProperty("issuedTime")
  private Long issuedTime = null;

  @JsonProperty("expireTime")
  private Long expireTime = null;

  public Token token(String token) {
    this.token = token;
    return this;
  }

   /**
   * The token
   * @return token
  **/
  @ApiModelProperty(example = "theToken", required = true, value = "The token")
  @NotNull


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Token owner(String owner) {
    this.owner = owner;
    return this;
  }

   /**
   * owner of the token
   * @return owner
  **/
  @ApiModelProperty(example = "owner@home.office", value = "owner of the token")


  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public Token issuedTime(Long issuedTime) {
    this.issuedTime = issuedTime;
    return this;
  }

   /**
   * SystemMillis Token was issued
   * @return issuedTime
  **/
  @ApiModelProperty(example = "1273222647313", value = "SystemMillis Token was issued")


  public Long getIssuedTime() {
    return issuedTime;
  }

  public void setIssuedTime(Long issuedTime) {
    this.issuedTime = issuedTime;
  }

  public Token expireTime(Long expireTime) {
    this.expireTime = expireTime;
    return this;
  }

   /**
   * SystemMillis the Token expires
   * @return expireTime
  **/
  @ApiModelProperty(example = "1273827447313", value = "SystemMillis the Token expires")


  public Long getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(Long expireTime) {
    this.expireTime = expireTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Token token = (Token) o;
    return Objects.equals(this.token, token.token) &&
        Objects.equals(this.owner, token.owner) &&
        Objects.equals(this.issuedTime, token.issuedTime) &&
        Objects.equals(this.expireTime, token.expireTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, owner, issuedTime, expireTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Token {\n");
    
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    issuedTime: ").append(toIndentedString(issuedTime)).append("\n");
    sb.append("    expireTime: ").append(toIndentedString(expireTime)).append("\n");
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

