package io.github.cloudiator.rest.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Part of AttributeRequirement
 */
public enum RequirementOperator {
  
  EQ("EQ"),
  
  LEQ("LEQ"),
  
  GEQ("GEQ"),
  
  GT("GT"),
  
  LT("LT"),
  
  NEQ("NEQ"),
  
  IN("IN");

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

