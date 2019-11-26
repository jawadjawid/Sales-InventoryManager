package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.users.User;

import java.math.BigDecimal;
import java.sql.SQLException;

public class SalesValidator {

  public static boolean validateUserId(int id) {
    try {
      User user = DatabaseSelectHelper.getUserDetails(id);
      if (user == null) {
        return false;
      }
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  public static boolean validateTotalPrice(BigDecimal price) {
    if (!(price.compareTo(BigDecimal.ZERO) > 0) || !(price.scale() == 2)) {
      return false;
    }
    return true;
  }
}
