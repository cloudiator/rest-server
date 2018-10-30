package io.github.cloudiator.rest.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * OS Type
 */
public enum OperatingSystemType {
  
  LINUX("LINUX"),
  
  UNKOWN("UNKOWN"),
  
  UNIX("UNIX"),
  
  WINDOWS_OS("WINDOWS_OS"),
  
  BSD("BSD"),
  
  MAC("MAC");

  private String value;

  OperatingSystemType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OperatingSystemType fromValue(String text) {
    for (OperatingSystemType b : OperatingSystemType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

