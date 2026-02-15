package edu.ntnu.idi.idatt2003.group18v26.model.property;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player's portfolio, storing all purchased shares.
 */
public class Portfolio {
  private List<Share> shares;

  /** Constructs the portofolio that contains the list of shares the user has */
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
   * @param symbol the stock symbol to search for
   * @return a matching share, or null if not found
   */
  public Share getShare(String symbol) {
    if (symbol == null || symbol.isBlank()) {
      throw new IllegalArgumentException("symbol cannot be null or blank");
    }
    for (Share share : shares) {
      if (share.getStock().getSymbol().equalsIgnoreCase(symbol)) {
        return share;
      }
    }
    return null;
  }

  /**
   * Adds a share to the portfolio.
   *
   * @param share the share to add
   * @return true if added
   */
  public boolean addShare(Share share) {
    if (share == null) {
      throw new IllegalArgumentException("share cannot be null");
    }
    return shares.add(share);
  }

  /**
   * Removes a share from the portfolio.
   *
   * @param share the share to remove
   * @return true if removed
   */
  public boolean removeShare(Share share) {
    if (share == null) {
      throw new IllegalArgumentException("share cannot be null");
    }
    return shares.remove(share);
  }

  /**
   * Checks if the portfolio contains a given share.
   *
   * @param share the share to look for
   * @return true if the share is in the portfolio
   * @throws IllegalArgumentException if share is null
   */
  public boolean contains(Share share) {
    if (share == null) {
      throw new IllegalArgumentException("The share cannot be null");
    }
    return shares.contains(share);
  }
}
