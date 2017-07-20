package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.IpAddressType;
import io.github.cloudiator.rest.model.IpVersion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * IpAddress
 */

public class IpAddress   {
  @JsonProperty("IpAddressType")
  private IpAddressType ipAddressType = null;

  @JsonProperty("IpVersion")
  private IpVersion ipVersion = null;

  public IpAddress ipAddressType(IpAddressType ipAddressType) {
    this.ipAddressType = ipAddressType;
    return this;
  }

   /**
   * Get ipAddressType
   * @return ipAddressType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public IpAddressType getIpAddressType() {
    return ipAddressType;
  }

  public void setIpAddressType(IpAddressType ipAddressType) {
    this.ipAddressType = ipAddressType;
  }

  public IpAddress ipVersion(IpVersion ipVersion) {
    this.ipVersion = ipVersion;
    return this;
  }

   /**
   * Get ipVersion
   * @return ipVersion
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public IpVersion getIpVersion() {
    return ipVersion;
  }

  public void setIpVersion(IpVersion ipVersion) {
    this.ipVersion = ipVersion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IpAddress ipAddress = (IpAddress) o;
    return Objects.equals(this.ipAddressType, ipAddress.ipAddressType) &&
        Objects.equals(this.ipVersion, ipAddress.ipVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ipAddressType, ipVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IpAddress {\n");
    
    sb.append("    ipAddressType: ").append(toIndentedString(ipAddressType)).append("\n");
    sb.append("    ipVersion: ").append(toIndentedString(ipVersion)).append("\n");
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

