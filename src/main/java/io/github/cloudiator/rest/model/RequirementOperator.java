package io.github.cloudiator.rest.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Part of AttributeRequirement
 */
public enum RequirementOperator {
  
  EQ("EQ"),
  
  LEQ("LEQ"),
  
  GEQ("GEQ"),
  
  GT("GT"),
  
  LT("LT");

  private String value;

  RequirementOperator(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static RequirementOperator fromValue(String text) {
    for (RequirementOperator b : RequirementOperator.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

