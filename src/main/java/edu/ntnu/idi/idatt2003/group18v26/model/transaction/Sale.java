package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.transaction.Transaction;

public class Sale extends Transaction {
  public Sale(Share share, int week) {
    super(share, week, new SaleCalculator(share));
  }

  @Override
  public void commit(Player player) {
    player.withdrawMoney(this.getCalculator().calculateTotal());
    super.commit(player);
  }
}
