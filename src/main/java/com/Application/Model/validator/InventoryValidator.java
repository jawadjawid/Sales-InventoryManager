package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.inventory.Item;

public class InventoryValidator {

  private static DatabaseDriverAndroidHelper mydb;

  public static boolean validateItemId(int itemId) {
    Item item = mydb.getItemH(itemId);
    return item != null;
  }

  public static boolean validateQuantity(int quantity) {
    return quantity >= 0;
  }


  public static void setContext(DatabaseDriverAndroidHelper db) {
    mydb = db;
  }
}
