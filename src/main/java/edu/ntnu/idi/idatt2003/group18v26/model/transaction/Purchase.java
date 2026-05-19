package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class to perform a purchase of a share using a player.
 */
public class Purchase extends Transaction {
  private static final Logger logger
      = LoggerFactory.getLogger(Player.class);

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
   * Commits a purchase on a player, withdrawing the money from the Player, adding the Share to
   * the Player's Portfolio, and adding the transaction to the Player's TransactionArchive.
   *
   * @param player The player the purchase is performed on. Must be non-null.
   * @throws UnsupportedOperationException Is thrown whenever there's an attempt to commit the same
   *      Transaction more than once.
   * @throws ArithmeticException Is thrown whenever the player has insufficient money to buy the
   *      Share.
   */
  @Override
  public void commit(Player player) throws UnsupportedOperationException {
    ParameterValidator.objectChecker(player, "player");
    if (this.getCalculator().calculateTotal().compareTo(player.getMoney()) <= 0
        && !this.committed) {
      logger.info("Purchase committed: {} x{} for {}", 
          this.getShare().stock().getSymbol(), 
          this.getShare().quantity(), 
          this.getCalculator().calculateTotal());
      
      player.withdrawMoney(this.getCalculator().calculateTotal());
      player.getPortfolio().addShare(this.getShare());
      player.getTransactionArchive().add(this);
      this.committed = true;
    } else if (this.committed) {
      logger.error("Purchase commit failed: Transaction already committed");
      throw new UnsupportedOperationException("Can't commit the same Transaction more than once");
    } else {
      logger.error("Purchase commit failed: Insufficient funds. Required {}, Available {}",
          this.getCalculator().calculateTotal(), player.getMoney());
      throw new ArithmeticException("player has insufficient money to buy this Share");
    }
  }
}
