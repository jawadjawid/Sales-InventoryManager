package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseSelectHelper;

import java.sql.SQLException;

public class UsersValidator {

  public static boolean validateName(String name) {
    if (name == null || name.equals("")) {
      return false;
    }
    return true;
  }

  public static boolean validateId(int id) {
    try {
      if (DatabaseSelectHelper.userIdExists(id)) {
        return true;
      }
      return false;
    } catch (SQLException e) {
      return false;
    }
  }

  public static boolean validatePassword(String password) {
    if (password == null) {
      return false;
    }
    return true;
  }

  public static boolean validateAge(int age) {
    if (!(age >= 0)) {
      return false;
    }
    return true;
  }

  public static boolean validateAddress(String address) {
    if (address == null || address.length() > 100) {
      return false;
    }
    return true;
  }

}
