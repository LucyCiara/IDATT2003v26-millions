package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;

/**
 * A class to perform a purchase of a share using a player.
 */
public class Purchase extends Transaction {
  /**
   * The constructor sets up a calculator, and sets some information about the transaction and
   * Share.
   * 
   * @param share The Share to buy.
   * @param week The week of the Transaction.
   */
  public Purchase(Share share, int week) {
    super(share, week, new PurchaseCalculator(share));
  }

  /**
   * Commits a purchase on a player, withdrawing the money from the Player, and adding the Share to
   * the Player's Portfolio and TransactionArchive.
   * 
   * @param player The player the purchase is performed on. Must be non-null.
   */
  @Override
  public void commit(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("player can't be null");
    }
    if (this.getCalculator().calculateTotal().compareTo(player.getMoney()) <= 0
        && !this.committed) {
      player.withdrawMoney(this.getCalculator().calculateTotal());
      player.getPortfolio().addShare(this.getShare());
      player.getTransactionArchive().add(this);
      this.committed = true;
    } else if (this.committed) {
      throw new UnsupportedOperationException("Can't commit the same Transaction more than once");
    } else {
      throw new ArithmeticException("player has insufficient money to buy this Share");
    }
  }
}
