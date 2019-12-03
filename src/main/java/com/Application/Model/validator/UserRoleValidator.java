package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;

public class UserRoleValidator {

  private static DatabaseDriverAndroidHelper mydb;

  public static boolean validateUserId(int userId) {
    return mydb.userIdExists(userId);
  }

  public static boolean validateRoleId(int roleId) {
    return mydb.getRoleIdsH().contains(roleId);
  }

  public static void setContext(DatabaseDriverAndroidHelper db) {
    mydb = db;
  }
}
