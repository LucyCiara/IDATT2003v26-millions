package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows;

/**
 * Class for the transaction rows component in the application.
 */
public class TransactionRows extends RowType {
  public TransactionRows() {
    super();
  }

  /**
   * Adds a new transaction item to the rows.
   *
   * @param week the week of the transaction
   * @param transactionType the type of the transaction
   * @param stock the stock involved in the transaction
   * @param quantity the quantity of the stock
   * @param price the price of the stock
   * @param costReward the cost or reward associated with the transaction
   */
  public void addItem(String week, String transactionType,
      String stock, String quantity, String price, String costReward) {
    this.getContents().getChildren().add(
      new TransactionItem(week, transactionType, stock, quantity, price, costReward)
    );
  }

}
