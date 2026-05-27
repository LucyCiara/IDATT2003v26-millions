package edu.ntnu.idi.idatt2003.group18v26.model;

/**
 * Observer interface for game state changes.
 * GUI components implement this to receive notifications.
 */
public interface GameObserver {
  
  /**
   * Called when the exchange week advances.
   *
   * @param newWeek The new week number
   */
  void onWeekAdvanced(int newWeek);
  
  /**
   * Called when stock prices change.
   *
   * @param symbol The stock symbol that changed
   */
  void onStockPriceChanged(String symbol);
  
  /**
   * Called when a purchase is completed.
   *
   * @param symbol Stock symbol
   * @param quantity Amount purchased
   */
  void onPurchaseCompleted(String symbol, String quantity);

  /**
   * Called when a sale is completed.
   *
   * @param symbol Stock symbol
   * @param quantity Amount sold
   */
  void onSaleCompleted(String symbol, String quantity);


  /**
   * Called when the player's money changes.
   *
   * @param newBalance The new balance
   */
  void onMoneyChanged(String newBalance);

  /**
   * Called when player's portfolio changes.
   */
  void onPortfolioChanged();

}
