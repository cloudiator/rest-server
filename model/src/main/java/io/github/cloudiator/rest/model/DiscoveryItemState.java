package io.github.cloudiator.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets DiscoveryItemState
 */
public enum DiscoveryItemState {
  
  NEW("NEW"),
  
  OK("OK"),
  
  REMOTELY_DELETED("REMOTELY_DELETED"),
  
  LOCALLY_DELETED("LOCALLY_DELETED"),
  
  DISABLED("DISABLED"),
  
  DELETED("DELETED"),
  
  UNKNOWN("UNKNOWN");

  private String value;

  DiscoveryItemState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static DiscoveryItemState fromValue(String text) {
    for (DiscoveryItemState b : DiscoveryItemState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

