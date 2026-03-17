package edu.ntnu.idi.idatt2003.group18v26.util;

import java.math.BigDecimal;

public final class ParameterValidator {
  private static final void nullCheck(Object variable, String variableName) throws IllegalArgumentException {
    if (variable == null) {
      throw new IllegalArgumentException(String.format("%s can't be null", variableName));
    }
  }

  public static final void stringChecker(String stringArg, String variableName) throws IllegalArgumentException {
    nullCheck(stringArg, variableName);
    if (stringArg.isBlank()) {
      throw new IllegalArgumentException(String.format("%s can't be blank", variableName));
    }
  }

  public static final void objectChecker(Object objectArg, String variableName) throws IllegalArgumentException {
    nullCheck(objectArg, variableName);
  }

  public static final void bigDecimalChecker(BigDecimal bigDecimalArg, String variableName)
      throws IllegalArgumentException {
    nullCheck(bigDecimalArg, variableName);
    if (bigDecimalArg.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException(String.format("%s must be larger than 0", variableName));
    }
  }
}
