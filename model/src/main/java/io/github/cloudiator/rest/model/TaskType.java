package io.github.cloudiator.rest.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets TaskType
 */
public enum TaskType {
  
  BATCH("BATCH"),
  
  SERVICE("SERVICE");

  private String value;

  TaskType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TaskType fromValue(String text) {
    for (TaskType b : TaskType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

