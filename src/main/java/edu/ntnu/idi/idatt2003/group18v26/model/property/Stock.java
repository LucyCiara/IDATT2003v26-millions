package edu.ntnu.idi.idatt2003.group18v26.model.property;

import java.math.BigDecimal;
import java.util.List;

public class Stock {
  private String symbol;
  private String company;
  private List<BigDecimal> prices;

  public Stock(String symbol, String company, List<BigDecimal> prices) {
    if (symbol == null || symbol.isEmpty()) {
      throw new IllegalArgumentException("symbol cannot be empty");
    }
    if (company == null || company.isEmpty()) {
      throw new IllegalArgumentException("company cannot be empty");
    }
    if (prices.isEmpty()) {
      throw new IllegalArgumentException("prices cannot be empty");
    }
    this.symbol = symbol;
    this.company = company;
    this.prices = prices;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getCompany() {
    return company;
  }
}
