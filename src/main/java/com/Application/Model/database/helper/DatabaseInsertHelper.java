package com.b07.database.helper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import com.b07.database.DatabaseInserter;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.store.SalesApplication;
import com.b07.validator.AccountSummaryValidator;
import com.b07.validator.AccountValidator;
import com.b07.validator.InventoryValidator;
import com.b07.validator.ItemizedSalesValidator;
import com.b07.validator.ItemsValidator;
import com.b07.validator.RolesValidator;
import com.b07.validator.SalesValidator;
import com.b07.validator.UserRoleValidator;
import com.b07.validator.UsersValidator;

public class DatabaseInsertHelper extends DatabaseInserter {

	public static int insertRole(String name) throws DatabaseInsertException, SQLException {

		boolean val_success = true;
		val_success = val_success && RolesValidator.validateName(name);

		if (!val_success) {
			throw new DatabaseInsertException();
		}

		Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
		int roleId = DatabaseInserter.insertRole(name, connection);
		SalesApplication.getRolesToId().put(name, roleId);
		connection.close();
		return roleId;
	}

	public static int insertNewUser(String name, int age, String address, String password)
			throws DatabaseInsertException, SQLException {

		boolean val_success = true;
		val_success = val_success && UsersValidator.validateName(name);
		val_success = val_success && UsersValidator.validateAge(age);
		val_success = val_success && UsersValidator.validateAddress(address);
		val_success = val_success && UsersValidator.validatePassword(password);

		if (!val_success) {
			throw new DatabaseInsertException();
		}

		Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
		int userId = DatabaseInserter.insertNewUser(name, age, address, password, connection);
		connection.close();
		return userId;
	}

	public static int insertUserRole(int userId, int roleId)
			throws DatabaseInsertException, SQLException {

		boolean val_success = true;
		val_success = val_success && UserRoleValidator.validateRoleId(roleId);
		val_success = val_success && UserRoleValidator.validateUserId(userId);

		if (!val_success) {
			throw new DatabaseInsertException();
		}

		Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
		int userRoleId = DatabaseInserter.insertUserRole(userId, roleId, connection);
		connection.close();
		return userRoleId;
	}

	public static int insertItem(String name, BigDecimal price)
			throws DatabaseInsertException, SQLException {

		boolean val_success = true;
		val_success = val_success && ItemsValidator.validateName(name);
		val_success = val_success && ItemsValidator.validatePrice(price);

		if (!val_success) {
			throw new DatabaseInsertException();
		}

		Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
		int itemId = DatabaseInserter.insertItem(name, price, connection);
		connection.close();
		return itemId;
	}

	public static int insertInventory(int itemId, int quantity)
			throws DatabaseInsertException, SQLException {

		boolean val_success = true;
		val_success = val_success && InventoryValidator.validateItemId(itemId);
		val_success = val_success && InventoryValidator.validateQuantity(quantity);

		if (!val_success) {
			throw new DatabaseInsertException();
		}

		Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
		int inventoryId = DatabaseInserter.insertInventory(itemId, quantity, connection);
		connection.close();
		return inventoryId;
	}

	public static int insertSale(int userId, BigDecimal totalPrice)
			throws DatabaseInsertException, SQLException {

		boolean val_success = true;
		val_success = val_success && SalesValidator.validateUserId(userId);
		val_success = val_success && SalesValidator.validateTotalPrice(totalPrice);

		if (!val_success) {
			throw new DatabaseInsertException();
		}

		Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
		int saleId = DatabaseInserter.insertSale(userId, totalPrice, connection);
		connection.close();
		return saleId;
	}

	public static int insertItemizedSale(int saleId, int itemId, int quantity)
			throws DatabaseInsertException, SQLException {

		// Note: sale been inserted into sales already

		boolean val_success = true;
		val_success = val_success && ItemizedSalesValidator.validateSaleId(saleId);
		val_success = val_success && ItemizedSalesValidator.validateItemId(itemId);
		val_success = val_success && ItemizedSalesValidator.validateQuantity(quantity);
		val_success = val_success && ItemizedSalesValidator.validateUniqueComb(saleId, itemId);
		val_success = val_success && ItemizedSalesValidator.validateTotalPrice(saleId, itemId, quantity);

		if (!val_success) {
			throw new DatabaseInsertException();
		}

		Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
		int itemizedId = DatabaseInserter.insertItemizedSale(saleId, itemId, quantity, connection);
		connection.close();
		return itemizedId;
	}
	
	public static int insertAccount(int userId)
			throws DatabaseInsertException, SQLException {

		boolean val_success = true;
		val_success = val_success && AccountValidator.validateUserId(userId);

		if (!val_success) {
			throw new DatabaseInsertException();
		}

		Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
		int accountId = DatabaseInserter.insertAccount(userId, connection);
		connection.close();
		return accountId;
	}
	
	public static int insertAccountSummary(int acctId, int itemId, int quantity)
			throws DatabaseInsertException, SQLException {

		boolean val_success = true;
		val_success = val_success && AccountSummaryValidator.validateAccountId(acctId);
		val_success = val_success && AccountSummaryValidator.validateItemId(itemId);
		val_success = val_success && AccountSummaryValidator.validateQuantity(quantity);

		if (!val_success) {
			throw new DatabaseInsertException();
		}

		Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
		int accountSummaryId = DatabaseInserter.insertAccountLine(acctId, itemId, quantity, connection);
		connection.close();
		return accountSummaryId;
	}

}
