package com.bodega.api.ui.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaypalCreateOrderResponse {
  private String id;
  private String status;
  private List<Links> links;

  @Getter
  @Setter
  public static class Links {
    private String href;
    private String rel;
    private String method;
  }
}
