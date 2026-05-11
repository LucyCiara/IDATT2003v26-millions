package edu.ntnu.idi.idatt2003.group18v26.util;

import java.math.BigDecimal;

/**
 * A class for validating parameters.
 */
public final class ParameterValidator {
  /**
   * [PRIVATE]
   * An internal method for checking if a variable is null.
   *
   * @param variable The variable to check if is null.
   * @param variableName The name of the variable.
   * @throws IllegalArgumentException The exception thrown if the validation fails.
   */
  private static final void nullCheck(
        Object variable,
        String variableName) throws IllegalArgumentException {
    if (variable == null) {
      throw new IllegalArgumentException(String.format("%s can't be null", variableName));
    }
  }

  /**
   * A method for checking the validity of a String.
   *
   * @param stringArg The String variable to check the validity of.
   * @param variableName The name of the String variable to check.
   * @throws IllegalArgumentException The exception thrown if the validation fails.
   */
  public static final void stringChecker(
      String stringArg,
      String variableName) throws IllegalArgumentException {
    nullCheck(stringArg, variableName);
    if (stringArg.isBlank()) {
      throw new IllegalArgumentException(String.format("%s can't be blank", variableName));
    }
  }

  /**
   * A method for checking the validity of an Object.
   *
   * @param objectArg The Object variable to check the validity of
   * @param variableName The name of the Object variable to check.
   * @throws IllegalArgumentException The exception thrown if the validation fails.
   */
  public static final void objectChecker(
      Object objectArg,
      String variableName) throws IllegalArgumentException {
    nullCheck(objectArg, variableName);
  }

  /**
   * A method for checking the validity of a BigDecimal.
   *
   * @param bigDecimalArg The BigDecimal variable to check the validity of.
   * @param variableName The name of the BigDecimal variable to check.
   * @throws IllegalArgumentException The exception thrown if the validation fails.
   */
  public static final void bigDecimalChecker(BigDecimal bigDecimalArg, String variableName)
      throws IllegalArgumentException {
    nullCheck(bigDecimalArg, variableName);
    if (bigDecimalArg.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException(String.format("%s must be larger than 0", variableName));
    }
  }

  /**
   * A method for checking the validity of an int variable.
   *
   * @param intArg The int variable to check the validity of.
   * @param variableName The name of the int variable to check.
   * @throws IllegalArgumentException The exception thrown if the validation fails.
   */
  public static final void intChecker(int intArg, String variableName)
      throws IllegalArgumentException {
    if (intArg < 0) {
      throw new IllegalArgumentException(
        String.format("%s can't be negative", variableName));
    }
  }

  /**
   * A method for checking the validity of a limit variable.
   *
   * @param limit The limit variable to check the validity of.
   * @param variableName The name of the limit variable to check.
   * @param max The maximum value of the limit variable, past which problems will occur.
   * @param variableName2 The name of the variable representing the max limit.
   * @throws IllegalArgumentException The exception thrown if the validation fails.
   */
  public static final void limitChecker(
      int limit,
      String variableName,
      int max,
      String variableName2) throws IllegalArgumentException {
    if (limit <= 0) {
      throw new IllegalArgumentException(String.format("%s must be larger than 0", variableName));
    }
    if (limit > max) {
      throw new IllegalArgumentException(
        String.format("%s can't be larger than %s", variableName, variableName2)
      );
    }
  }
}
