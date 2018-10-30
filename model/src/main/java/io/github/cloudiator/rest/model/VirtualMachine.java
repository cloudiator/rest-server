package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.cloudiator.rest.model.Hardware;
import io.github.cloudiator.rest.model.Image;
import io.github.cloudiator.rest.model.IpAddress;
import io.github.cloudiator.rest.model.Location;
import io.github.cloudiator.rest.model.LoginCredential;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VirtualMachine
 */
@Validated

public class VirtualMachine   {
  @JsonProperty("image")
  private Image image = null;

  @JsonProperty("hardware")
  private Hardware hardware = null;

  @JsonProperty("location")
  private Location location = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("ipaddresses")
  @Valid
  private List<IpAddress> ipaddresses = null;

  @JsonProperty("logincredential")
  private LoginCredential logincredential = null;

  public VirtualMachine image(Image image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public VirtualMachine hardware(Hardware hardware) {
    this.hardware = hardware;
    return this;
  }

  /**
   * Get hardware
   * @return hardware
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Hardware getHardware() {
    return hardware;
  }

  public void setHardware(Hardware hardware) {
    this.hardware = hardware;
  }

  public VirtualMachine location(Location location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
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

