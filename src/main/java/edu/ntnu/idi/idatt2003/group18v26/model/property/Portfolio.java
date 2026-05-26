package edu.ntnu.idi.idatt2003.group18v26.model.property;

import edu.ntnu.idi.idatt2003.group18v26.model.GameObserver;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Purchase;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.PurchaseCalculator;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.SaleCalculator;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a player's portfolio, storing all purchased shares.
 */
public class Portfolio {
  private final List<Share> shares;
  private static final Logger logger = LoggerFactory.getLogger(Portfolio.class);
  private List<GameObserver> observers = new ArrayList<>();

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

  public List<Share> getSharesBySymbol() {
    return this.shares.stream().sorted((s1,s2) -> s1.stock().getSymbol().compareTo(s2.stock().getSymbol())).toList();
  }

  public List<Share> getSharesByCompany() {
    return this.shares.stream().sorted((s1,s2) -> s1.stock().getCompany().compareTo(s2.stock().getCompany())).toList();
  }

  public List<Share> getSharesByQuantity() {
    return this.shares.stream().sorted((s1,s2) -> s1.quantity().compareTo(s2.quantity())).toList();
  }

  public List<Share> getSharesByPurchasePrice() {
    return this.shares.stream().sorted((s1,s2) -> s1.purchasePrice().compareTo(s2.purchasePrice())).toList();
  }

  public List<Share> getSharesByCurrentValue() {
    return this.shares.stream().sorted((s1,s2) -> new SaleCalculator(s1).calculateTotal().compareTo(new SaleCalculator(s2).calculateTotal())).toList();
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

  public List<Share> search(String query) {
    try {
      ParameterValidator.stringChecker(query, "query");
      return this.shares.stream()
          .filter(s -> s.stock().getSymbol().toLowerCase().contains(query.toLowerCase())
            || s.stock().getCompany().toLowerCase().contains(query.toLowerCase())
            || s.quantity().toString().contains(query.toLowerCase())
            || s.purchasePrice().toString().contains(query)
            || new PurchaseCalculator(s).calculateTotal().toString().contains(query))
          .toList();
    } catch (Exception e) {
      return this.shares;
    }
  }

  /**
   * Adds a share to the portfolio.
   *
   * @param share The share to add.
   * @return True if added.
   */
  public boolean addShare(Share share) {
    ParameterValidator.objectChecker(share, "share");
    logger.debug("Adding share to portfolio: {} x{}", 
        share.stock().getSymbol(), share.quantity());
    boolean result = this.shares.add(share);
    this.notifyPortfolioChanged();
    return result;
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
    logger.debug("Removing share from portfolio: {} x{}", 
        share.stock().getSymbol(), share.quantity());
    boolean result = this.shares.remove(share);
    this.notifyPortfolioChanged();
    return result;
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

  /**
   * Adds an observer to be notified of Player changes.
   *
   * @param observer The observer to add
   */
  public void addObserver(GameObserver observer) {
    logger.debug("Observer added: {}", observer.getClass().getSimpleName());
    this.observers.add(observer);
  }

  /**
   * Removes an observer from being notified of Player changes.
   *
   * @param observer The observer to remove
   */
  public void removeObserver(GameObserver observer) {
    logger.debug("Observer removed: {}", observer.getClass().getSimpleName());
    this.observers.remove(observer);
  }

  private void notifyPortfolioChanged() {
    logger.debug("Notifying observers: Portfolio changed");
    observers.forEach(observer -> observer.onPortfolioChanged());
  }
}
