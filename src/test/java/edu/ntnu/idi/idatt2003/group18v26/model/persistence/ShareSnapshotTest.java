package edu.ntnu.idi.idatt2003.group18v26.model.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ShareSnapshotTest {

  @Test
  void defaultConstructor_createsEmptyObject() {
    ShareSnapshot snapshot = new ShareSnapshot();

    assertNull(snapshot.symbol);
    assertNull(snapshot.quantity);
    assertNull(snapshot.purchasePrice);
  }

  @Test
  void constructor_setsFieldsCorrectly() {
    ShareSnapshot snapshot = new ShareSnapshot(
        "AAPL",
        new BigDecimal("5"),
        new BigDecimal("150.00"));

    assertEquals("AAPL", snapshot.symbol);
    assertEquals(0, snapshot.quantity.compareTo(new BigDecimal("5")));
    assertEquals(0, snapshot.purchasePrice.compareTo(new BigDecimal("150.00")));
  }
}