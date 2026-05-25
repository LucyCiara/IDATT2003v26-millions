package edu.ntnu.idi.idatt2003.group18v26.model.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class TransactionSnapshotTest {

  @Test
  void defaultConstructor_createsEmptyObject() {
    TransactionSnapshot snapshot = new TransactionSnapshot();

    assertNull(snapshot.type);
    assertNull(snapshot.symbol);
    assertNull(snapshot.quantity);
    assertNull(snapshot.totalPrice);
    assertEquals(-1, snapshot.week);
  }

  @Test
  void constructor_setsFieldsCorrectly() {
    TransactionSnapshot snapshot = new TransactionSnapshot(
        "PURCHASE",
        "AAPL",
        new BigDecimal("2"),
        new BigDecimal("300.00"), 2);

    assertEquals("PURCHASE", snapshot.type);
    assertEquals("AAPL", snapshot.symbol);
    assertEquals(0, snapshot.quantity.compareTo(new BigDecimal("2")));
    assertEquals(0, snapshot.totalPrice.compareTo(new BigDecimal("300.00")));
    assertEquals(2, snapshot.week);
  }
}