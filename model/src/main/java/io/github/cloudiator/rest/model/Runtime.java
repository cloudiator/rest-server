package io.github.cloudiator.rest.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents runtime provided by a FaaS platform. 
 */
public enum Runtime {
  
  NODEJS("nodejs"),
  
  PYTHON("python"),
  
  JAVA("java"),
  
  DOTNET("dotnet"),
  
  GO("go");

  private String value;

  Runtime(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Runtime fromValue(String text) {
    for (Runtime b : Runtime.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

