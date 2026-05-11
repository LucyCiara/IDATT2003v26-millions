package edu.ntnu.idi.idatt2003.group18v26.model;


import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;

/**
 * Abstract factory class for creating Transaction objects, 
 * specifically Purchase and Sale transactions.
 * 
 */
public abstract class TransactionFactory {

  /**
   * Abstract method for creating a Transaction. 
   * The specific type of Transaction is determined by the concrete subclass.
   *
   * @return A Transaction object, either a Purchase or a Sale, 
    depending on the concrete factory implementation.
   */
  public abstract Transaction createTransaction();

}
