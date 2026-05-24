package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class SharePurchasePriceSortButton extends ButtonType {
  public SharePurchasePriceSortButton() {
    super();
    setText("Purchase Price");
    setOnAction(e -> System.out.println("Purchase Price"));
  }
}
