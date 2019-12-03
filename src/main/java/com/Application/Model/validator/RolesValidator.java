package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.users.Roles;

public class RolesValidator {

  private static DatabaseDriverAndroidHelper mydb;

  public static boolean validateName(String name) {
    if (name == null) {
      return false;
    }
    return name.equals(Roles.EMPLOYEE.name()) || name.equals(Roles.CUSTOMER.name()) || name
        .equals(Roles.ADMIN.name());
  }

  public static boolean validateId(int id) {
    return mydb.getRoleIdsH().contains(id);
  }

  public static void setContext(DatabaseDriverAndroidHelper db) {
    mydb = db;
  }
}
