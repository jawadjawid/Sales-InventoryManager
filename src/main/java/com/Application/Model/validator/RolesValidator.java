package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseSelectHelper;
import com.Application.Model.users.Roles;

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
