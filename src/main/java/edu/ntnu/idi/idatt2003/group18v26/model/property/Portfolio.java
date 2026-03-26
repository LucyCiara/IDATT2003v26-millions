package edu.ntnu.idi.idatt2003.group18v26.model.property;

import edu.ntnu.idi.idatt2003.group18v26.model.transaction.SaleCalculator;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player's portfolio, storing all purchased shares.
 */
public class Portfolio {
  private final List<Share> shares;

  /**
   * Constructs the portfolio that contains the list of shares the user has.
   */
  public Portfolio() {
    this.shares = new ArrayList<>();
  }

  /**
   * Returns an unmodifiable list of all shares in the portfolio.
   *
   * @return list of shares
   */
  public List<Share> getShares() {
    return this.shares;
  }

  /**
   * Returns the first share matching the given stock symbol, or null if not
   * found.
   *
   * @param symbol The stock symbol to search for.
   * @return A matching share, or null if not found.
   */
  public Share getShare(String symbol) {
    ParameterValidator.stringChecker(symbol, "symbol");
    for (Share share : this.shares) {
      if (share.stock().getSymbol().equalsIgnoreCase(symbol)) {
        return share;
      }
    }
    return null;
  }

  /**
   * Adds a share to the portfolio.
   *
   * @param share The share to add.
   * @return True if added.
   */
  public boolean addShare(Share share) {
    ParameterValidator.objectChecker(share, "share");
    return this.shares.add(share);
  }

  /**
   * Removes a share from the portfolio.
   *
   * @param share The share to remove.
   * @return True if removed.
   * @throws IllegalArgumentException if share is null
   */
  public boolean removeShare(Share share) {
    ParameterValidator.objectChecker(share, "share");
    return this.shares.remove(share);
  }

  /**
   * Checks if the portfolio contains a given share.
   *
   * @param share the share to look for
   * @return true if the share is in the portfolio
   * @throws IllegalArgumentException if share is null
   */
  public boolean contains(Share share) {
    ParameterValidator.objectChecker(share, "share");
    return this.shares.contains(share);
  }

  /**
   * A method for getting the net worth of a Portfolio, or how much it is worth.
   * 
   * @return The amount of money the Portfolio is worth.
   */
  public BigDecimal getNetWorth() {
    BigDecimal netWorth = BigDecimal.ZERO;
    List<BigDecimal> prices
        = this.shares.stream().map(s -> new SaleCalculator(s).calculateTotal()).toList();
    for (BigDecimal price : prices) {
      netWorth = netWorth.add(price);
    }
    return netWorth;
  }
}
