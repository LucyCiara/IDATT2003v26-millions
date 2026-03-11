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

public class SaleTest {
  int testInt = 2008;
  BigDecimal testBigDec;
  Stock testStock;
  Share testShare;
  Share tooBigShare;
  Player testPlayer;
  SaleCalculator testCalculator;
  Sale testSale;
  Sale wrongSale;

  @BeforeEach
  void init() {

    this.testBigDec = new BigDecimal(20.0204);
    this.testStock = new Stock("TTC", "TestINC", this.testBigDec);
    this.testShare = new Share(this.testStock, BigDecimal.TWO, this.testBigDec);

    this.testCalculator = new SaleCalculator(testShare);
    this.testSale = new Sale(this.testShare, this.testInt);

    BigDecimal startingMoney = BigDecimal.TEN;
    this.testPlayer = new Player("testName", startingMoney);

    this.testPlayer.getPortfolio().addShare(this.testShare);

    Share wrongShare = new Share(this.testStock, BigDecimal.ONE, this.testBigDec);
    this.wrongSale = new Sale(wrongShare, this.testInt);
  }

  @Test
  public void saleOnNullPlayerThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> this.testSale.commit(null));
    assertEquals("player can't be null", exception.getMessage());
  }

  @Test
  public void saleAddsCorrectAmountOfMoney() {
    BigDecimal moneyBeforePurchase = this.testPlayer.getMoney();
    this.testSale.commit(this.testPlayer);
    assertEquals(moneyBeforePurchase.add(this.testCalculator.calculateTotal()), this.testPlayer.getMoney());
  }

  @Test
  public void saleLogsCorrectWeek() {
    assertEquals(this.testInt, this.testSale.getWeek());
  }

  @Test
  public void successfulSaleChangesCommittedToTrueFromFalse() {
    assertFalse(this.testSale.isCommitted());
    this.testSale.commit(this.testPlayer);
    assertTrue(this.testSale.isCommitted());
  }

  @Test
  public void committingTwiceThrowsExpectedException() {
    this.testSale.commit(this.testPlayer);
    assertTrue(this.testSale.isCommitted());
    this.testPlayer.getPortfolio().addShare(this.testShare);
    UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
        () -> this.testSale.commit(this.testPlayer));
    assertEquals("Can't commit the same Transaction more than once", exception.getMessage());
  }

  @Test
  public void cannotCommitShareYouDoNotHave() {
    UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
        () -> this.wrongSale.commit(this.testPlayer));
    assertEquals("player has to own the Share to sell it", exception.getMessage());
    assertFalse(this.wrongSale.isCommitted());
  }

  @Test
  public void shareIsRemovedFromPortfolioAfterSale() {
    assertTrue(this.testPlayer.getPortfolio().contains(this.testShare));
    this.testSale.commit(this.testPlayer);
    assertFalse(this.testPlayer.getPortfolio().contains(this.testShare));
  }

}
