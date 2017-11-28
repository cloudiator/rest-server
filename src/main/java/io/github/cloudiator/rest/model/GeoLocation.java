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
        Objects.equals(this.country, geoLocation.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, country);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeoLocation {\n");
    
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
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

