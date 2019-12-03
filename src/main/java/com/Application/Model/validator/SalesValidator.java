package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.users.User;
import java.math.BigDecimal;

public class SalesValidator {

  private static DatabaseDriverAndroidHelper mydb;

  public static boolean validateUserId(int id) {
    User user = mydb.getUserDetailsH(id);
    return user != null;
  }

  public static boolean validateTotalPrice(BigDecimal price) {
    return price.compareTo(BigDecimal.ZERO) > 0 && price.scale() == 2;
  }

  public static void setContext(DatabaseDriverAndroidHelper db) {
    mydb = db;
  }
}
