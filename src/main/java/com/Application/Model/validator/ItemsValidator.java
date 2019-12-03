package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import java.math.BigDecimal;

public class ItemsValidator {

  private static DatabaseDriverAndroidHelper mydb;

  public static boolean validateName(String name) {
    return name != null && name.length() < 64;
  }

  public static boolean validatePrice(BigDecimal price) {
    return price.compareTo(BigDecimal.ZERO) > 0 && (price.scale() == 2);
  }

  public static boolean validateId(int id) {
    return mydb.getItemH(id) != null;
  }

  public static void setContext(DatabaseDriverAndroidHelper db) {
    mydb = db;
  }
}
