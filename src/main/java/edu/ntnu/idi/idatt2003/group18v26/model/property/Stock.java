package edu.ntnu.idi.idatt2003.group18v26.model.property;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a stock with a ticker symbol, company name, and a history of
 * prices.
 * The current sales price is the last element in {@code prices}.
 */
public class Stock {
  private final String symbol;
  private final String company;
  private final List<BigDecimal> prices;

  /**
   * Initializes the stock with an initial sales price; the price history will
   * contain exactly this price after construction.
   *
   * @param symbol     the ticker symbol (non-blank)
   * @param company    the company name (non-blank)
   * @param salesPrice the initial/current sales price (non-null)
   * @throws IllegalArgumentException if {@code symbol} or {@code company} is
   *                                  null/blank,
   *                                  or if {@code salesPrice} is null or
   *                                  negative/zero
   */
  public Stock(String symbol, String company, BigDecimal salesPrice) {
    if (symbol == null || symbol.isBlank()) {
      throw new IllegalArgumentException("symbol cannot be blank");
    }
    if (company == null || company.isBlank()) {
      throw new IllegalArgumentException("company cannot be blank");
    }
    if (salesPrice == null) {
      throw new IllegalArgumentException("salesPrice cannot be null");
    }
    if (salesPrice.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("salesPrice must be greater than zero");
    }
    this.symbol = symbol;
    this.company = company;
    this.prices = new ArrayList<>();
    this.prices.add(salesPrice);
  }

  /**
   * Returns the ticker symbol of the stock as a {@code String}.
   * 
   * @return symbol The stock's ticker symbol.
   */
  public String getSymbol() {
    return this.symbol;
  }

  /**
   * Returns the company name of the stock as a {@code String}.
   * 
   * @return company The name of the company tied to the stock.
   */
  public String getCompany() {
    return this.company;
  }

  /**
   * Returns the current sales price (the last price in the list).
   *
   * @return the latest sales price
   */
  public BigDecimal getSalesPrice() {
    return this.prices.getLast();
  }

  /**
   * Adds a new sales price to the {@code prices} list.
   *
   * @param newPrice the new price
   * @throws IllegalArgumentException if newPrice is {@code null}
   */
  public void addNewSalesPrice(BigDecimal newPrice) {
    if (newPrice == null) {
      throw new IllegalArgumentException("The new price cannot be null");
    }
    if (newPrice.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("The new price must be greater than zero");
    }
    this.prices.add(newPrice);
  }

  @Override
  public String toString() {
    return this.symbol + " (" + this.company + ") - current price: " + this.getSalesPrice();
  }

  public List<BigDecimal> getHistoricalPrices() {
    return this.prices;
  }

  public BigDecimal getHighestPrice() {
    return this.prices.stream().max(BigDecimal::compareTo).get();
  }

  public BigDecimal getLowestPrice() {
    return this.prices.stream().min(BigDecimal::compareTo).get();
  }

  /**
   * A method for getting the latest price change, AKA the change in price between the 2 latest
   * sale prices.
   * 
   * @return An amount of money that's the difference between the second latest and latest sale
   *      prices.
   */
  public BigDecimal getLatestPriceChange() {
    if (prices.size() == 1) {
      return BigDecimal.ZERO;
    } else {
      return prices.getLast().subtract(prices.get(prices.size() - 2));
    }
  }
}
