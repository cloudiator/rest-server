package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets ProcessMapping
 */
public enum ProcessMapping {
  
  SINGLE("SINGLE"),
  
  CLUSTER("CLUSTER");

  private String value;

  ProcessMapping(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ProcessMapping fromValue(String text) {
    for (ProcessMapping b : ProcessMapping.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

