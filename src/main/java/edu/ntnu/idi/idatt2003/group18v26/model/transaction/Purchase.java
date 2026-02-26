package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.transaction.Transaction;

public class Purchase extends Transaction {
  public Purchase(Share share, int week) {
    super(share, week, new PurchaseCalculator(share))
  }

  @Override
  public void commit(Player player) {
    player.withdrawMoney(this.getCalculator().calculateTotal());
    super.commit(player);
  }
}
