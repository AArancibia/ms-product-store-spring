package com.bodega.api.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CreateOrderRequest implements Serializable {
  private String intent;
  private List<PurchaseUnits> purchase_units;

  @Getter
  @Setter
  public static class PurchaseUnits {
    private List<Items> items;
    private Amount amount;

    @Getter
    @Setter
    public static class Amount {
      private String currency_code;
      private int value;
      private Map<String, ItemTotal> breakdown;

      @Getter
      @Setter
      public static class ItemTotal {
        private String currency_code;
        private int value;
      }
    }

    @Getter
    @Setter
    public static class Items {
      private String name;
      private String description;
      private int quantity;
      private UnitAmount unit_amount;

      @Getter
      @Setter
      public static class UnitAmount {
        private String currency_code;
        private int value;
      }
    }
  }
}
