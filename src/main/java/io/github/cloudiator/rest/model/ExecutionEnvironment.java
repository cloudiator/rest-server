package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets ExecutionEnvironment
 */
public enum ExecutionEnvironment {
  
  SPARK("SPARK"),
  
  NATIVE("NATIVE"),
  
  CONTAINER("CONTAINER"),
  
  LANCE("LANCE");

  private String value;

  ExecutionEnvironment(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ExecutionEnvironment fromValue(String text) {
    for (ExecutionEnvironment b : ExecutionEnvironment.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

