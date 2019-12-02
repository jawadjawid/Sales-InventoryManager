package com.Application.Model.database.helper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import com.Application.Model.database.DatabaseUpdater;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.store.SalesApplication;
import com.Application.Model.validator.InventoryValidator;
import com.Application.Model.validator.ItemsValidator;
import com.Application.Model.validator.RolesValidator;
import com.Application.Model.validator.UserRoleValidator;
import com.Application.Model.validator.UsersValidator;

public class DatabaseUpdateHelper extends DatabaseUpdater {

  public static boolean updateRoleName(String name, int id)
      throws SQLException, DatabaseInsertException {

    boolean val_success = true;
    val_success = val_success && RolesValidator.validateName(name);
    val_success = val_success && RolesValidator.validateId(id);

    if (!val_success) {
      throw new DatabaseInsertException();
    }

    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateRoleName(name, id, connection);
    connection.close();
    return complete;
  }

  public static boolean updateUserName(String name, int userId)
      throws SQLException, DatabaseInsertException {

    boolean val_success = true;
    val_success = val_success && UsersValidator.validateName(name);
    val_success = val_success && UsersValidator.validateId(userId);

    if (!val_success) {
      throw new DatabaseInsertException();
    }

    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateUserName(name, userId, connection);
    connection.close();
    return complete;
  }

  public static boolean updateUserAge(int age, int userId)
      throws DatabaseInsertException, SQLException {

    boolean val_success = true;
    val_success = val_success && UsersValidator.validateId(userId);
    val_success = val_success && UsersValidator.validateAge(age);

    if (!val_success) {
      throw new DatabaseInsertException();
    }

    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateUserAge(age, userId, connection);
    connection.close();
    return complete;
  }

  public static boolean updateUserAddress(String address, int userId)
      throws SQLException, DatabaseInsertException {

    boolean val_success = true;
    val_success = val_success && UsersValidator.validateId(userId);
    val_success = val_success && UsersValidator.validateAddress(address);

    if (!val_success) {
      throw new DatabaseInsertException();
    }

    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateUserAddress(address, userId, connection);
    connection.close();
    return complete;
  }

  public static boolean updateUserRole(int roleId, int userId)
      throws SQLException, DatabaseInsertException {

    boolean val_success = true;
    val_success = val_success && UserRoleValidator.validateUserId(userId);
    val_success = val_success && UserRoleValidator.validateRoleId(roleId);

    if (!val_success) {
      throw new DatabaseInsertException();
    }

    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateUserRole(roleId, userId, connection);
    connection.close();
    return complete;
  }

  public static boolean updateItemName(String name, int itemId)
      throws SQLException, DatabaseInsertException {

    boolean val_success = true;
    val_success = val_success && ItemsValidator.validateName(name);
    val_success = val_success && ItemsValidator.validateId(itemId);

    if (!val_success) {
      throw new DatabaseInsertException();
    }

    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateItemName(name, itemId, connection);
    connection.close();
    return complete;

  }

  public static boolean updateItemPrice(BigDecimal price, int itemId)
      throws SQLException, DatabaseInsertException {

    boolean val_success = true;
    val_success = val_success && ItemsValidator.validatePrice(price);
    val_success = val_success && ItemsValidator.validateId(itemId);

    if (!val_success) {
      throw new DatabaseInsertException();
    }

    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateItemPrice(price, itemId, connection);
    connection.close();
    return complete;
  }

  public static boolean updateInventoryQuantity(int quantity, int itemId)
      throws SQLException, DatabaseInsertException {

    boolean val_success = true;
    val_success = val_success && InventoryValidator.validateQuantity(quantity);
    val_success = val_success && InventoryValidator.validateItemId(itemId);

    if (!val_success) {
      throw new DatabaseInsertException();
    }

    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateInventoryQuantity(quantity, itemId, connection);
    connection.close();
    return complete;
  }
}
