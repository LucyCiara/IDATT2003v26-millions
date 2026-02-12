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
  private String symbol;
  private String company;
  private List<BigDecimal> prices;

  /**
   * Initializes the stock with an initial sales price; the price history will
   * contain exactly this price after construction.
   *
   * @param symbol     the ticker symbol (non-blank)
   * @param company    the company name (non-blank)
   * @param salesPrice the initial/current sales price (non-null)
   * @throws IllegalArgumentException if {@code symbol} or {@code company} is
   *                                  null/blank,
   *                                  or if {@code salesPrice} is null
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
    this.symbol = symbol;
    this.company = company;
    this.prices = new ArrayList<>();
    this.prices.add(salesPrice);
  }

  /**
   * Returns the ticker symbol of the stock as a {@code String}
   * 
   * @return symbol
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Returns the company name of the stock as a {@code String}
   * 
   * @return company
   */
  public String getCompany() {
    return company;
  }

  /**
   * Returns the current sales price (the last price in the list).
   *
   * @return the latest sales price
   */
  public BigDecimal getSalesPrice() {
    return prices.get(prices.size() - 1);
  }

  /**
   * Adds a new sales price to the {@code Prices} list.
   * 
   * @throws IllegalArgumentException if newPrice is {@code null}
   * @param newPrice the new price
   */
  public void addNewSalesPrice(BigDecimal newPrice) {
    if (newPrice == null) {
      throw new IllegalArgumentException("The new price cannot be null");
    }
    prices.add(newPrice);
  }
}
