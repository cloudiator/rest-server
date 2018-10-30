package io.github.cloudiator.rest.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Type of OS Architecture
 */
public enum OperatingSystemArchitecture {
  
  AMD64("AMD64"),
  
  UNKOWN("UNKOWN"),
  
  I386("I386");

  private String value;

  OperatingSystemArchitecture(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OperatingSystemArchitecture fromValue(String text) {
    for (OperatingSystemArchitecture b : OperatingSystemArchitecture.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

