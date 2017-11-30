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
 * Represents a geographical location
 */
@ApiModel(description = "Represents a geographical location")
@Validated

public class GeoLocation   {
  @JsonProperty("city")
  private String city = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("latitude")
  private Float latitude = null;

  @JsonProperty("longitude")
  private Float longitude = null;

  public GeoLocation city(String city) {
    this.city = city;
    return this;
  }

   /**
   * City of the location
   * @return city
  **/
  @ApiModelProperty(value = "City of the location")


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public GeoLocation country(String country) {
    this.country = country;
    return this;
  }

   /**
   * An ISO 3166-1 alpha-2 country code
   * @return country
  **/
  @ApiModelProperty(value = "An ISO 3166-1 alpha-2 country code")


  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public GeoLocation latitude(Float latitude) {
    this.latitude = latitude;
    return this;
  }

   /**
   * Latitude of the location in decimal degrees
   * @return latitude
  **/
  @ApiModelProperty(value = "Latitude of the location in decimal degrees")


  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public GeoLocation longitude(Float longitude) {
    this.longitude = longitude;
    return this;
  }

   /**
   * Longitude of the location in decimal degrees
   * @return longitude
  **/
  @ApiModelProperty(value = "Longitude of the location in decimal degrees")


  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeoLocation geoLocation = (GeoLocation) o;
    return Objects.equals(this.city, geoLocation.city) &&
        Objects.equals(this.country, geoLocation.country) &&
        Objects.equals(this.latitude, geoLocation.latitude) &&
        Objects.equals(this.longitude, geoLocation.longitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, country, latitude, longitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeoLocation {\n");
    
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
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

