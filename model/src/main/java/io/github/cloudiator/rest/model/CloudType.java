package io.github.cloudiator.rest.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * type of the cloud
 */
public enum CloudType {
  
  PRIVATE("PRIVATE"),
  
  PUBLIC("PUBLIC");

  private String value;

  CloudType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CloudType fromValue(String text) {
    for (CloudType b : CloudType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

