package com.b07.validator;

import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.inventory.Item;
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
