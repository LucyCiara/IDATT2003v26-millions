package edu.ntnu.idi.idatt2003.group18v26.model;


import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;


/**
 * Abstract factory class for creating Transaction objects, 
 * specifically Purchase and Sale transactions.
 * 
 */
public abstract class TransactionFactory {

  public abstract Transaction createTransaction();

}
