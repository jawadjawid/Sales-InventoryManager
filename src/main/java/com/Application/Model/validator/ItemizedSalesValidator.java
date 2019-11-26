package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.inventory.Item;
import com.Application.Model.store.Sale;
import com.Application.Model.store.SalesLog;

import java.math.BigDecimal;
import java.sql.SQLException;

public class ItemizedSalesValidator {

  public static boolean validateSaleId(int saleId) {
    try {
      Sale sale = DatabaseSelectHelper.getSaleById(saleId);
      if (sale == null) {
        return false;
      }
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  public static boolean validateItemId(int itemId) {
    try {
      Item item = DatabaseSelectHelper.getItem(itemId);
      if (item == null) {
        return false;
      }
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  public static boolean validateQuantity(int quantity) {
    if (quantity <= 0) {
      return false;
    }
    return true;
  }

  public static boolean validateUniqueComb(int saleId, int itemId) throws SQLException {
    SalesLog saleslog = DatabaseSelectHelper.getItemizedSales();
    int count = 0;
    for (Sale sale : saleslog.getSales()) {
      if (sale.getId() == saleId) {
        for (Item item : sale.getItemMap().keySet()) {
          if (item.getId() == itemId) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static boolean validateTotalPrice(int saleId, int itemId, int quantity) {
    try {
      SalesLog saleslog = DatabaseSelectHelper.getItemizedSales();
      BigDecimal requiredTotal = DatabaseSelectHelper.getSaleById(saleId).getTotalPrice();
      BigDecimal accumulator = new BigDecimal("0.00");
      for (Sale sale : saleslog.getSales()) {
        if (sale.getId() == saleId) {
          for (Item item : sale.getItemMap().keySet()) {
            accumulator = (item.getPrice().multiply(new BigDecimal(sale.getItemMap().get(item))))
                .add(accumulator);
          }
        }
      }
      return accumulator.compareTo(requiredTotal) <= 0;
    } catch (SQLException e) {
      return false;
    }
  }
}
