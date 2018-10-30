package io.github.cloudiator.rest.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets IpAddressType
 */
public enum IpAddressType {
  
  PUBLIC_IP("PUBLIC_IP"),
  
  PRIVATE_IP("PRIVATE_IP");

  private String value;

  IpAddressType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static IpAddressType fromValue(String text) {
    for (IpAddressType b : IpAddressType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

