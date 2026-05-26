package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import java.math.BigDecimal;
import java.util.List;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;
import edu.ntnu.idi.idatt2003.group18v26.view.components.LabelStandard;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.DisplayType;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StockContent extends BorderPane {
  private static final GameController gameCont = GameController.getInstance();
  private String symbol;

  private LabelStandard symbolTitle;
  private LabelStandard companyName;
  private DisplayType highestPrice;
  private DisplayType lowestPrice;
  private LineChart<Number, Number> priceHist;
  
  public StockContent(String symbol) {
    this.symbol = symbol;
    VBox title = new VBox();
    this.symbolTitle = new LabelStandard(symbol);
    this.symbolTitle.setFont(new Font(30));
    this.companyName = new LabelStandard(gameCont.getCompanyFromSymbol(symbol));
    title.getChildren().addAll(this.symbolTitle, this.companyName);
    setTop(title);

    VBox highLowPrice = new VBox();
    this.highestPrice = new DisplayType();
    this.lowestPrice = new DisplayType();
    highLowPrice.getChildren().addAll(this.highestPrice, this.lowestPrice);
    setRight(highLowPrice);

    this.update();
  }

  private void renderPriceHist() {
    List<BigDecimal> historicalPrices = gameCont.getPriceHistory(this.symbol);
    if (historicalPrices.size() > 1) {
      NumberAxis xAxis = new NumberAxis();
      xAxis.setAutoRanging(true);
      xAxis.setLabel("Weeks");

      NumberAxis yAxis = new NumberAxis();
      yAxis.setAutoRanging(true);
      yAxis.setLabel("Stock value");

      this.priceHist = new LineChart<>(xAxis, yAxis);

      XYChart.Series<Number, Number> series = new XYChart.Series<>();
      series.setName("Stock value for each week");

      for (int i = 0; i < historicalPrices.size(); i++) {
        series.getData().add(new XYChart.Data<>(i, historicalPrices.get(i)));
      }

      this.priceHist.getData().add(series);

      this.setCenter(this.priceHist);
    } else {
      this.setCenter(new LabelStandard("Not enough time has passed"));
    }
  }

  private void setHighestAndLowestPrice() {
    this.highestPrice.setDisplayText(
      "Highest price: " + gameCont.getHighestPriceString(this.symbol)
    );
    this.lowestPrice.setDisplayText(
      "Lowest price: " + gameCont.getLowestPriceString(this.symbol)
    );
  }

  public void update() {
    this.renderPriceHist();
    this.setHighestAndLowestPrice();
  }
}
