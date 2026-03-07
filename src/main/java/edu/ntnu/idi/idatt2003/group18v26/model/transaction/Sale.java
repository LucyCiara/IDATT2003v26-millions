package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;

public class Sale extends Transaction {
  public Sale(Share share, int week) {
    super(share, week, new SaleCalculator(share));
  }

  @Override
  public void commit(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("player can't be null");
    }
    if (player.getPortfolio().contains(this.getShare()) && !this.committed) {
      player.addMoney(this.getCalculator().calculateTotal());
      player.getPortfolio().removeShare(this.getShare());
      // TODO: Add itself to transaction archive.
      this.committed = true;
    } else if (this.committed) {
      throw new UnsupportedOperationException("Can't commit the same Transaction more than once");
    } else {
      throw new UnsupportedOperationException("player has to own the Share to sell it");
    }
  }
}
