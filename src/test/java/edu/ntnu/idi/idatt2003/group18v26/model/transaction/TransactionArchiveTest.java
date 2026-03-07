package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import org.junit.jupiter.api.BeforeEach;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Purchase;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Sale;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TransactionArchiveTest {
  private TransactionArchive transArchive;
  private Stock stockA;
  private Stock stockB;
  private Share share1;
  private Share share2;
  private Transaction transaction1;
  private Transaction transaction2;

  @BeforeEach
  public void setUp() {
    transArchive = new TransactionArchive();
    stockA = new Stock("StockA", "CompanyA", new BigDecimal("100.00"));
    stockB = new Stock("StockB", "CompanyB", new BigDecimal("200.00"));
    share1 = new Share(stockA, new BigDecimal("100.00"), new BigDecimal("50.00"));
    share2 = new Share(stockB, new BigDecimal("200.00"), new BigDecimal("100.00"));
    transaction1 = new Purchase("Player1", share1, 10);
    transaction2 = new Sale("Player2", share2, 20);
  }
}
