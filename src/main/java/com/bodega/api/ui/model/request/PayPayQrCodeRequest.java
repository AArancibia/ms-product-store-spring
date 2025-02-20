package com.bodega.api.ui.model.request;

import jp.ne.paypay.model.MerchantOrderItem;
import jp.ne.paypay.model.MoneyAmount;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PayPayQrCodeRequest {
  private MoneyAmount amount;
  private List<MerchantOrderItem> orderItems;
}
