package io.github.cloudiator.rest.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The unit of the interval
 */
public enum TimeUnit {
  
  DAYS("DAYS"),
  
  HOURS("HOURS"),
  
  MICROSECONDS("MICROSECONDS"),
  
  MILLISECONDS("MILLISECONDS"),
  
  MINUTES("MINUTES"),
  
  NANOSECONDS("NANOSECONDS"),
  
  SECONDS("SECONDS");

  private String value;

  TimeUnit(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TimeUnit fromValue(String text) {
    for (TimeUnit b : TimeUnit.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

