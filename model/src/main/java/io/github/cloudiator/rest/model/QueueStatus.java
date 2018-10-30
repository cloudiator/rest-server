package io.github.cloudiator.rest.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Status of the running task 
 */
public enum QueueStatus {
  
  SCHEDULED("SCHEDULED"),
  
  RUNNING("RUNNING"),
  
  COMPLETED("COMPLETED"),
  
  FAILED("FAILED");

  private String value;

  QueueStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static QueueStatus fromValue(String text) {
    for (QueueStatus b : QueueStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

