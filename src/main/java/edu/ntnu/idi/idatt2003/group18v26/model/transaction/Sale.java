package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A method for performing a sale on a player.
 */
public class Sale extends Transaction {
  private static final Logger logger
      = LoggerFactory.getLogger(Player.class);

  /**
   * The constructor sets up a calculator, and sets some information about the transaction and
   * Share.
   *
   * @param share The Share to sell.
   * @param week The week of the Transaction.
   */
  public Sale(Share share, int week) {
    super(share, week, new SaleCalculator(share));
  }

  /**
   * Commits a sale on a player, adding the money from the Player, removing the Share to
   * the Player's Portfolio, and adding the transaction to the Player's TransactionArchive.
   *
   * @param player The player the sale is performed on. Must be non-null.
   * @throws UnsupportedOperationException Is thrown whenever there's an attempt to commit the same
   *      Transaction more than once, or if an attempt to sell a Share the Player doesn't own is
   *      made.
   */
  @Override
  public void commit(Player player) throws UnsupportedOperationException {
    ParameterValidator.objectChecker(player, "player");
    if (player.getPortfolio().contains(this.getShare()) && !this.committed) {
      logger.info("Sale committed: {} x{} for {}", 
          this.getShare().stock().getSymbol(), 
          this.getShare().quantity(), 
          this.getCalculator().calculateTotal());
      
      player.addMoney(this.getCalculator().calculateTotal());
      player.getPortfolio().removeShare(this.getShare());
      player.getTransactionArchive().add(this);
      this.committed = true;
    } else if (this.committed) {
      logger.error("Sale commit failed: Transaction already committed");
      throw new UnsupportedOperationException("Can't commit the same Transaction more than once");
    } else {
      logger.error("Sale commit failed: Player does not own share: {}", 
          this.getShare().stock().getSymbol());
      throw new UnsupportedOperationException("player has to own the Share to sell it");
    }
  }
}
