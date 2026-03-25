package edu.ntnu.idi.idatt2003.group18v26.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Portfolio;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.SaleCalculator;

/**
 * A class for testing the Player class.
 */
public class PlayerTest {
  private String testName;
  private String wrongName;
  private BigDecimal testStartingMoney;
  private BigDecimal wrongStartingMoney;
  private BigDecimal negativeStartingMoney;

  /**
   * Sets some test variables before each test.
   */
  @BeforeEach
  void init() {
    this.testName = "test";
    this.wrongName = "wrong";
    this.testStartingMoney = new BigDecimal(20.19);
    this.wrongStartingMoney = new BigDecimal(23.19);
    this.negativeStartingMoney = new BigDecimal(-14.19);
  }

  /**
   * A method for checking if the player's constructor throws the expected exception when the name input is null.
   */
  @Test
  public void nullNamePlayerConstructorThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Player(null, testStartingMoney));
    assertEquals("name can't be null", exception.getMessage());
  }

  /**
   * A method for checking if the player's constructor throws the expected exception when the name input is empty.
   */
  @Test
  public void emptyNamePlayerConstructorThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Player("", this.testStartingMoney));
    assertEquals("name can't be blank", exception.getMessage());
  }

  /**
   * A method for checking if the player's constructor throws the expected exception when the startingMoney is null.
   */
  @Test
  public void nullStartingMoneyPlayerConstructorThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Player(testName, null));
    assertEquals("startingMoney can't be null", exception.getMessage());
  }

  /**
   * A method for checking if the player's constructor throws the expected exception when the startingMoney is zero.
   */
  @Test
  public void zeroStartingMoneyPlayerConstructorThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Player(this.testName, BigDecimal.ZERO));
    assertEquals("startingMoney must be a positive non-zero number", exception.getMessage());
  }

  /**
   * A method for checking if the player's constructor throws the expected exception when the startingMoney is negative.
   */
  @Test
  public void negativeStartingMoneyPlayerConstructorThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Player(this.testName, this.negativeStartingMoney));
    assertEquals("startingMoney must be a positive non-zero number", exception.getMessage());
  }

  /**
   * A method for checking if the player's getName method returns the name and nothing but the name.
   */
  @Test
  public void getNameReturnsNameAndOnlyTheName() {
    String outputName = new Player(this.testName, this.testStartingMoney).getName();
    assertEquals(this.testName, outputName);
    assertNotEquals(this.wrongName, outputName);
  }

  /**
   * A method for checking if the player's unmodified money is the same as the starting money.
   */
  @Test
  public void unModifiedMoneyIsStartingMoney() {
    BigDecimal outputMoney = new Player(this.testName, this.testStartingMoney).getMoney();
    assertEquals(this.testStartingMoney, outputMoney);
    assertNotEquals(this.wrongStartingMoney, outputMoney);
  }

  /**
   * A method for checking if adding null money causes the expected exception to be thrown.
   */
  @Test
  public void nullMoneyToAddThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Player(testName, testStartingMoney).addMoney(null));
    assertEquals("moneyToAdd can't be null", exception.getMessage());
  }

  /**
   * A method for checking if adding zero money causes the expected exception to be thrown.
   */
  @Test
  public void zeroMoneyToAddThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Player(testName, testStartingMoney).addMoney(BigDecimal.ZERO));
    assertEquals("moneyToAdd must be a positive non-zero number", exception.getMessage());
  }

  /**
   * A method for checking if adding negative money causes the expected exception to be thrown.
   */
  @Test
  public void negativeMoneyToAddThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Player(testName, testStartingMoney).addMoney(this.negativeStartingMoney));
    assertEquals("moneyToAdd must be a positive non-zero number", exception.getMessage());
  }

  /**
   * A method for checking if addMoney causes the new money to be as expected.
   */
  @Test
  public void addMoneyReturnsCorrectNewMoney() {
    BigDecimal expectedSum = this.testStartingMoney.add(this.testStartingMoney);
    Player testPlayer = new Player(this.testName, this.testStartingMoney);
    testPlayer.addMoney(this.testStartingMoney);
    assertEquals(expectedSum, testPlayer.getMoney());
    assertNotEquals(testStartingMoney, testPlayer.getMoney());
  }

  /**
   * A method for checking if withdrawing null money causes the expected exception to be thrown.
   */
  @Test
  public void nullMoneyToWithdrawThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Player(testName, testStartingMoney).withdrawMoney(null));
    assertEquals("moneyToWithdraw can't be null", exception.getMessage());
  }

  /**
   * A method for checking if withdrawing zero money causes the expected exception to be thrown.
   */
  @Test
  public void zeroMoneyToWithdrawThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Player(testName, testStartingMoney).withdrawMoney(BigDecimal.ZERO));
    assertEquals("moneyToWithdraw must be a positive non-zero number", exception.getMessage());
  }

  /**
   * A method for checking if withdrawing negative money causes the expected exception to be thrown.
   */
  @Test
  public void negativeMoneyToWithdrawThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Player(testName, testStartingMoney).withdrawMoney(this.negativeStartingMoney));
    assertEquals("moneyToWithdraw must be a positive non-zero number", exception.getMessage());
  }

  /**
   * A method for checking if getMoney returns the correct money after withdrawing.
   */
  @Test
  public void withdrawMoneyReturnsCorrectNewMoney() {
    BigDecimal expectedSum = this.testStartingMoney.subtract(this.testStartingMoney);
    Player testPlayer = new Player(this.testName, this.testStartingMoney);
    testPlayer.withdrawMoney(this.testStartingMoney);
    assertEquals(expectedSum, testPlayer.getMoney());
    assertNotEquals(testStartingMoney, testPlayer.getMoney());
  }

  /**
   * A method for checking if getPortfolio gets the portfolio, and not another portfolio.
   */
  @Test
  public void getPortfolioGetsOnlyPortfolio() {
    Player testPlayer = new Player(this.testName, this.testStartingMoney);
    Portfolio playerPortfolio = testPlayer.getPortfolio();
    Portfolio notPlayerPortfolio = new Portfolio();
    assertEquals(playerPortfolio, testPlayer.getPortfolio());
    assertNotEquals(notPlayerPortfolio, testPlayer.getPortfolio());
  }

  @Test
  void getNetWorthReturnsExpectedSum() {
    Player testPlayer = new Player(this.testName, this.testStartingMoney);
    BigDecimal expectedWorth = this.testStartingMoney;
    assertEquals(0, testPlayer.getNetWorth().compareTo(expectedWorth));
    testPlayer.addMoney(BigDecimal.TEN);
    expectedWorth = expectedWorth.add(BigDecimal.TEN);
    assertEquals(0, testPlayer.getNetWorth().compareTo(expectedWorth));
    Share testShare
        = new Share(new Stock("A", "B", BigDecimal.TEN), BigDecimal.TWO, BigDecimal.TEN);
    testPlayer.getPortfolio().addShare(testShare);
    expectedWorth = expectedWorth.add(new SaleCalculator(testShare).calculateTotal());
  }
}
