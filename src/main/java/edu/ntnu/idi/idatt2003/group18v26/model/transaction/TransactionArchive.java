package edu.ntnu.idi.idatt2003.group18v26.model.transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

  /**
   * Constructs a new TransactionArchive with an empty list of transactions.
   * This constructor initializes the transactions list, allowing for the addition of transactions to the archive.
   */
  public TransactionArchive() {
    this.transactions = new ArrayList<>();
  }

  /**
   * Adds a transaction to the archive.
   * @param transaction The transaction to be added to the archive. Must not be {@code null}.
   * @return true if the transaction was successfully added to the archive.
   * @throws IllegalArgumentException if the transaction is null.
   */
  public boolean add(Transaction transaction) {
    if (transaction == null) {
      throw new IllegalArgumentException("The transaction cannot be null");
    }
    transactions.add(transaction);
    return true;
  }
  
  /**
   * Checks if the transaction archive is {@code empty}.
   * This method returns true if there are no transactions in the archive, and false otherwise.
   * @return true if the archive is empty, false otherwise.
   */
  public boolean isEmpty() {
    return transactions.stream().noneMatch(Objects::nonNull);
  }

  /**
   * Retrieves a list of all transactions in the archive.
   * This method returns a new list containing all transactions currently stored in the archive.
   * @return a list of all transactions in the archive.
   * @throws IllegalArgumentException if the provided week is negative.
   */
  public List<Transaction> getTransactions(int week) {
    if (week < 0) {
      throw new IllegalArgumentException("week can't be negative");
    }
    return transactions.stream()
        .filter(transaction -> transaction.getWeek() == week)
        .collect(Collectors.toList());
  }

  /**
   * Retrieves a list of all purchase transactions in the archive.
   * This method filters the transactions in the archive to return only those that are instances of the Purchase class.
   * @return a list of all purchase transactions in the archive.
   * @throws IllegalArgumentException if the provided week is negative.
   */
  public List<Transaction> getPurchases(int week) {
    if (week < 0) {
      throw new IllegalArgumentException("week can't be negative");
    }
    return transactions.stream()
        .filter(transaction -> transaction instanceof Purchase && transaction.getWeek() == week)
        .collect(Collectors.toList());
  }

  /**
   * Retrieves a list of all sale transactions in the archive.
   * This method filters the transactions in the archive to return only those that are instances of the Sale class.
   * @return a list of all sale transactions in the archive.
   * @throws IllegalArgumentException if the provided week is negative.
   */
  public List<Transaction> getSales(int week) {
    if (week < 0) {
      throw new IllegalArgumentException("week can't be negative");
    }
    return transactions.stream()
        .filter(transaction -> transaction instanceof Sale && transaction.getWeek() == week)
        .collect(Collectors.toList());
  }

  /**
   * Counts the number of distinct weeks in which transactions occurred.
   * @return the number of distinct weeks in which transactions occurred.
   */
  public int countDistinctWeeks() {
    return (int) transactions.stream()
        .map(Transaction::getWeek)
        .filter(Objects::nonNull)
        .distinct()
        .count();
  }
}