package edu.ntnu.idi.idatt2003.group18v26.model.transaction;
import java.util.ArrayList;
import java.util.List;
/**
 * The TransactionArchive class manages a collection of transactions, 
 * allowing for the addition of new transactions and retrieval of existing ones. 
 * It provides methods to get all transactions, as well as filtered lists of purchases and sales. 
 * The class ensures that transactions are stored in a way that allows for easy access and manipulation, 
 * making it a central component for managing transaction history within the application.
 * <p>The TransactionArchive class provides the following functionalities:
 * <ul>
 * <li>Adding a transaction to the archive.</li>
 * <li>Checking if the archive is empty.</li>
 * <li>Retrieving a list of all transactions.</li>
 * <li>Retrieving a list of all purchase transactions.</li>
 * <li>Retrieving a list of all sale transactions.</li>
 * <li>Counting the number of distinct weeks in which transactions occurred.</li>
 * </ul>
 */
public class TransactionArchive {
  private final List<Transaction> transactions;


  public TransactionArchive() {
    this.transactions = new ArrayList<>();
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
    return new ArrayList<>(transactions);
  }

  public List<Transaction> getPurchases() {
    List<Transaction> purchases = new ArrayList<>();
    for (Transaction transaction : transactions.getTransactions()) {
      if (transaction instanceof Purchase) {
        purchases.add(transaction);
      }
    }
    return purchases;
  }

  public List<Transaction> getSales() {
    List<Transaction> sales = new ArrayList<>();
    for (Transaction transaction : transactions.getTransactions()) {
      if (transaction instanceof Sale) {
        sales.add(transaction);
      }
    }
    return sales;
  }

  public int countDistinctWeeks() {
    // TODO Implementation to count distinct weeks from transactions
    return 0;
  }
}
