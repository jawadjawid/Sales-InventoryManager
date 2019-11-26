package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.inventory.Item;

import java.sql.SQLException;

public class InventoryValidator {

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
    if (quantity < 0) {
      return false;
    }
    return true;
  }
}
