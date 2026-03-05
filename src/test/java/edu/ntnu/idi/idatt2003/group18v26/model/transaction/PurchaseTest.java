package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;

public class PurchaseTest {
  int testInt = 2008;
  BigDecimal testBigDec;
  Stock testStock;
  Share testShare;
  Share tooBigShare;
  Player testPlayer;
  PurchaseCalculator testCalculator;
  Purchase testPurchase;
  Purchase tooBigPurchase;

  @BeforeEach
  void init() {
    int amountOfSharesPlayerCanBuy = 2;

    this.testBigDec = new BigDecimal(20.0204);
    this.testStock = new Stock("TTC", "TestINC", this.testBigDec);
    this.testShare = new Share(this.testStock, new BigDecimal(amountOfSharesPlayerCanBuy - 1), this.testBigDec);

    this.testCalculator = new PurchaseCalculator(testShare);
    this.testPurchase = new Purchase(testShare, testInt);

    BigDecimal startingMoney = this.testCalculator.calculateTotal().multiply(new BigDecimal(amountOfSharesPlayerCanBuy));
    this.testPlayer = new Player("testName", startingMoney);

    BigDecimal tooMany = new BigDecimal(amountOfSharesPlayerCanBuy + 1);
    this.tooBigShare = new Share(this.testStock, tooMany, this.testBigDec);
    this.tooBigPurchase = new Purchase(this.tooBigShare, this.testInt);
  }

  @Test
  public void purchaseOnNullPlayerThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> this.testPurchase.commit(null));
    assertEquals("player can't be null", exception.getMessage());
  }

  @Test
  public void purchaseWithdrawsCorrectAmountOfMoney() {
    BigDecimal moneyBeforePurchase = this.testPlayer.getMoney();
    this.testPurchase.commit(this.testPlayer);
    assertEquals(moneyBeforePurchase.subtract(this.testCalculator.calculateTotal()), this.testPlayer.getMoney());
  }

  @Test
  public void purchaseLogsCorrectWeek() {
    assertEquals(this.testInt, this.testPurchase.getWeek());
  }

  @Test
  public void successfulPurchaseChangesCommittedToTrueFromFalse() {
    assertFalse(this.testPurchase.isCommitted());
    this.testPurchase.commit(this.testPlayer);
    assertTrue(this.testPurchase.isCommitted());
  }

  @Test
  public void committingTwiceThrowsExpectedException() {
    this.testPurchase.commit(this.testPlayer);
    assertTrue(this.testPurchase.isCommitted());
    UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
        () -> this.testPurchase.commit(this.testPlayer));
    assertEquals("Can't commit the same Transaction more than once", exception.getMessage());
  }

  @Test
  public void cannotCommitWhenInsufficientMoney() {
    ArithmeticException exception = assertThrows(ArithmeticException.class,
        () -> this.tooBigPurchase.commit(this.testPlayer));
    assertEquals("Player has insufficient money to buy this Share", exception.getMessage());
    assertFalse(this.tooBigPurchase.isCommitted());
  }

  @Test
  public void shareIsAddedToPortfolioAfterPurchase() {
    this.testPurchase.commit(this.testPlayer);
    assertTrue(this.testPlayer.getPortfolio().contains(this.testShare));
  }

}
