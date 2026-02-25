package edu.ntnu.idi.idatt2003.group18v26.model.transaction;
import java.util.ArrayList;
import java.util.List;
public class TransactionArchive {
  private final List<Transaction> transactions;


  public TransactionArchive() {
    this.transactions = new ArrayList<>()
  }

  public boolean add(Transaction transaction) {
    if (transaction == null) {
      throw new IllegalArgumentException("The transaction cannot be null");
    }
    transactions.add(transaction);
    return true;
  }
  
  public boolean isEmpty() {
    return transactions.isEmpty();
  }

  public List<Transaction> getTransactions() {
    return New ArrayList<transaction>;
  }

  public List<Transaction> getPurchases() {
    List<Transaction> purchases = getTransactions();
    for (Transaction transaction : transactions) {
      if (transaction instanceof Purchase) {
        purchases.add(transaction);
      }
    }
    return purchases;
  }

  public List<Transaction> getSales() {
    List<Transaction> sales = getTransactions();
    for (Transaction transaction : transactions) {
      if (sales instanceof Sale) {
        sales.add(transaction);
      }
    }
    return sales;
  }
}
