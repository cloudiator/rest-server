package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.LoginCredential;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VirtualMachine
 */

public class VirtualMachine   {
  @JsonProperty("image")
  private String image = null;

  @JsonProperty("hardware")
  private String hardware = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("ipaddresses")
  private List<IpAddress> ipaddresses = null;

  @JsonProperty("logincredential")
  private LoginCredential logincredential = null;

  public VirtualMachine image(String image) {
    this.image = image;
    return this;
  }

   /**
   * Get image
   * @return image
  **/
  @ApiModelProperty(example = "the image", value = "")


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public VirtualMachine hardware(String hardware) {
    this.hardware = hardware;
    return this;
  }

   /**
   * Get hardware
   * @return hardware
  **/
  @ApiModelProperty(example = "this is vm hardware", value = "")


  public String getHardware() {
    return hardware;
  }

  public void setHardware(String hardware) {
    this.hardware = hardware;
  }

  public VirtualMachine location(String location) {
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(example = "here I am", value = "")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public VirtualMachine id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1a79a4d60de6718e8e5b326e338ae5vm", value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VirtualMachine ipaddresses(List<IpAddress> ipaddresses) {
    this.ipaddresses = ipaddresses;
    return this;
  }

  public VirtualMachine addIpaddressesItem(IpAddress ipaddressesItem) {
    if (this.ipaddresses == null) {
      this.ipaddresses = new ArrayList<IpAddress>();
    }
    this.ipaddresses.add(ipaddressesItem);
    return this;
  }

   /**
   * Get ipaddresses
   * @return ipaddresses
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<IpAddress> getIpaddresses() {
    return ipaddresses;
  }

  public void setIpaddresses(List<IpAddress> ipaddresses) {
    this.ipaddresses = ipaddresses;
  }

  public VirtualMachine logincredential(LoginCredential logincredential) {
    this.logincredential = logincredential;
    return this;
  }

   /**
   * Get logincredential
   * @return logincredential
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LoginCredential getLogincredential() {
    return logincredential;
  }

  public void setLogincredential(LoginCredential logincredential) {
    this.logincredential = logincredential;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VirtualMachine virtualMachine = (VirtualMachine) o;
    return Objects.equals(this.image, virtualMachine.image) &&
        Objects.equals(this.hardware, virtualMachine.hardware) &&
        Objects.equals(this.location, virtualMachine.location) &&
        Objects.equals(this.id, virtualMachine.id) &&
        Objects.equals(this.ipaddresses, virtualMachine.ipaddresses) &&
        Objects.equals(this.logincredential, virtualMachine.logincredential);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, hardware, location, id, ipaddresses, logincredential);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VirtualMachine {\n");
    
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    hardware: ").append(toIndentedString(hardware)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ipaddresses: ").append(toIndentedString(ipaddresses)).append("\n");
    sb.append("    logincredential: ").append(toIndentedString(logincredential)).append("\n");
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

