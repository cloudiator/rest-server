package io.github.cloudiator.rest.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * a Cloudiator tool to be installed on a node
 */
public enum Tool {
  
  DOCKER("DOCKER"),
  
  KAIROSDB("KAIROSDB"),
  
  LANCE("LANCE"),
  
  VISOR("VISOR"),
  
  AXE("AXE"),
  
  SPARK_WORKER("SPARK_WORKER"),
  
  DLMS_AGENT("DLMS_AGENT"),
  
  ALLUXIO_CLIENT("ALLUXIO_CLIENT");

  private String value;

  Tool(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Tool fromValue(String text) {
    for (Tool b : Tool.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

