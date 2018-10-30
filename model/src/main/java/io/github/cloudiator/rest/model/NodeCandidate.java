package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * A node creatable by the system
 */
@ApiModel(description = "A node creatable by the system")
@Validated

public class NodeCandidate   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("price")
  private Double price = null;

  @JsonProperty("cloud")
  private Cloud cloud = null;

  @JsonProperty("image")
  private Image image = null;

  @JsonProperty("hardware")
  private Hardware hardware = null;

  @JsonProperty("location")
  private Location location = null;

  public NodeCandidate id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public NodeCandidate price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(value = "")


  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public NodeCandidate cloud(Cloud cloud) {
    this.cloud = cloud;
    return this;
  }

  /**
   * Get cloud
   * @return cloud
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Cloud getCloud() {
    return cloud;
  }

  public void setCloud(Cloud cloud) {
    this.cloud = cloud;
  }

  public NodeCandidate image(Image image) {
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

  public NodeCandidate hardware(Hardware hardware) {
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

  public NodeCandidate location(Location location) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NodeCandidate nodeCandidate = (NodeCandidate) o;
    return Objects.equals(this.id, nodeCandidate.id) &&
        Objects.equals(this.price, nodeCandidate.price) &&
        Objects.equals(this.cloud, nodeCandidate.cloud) &&
        Objects.equals(this.image, nodeCandidate.image) &&
        Objects.equals(this.hardware, nodeCandidate.hardware) &&
        Objects.equals(this.location, nodeCandidate.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, price, cloud, image, hardware, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NodeCandidate {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    cloud: ").append(toIndentedString(cloud)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    hardware: ").append(toIndentedString(hardware)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
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

