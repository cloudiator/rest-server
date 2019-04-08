package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.cloudiator.rest.model.Behaviour;
import io.github.cloudiator.rest.model.Interval;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Subtype of Behaviour Represents a periodic runtime behaviour 
 */
@ApiModel(description = "Subtype of Behaviour Represents a periodic runtime behaviour ")
@Validated

public class PeriodicBehaviour extends Behaviour  {
  @JsonProperty("interval")
  private Interval interval = null;

  /**
   * Gets or Sets collisionHandling
   */
  public enum CollisionHandlingEnum {
    CANCEL("CANCEL"),
    
    PARALLEL("PARALLEL"),
    
    SKIP("SKIP");

    private String value;

    CollisionHandlingEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CollisionHandlingEnum fromValue(String text) {
      for (CollisionHandlingEnum b : CollisionHandlingEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("collisionHandling")
  private CollisionHandlingEnum collisionHandling = null;

  public PeriodicBehaviour interval(Interval interval) {
    this.interval = interval;
    return this;
  }

  /**
   * Get interval
   * @return interval
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Interval getInterval() {
    return interval;
  }

  public void setInterval(Interval interval) {
    this.interval = interval;
  }

  public PeriodicBehaviour collisionHandling(CollisionHandlingEnum collisionHandling) {
    this.collisionHandling = collisionHandling;
    return this;
  }

  /**
   * Get collisionHandling
   * @return collisionHandling
  **/
  @ApiModelProperty(value = "")


  public CollisionHandlingEnum getCollisionHandling() {
    return collisionHandling;
  }

  public void setCollisionHandling(CollisionHandlingEnum collisionHandling) {
    this.collisionHandling = collisionHandling;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeriodicBehaviour periodicBehaviour = (PeriodicBehaviour) o;
    return Objects.equals(this.interval, periodicBehaviour.interval) &&
        Objects.equals(this.collisionHandling, periodicBehaviour.collisionHandling) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(interval, collisionHandling, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PeriodicBehaviour {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    interval: ").append(toIndentedString(interval)).append("\n");
    sb.append("    collisionHandling: ").append(toIndentedString(collisionHandling)).append("\n");
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

