package com.Application.Model.validator;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import java.math.BigDecimal;

public class UsersValidator {

  private static DatabaseDriverAndroidHelper mydb;

  public static boolean validateName(String name) {
    return name != null && !name.equals("");
  }

  public static boolean validateId(int id) {
    return mydb.userIdExists(id);
  }

  public static boolean validatePassword(String password) {
    return password != null && !password.equals("");
  }

  public static boolean validateAge(int age) {
    return age >= 0;
  }

  public static boolean validateAddress(String address) {
    return address != null && address.length() <= 100;
  }

  public static void setContext(DatabaseDriverAndroidHelper db) {
    mydb = db;
  }

  public static boolean validateBalance(BigDecimal balance) {
    return balance.compareTo(BigDecimal.ZERO) >= 0 && (balance.scale() == 2);
  }
}
