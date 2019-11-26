package com.b07.validator;

import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.users.Roles;
import java.sql.SQLException;

public class RolesValidator {

  public static boolean validateName(String name) {
    if (name == null) {
      return false;
    }
    if (name.equals(Roles.EMPLOYEE.name()) || name.equals(Roles.CUSTOMER.name()) || name
        .equals(Roles.ADMIN.name())) {
      return true;
    }
    return false;
  }

  public static boolean validateId(int id) {
    try {
      if (DatabaseSelectHelper.getRoleIds().contains(id)) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      return false;
    }
  }
}
