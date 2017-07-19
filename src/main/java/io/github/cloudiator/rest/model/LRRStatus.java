package io.github.cloudiator.rest.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The status of the running LRR
 */
public enum LRRStatus {
  
  SCHEDULED("SCHEDULED"),
  
  RUNNING("RUNNING"),
  
  COMPLETED("COMPLETED"),
  
  FAILED("FAILED");

  private String value;

  LRRStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LRRStatus fromValue(String text) {
    for (LRRStatus b : LRRStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

