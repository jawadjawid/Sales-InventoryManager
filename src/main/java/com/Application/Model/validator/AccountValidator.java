package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.users.User;

public class AccountValidator {

  private static DatabaseDriverAndroidHelper mydb;

  public static boolean validateUserId(int id) {
    User user = mydb.getUserDetailsH(id);
    return user != null;
  }


  public static void setContext(DatabaseDriverAndroidHelper db) {
    mydb = db;
  }
}
