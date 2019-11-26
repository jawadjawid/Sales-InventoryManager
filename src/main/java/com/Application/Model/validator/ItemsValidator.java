package com.b07.validator;

import com.b07.database.helper.DatabaseSelectHelper;
import java.math.BigDecimal;
import java.sql.SQLException;

public class ItemsValidator {

  public static boolean validateName(String name) {
    if (name == null || !(name.length() < 64)) {
      return false;
    }
    return true;
  }

  public static boolean validatePrice(BigDecimal price) {
    if (!(price.compareTo(BigDecimal.ZERO) > 0) || (price.scale() != 2)) {
      return false;
    }
    return true;
  }

  public static boolean validateId(int id) {
    try {
      if (DatabaseSelectHelper.getItem(id) == null) {
        return false;
      }
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

}
