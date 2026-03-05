package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;

public class Purchase extends Transaction {
  public Purchase(Share share, int week) {
    super(share, week, new PurchaseCalculator(share));
  }

  @Override
  public void commit(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("player can't be null");
    }
    if (this.getCalculator().calculateTotal().compareTo(player.getMoney()) <= 0 && !this.committed) {
      player.withdrawMoney(this.getCalculator().calculateTotal());
      player.getPortfolio().addShare(this.getShare());
      // TODO: add itself to Player's archive.
      this.committed = true;
    } else if (this.committed) {
      throw new UnsupportedOperationException("Can't commit the same Transaction more than once");
    } else {
      throw new ArithmeticException("Player has insufficient money to buy this Share");
    }
  }
}
