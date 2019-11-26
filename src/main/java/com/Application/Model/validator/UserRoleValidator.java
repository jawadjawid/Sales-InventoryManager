package com.b07.validator;

import com.b07.database.helper.DatabaseSelectHelper;
import java.sql.SQLException;

public class UserRoleValidator {

  public static boolean validateUserId(int userId) {
    try {
      return DatabaseSelectHelper.userIdExists(userId);
    } catch (SQLException e) {
      return false;
    }
  }

  public static boolean validateRoleId(int roleId) {
    try {
      if (!DatabaseSelectHelper.getRoleIds().contains(roleId)) {
        return false;
      }
      return true;
    } catch (SQLException e) {
      return false;
    }
  }
}
