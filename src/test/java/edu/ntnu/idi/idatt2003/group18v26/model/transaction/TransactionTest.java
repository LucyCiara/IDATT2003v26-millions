package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;

public class TransactionTest {
  Share testShare;
  int testInt = 2008;

  @BeforeEach
  void init() {
    BigDecimal testBigDec = new BigDecimal(20.0204);
    this.testShare = new Share(new Stock("TTC", "TestINC", testBigDec), BigDecimal.TWO, testBigDec);
  }

  @Test
  public void negativeWeekThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Purchase(this.testShare, -1));
    assertEquals("week can't be negative", exception.getMessage());
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
        () -> new Sale(this.testShare, -1));
    assertEquals("week can't be negative", exception2.getMessage());
  }
}
